package com.study.android.activity.data;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;

import com.oyz.thisismyapp.R;

public class SharedPreferencesActivity extends Activity {
	TextView txt_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(this);
		
		setContentView(R.layout.simple_text_view);
		txt_view=(TextView) findViewById(R.id.text_view);
		
		SharedPreferences pref=getSharedPreferences("myPref", MODE_PRIVATE);
		if(pref.getLong("id", 0l)==0){
			Editor editor=pref.edit();
			editor.putString("name", "李四");
			
			editor.putInt("age", 12)
			.putBoolean("gendle", true)
			.putLong("id", 0xfff)
			.putFloat("height",1.5f);
			editor.commit();
			txt_view.setText("create a new data");
		}else{
			
			StringBuilder sb=new StringBuilder();
			sb.append("name:");sb.append(pref.getString("name", null));
			sb.append("-age:");sb.append(pref.getInt("age", 0));
			sb.append("-gendle:");sb.append(pref.getBoolean("gendle", true)?"男":"女");
			sb.append("-height:");sb.append(pref.getFloat("height", 0.0f));
			
			txt_view.setText(sb.toString());
		}
		
		//...
	}
}
