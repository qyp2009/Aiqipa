package com.example.aiqipa;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.aiqipa.adapter.NewsListAdapter;
import com.example.aiqipa.application.ApplicationController;
import com.example.aiqipa.application.PublicVars;
import com.example.aiqipa.control.XListView;
import com.example.aiqipa.control.XListView.IXListViewListener;
import com.example.aiqipa.model.NewsListCell;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class NewsFragment extends Fragment implements IXListViewListener{
	private final String TAG=NewsFragment.class.getSimpleName();
	private XListView mListView = null;
	private List<NewsListCell> cellList = null;
	private NewsListAdapter mAdapter = null;
	private static int pageNum=1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_news, container, false);
		mListView = (XListView)rootView.findViewById(R.id.xListView_newsList);
		mListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mListView.getContext(),NewsDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString(PublicVars.NEWS_ARTICLE_ID, cellList.get(position - 1).getId());
				intent.putExtras(bundle);
				startActivity(intent);
			}
        	
        });
		cellList = new ArrayList<NewsListCell>();
		mAdapter = new NewsListAdapter(cellList,this.getActivity());
		//this.setListAdapter(mAdapter);
		mListView.setAdapter(mAdapter);
		loadNetworkData(true,1);
		
		return rootView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
		mListView.setPullLoadEnable(true);
		mListView.setXListViewListener(this);
		
		super.onStart();
	}
	
	private void loadNetworkData(final boolean clearOldData,final int pageNum){
    	String url = PublicVars.NEWS_SOURCE_URL + (pageNum<1 ? 1 : pageNum);
    	
    	JsonObjectRequest req = new JsonObjectRequest(url,null,new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject arg0) {
				// TODO Auto-generated method stub
				Log.e(TAG,arg0.toString());
				
				parseListData(arg0,clearOldData);
				onDataLoaded();
			}
		},new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.e(TAG,"error:"+arg0.getMessage());
			}
		});
    	
    	ApplicationController.getInstance().addToRequestQueue(req);
    }
	
	private void parseListData(JSONObject jsonObj,final boolean clearOldData){
		try {
			if(clearOldData){
				cellList.clear();
			}
			JSONArray jsonArray=jsonObj.getJSONObject("data").getJSONArray("data");
			for(int i=0;i<jsonArray.length();i++){
				JSONObject object=jsonArray.getJSONObject(i);
				NewsListCell cell = new NewsListCell();
				cell.setNewsImageUrl(object.getString("cover"));
				cell.setNewsTitle(object.getString("title"));
				cell.setNewsAbstract(object.getString("description"));
				cellList.add(cell);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void onDataLoaded() {
		mAdapter.setList(cellList);
		mAdapter.notifyDataSetChanged();
		
		mListView.stopRefresh();
		mListView.stopLoadMore();
		mListView.setRefreshTime("刚刚");
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		pageNum = 1;
		this.loadNetworkData(true,pageNum);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		pageNum++;
		this.loadNetworkData(false,pageNum);
	}

}
