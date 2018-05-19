package com.study.android.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
/**
 * 适配器-FragmentPagerAdapter
 * 关联:FragmentViewPagerActivity
 * 自动创建和销毁
 * @author ousir
 *
 */
public class MyFragmentViewPager2 extends FragmentStatePagerAdapter {

	List<Fragment> frgs;
	List<String> tabs;
	public MyFragmentViewPager2(FragmentManager fm,List<Fragment> frgs, List<String> tabs) {
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
	
	@Override
	public Object instantiateItem(ViewGroup arg0, int arg1) {
		return super.instantiateItem(arg0, arg1);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		super.destroyItem(container, position, object);
	}
}
