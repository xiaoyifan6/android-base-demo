package com.study.android.activity.data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.oyz.thisismyapp.R;

/**
 * SQLite数据存储
 * @author ousir
 *
 */
public class SQLiteActivity extends Activity {
	
	private void testSQLite(){
		//创建一个数据库,并且打开
		SQLiteDatabase db=openOrCreateDatabase("user.db", MODE_PRIVATE,null);
		//插入数据
		db.execSQL("create table if not exists tb_user(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null)");
		db.execSQL("insert into tb_user(name,sex,age)values('李四','男',12)");
		db.execSQL("insert into tb_user(name,sex,age)values('张三','女',15)");
		db.execSQL("insert into tb_user(name,sex,age)values('王五','男',18)");
		//
		ContentValues values=new ContentValues();
		values.put("name", "赵六");
		values.put("sex", "女");
		values.put("age", 19);
		db.insert("tb_user", null, values);
		values.clear();
		
		values.put("name", "钱七");
		values.put("sex", "男");
		values.put("age", 21);
		db.insert("tb_user", null, values);
		values.clear();		
		
		//修改:将_id>3的用户性别全部改成女
		values.put("sex", "女");
		db.update("tb_user", values, "_id>?", new String[]{"3"});
		values.clear();
		
		//删除
		db.delete("tb_user", "name like ?", new String[]{"%三%"});
		//游标查询
//		Cursor c=db.rawQuery("select * from tb_user", null);
//		if(c!=null){
//			c.moveToFirst();//移到开头
//			while(c.moveToNext()){
//				Log.i("info","_id:"+c.getInt( c.getColumnIndex("_id")));
//				Log.i("info","name:"+c.getString( c.getColumnIndex("name")));
//				Log.i("info","age:"+c.getInt( c.getColumnIndex("age")));
//				Log.i("info","sex:"+c.getString( c.getColumnIndex("sex")));
//			}
//			c.close();
//		}
		
		Cursor c=db.query("tb_user", null, "_id>?", new String[]{"0"}, null, null, "name");
		if(c!=null){
			c.moveToFirst();
			String []columns=c.getColumnNames();
			while(c.moveToNext()){
				for (String columnName : columns) {
					Log.i("info",columnName+":"+c.getString( c.getColumnIndex(columnName)));
				}
			}
			c.close();
		}
		
		db.close();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.simple_text_view);
		((TextView)findViewById(R.id.text_view)).setText("输出数据请看LogCat中的log日志,tag为info");
		
		//每个程序都有自己的数据库 默认情况下是各自互相不干扰
		SQLiteHelper helper=new SQLiteHelper(this, "user.db");
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor c=db.query("tb_user", null, "_id>?", new String[]{"0"}, null, null, "name");
		if(c!=null){
			c.moveToFirst();
			String []columns=c.getColumnNames();
			while(c.moveToNext()){
				for (String columnName : columns) {
					Log.i("info",columnName+":"+c.getString( c.getColumnIndex(columnName)));
				}
			}
			c.close();
		}
		
		db.close();
	}
}

class SQLiteHelper extends SQLiteOpenHelper{

	public SQLiteHelper(Context context, String name) {
		super(context, name, null, 1);
	}
	
	//首次创建数据库调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		//插入数据
		db.execSQL("create table if not exists tb_user(_id integer primary key autoincrement,name text not null,age integer not null,sex text not null)");
		db.execSQL("insert into tb_user(name,sex,age)values('李四','男',12)");
		db.execSQL("insert into tb_user(name,sex,age)values('张三','女',15)");
		db.execSQL("insert into tb_user(name,sex,age)values('王五','男',18)");
		//
		ContentValues values=new ContentValues();
		values.put("name", "赵六");
		values.put("sex", "女");
		values.put("age", 19);
		db.insert("tb_user", null, values);
		values.clear();
		
		values.put("name", "钱七");
		values.put("sex", "男");
		values.put("age", 21);
		db.insert("tb_user", null, values);
		values.clear();		
	}
	//当数据库版本发生变化时，自动执行
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
}
