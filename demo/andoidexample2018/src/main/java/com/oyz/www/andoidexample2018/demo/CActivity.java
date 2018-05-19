package com.oyz.www.andoidexample2018.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.oyz.www.andoidexample2018.R;
import com.oyz.www.andoidexample2018.demo.c.JniTest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CActivity extends AppCompatActivity {

    @BindView(R.id.textView5)
    TextView textView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        ButterKnife.bind(this);

        this.textView5.setText(new JniTest().getString());
    }
}
