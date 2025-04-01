package kr.ac.tukorea.ge.lkm.bunnyface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.MotionEvent;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.content.res.Resources;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class BunnyFace extends View implements View.OnClickListener, View.OnLongClickListener  {
    private static final String TAG = "BunnyFace";

    private enum State {
        IDLE, SMILE, HAPPY, PETTED
    }

    private State state = State.IDLE;
    private Paint paintFace;
    private Paint paintEye;
    private Paint paintEar;
    private Paint paintHead;

    public BunnyFace(Context context) {
        super(context);
        init(null, 0);
    }

    public BunnyFace(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BunnyFace(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        setOnLongClickListener(this);
        setOnClickListener(this);

        Resources res = getContext().getResources();

        paintFace = new Paint();
        paintFace.setColor(res.getColor(R.color.bunny_face, null));
        paintFace.setStyle(Paint.Style.FILL);

        paintEye = new Paint();
        paintEye.setColor(res.getColor(R.color.bunny_eye, null));
        paintEye.setStrokeWidth(0.04f);
        paintEye.setStyle(Paint.Style.STROKE);

        paintHead = new Paint();
        paintHead.setColor(res.getColor(R.color.bunny_head, null));
        paintHead.setStyle(Paint.Style.FILL);

        paintEar = new Paint();
        paintEar.setColor(res.getColor(R.color.bunny_ear, null));
        paintEar.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int l = getPaddingLeft(), r = getPaddingRight();
        int t = getPaddingTop(), b = getPaddingBottom();
        int w = getWidth(), h = getHeight();
        int contentWidth = (w - l - r);
        int contentHeight = (h - t - b);
        int contentSize = Math.min(contentWidth, contentHeight);

        int cx = l + contentWidth / 2;
        int cy = t + contentHeight / 2;
        int radius;
        radius = contentSize / 2;

        canvas.drawColor(0xffece6cc);
        drawHead(canvas, cx, cy, radius);
    }

    private void drawHead(Canvas canvas, float x, float y, float r){
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(r, r);
//        canvas.drawCircle(0, 0, 1, paint);
        drawEars(canvas);
        canvas.drawArc(-1, 0, 1, 2, 180, 180, true, paintHead);
        canvas.drawArc(-0.9f, 0.2f, 0.9f, 1.8f, 180, 180, true, paintFace);
        drawEyes(canvas);
        canvas.restore();
    }

    private void drawHeart(Canvas canvas, float x, float y, float r){
        Path path = new Path();
        path.moveTo(x, y - r * 0.4f);
        // 왼쪽 곡선 (Bezier 곡선 사용)
        // 첫번째 control point는 좌측 상단으로 크게 뻗어 하트 왼쪽 로브의 볼륨을 극대화합니다.
        path.cubicTo(x - r * 1.4f, y - r * 1.8f,   // 좌측 상단 control point
                x - r * 1.4f, y + r * 0.4f,    // 좌측 하단 control point
                x, y + r);                    // 하단의 끝점 (심장 밑)
        // 오른쪽 곡선 (Bezier 곡선 사용)
        // 좌측 곡선과 대칭되도록 control point들을 동일 계수로 설정합니다.
        path.cubicTo(x + r * 1.4f, y + r * 0.4f,    // 우측 하단 control point
                x + r * 1.4f, y - r * 1.8f,    // 우측 상단 control point
                x, y - r * 0.4f);              // 시작점(골 부분)으로 돌아와 깊은 노치 완성
        path.close();
        canvas.drawPath(path, paintEye);
    }

    private void drawEyes(Canvas canvas) {
        final float eyeX = 0.5f;
        final float eyeY = 0.75f;
        final float r = 0.2f;

        if(state == State.IDLE) {
            canvas.drawCircle(-eyeX, eyeY, r, paintEye);
            canvas.drawCircle(eyeX, eyeY, r, paintEye);
        }else if(state == State.SMILE){
            canvas.drawArc(-eyeX -r, eyeY-r, -eyeX +r, eyeY+r, 180, 180, false, paintEye);
            canvas.drawArc(eyeX -r, eyeY-r, eyeX +r, eyeY+r, 180, 180, false, paintEye);
        }else if(state == State.HAPPY){
            // 하트 그리기
            drawHeart(canvas, -eyeX, eyeY, r);
            drawHeart(canvas, eyeX, eyeY, r);
        }else if(state == State.PETTED){
            Path path = new Path();
            path.moveTo(-eyeX - r, eyeY);
            path.lineTo(-eyeX + r, eyeY);
            path.moveTo(eyeX - r, eyeY);
            path.lineTo(eyeX + r, eyeY);
            path.close();
            canvas.drawPath(path, paintEye);
        }
    }

    private void drawEars(Canvas canvas){
        final float width = 0.4f;
        final float height = 1.5f;
        canvas.drawOval(-0.5f-width/2, -1f, -0.5f+width/2, 0.5f, paintHead);
        canvas.drawOval(-0.5f-width/2*0.7f, -0.8f, -0.5f+width/2*0.7f, 0.5f, paintEar);
        canvas.drawOval(0.5f-width/2, -1f, 0.5f+width/2, 0.5f, paintHead);
        canvas.drawOval(0.5f-width/2*0.7f, -0.8f, 0.5f+width/2*0.7f, 0.5f, paintEar);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        boolean handled = false;
        switch (state) {
            case IDLE:
                state = State.SMILE;
                handled = true;
                break;
            case SMILE:
                state = State.IDLE;
                handled = true;
                break;
            case HAPPY:
                state = State.IDLE;
                handled = true;
                break;
            default:
                break;
        }
        if (handled) {
            invalidate();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Log.d(TAG, "onLongClick");
        state = State.HAPPY;
        invalidate();
        return true;
    }
}