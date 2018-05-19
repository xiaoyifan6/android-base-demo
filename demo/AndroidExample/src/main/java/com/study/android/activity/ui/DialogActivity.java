package com.study.android.activity.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.oyz.thisismyapp.R;
import com.study.util.PP;

/**
 * 自定义Toast
 * @author ousir
 *
 */
public class DialogActivity extends Activity implements OnClickListener{
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.simple_btn_view);
		
		LayoutInflater inflater=getLayoutInflater();
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.simple_btn_view, null);
		setContentView(layout);
		
		btn1=(Button)layout.findViewById(R.id.button1);
		btn2=(Button)layout.findViewById(R.id.button2);
		btn3=(Button)layout.findViewById(R.id.button3);
		btn4=(Button)layout.findViewById(R.id.button4);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		
		
		btn5=new Button(this);
		btn5.setId(R.id.button7);
		btn5.setBackground(getResources().getDrawable(R.drawable.btn_selector));
		btn5.setPadding(20, 20, 20, 20);
		btn5.setTextSize(16);
		btn5.setTextColor(getResources().getColor(R.color.black));
		layout.addView(btn5);
		btn5.setOnClickListener(this);
		
		btn1.setText("确认对话框");
		btn2.setText("单选对话框");
		btn3.setText("多选对话框");
		btn4.setText("列表对话框");
		btn5.setText("自定义对话框");
	}

	@Override
	public void onClick(View v) {
		Button button=(Button) v;
		
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle(button.getText());
		builder.setIcon(getResources().getDrawable(R.drawable.ic_menu_save));
		switch (v.getId()) {
			case R.id.button1:
				builder.setMessage("这是"+button.getText());
				{
					builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, "确认对话框-确认按钮", Toast.LENGTH_SHORT).show();
						}
					});
					builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, "确认对话框-取消按钮", Toast.LENGTH_SHORT).show();
						}
					});
					AlertDialog dialog=builder.create();
					dialog.show();
				}
				break;
			case R.id.button2:
				{
					final String citys[]=PP.getValueArray("CITY","\\|");
					builder.setSingleChoiceItems(citys, 0, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, citys[which] , Toast.LENGTH_SHORT).show();
							dialog.dismiss();
						}
					});
					AlertDialog dialog=builder.create();
					dialog.show();
				}
				break;
			case R.id.button3:
				{
					final String citys[]=PP.getValueArray("CITY","\\|");
					builder.setMultiChoiceItems(citys, null, new DialogInterface.OnMultiChoiceClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {
							if(isChecked){
								Toast.makeText(DialogActivity.this, "我选择了"+citys[which] , Toast.LENGTH_SHORT).show();
							}else{
								Toast.makeText(DialogActivity.this, "我取消了"+citys[which] , Toast.LENGTH_SHORT).show();
							}
						}
					});
					
					builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					
					AlertDialog dialog=builder.create();
					dialog.show();
				}
				break;
			case R.id.button4:
				{
					final String citys[]=PP.getValueArray("CITY","\\|");
					builder.setItems(citys, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(DialogActivity.this, citys[which] , Toast.LENGTH_SHORT).show();
						}
					});
					
					AlertDialog dialog=builder.create();
					dialog.show();
				}
				break;
			case R.id.button7:
				{
					View view=getLayoutInflater().inflate(R.layout.dialog_view, null);
					final EditText editText= ((EditText)view.findViewById(R.id.edit_text));
					((Button)view.findViewById(R.id.btn_submit)).setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							Toast.makeText(DialogActivity.this,editText.getText()+"", Toast.LENGTH_SHORT).show();
						}
					});
					builder.setView(view);
										
					builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
					AlertDialog dialog=builder.create();
					dialog.show();
				}
				break;
			default:
				break;
		}
	}
}
