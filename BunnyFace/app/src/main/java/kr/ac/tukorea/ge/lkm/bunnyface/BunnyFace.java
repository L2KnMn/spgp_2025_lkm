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

import androidx.annotation.NonNull;

public class BunnyFace extends View implements View.OnTouchListener {
    private static final String TAG = "BunnyFace";

    private enum State {
        IDLE, SMILE, HAPPY, PETTED
    }

    private State state = State.IDLE;
    private Paint paintFace;
    private Paint paintEye;
    private Paint paintEar;
    private Paint paintHead;

    private float animationProgress = 0.0f;

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
        setOnTouchListener(this);

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
    protected void onDraw(@NonNull Canvas canvas) {
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

    float motion_speed = 0.05f;
    private void animatePetting() {
        animationProgress += motion_speed;
        if(animationProgress >= 1.0f) {
            motion_speed *= -1;
        }else if (animationProgress <= -1.0f){
            motion_speed *= -1;
        }
    }

    private void drawHead(Canvas canvas, float x, float y, float r){
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(r, r);
        // animation code 넣기
        canvas.save();
        canvas.translate(0.1f * animationProgress, 0 * animationProgress);
        drawEars(canvas);
        canvas.restore();
        // 원복
        canvas.drawArc(-1, 0, 1, 2, 180, 180, true, paintHead);
        canvas.drawArc(-0.9f, 0.2f, 0.9f, 1.8f, 180, 180, true, paintFace);
        // 마찬가지
        canvas.save();
        canvas.translate(0.1f * animationProgress, 0 * animationProgress);
        drawEyes(canvas);
        canvas.restore();
        // 원복
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

    private float previousTouchX = 0;
    private float previousTouchY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 터치 다운 이벤트 발생
                System.out.println("Touch down!");
                previousTouchX = event.getX();
                previousTouchY = event.getY();
                return true; // 이벤트 처리 미완료
            case MotionEvent.ACTION_UP:
                // 터치 업 이벤트 발생 (클릭으로 간주될 수 있음)
                System.out.println("Touch up!");
                chageStateAtTouch(); // 클릭 이벤트 발생 처리
                previousTouchX = 0;
                previousTouchY = 0;
                return true;
            case MotionEvent.ACTION_MOVE:
                // 터치 이동 이벤트 발생
                float dx = event.getX() - previousTouchX;
                float dy = event.getY() - previousTouchY;
                previousTouchX = event.getX();
                previousTouchY = event.getY();
                if(state == State.PETTED){
                    // 쓰다듬기 이벤트 발생 코드 나중에 쓰다듬은 거리에 따라 귀 눈 움직이게 구현
                    Log.d(TAG, "Petting");
                    if(state == State.PETTED)
                        animatePetting();
                    invalidate();
                }else if(dx != 0 || dy != 0) {
                    state = State.PETTED;
                }
                return true;
            case MotionEvent.ACTION_CANCEL:
                state = State.IDLE;
                animationProgress = 0.0f;
                break;
        }
        return false;
    }

    private void chageStateAtTouch() {
        Log.d(TAG, "onClick");
        boolean handled = false;
        switch (state) {
            case IDLE:
                state = State.SMILE;
                handled = true;
                break;
            case SMILE:
                state = State.HAPPY;
                handled = true;
                break;
            case HAPPY:
                state = State.IDLE;
                handled = true;
                break;
            case PETTED:
                state = State.IDLE;
                animationProgress = 0.0f;
                handled = true;
                break;
            default:
                Log.d(TAG, "Unknown state");
                break;
        }
        if (handled) {
            invalidate();
        }
    }
}