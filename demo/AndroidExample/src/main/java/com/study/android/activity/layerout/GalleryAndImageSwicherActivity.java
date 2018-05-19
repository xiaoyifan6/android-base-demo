package com.study.android.activity.layerout;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

import com.oyz.thisismyapp.R;
import com.study.android.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class GalleryAndImageSwicherActivity extends Activity implements OnClickListener{
	
	public final static int dir_Left=1;
	public final static int dir_Right=1;
	
	Gallery gallery;
	ImageSwitcher imgSwitcher;
	List<ImageView> views;
	
	Button btn_pre;
	Button btn_next;
	Button btn_left;
	Button btn_right;
	int old_position;
	
	int ids[]={R.drawable.item1,R.drawable.item2,R.drawable.item3,R.drawable.item4,R.drawable.item5,R.drawable.item6,R.drawable.item7,
			R.drawable.item8,R.drawable.item9,R.drawable.item10,R.drawable.item11,R.drawable.item12		
	};
	float rots[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery_and_imageswitcher);
		
		gallery=(Gallery) findViewById(R.id.gallery);
		imgSwitcher=(ImageSwitcher) findViewById(R.id.image_switcher);
		btn_pre=(Button) findViewById(R.id.btn_pre);
		btn_next=(Button) findViewById(R.id.btn_next);
		btn_left=(Button) findViewById(R.id.btn_left);
		btn_right=(Button) findViewById(R.id.btn_right);
		old_position=0;
		views=new ArrayList<ImageView>();
		
		imgSwitcher.setFactory(new ViewFactory() {
			@Override
			public View makeView() {
				ImageView img=new ImageView(GalleryAndImageSwicherActivity.this);
				img.setScaleType(ScaleType.FIT_CENTER);
				return img;
			}
		});
		
		imgSwitcher.setImageResource(ids[0]);
		
		btn_left.setOnClickListener(this);
		btn_right.setOnClickListener(this);
		btn_pre.setOnClickListener(this);
		btn_next.setOnClickListener(this);
		Log.i("height",gallery.getHeight()+"");
		gallery.setAdapter(new ImageAdapter(this, ids));
		imgSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		imgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		
		rots=new float[ids.length];
		
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				show(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}
	
	public void show(int position){
		if(position<0)position=0;
		if(old_position>position){
//			imgSwitcher.setInAnimation(GalleryAndImageSwicherActivity.this,R.anim.left_in);
//			imgSwitcher.setOutAnimation(GalleryAndImageSwicherActivity.this, R.anim.left_out);
			imgSwitcher.setImageResource(ids[position%ids.length]);
		}
		if(old_position<position){
//			imgSwitcher.setInAnimation(GalleryAndImageSwicherActivity.this,R.anim.right_in);
//			imgSwitcher.setOutAnimation(GalleryAndImageSwicherActivity.this, R.anim.right_out);
			imgSwitcher.setImageResource(ids[position%ids.length]);
		}
		
		gallery.setSelection(position);
		
//		ImageView view=(ImageView) imgSwitcher.getCurrentView();
		//这个有缺陷
//		view.setRotation(rots[position%ids.length]);
		imgSwitcher.setRotation(rots[position%ids.length]);
		
		old_position=position;
	}

	@Override
	public void onClick(View v) {
		Resources res=getResources();
		switch (v.getId()) {
		case R.id.btn_pre:
			show(old_position-1);
			break;
		case R.id.btn_next:
			
			show(old_position+1);
			break;
		case R.id.btn_left:
		{
			rots[old_position%ids.length]+=90;
			show(old_position);
		}
			break;
		case R.id.btn_right:
		{
			rots[old_position%ids.length]-=90;
			show(old_position);
		}
			break;
		default:
			break;
		}
	}
}
