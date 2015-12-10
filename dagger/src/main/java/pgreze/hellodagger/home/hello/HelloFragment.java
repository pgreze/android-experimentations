package pgreze.hellodagger.home.hello;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import pgreze.hellodagger.R;
import pgreze.hellodagger.home.BaseFragment;

public class HelloFragment extends BaseFragment {

    @Inject HelloPresenter presenter;
    private Button helloBtn;

    @Override
    public List<? extends Object> getModules() {
        return Arrays.asList(new HelloModule(this));
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
