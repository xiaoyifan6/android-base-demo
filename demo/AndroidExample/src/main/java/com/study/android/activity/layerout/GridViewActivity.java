package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.oyz.thisismyapp.R;
import com.study.util.PP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 网格布局
 * @author ousir
 *
 */
public class GridViewActivity extends Activity {
	GridView grid_view;
	SimpleAdapter adapter;
	List<Map<String, Object>> list;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_gridview);
		init();
	}

	private void init() {
		list=new ArrayList<Map<String,Object>>();

		grid_view=(GridView) findViewById(R.id.grid_view);
		adapter=new SimpleAdapter(this, getData(), R.layout.grid_view_item,
					new String[]{"icon","name"}, new int[]{R.id.icon,R.id.txt_name});
		grid_view.setAdapter(adapter);
		
		grid_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				@SuppressWarnings("unchecked")
				Map<String, Object> m=(Map<String, Object>) adapter.getItem(position);
				Toast.makeText(GridViewActivity.this,m.get("descript")+"", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	private List<Map<String, Object>> getData() {
		String[] apps=PP.getValue("APPS").split("\\|");
		
		for (int i = 0; i < apps.length; i++) {
			String[] info=apps[i].split(":");
			Map<String, Object> m=new HashMap<String, Object>();
			try {
				Object obj=R.drawable.class.getField(info[0]).get(null);
				if(obj==null)continue;
				
				m.put("icon",obj);
				
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
}
