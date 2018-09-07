package com.renygit.scrolltoplib;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by reny on 2018/8/9.
 */

public class NestedScrollViewEx extends NestedScrollView {

    private ScrollExListener scrollExListener;

    public void setScrollExListener(ScrollExListener scrollExListener) {
        this.scrollExListener = scrollExListener;
    }

    public NestedScrollViewEx(@NonNull Context context) {
        super(context);
        init(context);
    }

    public NestedScrollViewEx(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NestedScrollViewEx(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOnTouchListener(new OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -9983761;
            Handler handler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    View scroller = (View) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {
                            handleStop(scroller);
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(touchEventId, scroller), 5);
                            lastY = scroller.getScrollY();
                        }
                    }
                    return true;
                }
            }) ;


            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(handler.obtainMessage(touchEventId, v), 5);
                }
                return false;
            }

            private void handleStop(Object view) {
                NestedScrollView scroller = (NestedScrollView) view;
                //int scrollY = scroller.getScrollY();
                if(null != scrollExListener){
                    scrollExListener.onScrollStateChanged(scroller, 0);
                }
            }
        });


        setOnScrollChangeListener(new OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(null != scrollExListener){
                    //Log.e("scroll", "scrollY:"+scrollY + "   oldScrollY:"+oldScrollY);
                    scrollExListener.onScrollStateChanged(v, scrollY);
                    scrollExListener.onScrolled(v, scrollY);
                }
            }
        });
    }

    public interface ScrollExListener{
        void onScrollStateChanged(NestedScrollView scrollViewEx, int newState);
        void onScrolled(NestedScrollView recyclerView, int dy);
    }
}
