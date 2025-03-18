package kr.ac.tukorea.ge.and.scgyong.cardsa01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageButton previousCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnCard(View view) {
        Log.d("MainActivity", "Button Clicked: ID=" + view.getId());
        //Toast.makeText(this, "BTN ID=" + view.getId(), Toast.LENGTH_SHORT).show();

        ImageButton btn = (ImageButton) view;
        btn.setImageResource(R.mipmap.card_as);

        previousCardButton.setImageResource(R.mipmap.card_blue_back);
        previousCardButton = btn;
    }
}