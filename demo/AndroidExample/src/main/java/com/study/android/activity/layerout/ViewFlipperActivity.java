package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.oyz.thisismyapp.R;

public class ViewFlipperActivity extends Activity{
	
	ViewFlipper view_flipper;
	int ids[]={R.drawable.pic1, R.drawable.pic2,R.drawable.pic3,R.drawable.pic4};
	float startX=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewfliper);
		
		view_flipper=(ViewFlipper) findViewById(R.id.view_flipper);
		for (int id : ids) {
			view_flipper.addView(getImageView(id));
		}
		
//		/**
//		 * 添加动画效果
//		 */
		
		view_flipper.setInAnimation(this,R.anim.left_in);
		view_flipper.setOutAnimation(this,R.anim.left_out);
		//设置播放时间间隔
		view_flipper.setFlipInterval(3000);
		//开始播放
		view_flipper.startFlipping();
		
	}

	private View getImageView(int id) {
		ImageView imageView=new ImageView(this);
//		imageView.setImageResource(id);
		imageView.setBackgroundResource(id);
				
		return imageView;
	}

	int dir=0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX=event.getX();
				dir=0;
				break;
			case MotionEvent.ACTION_MOVE:
				//右滑
				if(event.getX()-startX>100){
					view_flipper.stopFlipping();
					dir=1;
				}
				//左滑
				if(startX-event.getX()>100){
					view_flipper.stopFlipping();
					dir=2;
				}
				break;
			case MotionEvent.ACTION_UP:
				if(dir==1){
					view_flipper.setInAnimation(this,R.anim.left_in);
					view_flipper.setOutAnimation(this,R.anim.left_out);
					//显示前一页
					view_flipper.showPrevious();
					view_flipper.startFlipping();
				}else if(dir==2){
					view_flipper.setInAnimation(this,R.anim.right_in);
					view_flipper.setOutAnimation(this,R.anim.right_out);
					//显示前一页
					view_flipper.showNext();
					view_flipper.startFlipping();
				}
				break;
			default:
				break;
		}
	
		return super.onTouchEvent(event);
	}
	
}
