package pgreze.dagger2.home.hello;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import pgreze.dagger2.R;
import pgreze.dagger2.app.AppComponent;
import pgreze.dagger2.di.HasComponent;

public class HelloFragment extends Fragment {

    private HelloComponent component;

    @Inject HelloPresenter presenter;
    private Button helloBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerHelloComponent.builder()
                .appComponent(((HasComponent<AppComponent>) getActivity()).component())
                .helloModule(new HelloModule(this))
                .build();
        component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        helloBtn = (Button) view.findViewById(R.id.hello_btn);
        helloBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onHelloClick();
            }
        });
    }

    public Button getHelloBtn() {
        return helloBtn;
    }
}
