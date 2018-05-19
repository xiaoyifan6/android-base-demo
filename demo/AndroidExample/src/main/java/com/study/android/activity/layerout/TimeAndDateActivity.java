package com.study.android.activity.layerout;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.oyz.thisismyapp.R;

import java.util.Calendar;
/**
 * 时间选择器
 * @author ousir
 *
 */
public class TimeAndDateActivity extends Activity implements OnClickListener{
	Calendar calendar;
	DatePicker datePicker;
	TimePicker timePicker;
	Button btn_show_date;
	Button btn_show_time;
	TextView digitalClock;
	DatePickerDialog datePickerDialog;
	TimePickerDialog timePickerDialog;
	
	int year;
	int month;
	int day;
	int hour;
	int minute;
	int second;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//去掉标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_time_and_date);
		init();
	}

	private void init() {
		calendar=Calendar.getInstance();
		
		year=calendar.get(Calendar.YEAR);
		month=calendar.get(Calendar.MONTH)+1;
		day=calendar.get(Calendar.DAY_OF_MONTH);
		
		hour=calendar.get(Calendar.MONTH);
		minute=calendar.get(Calendar.HOUR_OF_DAY);
		second=calendar.get(Calendar.SECOND);
		
		datePicker=(DatePicker) findViewById(R.id.date_pick);
		timePicker=(TimePicker) findViewById(R.id.time_pick);
		btn_show_date=(Button) findViewById(R.id.btn_show_date_dialog);
		btn_show_time=(Button) findViewById(R.id.btn_show_time_dialog);
		digitalClock=(TextView) findViewById(R.id.dig_clock);
		
		btn_show_date.setOnClickListener(this);
		btn_show_time.setOnClickListener(this);
		
		datePicker.init(year, month-1, day, new OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				TimeAndDateActivity.this.year=year;
				TimeAndDateActivity.this.month=monthOfYear+1;
				TimeAndDateActivity.this.day=dayOfMonth;
				flush();
			}
		});
		
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				TimeAndDateActivity.this.hour=hourOfDay;
				TimeAndDateActivity.this.minute=minute;
				flush();
			}
		});
		
		datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				TimeAndDateActivity.this.month=monthOfYear+1;
				TimeAndDateActivity.this.day=dayOfMonth;
				TimeAndDateActivity.this.year=year;
				flush();
			}
		}, year, month-1, day);
		
		timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
		
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				TimeAndDateActivity.this.hour=hourOfDay;
				TimeAndDateActivity.this.minute=minute;
				flush();
			}
		}, hour, minute, true);
		
		flush();
	}

	private void flush() {
		digitalClock.setText(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_show_date_dialog:
				datePickerDialog.show();
				break;
			case R.id.btn_show_time_dialog:
				timePickerDialog.show();
				break;
	
			default:
				break;
		}
	}
}
