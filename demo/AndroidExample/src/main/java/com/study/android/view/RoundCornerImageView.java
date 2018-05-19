package com.study.android.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.oyz.thisismyapp.R;

/**
 * 圆角图标
 * 自定义属性:radiu
 * @author ousir
 *
 */
public class RoundCornerImageView extends AppCompatImageView {
	float radiu=0f;
	public RoundCornerImageView(Context context) {
		super(context);
	}
	private Paint paint;
	public RoundCornerImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs,0);
	}
	
	public RoundCornerImageView(Context context, AttributeSet attrs,
	int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}
	
	private void init(Context context, AttributeSet attrs, int defStyle){
//		TypedArray a = context.obtainStyledAttributes(attrs,null, defStyle, 0);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView, defStyle, 0);
		if(a==null)return;
		int count = a.getIndexCount();  
		int index = 0;
		 for (int i = 0; i < count; i++) {  
			 index = a.getIndex(i);
			 if(index== R.styleable.RoundCornerImageView_radiu){
				 radiu=a.getFloat(index, 0);
				 if(radiu<0)radiu=0;
				 if(radiu>50)radiu=50;
			 }
		 }
		 a.recycle();
		paint = new Paint();
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		Path clipPath = new Path();
		int w = this.getWidth();
		int h = this.getHeight();
		clipPath.addRoundRect(new RectF(0, 0, w, h), radiu, radiu, Path.Direction.CW);
	    canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
	    canvas.clipPath(clipPath);
		super.onDraw(canvas);
	}


}
