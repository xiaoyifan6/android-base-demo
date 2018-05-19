package com.study.android.activity.data;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import com.oyz.thisismyapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.simple_text_view);
		TextView txt_view=(TextView) findViewById(R.id.text_view);
		txt_view.setText("自己看代码");
		
//		File f=new File("/mnt/sdcard/test");
//		//外置目录
////		new File("/mnt/extsdcard/test")
//		
//		if(!f.exists()){
//			try {
//				f.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		//删除文件
////		f.delete();
		
//		//当前应用程序默认的数据目录
//		File f=this.getFilesDir();
		
//		//不是重要的文件在此创建使用
//		//手机内存不足的时候系统会自动删除app的cache目录的数据
//		File f=this.getCacheDir();
		
//		// data/data/
//		File f=this.getDir("_user", MODE_PRIVATE);
		
		//可以得到外部存储位置,这里面的数据也会自动清除掉
		
//		File f=this.getExternalCacheDir();
		
		
//		File f = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/oyz");
//		if(f.exists())f.delete();
//		f.mkdirs();
		File f= this.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
		
//		if(!f.exists()){
//			if(f.isDirectory()){
//				f.mkdirs();
//			}else{
//				try {
//					f.createNewFile();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		for (int i = 1; i <= 10; i++) {
			FileOutputStream fos = null;
			File f0 = new File(f,i+".txt");
			try {
				fos = new FileOutputStream(f0);
				fos.write(("Hello 傻逼!!!["+i+"]").getBytes());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(fos!=null){
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(f);
//			fos.write("Hello 傻逼!!!".getBytes());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally{
//			if(fos!=null){
//				try {
//					fos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
//		//如果开发者不遵循这样的规则,不把数据放入data/data/<包名>
//		//	/mnt/sdcard/Android/data/<包名>
//		//拆卸之后数据不会自动清除掉,将会造成所谓的垃圾数据
		
	}
	//写
	private void write(String content) throws IOException{
//		FileOutputStream fos=openFileOutput("a.txt",MODE_PRIVATE);
		//支持外部应用访问
		FileOutputStream fos=openFileOutput("a.txt",MODE_WORLD_READABLE+MODE_WORLD_WRITEABLE);
		fos.write(content.getBytes());
		fos.close();
	}
	//读
	private String read(String content) throws IOException{
		FileInputStream fis=openFileInput("a.txt");
		
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		byte bbs[]=new byte[1024];
		int len=0;
		while((len=fis.read(bbs))>0){
			baos.write(bbs,0,len);
		}
		fis.close();
		return baos.toString();
	}
}
