package com.study.android.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * 适配器-FragmentPagerAdapter
 * 关联:FragmentViewPagerActivity
 * @author ousir
 *不会自动创建和销毁
 */
public class MyFragmentViewPager extends FragmentPagerAdapter {

	List<Fragment> frgs;
	List<String> tabs;
	public MyFragmentViewPager(FragmentManager fm,List<Fragment> frgs, List<String> tabs) {
		super(fm);
		this.frgs=frgs;
		this.tabs=tabs;
	}

	@Override
	public Fragment getItem(int arg0) {
		return frgs.get(arg0);
	}

	@Override
	public int getCount() {
		return frgs.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return tabs.get(position);
	}
}
