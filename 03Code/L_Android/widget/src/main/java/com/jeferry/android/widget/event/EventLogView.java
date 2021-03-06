package com.jeferry.android.widget.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.socks.library.KLog;

public class EventLogView extends View {

    public final String TAG = this.getClass().getSimpleName();

    public EventLogView(Context context) {
        super(context);
    }

    public EventLogView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventLogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        int width = getWidth();
        int height = getHeight();
        KLog.d(TAG, "width: " + width + " ;height: " + height);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        KLog.d(TAG, "onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        KLog.d(TAG, "onDetachedFromWindow");
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = super.dispatchTouchEvent(event);
        KLog.i(TAG, "dispatchTouchEvent : " + result + " ; MotionEvent : " + getDesc(event.getAction()));
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        KLog.i(TAG, "onTouchEvent : " + result + " ; MotionEvent : " + getDesc(event.getAction()));
        return result;
    }

    private String getDesc(int action) {
        String desc = "null";
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                desc = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                desc = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                desc = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                desc = "ACTION_CANCEL";
                break;
            default:
                desc = "default";
                break;
        }
        return desc;
    }
}
