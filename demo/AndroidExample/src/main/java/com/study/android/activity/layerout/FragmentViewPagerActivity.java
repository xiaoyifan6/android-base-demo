package com.study.android.activity.layerout;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;

import com.oyz.thisismyapp.R;
import com.study.android.adapter.MyFragmentViewPager2;
import com.study.android.fragment.MyFragmant4;
import com.study.android.fragment.MyFragmant5;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPager页面浏览
 * android.support.v4.app.Fragment:支持低版本
 * 关联:MyFragmentViewPager,MyFragmentViewPager2
 * @author ousir
 *
 */
public class FragmentViewPagerActivity extends FragmentActivity {
	ViewPager viewPager;
	List<Fragment> frgs;
	List<String> tabs;
	PagerAdapter adapter;
	FragmentStatePagerAdapter adapter2;
	PagerTabStrip tab;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pageview);
		
		viewPager=(ViewPager) findViewById(R.id.view_pager);
		tab=(PagerTabStrip) findViewById(R.id.tab);
		
		frgs=new ArrayList<Fragment>();
		
		frgs.add(new MyFragmant4());
		frgs.add(new MyFragmant5());
		
		tabs=new ArrayList<String>();
		tabs.add("页面1");
		tabs.add("页面2");
		
		//不会自动创建和销毁
//		adapter=new MyFragmentViewPager(getSupportFragmentManager(),frgs,tabs);
//		viewPager.setAdapter(adapter);
		
		//自动创建和销毁
		adapter2=new MyFragmentViewPager2(getSupportFragmentManager(),frgs,tabs);
		viewPager.setAdapter(adapter2);
		
		Resources res=getResources();
		tab.setBackgroundColor(res.getColor(R.color.darkorange));
		tab.setTextColor(res.getColor(R.color.gainsboro));
		tab.setDrawFullUnderline(false);
		tab.setTabIndicatorColor(res.getColor(R.color.maroon));
	}
}
