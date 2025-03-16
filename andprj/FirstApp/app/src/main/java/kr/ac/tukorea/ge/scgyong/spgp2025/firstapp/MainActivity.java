package kr.ac.tukorea.ge.scgyong.spgp2025.firstapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.mainTextView);
        tv.setText("Program Started");

        Button btn = findViewById(R.id.mainButton);
    }
}