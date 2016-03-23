package fr.pgreze.testme.ui.gist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.pgreze.testme.R;
import fr.pgreze.testme.data.github.GithubService;
import fr.pgreze.testme.domain.common.di.HasComponent;
import fr.pgreze.testme.ui.common.di.ActivityComponent;
import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;

public class PublicGistsFragment extends Fragment {
    public static final String TAG = PublicGistsFragment.class.getName();

    @Bind(R.id.gists_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(android.R.id.list)
    RecyclerView recyclerView;
    @Bind(android.R.id.message)
    ViewGroup errorContainer;
    @Bind(R.id.progress)
    View progressView;

    @Inject GithubService githubService;

    private PublishSubject<Void> retryListener;
    private PublishSubject<Void> refreshListener;
    private Subscription subscription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((HasComponent<ActivityComponent>) getActivity()).component().inject(this);
        retryListener = PublishSubject.create();
        refreshListener = PublishSubject.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gist_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        refreshLayout.setOnRefreshListener(() -> refreshListener.onNext(null));
    }

    @Override
    public void onStart() {
        super.onStart();
        subscription = Observable.defer(() -> {
            Log.d(TAG, "Show progress view and requests gists");
            show(progressView);
            return githubService.publicGists();
        }).observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> refreshLayout.setRefreshing(false))
                .retryWhen(attempts -> attempts.flatMap(e -> {
                    // Display error screen and retry with retry click
                    Log.e(TAG, "Error while fetching public gists: " + e + ". " + errorMessage(e), e);
                    show(errorContainer);
                    return retryListener;
                }))
                .repeatWhen(completions -> completions.flatMap(v -> retryListener))
                .subscribe(githubGists -> {
                    Log.d(TAG, "Received " + githubGists.size() + " public gists");
                    show(refreshLayout);
                    // TODO: adapter.setGists
                });
    }

    @Override
    public void onStop() {
        if (subscription != null) subscription.unsubscribe();
        super.onStop();
    }

    @OnClick(R.id.gists_retry_btn)
    void onRetryClick() {
        retryListener.onNext(null);
    }

    void show(View view) {
        for (View v: Arrays.asList(refreshLayout, errorContainer, progressView)) {
            v.setVisibility(v.equals(view) ? View.VISIBLE : View.GONE);
        }
    }

    private String errorMessage(Throwable e) {
        if (!(e instanceof RetrofitError)) return "";
        try {
            TypedByteArray array = (TypedByteArray) ((RetrofitError) e).getResponse().getBody();
            return new String(array.getBytes());
        } catch (Exception ex) {
            Log.e(TAG, ex.toString(), ex);
            return "";
        }
    }
}
