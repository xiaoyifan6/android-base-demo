package com.study.android.activity.layerout;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.oyz.thisismyapp.R;
import com.study.android.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager页面浏览
 * @author ousir
 *
 */
public class ViewPagerActivity extends Activity {
	ViewPager viewPager;
	List<View> views;
	List<String> tabs;
	PagerAdapter adapter;
	PagerTabStrip tab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pageview);
		
		viewPager=(ViewPager) findViewById(R.id.view_pager);
		tab=(PagerTabStrip) findViewById(R.id.tab);
		
		views=new ArrayList<View>();
		
		View view1=View.inflate(this, R.layout.simple_text_view, null);
		View view2=View.inflate(this, R.layout.activity_marquee, null);
		View view3=View.inflate(this, R.layout.edit_view, null);
		
		views.add(view1);
		views.add(view2);
		views.add(view3);
		
		tabs=new ArrayList<String>();
		tabs.add("页面1");
		tabs.add("页面2");
		tabs.add("页面3");
		
		adapter=new MyViewPagerAdapter(views,tabs);
		viewPager.setAdapter(adapter);
		Resources res=getResources();
		tab.setBackgroundColor(res.getColor(R.color.darkorange));
		tab.setTextColor(res.getColor(R.color.gainsboro));
		tab.setDrawFullUnderline(false);
		tab.setTabIndicatorColor(res.getColor(R.color.maroon));
	}
}
