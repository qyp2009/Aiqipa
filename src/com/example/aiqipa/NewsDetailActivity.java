package com.example.aiqipa;

import com.example.aiqipa.application.PublicVars;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsDetailActivity extends Activity{
	private String articleID ="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_detail);
		
		this.loadInitParams();
		WebView webView = (WebView) this.findViewById(R.id.webView_newsDetail);
		webView.loadUrl(PublicVars.NEWS_ARTICLE_URL+PublicVars.DEFAULT_NEWS_ARTICLE_ID);
	}
	
	private void loadInitParams(){
		Bundle bundle = this.getIntent().getExtras();
		this.articleID = bundle.getString(PublicVars.NEWS_ARTICLE_ID);
	}

}
