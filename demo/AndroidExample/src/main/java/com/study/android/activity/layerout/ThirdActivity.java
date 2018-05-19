package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.oyz.thisismyapp.R;
/**
 * 子页面
 * 关联:TableViewActivity
 * @author ousir
 *
 */
public class ThirdActivity extends Activity{
	EditText edit_txt;
	Button btn_submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_view);	
		
		
		setContentView(R.layout.edit_view);
		edit_txt=(EditText) findViewById(R.id.edit_view);
		
		edit_txt.setBackgroundResource(R.drawable.edit_white);
		
		btn_submit=(Button) findViewById(R.id.btn_submit);
		btn_submit.setText("关闭");
		
		edit_txt.setText("未命名");
		edit_txt.setEnabled(false);
		
		btn_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
