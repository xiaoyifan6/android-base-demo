package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;

import com.oyz.thisismyapp.R;
/**
 * 滚动文字的TextView
 * 关联:MarqueeTextView
 * @author ousir
 *
 */
public class MarqueeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marquee);
	}
}
