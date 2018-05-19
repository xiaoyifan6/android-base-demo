package com.oyz.www.andoidexample2018.demo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oyz.www.andoidexample2018.R;
import com.oyz.www.andoidexample2018.demo.TabLoyoutActivity;

/**
 * Created by ousir on 2018/1/28.
 */

public class TabContentFragment extends Fragment{
    private String tabName;

    TextView textView3;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_layout, container, false);
        textView3=(TextView) view.findViewById(R.id.textView3);
        textView3.setText("页面:"+tabName);
        return view;
    };

    public static Fragment newInstance(String tabName){
        TabContentFragment fragment = new TabContentFragment();
        fragment.tabName = tabName;
        return  fragment;
    }
}
