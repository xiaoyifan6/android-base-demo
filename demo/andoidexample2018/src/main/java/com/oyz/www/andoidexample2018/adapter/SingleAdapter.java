package com.oyz.www.andoidexample2018.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.oyz.www.andoidexample2018.R;
import com.oyz.www.andoidexample2018.model.Item;

import org.byteam.superadapter.SuperAdapter;
import org.byteam.superadapter.SuperViewHolder;

import java.util.List;

/**
 * Created by ousir on 2018/1/27.
 */

public class SingleAdapter extends SuperAdapter<Item> {

    public SingleAdapter(Context context, List<Item> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int position, Item item) {
        holder.setText(R.id.textView, item.getName());
        holder.setText(R.id.textIcon, item.getId()+"");
    }
}
