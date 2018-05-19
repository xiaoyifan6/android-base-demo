package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.oyz.thisismyapp.R;

public class ScrollViewActivity extends Activity implements OnTouchListener,OnClickListener{
	TextView txt_view;
	ScrollView scroll_view;
	Button btn_up;
	Button btn_down;
	boolean k=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scrollview);
		txt_view=(TextView) findViewById(R.id.txt_view);
		scroll_view=(ScrollView) findViewById(R.id.scroll_view);
		btn_up=(Button) findViewById(R.id.btn_up);
		btn_down=(Button) findViewById(R.id.btn_down);
		
		txt_view.setText(getResources().getString(R.string.content));
		
		scroll_view.setOnTouchListener(this);
		btn_down.setOnClickListener(this);
		btn_up.setOnClickListener(this);
		
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				break;
			case MotionEvent.ACTION_MOVE:
				
				//scroll_view.getHeight():整个内容高度
				//scroll_view.getChildAt(0).getMeasuredHeight():显示区域高度
				if(scroll_view.getScrollY()<=0){
					//Log.i("scroll", "顶部");
					if(!k){
						Toast.makeText(this, "到顶了", Toast.LENGTH_SHORT).show();
						k=true;
					}
					
				}else if(scroll_view.getScrollY()+ scroll_view.getHeight() >= scroll_view.getChildAt(0).getMeasuredHeight()){
					//Log.i("scroll", "底部");
					if(!k){
						Toast.makeText(this, "到底了", Toast.LENGTH_SHORT).show();
						k=true;
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				k=false;
				break;
			default:
				break;
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_up:
			scroll_view.scrollBy(0, -30);
			break;
		case R.id.btn_down:
			scroll_view.scrollBy(0, 30);			
			break;

		default:
			break;
		}
	}
}
