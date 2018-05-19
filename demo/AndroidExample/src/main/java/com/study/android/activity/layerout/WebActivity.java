package com.study.android.activity.layerout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.oyz.thisismyapp.R;

//如下,提供一个showToast的方法给javascript
//private static class JavaJs {
//	  private Context context;
//	  JavaJs(Context context) {
//	    this.context = context;
//	  }
//	  @JavascriptInterface
//	  public void showToast(String str) {
//	    Toast.makeText(context, str, Toast.LENGTH_LONG).show();
//	  }
//	}
//	webView.addJavascriptInterface(new JavaJs(this), "JavaJs");
//	 
//	 
//	<script type="text/javascript">
//	  JavaJs.showToast("toast from js");
//	</script>

/**
 * 内置浏览器 需要开启联网权限
 * @author ousir
 *
 */

/**
 * 内置浏览器-用外部浏览器打开链接
 */
public class WebActivity extends Activity implements OnClickListener{
	WebView webView;
	Button btn_open;
	Button btn_openother;
	EditText edit_web;
	boolean isLoadUrl=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_web);
		webView=(WebView) findViewById(R.id.web_brower);
		
		btn_open=(Button) findViewById(R.id.btn_open);
		btn_openother=(Button) findViewById(R.id.btn_openother);
		btn_open.setOnClickListener(this);
		edit_web=(EditText) findViewById(R.id.edit_web);
		
		edit_web.setText("https://www.baidu.com");
		btn_openother.setOnClickListener(this);
		
		initwebView();
		webView.loadUrl(edit_web.getText().toString());
	}
	
	private void suppose(){
		
		webView.setWebChromeClient(new WebChromeClient(){
			
			//在Logcat中输出javascript的日志信息
			@Override
			public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
			  Log.d("WebView", consoleMessage.message() + " js line: " + consoleMessage.lineNumber());
			  return true;
			}
			//支持javascript的警告框 alert
			@Override
			public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
			  new AlertDialog.Builder(WebActivity.this)
			      .setTitle("JsAlert")
			      .setMessage(message)
			      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			          result.confirm();
			        }
			      })
			      .setCancelable(false)
			      .show();
			  return true;
			}
			//支持javascript的确认框 confirm
			@Override
			public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
			  new AlertDialog.Builder(WebActivity.this)
			      .setTitle("JsConfirm")
			      .setMessage(message)
			      .setPositiveButton("OK", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			          result.confirm();
			        }
			      })
			      .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			          result.cancel();
			        }
			      })
			      .setCancelable(false)
			      .show();
			  return true;
			}
		});
		
		{
			WebSettings ws=webView.getSettings();
			ws.setJavaScriptEnabled(true);
			//设置为true表示支持使用js打开新的窗口
			ws.setJavaScriptCanOpenWindowsAutomatically(true);
			
			//大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
			ws.setDomStorageEnabled(true);
			
			// 设置为使用webview推荐的窗口 
			ws.setUseWideViewPort(true);
	 		// 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 
			ws.setLoadWithOverviewMode(true);
			// HTML5的地理位置服务,设置为true,启用地理定位 
			ws.setGeolocationEnabled(true);
			/* 提高网页渲染的优先级 */
			ws.setRenderPriority(RenderPriority.HIGH);
			
			//////////////////////////////////////////////
			///缩放相关
			//////////////////////////////////////////////
			
			//使WebView支持通过手势或者缩放控制器来缩放页面,默认是true
			//该设置不影响 WebView.zoomIn()和WebView.zoomOut()
			ws.setSupportZoom(true);
			 
			//设置使用默认的缩放控制器,默认是false
			ws.setBuiltInZoomControls(true);
			 
			//不显示默认的+/-缩放控制View, 默认是true
			ws.setDisplayZoomControls(false);
			
			////////////////////////////////////////
			///加载图片策略相关
			///////////////////////////////////////////
			
			//设置是否自动加载图片,默认是`true`,如果设置为`false`,那么所有图片都不会被加载,包括本地图片.
			ws.setLoadsImagesAutomatically(true);
			 
			//设置是否阻止加载网络图片,默认是`false`,如果设置为`true`,那么网络图片将不会加载.(可以先设置为true,然后再设置为false,来加快页面加载速度)
			ws.setBlockNetworkImage(false);
			 
			//设置是否阻止加载网络资源(不仅仅是图片),默认是`false`,如果设置为`true`,那么网络上的js,css,图片等资源都不会加载
			ws.setBlockNetworkLoads(false);				
			
		}
		/* 设置显示水平滚动条,就是网页右边的滚动条.我这里设置的不显示 */
		webView.setHorizontalScrollBarEnabled(false);
		/* 指定垂直滚动条是否有叠加样式 */
		webView.setVerticalScrollbarOverlay(true);
		/* 设置滚动条的样式 */
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initwebView() {
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
//				edit_web.setText(url);
				return true;
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				edit_web.setText(url);
			}
			
		});	

		
		webView.setOnKeyListener(new View.OnKeyListener() {  
            @Override  
            public boolean onKey(View v, int keyCode, KeyEvent event) {  
                if (event.getAction() == KeyEvent.ACTION_DOWN) {  
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键时的操作
                    	webView.goBack();   //后退  
                        //webview.goForward();//前进
                        return true;    //已处理  
                    }  
                }
                return false;  
            }  
        });		
		
		WebSettings ws=webView.getSettings();
		ws.setJavaScriptEnabled(true);
		//设置为true表示支持使用js打开新的窗口
		ws.setJavaScriptCanOpenWindowsAutomatically(true);
		//大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
		ws.setDomStorageEnabled(true);
		// 设置为使用webview推荐的窗口 
		ws.setUseWideViewPort(true);
 		// 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 
		ws.setLoadWithOverviewMode(true);
		// HTML5的地理位置服务,设置为true,启用地理定位 
		ws.setGeolocationEnabled(true);
		/* 提高网页渲染的优先级 */
		ws.setRenderPriority(RenderPriority.HIGH);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_open:
			webView.loadUrl(edit_web.getText().toString());
			break;
		case R.id.btn_openother:
			Uri uri=Uri.parse(edit_web.getText().toString());
			Intent intent=new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
