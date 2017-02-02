package com.razorhost.ndcar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class VerticalSeekBar extends SeekBar {

    TextView tv;

    public VerticalSeekBar(Context context) {
        super(context);

        tv = (TextView) findViewById(R.id.text);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(), 0);


        super.onDraw(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                //start thread asap
                //new OldMainActivity.SendGoForwardTask().execute();
                setProgress(getMax() - (int) (getMax() * event.getY() / getHeight()));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                Log.v("NDC",getMax() - (int) (getMax() * event.getY() / getHeight())+"");
                tv.setText(getMax() - (int) (getMax() * event.getY() / getHeight())+"");
                break;
            case MotionEvent.ACTION_UP:
                setProgress(0);
                //stop thread
                break;

            case MotionEvent.ACTION_CANCEL:
                setProgress(0);
                break;
        }
        return true;
    }
}