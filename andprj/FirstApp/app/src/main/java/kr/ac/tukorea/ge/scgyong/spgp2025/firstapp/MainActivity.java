package kr.ac.tukorea.ge.scgyong.spgp2025.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button;
        button = findViewById(R.id.mainButton);
        button.setOnClickListener(this);
        button = findViewById(R.id.pushMeButton);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView tv = findViewById(R.id.mainTextView);
        if (view.getId() == R.id.mainButton) {
            tv.setText("Main Button Clicked");
        } else {
            tv.setText("PushMe Button Clicked");
        }
    }
}