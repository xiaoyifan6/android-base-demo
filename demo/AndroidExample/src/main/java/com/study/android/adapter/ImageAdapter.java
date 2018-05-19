package com.study.android.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter{

	int ids[];
	List<View> views;
	Context context;
	int height=100;
	public ImageAdapter(Context context,int ids[]){
		this.ids=ids;
		this.context=context;
		views=new ArrayList<View>();
		for (int id : ids) {
			ImageView img=new ImageView(context);
			img.setBackgroundResource(id);
			img.setLayoutParams(new Gallery.LayoutParams(height*3/4, height));
			img.setScaleType(ScaleType.FIT_XY);
			views.add(img);
		}
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int position) {
		return ids[position%ids.length];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return views.get(position%ids.length);
	}
}
