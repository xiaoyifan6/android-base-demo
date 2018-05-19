package com.study.android.activity.layerout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.oyz.thisismyapp.R;
/**
 * 帧布局
 * @author ousir
 *
 */
@SuppressLint("HandlerLeak")
public class FrameActivity extends Activity {
	
	int[] ids;
	Drawable[] colors;
	MyHandler handler;
	int cur;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_frame);
		
		ids=new int[]{R.id.textView1, R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6};
		colors=new Drawable[ids.length];
		for (int i = 0; i < ids.length; i++) {
			colors[i]=((TextView)findViewById(ids[i])).getBackground();
		}
		handler = new MyHandler();
		handler.sleep((long) 100);
		cur=0;
	}
	
	private void show(){
		
		
//		for (int i = 0; i < ids.length; i++) {
//			((TextView)findViewById(ids[i])).
//				setBackground(colors[(i+cur)%ids.length]);
//		}		
//		cur=(cur+1)%ids.length;
		
		Drawable drawable=((TextView)findViewById(ids[0])).getBackground();
		for (int i = 0; i < ids.length-1; i++){
			((TextView)findViewById(ids[i])).setBackground(
					((TextView)findViewById(ids[i+1])).getBackground()
					);
		}
		((TextView)findViewById(ids[ids.length-1])).setBackground(drawable);
	}
	
	class MyHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			//代码
			show();
			sleep((long) 300);
		}
		
		public void sleep(Long delayMillis) {
			this.sendMessageDelayed(this.obtainMessage(0), delayMillis);
		}		
	}
}
