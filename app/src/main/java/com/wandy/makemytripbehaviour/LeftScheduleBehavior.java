package com.wandy.makemytripbehaviour;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wandy on 10/3/17.
 */

public class LeftScheduleBehavior extends CoordinatorLayout.Behavior<ScheduleView> {

    /*
    * Default Constructor
    * */
    public LeftScheduleBehavior() {

    }

    /*
    * Default constructor for inflating view from layout
    *
    * */
    public LeftScheduleBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        // Extract any custom attributes out
        // preferably prefixed with behavior_ to denote they
        // belong to a behavior
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ScheduleView child, View dependency) {
        return dependency instanceof ScheduleView;
    }
}
