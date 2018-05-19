package com.study.android.fragment;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.oyz.thisismyapp.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MyFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		/**
		 * resource：需要加载的布局文件
		 * root:加载layerout的父ViewGroup
		 * attactToRoot：false,不返回父ViewGroup
		 */
		View view=inflater.inflate(R.layout.activity_tableview, container,false);
		return view;
	}
	/**
	 * 当Fragment被添加到Activity时候会回调这个方法，并且只调用一次
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.i("Main", "Fragment1---onAttach()");

	}

	/**
	 * 创建Fragment时会回调，只会调用一次
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Main", "Fragment1---onCreate()");

	}

	/**
	 * 当Fragment所在的Activty启动完成后调用
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.i("Main", "Fragment1---onActivityCreated()");

	}

	/**
	 * 启动Fragment
	 * 
	 */
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("Main", "Fragment1---onStart()");

	}

	/**
	 * 恢复Fragment时会被回调，调用onStart（）方法后面一定会调用onResume()方法
	 */
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("Main", "Fragment1---onResume()");

	}

	/**
	 * 暂停Fragment
	 */
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("Main", "Fragment1---onPause()");

	}

	/**
	 * 停止Fragment
	 */
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("Main", "Fragment1---onStop()");

	}

	/**
	 * 销毁Fragment所包含的View组件时
	 */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.i("Main", "Fragment1---onDestroyView()");

	}

	/**
	 * 销毁Fragment时会被回调
	 */
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("Main", "Fragment1---onDestroy()");

	}

	/**
	 * Fragment从Activity中删除时会回调该方法，并且这个方法只会调用一次
	 */
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.i("Main", "Fragment1---onDetach()");
	}
}
