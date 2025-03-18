package kr.ac.tukorea.ge.and.scgyong.cardsa01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.and.scgyong.cardsa01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ImageButton previousCardButton;
    private @NonNull ActivityMainBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
    }

    public void onBtnCard(View view) {
        Log.d("MainActivity", "Button Clicked: ID=" + view.getId());
        //Toast.makeText(this, "BTN ID=" + view.getId(), Toast.LENGTH_SHORT).show();

        if (previousCardButton != null) {
            previousCardButton.setImageResource(R.mipmap.card_blue_back);
        }

        ImageButton btn = (ImageButton) view;
        btn.setImageResource(R.mipmap.card_as);

        previousCardButton = btn;
    }
}