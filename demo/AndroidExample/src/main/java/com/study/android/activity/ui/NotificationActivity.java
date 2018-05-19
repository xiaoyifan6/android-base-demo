package com.study.android.activity.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.oyz.thisismyapp.R;
import com.study.android.activity.layerout.GalleryAndImageSwicherActivity;
import com.study.android.activity.other.ColorActivity;

@SuppressLint("NewApi")
public class NotificationActivity extends Activity implements OnClickListener{
	NotificationManager manager;
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	
	private final int NOTIFICATION_ID=1;
	private final int NOTIFICATION2_ID=2;
	private final String STATUS_BAR_COVER_CLICK_ACTION="取消自定义通知";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.simple_btn_view);
		
		btn1=(Button)findViewById(R.id.button1);
		btn2=(Button)findViewById(R.id.button2);
		btn3=(Button)findViewById(R.id.button3);
		btn4=(Button)findViewById(R.id.button4);
		
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		
		btn1.setText("显示通知");
		btn2.setText("自定义通知");
		btn3.setText("取消通知");
		btn4.setText("取消所有通知");
		
		manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button1:
				{
					Builder builder=new Builder(this);
					builder.setTicker("hello");
					builder.setContentTitle("这是标题");
					builder.setContentText("这是内容");
					builder.setSubText("这是副内容");
					//设置指示灯
					builder.setDefaults(Notification.DEFAULT_LIGHTS);
					//设置通知铃声
					builder.setDefaults(Notification.DEFAULT_SOUND);
					//设置震动
					builder.setDefaults(Notification.DEFAULT_VIBRATE);
					
//					//所有都默认
//					builder.setDefaults(Notification.DEFAULT_ALL);
					
					builder.setSmallIcon(R.drawable.ic_menu_save);
					//设置通知时间
					builder.setWhen(System.currentTimeMillis());
					
					Intent intent=new Intent(this, ColorActivity.class);
					PendingIntent pIntent=PendingIntent.getActivity(this, 1, intent,PendingIntent.FLAG_CANCEL_CURRENT);
					builder.setContentIntent(pIntent);
					builder.setNumber(1);
					//点击后取消通知栏
					builder.setAutoCancel(true);
					
					//4.1及以上
					Notification notification=builder.build();
//					//4.1一下
//					Notification notification=builder.getNotification();
					
					manager.notify(NOTIFICATION_ID, notification);
				}
				break;
			case R.id.button2:
				{
					Notification notification=new Notification();
					notification.icon = R.drawable.ic_menu_save;
					notification.when = System.currentTimeMillis();
					notification.tickerText = "您有新短消息，请注意查收！";	
					notification.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
					
					RemoteViews rv = new RemoteViews(getPackageName(),R.layout.notification_view);
					rv.setTextViewText(R.id.txt_notifi_content, "hello wrold!");
					notification.contentView = rv;
					
					//给按钮添加点击事件,不支持2.3及以下版本
					BroadcastReceiver onClickReceiver = new BroadcastReceiver() {
						@Override
						public void onReceive(Context context, Intent intent) {
							if (intent.getAction().equals(STATUS_BAR_COVER_CLICK_ACTION)){
								manager.cancel(NOTIFICATION2_ID);
								Toast.makeText(NotificationActivity.this, "自定义通知已取消", Toast.LENGTH_SHORT).show();
							}
						}
					};
					IntentFilter filter = new IntentFilter();
					filter.addAction(STATUS_BAR_COVER_CLICK_ACTION);
					registerReceiver(onClickReceiver, filter);
					
					Intent buttonIntent = new Intent(STATUS_BAR_COVER_CLICK_ACTION);
					PendingIntent pendButtonIntent = PendingIntent.getBroadcast(this, 0, buttonIntent, 0);
					//R.id.btn_close为你要监听按钮的id
					rv.setOnClickPendingIntent(R.id.btn_close, pendButtonIntent);

					
					Intent intent = new Intent(this,GalleryAndImageSwicherActivity.class);
					PendingIntent contentIntent = PendingIntent.getActivity(this, 2,intent, PendingIntent.FLAG_CANCEL_CURRENT);
					notification.contentIntent = contentIntent;
					manager.notify(NOTIFICATION2_ID, notification);
				}
				break;
			case R.id.button3:
				{
					manager.cancel(NOTIFICATION_ID);
				}
				break;
			case R.id.button4:
				{
					//清除所有通知
					manager.cancelAll();
				}
				break;
			default:
				break;
		}
	}
}
