package com.wandy.makemytripbehaviour;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Bind(R.id.scheduleView)
    ScheduleView scheduleView1;

    @Bind(R.id.scheduleView2)
    ScheduleView scheduleView2;

    @Bind(R.id.main_content)
    CoordinatorLayout coordinatorLayout;

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private final String TAG = MainActivity.class.getSimpleName();

    private MyGestureDetector myGestureDetector = new MyGestureDetector();
    private float x1, x2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        scheduleView2.setOnTouchListener(this);

    }

    private void changeSize(float size) {

        ViewGroup.LayoutParams params = scheduleView2.getLayoutParams();
        params.width = scheduleView1.getMeasuredWidth() + (int) size;

        scheduleView2.setLayoutParams(params);
    }

    private void makeBigger(float size) {

        ViewGroup.LayoutParams params = scheduleView2.getLayoutParams();
        params.width = scheduleView1.getWidth() + (int) Math.abs(size);

        scheduleView2.setLayoutParams(params);
    }

    private void makeSmaller(float size) {

        ViewGroup.LayoutParams params = scheduleView2.getLayoutParams();
        params.width = scheduleView1.getWidth() - (int) Math.abs(size);

        scheduleView2.setLayoutParams(params);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                //Toast.makeText(this, "mboh 2", Toast.LENGTH_SHORT).show ();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                // Left to Right swipe action
                if (x2 > x1) {
                    makeSmaller(deltaX);
                    Log.d(TAG, x2 + " " + x1 + " " + deltaX);
                    Toast.makeText(this, "Left to Right swipe [Next]", Toast.LENGTH_SHORT).show();
                }

                // Right to left swipe action
                else {
                    makeBigger(deltaX);
                    Log.d(TAG, x2 + " " + x1 + " " + deltaX);
                    Toast.makeText(this, "Right to Left swipe [Previous]", Toast.LENGTH_SHORT).show();
                }

                break;
        }
        return true;
    }


    private class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    Log.d(TAG, "false");
                    return false;
                }
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    //onLeftSwipe();
                    Log.d(TAG, "onLeftSwipe");
                }
                // left to right swipe
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    //onRightSwipe();
                    Log.d(TAG, "onrightswipe");
                }
                Log.d(TAG, "mboh");
            } catch (Exception e) {

            }
            return false;
        }
    }
}
