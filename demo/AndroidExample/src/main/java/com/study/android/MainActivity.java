package com.study.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.oyz.thisismyapp.R;
import com.study.util.PP;

import java.io.IOException;

public class MainActivity extends Activity implements OnItemClickListener{
	ListView listv;
	String[] packges;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		try {
			PP.load(this.getApplicationContext().getAssets().open("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		initList();
	}

	private void initList() {
		listv=(ListView) findViewById(R.id.list_main);
		
		String lis=PP.getValue("EXAMPLE");
		if(lis.equals("null")){
			return;
		}else{
			String[] names=PP.getValueArray("EXAMPLE","\\|");
			String[] items=new String[names.length];
					 packges=new String[names.length];
			for (int i = 0; i < names.length; i++) {
				String[] ss=names[i].split("\\-");
				if(ss.length>0){
					items[i]=ss[0];
					if(ss.length>1){
						packges[i]=ss[1];
					}
				}
			}
			ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,items);
			listv.setAdapter(adapter);
			listv.setOnItemClickListener(this);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String className=packges[position];
		if(className!=null && className.length()>0){
			Intent intent=new Intent();
			intent.setClassName(this, className);
			startActivity(intent);
		}
	}
}
