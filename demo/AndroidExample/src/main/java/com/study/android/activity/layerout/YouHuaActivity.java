package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.widget.Button;

import com.oyz.thisismyapp.R;

/**
 * 布局优化
 * @author ousir
 *
 */
public class YouHuaActivity extends Activity{
	Button btn_load;
	ViewStub view_stub;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_youhua);
		btn_load=(Button) findViewById(R.id.btn_load);
		view_stub=(ViewStub) findViewById(R.id.view_stub);
		
		btn_load.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				view_stub.inflate();
				btn_load.setEnabled(false);
			}
		});
	}
}
