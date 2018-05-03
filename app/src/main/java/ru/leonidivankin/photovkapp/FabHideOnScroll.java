package ru.leonidivankin.photovkapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FabHideOnScroll extends FloatingActionButton.Behavior {

	private static final String TAG = FabHideOnScroll.class.getSimpleName();

	public FabHideOnScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
	                           @NonNull FloatingActionButton child,
	                           @NonNull View target,
	                           int dxConsumed,
	                           int dyConsumed,
	                           int dxUnconsumed,
	                           int dyUnconsumed,
	                           int type)
	{
		super.onNestedScroll(coordinatorLayout,
				child,
				target,
				dxConsumed,
				dyConsumed,
				dxUnconsumed,
				dyUnconsumed,
				type);
		if (child.getVisibility() == View.VISIBLE && dyConsumed > 0) {
			child.setVisibility(View.INVISIBLE);
		} else if (child.getVisibility() == View.INVISIBLE && dyConsumed < 0) {
			child.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onStartNestedScroll(
			@NonNull CoordinatorLayout coordinatorLayout,
			@NonNull FloatingActionButton child,
			@NonNull View directTargetChild,
			@NonNull View target,
			int axes,
			int type) {
		printNowType(axes);
		return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
	}

	private void printNowType(int type) {
		String s;
		switch (type) {
			case ViewCompat.SCROLL_AXIS_NONE:
				s = "NONE";
				break;
			case ViewCompat.SCROLL_AXIS_HORIZONTAL:
				s = "HORIZONTAL";
				break;
			case ViewCompat.SCROLL_AXIS_VERTICAL:
				s = "VERTICAL";
				break;
			default:
				s = "";
				break;
		}
		Log.d(TAG, "printNowType: " + s);
	}
}
