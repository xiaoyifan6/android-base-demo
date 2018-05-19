package com.oyz.thisismyapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewMainActivity extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        ((Button) findViewById(R.id.button)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_exit)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_q_exit)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Toast.makeText(NewMainActivity.this,"傻逼",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_exit:
                finish();//方法一
                break;
            case R.id.btn_q_exit:
                //方法二 System.exit(0); 强制退出
                System.exit(0);
                break;
        }
    }
}
