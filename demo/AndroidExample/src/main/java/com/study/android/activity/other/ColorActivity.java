package com.study.android.activity.other;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
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
 * 颜色板
 * @author ousir
 *
 */
public class ColorActivity extends Activity{
	
	GridView gridView;
	List<Map<String, Object>> datalist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_color);
		gridView=(GridView) findViewById(R.id.grid_color);
		gridView.setBackgroundColor(getResources().getColor(R.color.white));
		initGrid();
	}

	@SuppressLint("ResourceAsColor")
	private void initGrid() {
		SimpleAdapter adapter=new SimpleAdapter(this, getData(), R.layout.color_item,
				new String[]{"colorInfo","color"}, new int[]{R.id.item,R.id.item_name});
		
		final Resources res=getResources();
		
		adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
			@Override
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				String colorInfo= textRepresentation;
				String[] info=colorInfo.split(":");
				if(info.length==3){
					try{
//							Log.i("color",textRepresentation+","+data);
							TextView tv=(TextView)view.findViewById(R.id.item);
							if(tv!=null){
								tv.setTag(data);
								tv.setText(info[1]);
								
								Object id=R.color.class.getField(info[0]).get(null);
								int c= id==null?res.getColor(R.color.red):res.getColor((Integer)id);
								tv.setBackgroundColor(c);
							}
					}catch (Exception e){
						Log.i("myerror",textRepresentation+","+e.toString());
					}
					try{
						TextView tv2=(TextView) view.findViewById(R.id.item_name);
						if(tv2!=null)
							tv2.setText(info[2]);
					}catch (Exception e) {
						Log.i("myerror",textRepresentation+","+e.toString());
					}
				}
				return true;
			}
		});
		
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				TextView tv=(TextView)view.findViewById(R.id.item);
				Toast.makeText(ColorActivity.this, tv.getTag()+"", Toast.LENGTH_SHORT).show();
			}
		});
		
		gridView.setAdapter(adapter);
		
	}

	private List<? extends Map<String, Object>> getData() {
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		String[] colorlist=PP.getValue("COLORLIST").split("\\|");
		for (int i = 0; i < colorlist.length; i++) {
			Map<String, Object> m=new HashMap<String, Object>();
			m.put("colorInfo",colorlist[i]);
			m.put("color",colorlist[i]);
			list.add(m);
		}
		return list;
	}
}
