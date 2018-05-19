package com.study.android.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oyz.thisismyapp.R;


@SuppressLint("NewApi")
public class MyFragment3 extends Fragment {
	TextView textView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.simple_text_view, container,false);
		textView=(TextView) view.findViewById(R.id.text_view);
		String text=getArguments().getString("name")+"";
		textView.setText("接收到消息:"+text);	
		return view;
	}
	
}
