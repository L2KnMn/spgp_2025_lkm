package kr.ac.tukorea.ge.scgyong.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import kr.ac.tukorea.ge.scgyong.dragonflight.BuildConfig;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class HorzScrollBackground extends Sprite {
    private final float speed;
    private float width;
    private float height;
    private Paint bboxPaint;

    public HorzScrollBackground(int bitmapResId, float speed) {
        super(bitmapResId);
        this.width = bitmap.getWidth() * Metrics.height / bitmap.getHeight();
        this.height = Metrics.height;
        setPosition(Metrics.width / 2, Metrics.height / 2, width, Metrics.height);
        this.speed = speed;
    }

    public HorzScrollBackground setModeFullVert(boolean fullVert){
        if(fullVert){
            this.width = bitmap.getWidth() * Metrics.height / bitmap.getHeight();
            this.height = Metrics.height;
        }else{
            this.width = bitmap.getWidth();
            this.height = bitmap.getHeight();
        }
        return this;
    }

    @Override
    public void update() {
        this.x -= speed * GameView.frameTime; // y 값을 스크롤된 양으로 사용한다
    }

    @Override
    public void draw(Canvas canvas) {
        //super.draw(canvas);
        float curr = x % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.width) {
            dstRect.set(curr, 0, curr + width, this.height);
            if(this.height == bitmap.getHeight()){

            }
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }

        if (GameView.drawsDebugStuffs) {
            if (bboxPaint == null) {
                bboxPaint = new Paint();
                bboxPaint.setStyle(Paint.Style.STROKE);
                bboxPaint.setColor(Color.RED);
            }
            curr = x % width;
            if (curr > 0) curr -= width;
            while (curr < Metrics.width) {
                dstRect.set(curr, 0, curr + width, this.height);
                canvas.drawRect(dstRect, bboxPaint);
                curr += width;
            }

        }
    }
}
