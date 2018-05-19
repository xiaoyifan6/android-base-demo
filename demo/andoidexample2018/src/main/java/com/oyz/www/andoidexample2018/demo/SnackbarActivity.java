package com.oyz.www.andoidexample2018.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.oyz.www.andoidexample2018.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SnackbarActivity extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        //加上返回按钮 需放在toolbar设置之后
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SnackBar提示，相当于Toast
                View.OnClickListener listener = new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SnackbarActivity.this,"点击了SnackBar",Toast.LENGTH_SHORT).show();
                    }
                };
                Snackbar.make(view, "SnackBar:点击了FloatingActionButton", Snackbar.LENGTH_LONG)
                        .setAction("YES",listener)
                        .addCallback(new Snackbar.Callback(){
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, @DismissEvent int event) {
                                Toast.makeText(SnackbarActivity.this,"SnackBar消失",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }

    //添加菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this,"点击了设置按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Toast.makeText(this,"点击了搜索按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_share:
                Toast.makeText(this,"点击了分享按钮",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home://返回按钮
                this.finish(); // back button
                return true;
        }
        return true;
    }

}
