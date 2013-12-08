package com.example.cold;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity implements OnSharedPreferenceChangeListener {

	private LinearLayout layout;
	private ColdView mColdView;
	private FuncInputPanel mFuncInputPanel;
	private VariablesPanel mVarPanel;
	
	SharedPreferences pref;
	
	private ComplexExpression cExpr = new ComplexExpression(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mColdView       = new ColdView(this, cExpr);
		mFuncInputPanel = new FuncInputPanel(this);
		mVarPanel       = new VariablesPanel(this);
		
		layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));		
		layout.addView(mFuncInputPanel);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
		layout.addView(mColdView, p);
		layout.addView(mVarPanel);
		
		setContentView(layout);
		
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		pref.registerOnSharedPreferenceChangeListener(this);
		
		restoreSettings();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actionbar_menu, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		return true;
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mColdView != null) {
			mColdView.onPause();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mColdView != null) {
			mColdView.onResume();
		}
	}	
	
	public void updateComplexExpression(ComplexExpression expr) {
		cExpr = expr;
	}
	
	public void updateViews() {
		updateColdView();
		updateVariablesView();
	}
	
	private void updateColdView() {
		ViewGroup parent = (ViewGroup) mColdView.getParent();
		int index = parent.indexOfChild(mColdView);
		parent.removeView(mColdView);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
		mColdView = new ColdView(this, cExpr);
		parent.addView(mColdView, index, p);
		restoreSettings();
	}
	
	private void updateVariablesView() {
		mVarPanel.updateComplexExpression(cExpr);
	}
	
	public void redrawColdView() {
		mColdView.requestRender();
	}
	
	public boolean onClickSettings(MenuItem item) {
		Intent myIntent = new Intent(getBaseContext(), SettingsActivity.class);
		startActivity(myIntent);
		return true;
	}
	
	private void restoreSettings() {
		boolean doBlackWhiteColoring = pref.getBoolean("pref_blackWhiteColoring", false);
		if (doBlackWhiteColoring) {
			mColdView.setColoring(ColdSettings.Coloring.blackwhite);
		} else {
			mColdView.setColoring(ColdSettings.Coloring.standard);
		}
		mColdView.requestRender();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences pref, String key) {
		// TODO Auto-generated method stub
		if (key.equals("pref_blackWhiteColoring")) {
			boolean doBlackWhiteColoring = pref.getBoolean("pref_blackWhiteColoring", false);
			if (doBlackWhiteColoring) {
				mColdView.setColoring(ColdSettings.Coloring.blackwhite);
			} else {
				mColdView.setColoring(ColdSettings.Coloring.standard);
			}
			mColdView.requestRender();
		}
	}
}
