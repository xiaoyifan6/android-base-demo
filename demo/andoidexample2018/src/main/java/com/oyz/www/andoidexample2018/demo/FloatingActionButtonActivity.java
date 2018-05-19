package com.oyz.www.andoidexample2018.demo;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.oyz.www.andoidexample2018.R;
import com.oyz.www.andoidexample2018.demo.ui.FloatingDraftButton;
import com.oyz.www.andoidexample2018.util.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingDraftButton fab;

    @BindView(R.id.floatingActionButton)
    FloatingDraftButton floatingDraftButton;
    @BindView(R.id.floatingActionButton_liveness)
    FloatingActionButton liveness;
    @BindView(R.id.floatingActionButton_2)
    FloatingActionButton floatingActionButton2;
    @BindView(R.id.floatingActionButton_3)
    FloatingActionButton floatingActionButton3;
    @BindView(R.id.floatingActionButton_4)
    FloatingActionButton floatingActionButton4;
    @BindView(R.id.floatingActionButton_5)
    FloatingActionButton floatingActionButton5;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        ButterKnife.bind(this);

        //加上返回按钮
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //浮动按钮
        floatingDraftButton.registerButton(liveness);
        floatingDraftButton.registerButton(floatingActionButton2);
        floatingDraftButton.registerButton(floatingActionButton3);
        floatingDraftButton.registerButton(floatingActionButton4);
        floatingDraftButton.registerButton(floatingActionButton5);

        floatingDraftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                //弹出动态Button
                AnimationUtil.slideButtons(FloatingActionButtonActivity.this,floatingDraftButton);
            }
        });

        liveness.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //关闭动态Button
                AnimationUtil.slideButtons(FloatingActionButtonActivity.this,floatingDraftButton);
                Toast.makeText(getApplicationContext(), "liveness", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
