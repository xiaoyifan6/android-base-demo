package com.study.android.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.TextView;

import com.oyz.thisismyapp.R;

public class MyFragmant4 extends Fragment {
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.simple_text_view, container, false);
		TextView txt_view=(TextView) view.findViewById(R.id.text_view);
		txt_view.setText("页面1");
		return view;
	};
}
