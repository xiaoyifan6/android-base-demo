package com.study.android.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.oyz.thisismyapp.R;

@SuppressLint("NewApi")
public class MyFragment2 extends Fragment implements OnClickListener{
	
	public interface MyListener{
		public void run(String code);
	}
	
	Button btn_submit;
	EditText edit_txt;
	View view;
	MyListener myListener;
	
	@Override
	public void onAttach(Activity activity) {
		myListener=(MyListener) activity;
		super.onAttach(activity);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/**
		 * resource：需要加载的布局文件
		 * root:加载layerout的父ViewGroup
		 * attactToRoot：false,不返回父ViewGroup
		 */
		view=inflater.inflate(R.layout.edit_view, container,false);
		edit_txt=(EditText) view.findViewById(R.id.edit_view);
		edit_txt.setText("第二个FragMent");		
		btn_submit=(Button) view.findViewById(R.id.btn_submit);
		btn_submit.setOnClickListener(this);
//		edit_txt.setEnabled(false);
		return view;
	}
	
	
	public EditText getEdit_txt() {
		return edit_txt;
	}
	
	public Button getBtn_submit() {
		return btn_submit;
	}
	
	public String getText(){
		return edit_txt.getText().toString();
	}


	@Override
	public void onClick(View v) {
//		Toast.makeText(this, edit_txt, Toast.LENGTH_SHORT).show();
//		Log.i("color", edit_txt.getText().toString());
		myListener.run(edit_txt.getText().toString());
	}
}
