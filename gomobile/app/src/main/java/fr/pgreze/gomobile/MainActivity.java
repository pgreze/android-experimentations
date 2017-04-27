package fr.pgreze.gomobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import golib.Golib;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String greetings = Golib.greetings("Patrick");
        ((TextView) findViewById(R.id.hello)).setText(greetings);
    }
}
