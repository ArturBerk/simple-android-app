package ru.ab.eapp.ui.controls;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import ru.ab.eapp.R;

/**
 * Created by arturh on 03.04.2017.
 */

public class PieView extends View {

    private float start = 0;
    private float currentStart = 0.0f;
    private float end = 1.0f;
    private float currentEnd = 0.0f;
    private int color = 0;

    private ObjectAnimator startAnimator;
    private ObjectAnimator endAnimator;

    public float getStart() {
        return start;
    }

    public void setStart(float startAngle) {
        this.start = Math.max(Math.min(startAngle, 1.0f), 0);
        if (Math.abs(this.start - currentStart) > 0.01) {
            if (startAnimator != null) {
                startAnimator.cancel();
            }
            startAnimator = ObjectAnimator.ofFloat(this, "currentStart", currentStart, this.start);
            startAnimator.setDuration(200);
            startAnimator.start();
        }
        this.invalidate();
    }

    public float getEnd() {
        return end;
    }

    void setCurrentStart(float value) {
        this.currentStart = value;
        this.invalidate();
    }

    void setCurrentEnd(float value) {
        this.currentEnd = value;
        this.invalidate();
    }

    public void setEnd(float endAngle) {
        this.end = Math.max(Math.min(endAngle, 1.0f), 0);
        if (Math.abs(this.end - currentEnd) > 0.01) {
            if (endAnimator != null) {
                endAnimator.cancel();
            }
            endAnimator = ObjectAnimator.ofFloat(this, "currentEnd", currentEnd, this.end);
            endAnimator.setInterpolator(new DecelerateInterpolator());
            endAnimator.setDuration(500);
            endAnimator.start();
        }
        this.invalidate();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        this.invalidate();
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.PieView,
                0, 0);

        start = a.getFloat(R.styleable.PieView_start, 0);
        end = a.getFloat(R.styleable.PieView_end, 1.0f);
        color = a.getColor(R.styleable.PieView_color, 0);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawArc(new RectF(0, 0, width, height ), currentStart * 360, currentEnd * 360, false, paint);
    }
}
