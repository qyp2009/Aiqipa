package com.example.aiqipa;

import com.example.aiqipa.control.DefineButtonMainBottom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity {
	private Fragment curMainFragment = null;
	private int curFragmentIndex = 0 ;
	private NewsFragment newsFragment = null;
	private DefineButtonMainBottom mainButton1=null,mainButton2=null,mainButton3=null,mainButton4=null;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      //添加五个底部按钮的侦听事件，并且触发第一个
        AddDefineButtonMainBottomClickListener();
        selectBottomButtonByIndex(1);
		selectFragmentByIndex(1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	public void AddDefineButtonMainBottomClickListener(){
		mainButton1=(DefineButtonMainBottom)findViewById(R.id.mainButton1);
		mainButton2=(DefineButtonMainBottom)findViewById(R.id.mainButton2);
		mainButton3=(DefineButtonMainBottom)findViewById(R.id.mainButton3);
		mainButton4=(DefineButtonMainBottom)findViewById(R.id.mainButton4);
		mainButton1.setTextViewText("新闻","News");
        mainButton2.setTextViewText("图片","Images");
        mainButton3.setTextViewText("视频","Videos");
        mainButton4.setTextViewText("奇葩圈","QiPa Center");
        
		mainButton1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("main activity bottom button1 selected");
				selectBottomButtonByIndex(1);
				selectFragmentByIndex(0);
			}
			
		});
		
		mainButton2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectBottomButtonByIndex(2);
				selectFragmentByIndex(2);
			}
			
		});
		
		mainButton3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectBottomButtonByIndex(3);
				selectFragmentByIndex(3);
			}
			
		});
		
		mainButton4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectBottomButtonByIndex(4);
				selectFragmentByIndex(4);
			}
			
		});
		
	}
	
	public void selectBottomButtonByIndex(int defineButtonIndex){
		if(defineButtonIndex <1 || defineButtonIndex >4){
			System.out.println("button select error");
			return;
		}
		mainButton1.setTextColor(android.graphics.Color.BLACK);
		mainButton2.setTextColor(android.graphics.Color.BLACK);
		mainButton3.setTextColor(android.graphics.Color.BLACK);
		mainButton4.setTextColor(android.graphics.Color.BLACK);
		switch(defineButtonIndex){
		case 1:
			mainButton1.setTextColor(this.getResources().getColor(R.color.app_default_red));
			break;
		case 2:
			mainButton2.setTextColor(this.getResources().getColor(R.color.app_default_red));
			break;
		case 3:
			mainButton3.setTextColor(this.getResources().getColor(R.color.app_default_red));
			break;
		case 4:
			mainButton4.setTextColor(this.getResources().getColor(R.color.app_default_red));
			break;
		}
	}
	
	public void selectFragmentByIndex(int defineButtonIndex){
		FragmentTransaction transaction = this.getFragmentManager().beginTransaction();
		
		switch(defineButtonIndex){
		case 1:
			if(this.newsFragment == null){
				this.newsFragment = new NewsFragment();
//				Bundle bundle = new Bundle();
//				bundle.putInt(PublicVars.SCREEN_HEIGHT, this.screen_height);
//				bundle.putInt(PublicVars.SCREEN_WIDTH, this.screen_width);
//				this.indexFragment.setArguments(bundle);
				transaction.add(R.id.container, this.newsFragment);
				this.curMainFragment = this.newsFragment;
			}
			if(this.curMainFragment != this.newsFragment ){
				transaction.hide(this.curMainFragment);
				transaction.show(this.newsFragment);
			}
			this.curMainFragment = this.newsFragment;
			this.curFragmentIndex = 1;

			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
			
		}
		transaction.commit();
	}
}
