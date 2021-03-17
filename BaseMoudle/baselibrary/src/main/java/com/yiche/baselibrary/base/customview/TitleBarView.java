package com.yiche.baselibrary.base.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiche.baselibrary.R;


/**
 * 顶部标题栏
 */
public class TitleBarView extends LinearLayout {
    ImageView leftIcon;
    TextView titleText;
    TextView title_finishTv;
    TextView title_rightTv;
    ImageView right_icon;
    TextView titleRightTv;

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_titlebar, this);
        if (isInEditMode()) {
            return;
        }
        leftIcon = findViewById(R.id.left_icon);
        right_icon = findViewById(R.id.right_icon);
        titleText = findViewById(R.id.titleText);
        title_finishTv = findViewById(R.id.title_finishTv);
        title_rightTv = findViewById(R.id.title_rightTv);
        titleRightTv = findViewById(R.id.titleRightTv);
    }

    public void initTitleBar(int leftIconId, int rightIconId, String text, OnClickListener l) {
        titleText.setText(text);
        if (leftIconId == -1) {
            leftIcon.setVisibility(View.INVISIBLE);
        } else {
            leftIcon.setImageResource(leftIconId);
            title_finishTv.setOnClickListener(l);
        }
        if (rightIconId == -1) {
            right_icon.setVisibility(View.INVISIBLE);
        } else {
            right_icon.setVisibility(VISIBLE);
            right_icon.setImageResource(rightIconId);
            title_rightTv.setOnClickListener(l);
        }
    }

    public void initTitleBar(int leftIconId, String text, String rightText, OnClickListener l) {
        titleText.setText(text);
        if (leftIconId == -1) {
            leftIcon.setVisibility(View.INVISIBLE);
        } else {
            leftIcon.setImageResource(leftIconId);
            title_finishTv.setOnClickListener(l);
        }
        titleRightTv.setText(rightText);
        title_rightTv.setOnClickListener(l);
    }

    public TitleBarView(Context context) {
        super(context);
    }
}