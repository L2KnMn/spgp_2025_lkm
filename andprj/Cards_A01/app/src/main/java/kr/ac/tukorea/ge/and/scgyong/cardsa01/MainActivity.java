package kr.ac.tukorea.ge.and.scgyong.cardsa01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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

    private static final int[] CARD_RES_IDS = {
            R.mipmap.card_as, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_4h,
            R.mipmap.card_5s, R.mipmap.card_jc, R.mipmap.card_qh, R.mipmap.card_kd,
            R.mipmap.card_as, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_4h,
            R.mipmap.card_5s, R.mipmap.card_jc, R.mipmap.card_qh, R.mipmap.card_kd,
    };

    private Map<Integer, Boolean> cardFlipMap;

    private Integer[] suffledCardResIds;

    private ImageButton[] cardButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        cardButtons = new ImageButton[]{
                ui.card00, ui.card01, ui.card02, ui.card03,
                ui.card10, ui.card11, ui.card12, ui.card13,
                ui.card20, ui.card21, ui.card22, ui.card23,
                ui.card30, ui.card31, ui.card32, ui.card33,
        };
        suffledCardResIds = new Integer[CARD_RES_IDS.length];
        for (int i = 0; i < CARD_RES_IDS.length; i++) {
            suffledCardResIds[i] = CARD_RES_IDS[i];
        }
        shuffleCards();

        this.cardFlipMap = new HashMap<>();

        for (int i = 0; i < suffledCardResIds.length; i++) {
            ImageButton btn = cardButtons[i];
            int resId = suffledCardResIds[i];
            Integer resourceIdInteger = resId;
            btn.setTag(resourceIdInteger);
        }
    }

    private void shuffleCards() {
        // Convert array to list
        List<Integer> l = Arrays.asList(suffledCardResIds);
        // Shuffle the list
        Collections.shuffle(l);
        suffledCardResIds = l.toArray(new Integer[0]);
    }

    public void onBtnCard(View view) {
        //Log.d("MainActivity", "Button Clicked: ID=" + view.getId());
        //Toast.makeText(this, "BTN ID=" + view.getId(), Toast.LENGTH_SHORT).show();
        if(view.getTag().equals(R.mipmap.card_blue_back)){
            Log.d("MainActivity", "Click Fliped Button ID=" + view.getId());
            return;
        }
        ImageButton btn = (ImageButton) view;
        int resId = (Integer) btn.getTag();
        btn.setImageResource(resId);

        if (previousCardButton != null && previousCardButton != btn) {
            if (previousCardButton.getTag().equals(view.getTag())) {
                //Log.d("MainActivity", "Correct!");
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
                btn.setTag(R.mipmap.card_blue_back);
                previousCardButton.setTag(R.mipmap.card_blue_back);
                previousCardButton = null;
                return;
            } else {
                previousCardButton.setImageResource(R.mipmap.card_blue_back);
            }
        }
        previousCardButton = btn;
    }
}