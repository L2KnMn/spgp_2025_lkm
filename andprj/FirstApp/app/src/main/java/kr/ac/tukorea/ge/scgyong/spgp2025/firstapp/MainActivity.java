package kr.ac.tukorea.ge.scgyong.spgp2025.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.mainButton);
        btn.setOnClickListener(m_mainButtonListener);
    }

    private View.OnClickListener m_mainButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            TextView tv = findViewById(R.id.mainTextView);
            tv.setText("Main Button Clicked");
        }
    };
}