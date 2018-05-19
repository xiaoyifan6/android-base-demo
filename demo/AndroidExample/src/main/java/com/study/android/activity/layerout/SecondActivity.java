package com.study.android.activity.layerout;

import android.app.Activity;
import android.content.Intent;
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
 */
public class SecondActivity extends Activity {
	EditText edit_txt;
	Button btn_submit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.edit_view);
		edit_txt=(EditText) findViewById(R.id.edit_view);
		btn_submit=(Button) findViewById(R.id.btn_submit);
		
		edit_txt.setText("未命名");
		
		btn_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(SecondActivity.this,TableViewActivity.class);
				intent.putExtra("data", edit_txt.getText()+"");
				SecondActivity.this.setResult(2,intent);
				finish();
			}
		});
	}
}
