package com.study.android.activity.layerout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.oyz.thisismyapp.R;
import com.study.util.PP;

import java.util.Arrays;
/**
 * 基础控件
 * @author ousir
 *
 */
public class BaseActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		
		
		initAutoComplete();
		initMultyAutoComplete();
		initRatingBar();
		initSpinner();
		initRadioGroup();
		intiToggleButton();
		
	}

	private void intiToggleButton() {
		ToggleButton tgleBtn=(ToggleButton) findViewById(R.id.tbtn);
		tgleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				Toast.makeText(BaseActivity.this, buttonView.getText(), Toast.LENGTH_SHORT)
//				.show();
				ImageView imgv=(ImageView) BaseActivity.this.findViewById(R.id.imgv);
				if(isChecked){
					imgv.setImageResource(R.drawable.calendar);
				}else{
					imgv.setImageResource(R.drawable.camera);
				}
			}
		});
	}

	private void initRadioGroup() {
		RadioGroup rg=(RadioGroup) findViewById(R.id.bgng_gendle);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				String content="";
				RadioButton rButton=(RadioButton)findViewById(checkedId);
				switch (checkedId) {
					case R.id.rbtn_man:
						content=rButton.getText().toString();
						break;
					case R.id.rbtn_woman:
						content=rButton.getText().toString();
						break;
					default:
						break;
				}
				Toast.makeText(BaseActivity.this, content, Toast.LENGTH_SHORT)
				.show();
			}
		});
	}

	private void initSpinner() {
		Spinner spinner=(Spinner) findViewById(R.id.spinner1);
		
		String[] citys=PP.getValue("CITY").split("\\|");
		final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Arrays.asList(citys));
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(BaseActivity.this, adapter.getItem(position), Toast.LENGTH_SHORT)
				.show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}

	private void initRatingBar() {
		RatingBar ratingBar=(RatingBar) findViewById(R.id.ratingBar1);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				Toast.makeText(BaseActivity.this, rating+"", Toast.LENGTH_SHORT)
				.show();
			}
		});
	}

	private void initMultyAutoComplete() {
		MultiAutoCompleteTextView cmp_auto=(MultiAutoCompleteTextView) findViewById(R.id.cmp_ma);
		String[] citys=PP.getValue("CITY").split("\\|");
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.simple_list_item,citys);
		cmp_auto.setAdapter(adapter);
		//设置以逗号为分隔符为结束符
		cmp_auto.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		cmp_auto.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if(view instanceof TextView){
					Toast.makeText(BaseActivity.this, ((TextView)view).getText(), Toast.LENGTH_SHORT)
					.show();
				}
			}
		});
	}

	private void initAutoComplete() {
		AutoCompleteTextView cmp_auto=(AutoCompleteTextView) findViewById(R.id.cmp_auto);
		String[] citys=PP.getValue("CITY").split("\\|");
		//android.R.layout.simple_expandable_list_item_1
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.simple_list_item,citys);
		cmp_auto.setAdapter(adapter);
		cmp_auto.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				if(view instanceof TextView){
					Toast.makeText(BaseActivity.this, ((TextView)view).getText(), Toast.LENGTH_SHORT)
					.show();
				}
			}
		});
	}
}
