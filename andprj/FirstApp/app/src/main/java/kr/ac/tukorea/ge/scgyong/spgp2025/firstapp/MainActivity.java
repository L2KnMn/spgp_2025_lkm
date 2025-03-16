package kr.ac.tukorea.ge.scgyong.spgp2025.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button;
        button = findViewById(R.id.mainButton);
        button.setOnClickListener(m_mainButtonListener);
        button = findViewById(R.id.pushMeButton);
        button.setOnClickListener(m_pushMeButtonListener);
    }
    private View.OnClickListener m_mainButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = findViewById(R.id.mainTextView);
            tv.setText("Main Button Clicked");
        }
    };
    private View.OnClickListener m_pushMeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = findViewById(R.id.mainTextView);
            tv.setText("PushMe Button Clicked");
        }
    };
}