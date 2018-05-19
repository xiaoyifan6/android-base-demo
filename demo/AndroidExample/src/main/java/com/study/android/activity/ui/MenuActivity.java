package com.study.android.activity.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.oyz.thisismyapp.R;
import com.study.android.activity.other.ColorActivity;
import com.study.util.PP;

public class MenuActivity extends Activity{
	
	String[] citis=PP.getValue("CITY").split("\\|");
	ListView listView;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		listView=(ListView) findViewById(R.id.list_view);
		adapter=new ArrayAdapter<String>(this, R.layout.simple_list_item, citis);
		listView.setAdapter(adapter);
		
		registerForContextMenu(listView);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.my_menu, menu);
		
		//动态添加
		menu.add(1, R.id.item_copy, 1, R.string.copy);
		menu.add(1, R.id.item_cut, 1, R.string.cut);
		menu.add(1, R.id.item_paste, 1, R.string.paste);
		
		SubMenu subMenu=menu.addSubMenu("子菜单");
		subMenu.add(1, R.id.item_setting, 1, R.string.setting);
		subMenu.add(1, R.id.item_other, 1, R.string.other);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_copy:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.item_cut:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.item_paste:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.item_other:
			{
				Intent intent=new Intent(this, ColorActivity.class);
				item.setIntent(intent);
			}
			break;
		case R.id.item_setting:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		menu.setHeaderIcon(R.drawable.ic_menu_brush);
		menu.setHeaderTitle("上下文菜单");
//		//动态添加
//		menu.add(1, R.id.item_copy, 1, R.string.copy);
//		menu.add(1, R.id.item_cut, 1, R.string.cut);
//		menu.add(1, R.id.item_paste, 1, R.string.paste);
//		
//		SubMenu subMenu=menu.addSubMenu("子菜单");
//		subMenu.add(1, R.id.item_setting, 1, R.string.setting);
//		subMenu.add(1, R.id.item_other, 1, R.string.other);
		
		//xml
		getMenuInflater().inflate(R.menu.my_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_copy:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.item_cut:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.item_paste:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.item_other:
			{
				Intent intent=new Intent(this, ColorActivity.class);
				item.setIntent(intent);
			}
			break;
		case R.id.item_setting:
			{
				Toast.makeText(this, "点击了"+item.getTitle()+"按钮", Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
		return false;
	}
	
}
