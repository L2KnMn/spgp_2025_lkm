package kr.ac.tukorea.ge.scgyong.spgp2025.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    private TextView mainTextView;
    private Button mainButton;
    private Button pushMeButton;
    private TextView subTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);

        // 추가 선언 과정 설명.
        subTextView = findViewById(R.id.subTextView);

        mainButton = findViewById(R.id.mainButton);
        mainButton.setOnClickListener(m_mainButtonListener);

        pushMeButton = findViewById(R.id.pushMeButton);
        pushMeButton.setOnClickListener(m_pushMeButtonListener);
    }

    private View.OnClickListener m_mainButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainTextView.setText("Main Button Clicked");
            subTextView.setText("Main is 4 characters long");
        }
    };
    private View.OnClickListener m_pushMeButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mainTextView.setText("PushMe Button Clicked");
            subTextView.setText("PushMe is 6 characters long");
        }
    };
}