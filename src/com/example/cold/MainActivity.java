package com.example.cold;


import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
		getMenuInflater().inflate(R.menu.main, menu);
		getActionBar().setDisplayShowTitleEnabled(false);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_save) {
			generateSaveDialog();
			return true;
		} else if (id == R.id.action_load) {
			generateLoadDialog();
		}
		return super.onOptionsItemSelected(item);
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
	
	@Override
	public void onStop() {
		super.onStop();
		SharedPreferences.Editor editor = pref.edit();
	    editor.putBoolean("pref_fullScreen", false);
	    editor.commit();
	}
	
	
	public void updateComplexExpression(ComplexExpression newExpr) {
		if (cExpr != null) {
			newExpr.takeOverVariablesFrom(cExpr);
		}
		cExpr = newExpr;
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
		checkPrefColoring();
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences pref, String key) {
		// TODO Auto-generated method stub
		if (key.equals("pref_coloring")) {
			checkPrefColoring();
		} else if (key.equals("pref_fullScreen")) {
			checkPrefFullScreen();
		} else if (key.equals("pref_maxIterations")) {
			int maxIterations = pref.getInt("pref_maxIterations", 20);
			cExpr.setMaxIterations(maxIterations);
			mColdView.requestRender();
		}
	}
	
	private void checkPrefColoring() {
		String coloring = pref.getString("pref_coloring", "default");
		if (Utils.isInteger(coloring)) {
		//	System.out.println("coloring=" + coloring);
			int col = Integer.parseInt(coloring);
			mColdView.setColoring(ColdSettings.Coloring.values()[col]);
		}
		mColdView.requestRender();
	}
	
	private void checkPrefFullScreen() {
		boolean doShowFullScreen = pref.getBoolean("pref_fullScreen", false);
		if (doShowFullScreen) {
			mFuncInputPanel.setVisibility(View.GONE);
			mVarPanel.setVisibility(View.GONE);
		} else {
			mFuncInputPanel.setVisibility(View.VISIBLE);
			mVarPanel.setVisibility(View.VISIBLE);
		}
		updateColdView();
		mColdView.requestRender();	
	}
	
	private void generateSaveDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Save");

		// Set up the input
		final EditText labelEditText = new EditText(this);
		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
		labelEditText.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(labelEditText);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	String funcLabel = labelEditText.getText().toString().trim();
		    	if (funcLabel.length() <= 0) return;
		    	if (cExpr.getExpression().length() <= 0) return;
		    	try {
					String jsonExpr = cExpr.writeJson(mFuncInputPanel.getEditTextFunc());
					System.out.println("jsonExpr=" + jsonExpr);
					SharedPreferences.Editor editor = pref.edit();
					
					String key = ColdSettings.SAVEPREFIX + funcLabel;
					editor.putString(key, jsonExpr);
					editor.commit();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        dialog.cancel();
		    }
		});

		builder.show();
	}
	
	private void generateLoadDialog() {
		// collect all saved expressions
		Set<String> allKeys = pref.getAll().keySet();
		
		int numFuncLabels = 0;
		for (String key : allKeys) {
			if (key.startsWith(ColdSettings.SAVEPREFIX)) {
				numFuncLabels++;
			}
		}
		final CharSequence labels[] = new CharSequence[numFuncLabels];
		numFuncLabels = 0;
		for (String key : allKeys) {
			if (key.startsWith(ColdSettings.SAVEPREFIX)) {
				labels[numFuncLabels] = key.substring(ColdSettings.SAVEPREFIX.length(), key.length());
				numFuncLabels++;
			}
		}
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Load Expression");
		
		builder.setItems(labels, new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog, int pos) {
		    	String key = ColdSettings.SAVEPREFIX + labels[pos];
		    	String savedExpression = pref.getString(key, "dummy");
		    	System.out.println("savedExpression=" + savedExpression);
		    	if ( ! savedExpression.equals("dummy")) {
		    		try {
						loadExpression(savedExpression);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    }
		});
		AlertDialog loadDialog = builder.create();

		loadDialog.show();
	}
	
	private void loadExpression(String savedExpression) throws IOException {
		cExpr.initFromJson(new StringBufferInputStream(savedExpression));
		mFuncInputPanel.setEditTextFunc(cExpr.getUserExpression());
		updateViews();
	}
}
