package com.example.csdnscaner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class NewsDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsdetails);
		Button commentButton=(Button) findViewById(R.id.newsdetails_titlebar_comments);
		commentButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(NewsDetailsActivity.this,CommentActivity.class);
				startActivity(i);
			}
		});
		Intent comeIntent=getIntent();
		String uriString=comeIntent.getStringExtra("uriString");
		String titleString=comeIntent.getStringExtra("itemTitle");
		TextView tiTextView=(TextView) findViewById(R.id.newsdetails_titlebar_title);
		tiTextView.setText(titleString);
		WebView webView=(WebView) findViewById(R.id.news_body_flipper);
		  webView.getSettings().setJavaScriptEnabled(true);  
	        //加载需要显示的网页  
	        webView.loadUrl(uriString);  
	        //设置Web视图  
	        webView.setWebViewClient(new HelloWebViewClient ());  
	}
	private class HelloWebViewClient extends WebViewClient {  
        @Override 
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            view.loadUrl(url);  
            return true;  
        } }
}
