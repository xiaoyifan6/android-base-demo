package com.study.android.activity.layerout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.oyz.thisismyapp.R;
/**
 * 表格布局+Activity之间德通信
 * 关联:SecondActivity,ThirdActivity
 * @author ousir
 *
 */
public class TableViewActivity extends Activity implements OnClickListener{
	Button btn_open;
	Button btn_ynm;
	TextView txt_ynm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tableview);
		btn_open=(Button) findViewById(R.id.btn_open);
		btn_ynm=(Button) findViewById(R.id.btn_ynm);
		txt_ynm=(TextView) findViewById(R.id.txt_ynm);
		
		btn_open.setOnClickListener(this);
		btn_ynm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_open:
				{
					Intent intent=new Intent();
					intent.setAction("aaa.layerout.ThirdActivity");
					intent.addCategory("android.intent.category.DEFAULT");
					startActivity(intent);
				}
				break;
			case R.id.btn_ynm:
				{
					Intent intent=new Intent(this, SecondActivity.class);
					startActivityForResult(intent, 1);
				}
				break;
			default:
				break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1 && resultCode==2){
			String str=data.getStringExtra("data");
			txt_ynm.setText(str);
		}
	}
}
