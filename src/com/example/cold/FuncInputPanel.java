package com.example.cold;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

public class FuncInputPanel extends LinearLayout {
	private MainActivity mParent;
	private EditText mEditTextFunc;
	private ImageButton mAddFuncButton;
	private Button mOkButton;
	
	public FuncInputPanel(MainActivity acontext) {
		super(acontext);
		initLayout(acontext);
		
		mParent        = acontext;		
		mEditTextFunc  = (EditText) findViewById(R.id.editTextFunc);
		mAddFuncButton = (ImageButton) findViewById(R.id.buttonFuncAdd);
		mOkButton      = (Button) findViewById(R.id.buttonOk);
		
		setUpListeners();
	}
	
	private void setUpListeners() {
		mOkButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				String func = mEditTextFunc.getText().toString();
				ComplexExpression cExpr = new ComplexExpression();
				boolean success = cExpr.parseExpression(func);
				if ( ! success) return;
				try {
					mParent.updateComplexExpression(cExpr);
					mParent.updateViews();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mAddFuncButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showPopup();				
			}
		});		
	}
	
	
	private void initLayout(Context acontext) {
		LayoutInflater inflater = LayoutInflater.from(acontext);
		View layout = inflater.inflate(R.layout.func_input_panel, null);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
					LinearLayout.LayoutParams.WRAP_CONTENT));
		addView(layout);
	}
	
	public void showPopup() {
	    PopupMenu popup = new PopupMenu(mParent, this);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.function_menu, popup.getMenu());
	    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
					case R.id.item_sine:
						insertTextIntoEditText("sin()", 4);
		        		return true;
		        	case R.id.item_cosine:
		        		insertTextIntoEditText("cos()", 4);
		        		return true;
		        	case R.id.item_exp:
		        		insertTextIntoEditText("exp()", 4);
		        		return true;
		        	case R.id.item_log:
		        		insertTextIntoEditText("log()", 4);
		        		return true;
		        	case R.id.item_brackets:
		        		insertTextIntoEditText("()", 1);
		        		return true;
		        	default:
		        		return false;
				}				
			}
		});
	    popup.show();	   
	}
	
	private void insertTextIntoEditText(String textToInsert,int offset) {
		int start = Math.max(mEditTextFunc.getSelectionStart(), 0);
		int end = Math.max(mEditTextFunc.getSelectionEnd(), 0);
		mEditTextFunc.getText().replace(Math.min(start, end), Math.max(start, end),
		        textToInsert, 0, textToInsert.length());
		mEditTextFunc.setSelection(start + offset);
	}
}
