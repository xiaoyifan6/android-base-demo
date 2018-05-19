package com.study.android.activity.layerout;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.oyz.thisismyapp.R;
import com.study.android.fragment.MyFragment;
import com.study.android.fragment.MyFragment2;
import com.study.android.fragment.MyFragment2.MyListener;
import com.study.android.fragment.MyFragment3;

/**
 * Fragment底部导航
 * 关联:Fragment_view,MyFragment,MyFragment,MyFragment2,MyFragment3
 * 
 * @author ousir
 *
 */
@SuppressLint("NewApi")
public class FragmentActivity extends Activity implements MyListener{
	
	RadioGroup radioGroup;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		radioGroup=(RadioGroup) findViewById(R.id.btn_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@SuppressLint("NewApi")
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
					case R.id.radioButton1:
						{
							Intent intent=new Intent(FragmentActivity.this,Fragment_view.class);
							startActivity(intent);
						}
						break;
					case R.id.radioButton2:
						{
							MyFragment fragment=new MyFragment();
							FragmentManager fragmentManager=getFragmentManager();
							FragmentTransaction beginTransaction=fragmentManager.beginTransaction();
//							beginTransaction.add(R.id.linearLayout_f,fragment);
							beginTransaction.replace(R.id.linearLayout_f,fragment);
//							beginTransaction.addToBackStack(null);
							beginTransaction.commit();
						}
						break;
					case R.id.radioButton3:
						{
							MyFragment2 frag2=new MyFragment2();
							FragmentManager fragmentManager=getFragmentManager();
							FragmentTransaction beginTransaction=fragmentManager.beginTransaction();
							beginTransaction.replace(R.id.linearLayout_f, frag2);
							beginTransaction.commit();
						}
						break;
	
					default:
						break;
				}
			}
		});
	}

	@Override
	public void run(String code) {
		MyFragment3 frag3=new MyFragment3();
		Bundle bundle=new Bundle();
		bundle.putString("name", code);
		frag3.setArguments(bundle);
		
		FragmentManager fragmentManager=getFragmentManager();
		FragmentTransaction beginTransaction=fragmentManager.beginTransaction();
		beginTransaction.replace(R.id.linearLayout_f, frag3);
		beginTransaction.commit();
	}

}
