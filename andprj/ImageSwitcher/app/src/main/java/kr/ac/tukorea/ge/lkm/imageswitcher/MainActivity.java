package kr.ac.tukorea.ge.lkm.imageswitcher;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import kr.ac.tukorea.ge.lkm.imageswitcher.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final int MAX_PAGE = 5;
    private final int MIN_PAGE = 1;
    private int currentPage = 1;

    private ActivityMainBinding binding;
    private int[] imageList = { R.mipmap.cat1, R.mipmap.cat2, R.mipmap.cat3, R.mipmap.cat4, R.mipmap.cat5 };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void setCatImage(int imageId){
        if(imageId <= MAX_PAGE && imageId >= MIN_PAGE) {
            binding.catImageScreen.setImageResource(imageList[imageId - 1]);
            binding.textView.setText(imageId + "/5");
        }
    }

    public void pageRight(View view) {
        currentPage = currentPage < MAX_PAGE ? currentPage + 1 : MAX_PAGE;
        setCatImage(currentPage);
    }

    public void pageLeft(View view) {
        currentPage = currentPage > MIN_PAGE ? currentPage - 1 : MIN_PAGE;
        setCatImage(currentPage);
    }
}