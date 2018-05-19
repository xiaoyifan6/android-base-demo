package com.study.android.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * ViewPager适配器
 * 关联:ViewPagerActivity
 * @author ousir
 *
 */
public class MyViewPagerAdapter extends PagerAdapter{
	List<View> views;
	List<String> tabs;
	
	public MyViewPagerAdapter(List<View> views, List<String> tabs) {
		this.views=views;
		this.tabs=tabs;
	}
	
	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view=views.get(position);;
		container.addView(view);
		return view;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(views.get(position));
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return tabs.get(position);
	}
	
}
