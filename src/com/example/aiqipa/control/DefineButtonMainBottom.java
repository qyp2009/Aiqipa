package com.example.aiqipa.control;


import com.example.aiqipa.R;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DefineButtonMainBottom extends LinearLayout {
	private TextView textViewUp;
	private TextView textViewDown;
	private Typeface typeFace;
	
	public DefineButtonMainBottom(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public DefineButtonMainBottom(Context context,AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_main_bottom, this);
        textViewUp	=(TextView)findViewById(R.id.textView1);
        textViewDown=(TextView)findViewById(R.id.textView2);
//        if (!this.isInEditMode()) { 
//	        typeFace	=Typeface.createFromAsset(getContext().getAssets(),"fonts/fzzzh_gbk.ttf");
//	        textViewUp.setTypeface(typeFace);
//	        textViewDown.setTypeface(typeFace);
//        }
	}
	
	public void setTextViewText(String textUp,String textDown) { 
        textViewUp.setText(textUp);
        textViewDown.setText(textDown);
    }
	
	public void setTextColor(int color){
		textViewUp.setTextColor(color);
		textViewDown.setTextColor(color);
	}
}
