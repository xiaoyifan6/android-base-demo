package com.study.android.activity.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oyz.thisismyapp.R;

/**
 * 自定义Toast
 * @author ousir
 *
 */
public class ToastActivity extends Activity implements OnClickListener{
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_btn_view);
		
		btn1=(Button)findViewById(R.id.button1);
		btn2=(Button)findViewById(R.id.button2);
		btn3=(Button)findViewById(R.id.button3);
		btn4=(Button)findViewById(R.id.button4);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		
		btn1.setText("普通Toast");
		btn2.setText("改变Toast位置");
		btn3.setText("设置Toast图标");
		btn4.setText("自定义Toast");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button1:
				{
					Toast toast=Toast.makeText(this, "普通Toast", Toast.LENGTH_SHORT);
					toast.show();
				}
				break;
			case R.id.button2:
				{
					Toast toast=Toast.makeText(this, "改变Toast位置", Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.CENTER, 20, -20);
					toast.show();
				}
				break;
			case R.id.button3:
				{
					Toast toast=Toast.makeText(this, "设置Toast图标", Toast.LENGTH_SHORT);
					LinearLayout layout= (LinearLayout) toast.getView();
					
					ImageView img=new ImageView(this);
					img.setImageResource(R.drawable.ic_menu_save);
					
					layout.addView(img, 0);
					toast.show();
				}
				break;
			case R.id.button4:
				{
					Toast toast=new Toast(this);
					View layout=getLayoutInflater().inflate(R.layout.mytoast, null);
					toast.setView(layout);
					toast.show();
				}
				break;

		default:
			break;
		}
	}
}
