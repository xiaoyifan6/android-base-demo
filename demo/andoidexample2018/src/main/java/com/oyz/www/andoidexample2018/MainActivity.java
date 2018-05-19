package com.oyz.www.andoidexample2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.oyz.www.andoidexample2018.adapter.SingleAdapter;
import com.oyz.www.andoidexample2018.model.Item;
import com.oyz.www.andoidexample2018.util.PropertiesUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Item> itemList;

    @BindView(R.id.listVew)
    ListView listVew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.initListVew();
    }

    private void initListVew(){
        try {
            Properties prop = PropertiesUtil.getNetConfigProperties(this.getAssets().open("config.properties"));
            String items = prop.getProperty("items");
            String packageStr = prop.getProperty("package");
            if(packageStr == null){
                packageStr = "";
            }

            if(items != null){
                List<Item> itemList = new ArrayList<>();
                int i = 1;
                for (String str:items.split("[|]")
                        ) {
                    String item = str;
                    if(prop.containsKey(str)){
                        item = prop.getProperty(str);
                    }
                    Item item0 = new Item(item,packageStr);
                    item0.setId(i++);
                    itemList.add(item0);
                }
                ListAdapter adapter = new SingleAdapter(this,itemList,R.layout.list_item_layout);
                this.itemList = itemList;
                this.listVew.setAdapter(adapter);
                this.listVew.setOnItemClickListener(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Item item = this.itemList.get(i);
        if(item != null && item.getActivityName() != null){
            Intent intent=new Intent();
            intent.setClassName(this, item.getActivityName());
            startActivity(intent);
        }else{
            Toast.makeText(this,"没有这一项!",Toast.LENGTH_SHORT).show();
        }
    }

}
