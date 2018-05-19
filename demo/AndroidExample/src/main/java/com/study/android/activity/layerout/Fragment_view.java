package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;

import com.oyz.thisismyapp.R;
/**
 * 子页面
 * com.study.android.activity.layerout.FragmentActivity
 * 第一个radioButton指向的页面
 * 
 * @author ousir
 *
 */
public class Fragment_view extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_view);
	}
}
