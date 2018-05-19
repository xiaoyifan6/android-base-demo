package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.oyz.thisismyapp.R;
import com.study.util.PP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 列表布局
 * @author ousir
 *
 */
public class ListViewActivity extends Activity implements OnItemClickListener,OnScrollListener{
	
	ListView list_view;
	SimpleAdapter adapter;
	List<Map<String, Object>> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listview);
		
		init();
	}

	private void init() {
		list=new ArrayList<Map<String,Object>>();
		list_view=(ListView) findViewById(R.id.list_view);
		adapter=new SimpleAdapter(
				this, getData(), 
				R.layout.list_view_item, 
				new String[]{"icon","name","descript"},
				new int[]{R.id.icon,R.id.txt_name,R.id.txt_descript});
		
		list_view.setAdapter(adapter);
		
		list_view.setOnItemClickListener(this);
		list_view.setOnScrollListener(this);
	}

	private List<Map<String, Object>> getData() {
		String[] apps=PP.getValueArray("APPS","\\|");
		
		for (int i = 0; i < apps.length; i++) {
			String[] info=apps[i].split(":");
			Map<String, Object> m=new HashMap<String, Object>();
			try {
				Object obj=R.drawable.class.getField(info[0]).get(null);
				if(obj==null)continue;

				m.put("icon",obj);
				//m.put("icon",R.drawable.camera);
				
				m.put("name", info[1]);
				
				String descript="";
				if(info.length==3){
					descript=info[2];
				}
				m.put("descript", descript);
				
				list.add(m);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}			
		}
		
		return list;
	}

	@Override
	public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {

	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int scrollState) {
		switch (scrollState) {
			case SCROLL_STATE_FLING:
				Log.i("Main", "用户手指离开屏幕之前,由于用力滑了一下,视图仍依照惯性向下滚动");
				Map<String, Object> m=new HashMap<String, Object>();
				m.put("icon",R.drawable.pencil);		
				m.put("name", "新增应用"+(list.size()+1));
				m.put("descript", "将菜单下拉到底时添加一行数据");
				
				list.add(m);
				adapter.notifyDataSetChanged();
				break;
			case SCROLL_STATE_IDLE:
				Log.i("Main", "视图已经停止滑动");
				break;
			case SCROLL_STATE_TOUCH_SCROLL:
				Log.i("Main", "手指没有离开屏幕,视图正在滑动");
				break;
			default:
				break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		try{
			Toast.makeText(this,((TextView)view.findViewById(R.id.txt_name)).getText(),
					Toast.LENGTH_SHORT).show();
		}catch (Exception e) {
		}
	}
	
}
