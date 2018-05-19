package com.study.android.activity.layerout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.oyz.thisismyapp.R;
/**
 * 进度条
 * @author ousir
 *
 */
public class ProgressActivity extends Activity implements OnClickListener{
	Button btn_add;
	Button btn_reduce;
	Button btn_reset;
	Button btn_show_dialog;
	Button btn_auto_run;
	
	TextView txt_prg1;
	TextView txt_prg2;
	TextView txt_seek_bar;
	
	ProgressBar prg_bar2;
	ProgressBar prg_bar3;
	ProgressBar prg_bar4;
	ProgressDialog prg_dialog;
	SeekBar seekBar;
	
	
	MyHandler handler;	
	int max;
	int prg1;
	int prg2;
	int icm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
		init();
		
	}

	private void init() {
		max=100;
		prg1=20;
		prg2=50;
		icm=10;
		
		btn_add=(Button) findViewById(R.id.btn_add);
		btn_reduce=(Button) findViewById(R.id.btn_reduce);
		btn_reset=(Button) findViewById(R.id.btn_reset);
		btn_show_dialog=(Button) findViewById(R.id.btn_show);
		btn_auto_run=(Button) findViewById(R.id.btn_autorun);
		
		txt_prg1=(TextView) findViewById(R.id.txt_prg);
		txt_prg2=(TextView) findViewById(R.id.txt_prg2);
		txt_seek_bar=(TextView) findViewById(R.id.txt_seek_bar);
		
		prg_dialog=new ProgressDialog(this);
		prg_dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		prg_dialog.setTitle("进度条对话框");
		prg_dialog.setIcon(R.drawable.ic_launcher);
		prg_dialog.setCancelable(true);
		prg_dialog.setIndeterminate(false);
		prg_dialog.setMax(max);
		prg_dialog.setProgress(prg1);
		prg_dialog.setSecondaryProgress(prg2);
		prg_dialog.setButton(ProgressDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(ProgressActivity.this, "这是进度条哦~", Toast.LENGTH_SHORT).show();
			}
		});
		
		prg_bar2=(ProgressBar) findViewById(R.id.prg_bar2);
		prg_bar3=(ProgressBar) findViewById(R.id.prg_bar3);
		prg_bar4=(ProgressBar) findViewById(R.id.prg_bar4);
		
		seekBar=(SeekBar) findViewById(R.id.seek_bar);
		
		btn_add.setOnClickListener(this);
		btn_reduce.setOnClickListener(this);
		btn_reset.setOnClickListener(this);
		btn_show_dialog.setOnClickListener(this);
		btn_auto_run.setOnClickListener(this);
		
		
		txt_seek_bar.setText(((float)seekBar.getProgress()*100/seekBar.getMax())+"%");
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				txt_seek_bar.setText(((float)seekBar.getProgress()*100/seekBar.getMax())
						+"%(停止拖动)");				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				txt_seek_bar.setText(((float)seekBar.getProgress()*100/seekBar.getMax())
						+"%(开始拖动)");				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				txt_seek_bar.setText(((float)seekBar.getProgress()*100/seekBar.getMax())
						+"%(进度条值发生改变)");
			}
		});
		
		flush();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_add:
				prg_bar2.incrementProgressBy(icm);	
				
				seekBar.incrementProgressBy(icm);
				
				prg_bar3.incrementProgressBy(icm);		
				prg_bar4.incrementProgressBy(icm);			
				prg_bar3.incrementSecondaryProgressBy(icm);				
				prg_bar4.incrementSecondaryProgressBy(icm);
				
				flush();
				break;
			case R.id.btn_reduce:
				prg_bar2.incrementProgressBy(-icm);	
				
				seekBar.incrementProgressBy(-icm);
				
				prg_bar3.incrementProgressBy(-icm);		
				prg_bar4.incrementProgressBy(-icm);			
				prg_bar3.incrementSecondaryProgressBy(-icm);				
				prg_bar4.incrementSecondaryProgressBy(-icm);				
				flush();
				break;
			case R.id.btn_reset:
				prg_bar2.setProgress(prg1);	
				
				seekBar.setProgress(prg1);
				
				prg_bar3.setProgress(prg1);				
				prg_bar4.setProgress(prg1);				
				prg_bar3.setSecondaryProgress(prg2);				
				prg_bar4.setSecondaryProgress(prg2);
				flush();
				break;
			case R.id.btn_show:
				prg_dialog.incrementProgressBy(prg_bar4.getProgress()-prg_dialog.getProgress());
				prg_dialog.incrementSecondaryProgressBy(prg_bar4.getSecondaryProgress()-prg_dialog.getSecondaryProgress());
				flush();
				prg_dialog.show();
				break;
			case R.id.btn_autorun:
				if(handler==null){
					handler = new MyHandler();
					handler.sleep((long) 100);
				}
				break;
			default:
				break;
		}
	}

	private void flush() {
		txt_prg1.setText(((float)prg_bar4.getProgress()*100/max)+"%");
		txt_prg2.setText("进度一:"+((float)prg_bar4.getProgress()*100/max)+"%,进度二:"+((float)prg_bar4.getSecondaryProgress()*100/max)+"%");
		prg_dialog.setMessage("进度一:"+((float)prg_bar4.getProgress()*100/prg_bar4.getMax())+"%,进度二:"+((float)prg_bar4.getSecondaryProgress()*100/prg_bar4.getMax())+"%");
	}
	
	class MyHandler extends Handler{
		public void handleMessage(Message msg) {
			prg_bar2.incrementProgressBy(icm);	
			
			prg_bar3.incrementProgressBy(icm);		
			prg_bar4.incrementProgressBy(icm);			
			prg_bar3.incrementSecondaryProgressBy(icm);				
			prg_bar4.incrementSecondaryProgressBy(icm);
			prg_dialog.incrementProgressBy(prg_bar4.getProgress()-prg_dialog.getProgress());
			prg_dialog.incrementSecondaryProgressBy(prg_bar4.getSecondaryProgress()-prg_dialog.getSecondaryProgress());
			flush();
			if(prg_bar4.getProgress()>=max&&prg_bar4.getSecondaryProgress()>=max){
//				getLooper().quit();
				removeMessages(msg.what);
				handler=null;
			}else{
				sleep((long) 1000);
			}
		}
 
		public void sleep(Long delayMillis) {
			this.sendMessageDelayed(this.obtainMessage(0), delayMillis);
		}
	}
}
