package com.example.aiqipa.adapter;

import java.util.List;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.aiqipa.R;
import com.example.aiqipa.application.ApplicationController;
import com.example.aiqipa.model.NewsListCell;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class NewsListAdapter extends BaseAdapter {
	private List<NewsListCell> list;
	private Context context;
	
	public NewsListAdapter(List<NewsListCell> list,Context context){
		super();
		this.list = list;
		this.context = context;
	}
	
	public List<NewsListCell> getList() {
		return list;
	}


	public void setList(List<NewsListCell> list) {
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint("InflateParams") @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView ==null){
        	convertView = LayoutInflater.from(context).inflate(R.layout.news_item,null);
		}
		
		ImageLoader imageLoader = ApplicationController.getInstance().getImageLoader();
		
		NetworkImageView newsImage=(NetworkImageView)convertView.findViewById(R.id.networkImageView_newsImage);
		TextView newsTitle=(TextView)convertView.findViewById(R.id.textView_newsTitle);
		TextView newsAbstract=(TextView)convertView.findViewById(R.id.textView_newsAbstract);
		
		newsImage.setImageUrl(list.get(position).getNewsImageUrl(), imageLoader);
		newsTitle.setText(list.get(position).getNewsTitle());
		newsAbstract.setText(list.get(position).getNewsAbstract());
		
		return convertView;
	}
}
