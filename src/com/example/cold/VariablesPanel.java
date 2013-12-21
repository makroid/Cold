package com.example.cold;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;

public class VariablesPanel extends LinearLayout {
	private MainActivity mParent;
	private Spinner mVarSpinner;
	private SeekBar mSeekbarRe;
	private SeekBar mSeekbarIm;
	private Button mEditButton;
	private ComplexExpression cExpr = null;
	
	public VariablesPanel(MainActivity acontext) {
		super(acontext);
		initLayout(acontext);	
		
		mParent = acontext;
		mVarSpinner = (Spinner) findViewById(R.id.spinnerVar);
		mSeekbarRe = (SeekBar) findViewById(R.id.seekBarRe);
		mSeekbarIm = (SeekBar) findViewById(R.id.seekBarIm);
		mEditButton = (Button) findViewById(R.id.buttonEditVar);
	
		setUpListeners();
	}
	
	private void setUpListeners() {
		mVarSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (cExpr==null)  return;
				String curVarStr = mVarSpinner.getSelectedItem().toString();
				cExpr.setActiveVariable(curVarStr);
				ComplexVariable cVar = cExpr.getVariable(curVarStr);
				int progress = realToProgress(cVar);
				mSeekbarRe.setProgress(progress);
				progress = imagToProgress(cVar);
				mSeekbarIm.setProgress(progress);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		mSeekbarRe.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				if (cExpr == null) return;
				ComplexVariable activeVar = cExpr.getActiveVariable();
				if (activeVar == null) return;
				float val = progressToReal(activeVar, arg1);
				activeVar.real = val;
				mParent.redrawColdView();
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}			
		});
		
		mSeekbarIm.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				if (cExpr == null) return;
				ComplexVariable activeVar = cExpr.getActiveVariable();
				if (activeVar == null) return;
				float val = progressToImag(activeVar, arg1);
				activeVar.imag = val;
				mParent.redrawColdView();
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mEditButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (cExpr==null)  return;
				ComplexVariable curVar = cExpr.getActiveVariable();
				if (curVar == null) return;
				VariableDialog vDialog = new VariableDialog();
				vDialog.setVariable(curVar);
				vDialog.show(mParent.getFragmentManager().beginTransaction(), "");
			}
		});
	}
	
	private void initLayout(Context acontext) {
		LayoutInflater inflater = LayoutInflater.from(acontext);
		View layout = inflater.inflate(R.layout.variables_panel, null);
		layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT));
		addView(layout);
	}
	
	public void updateComplexExpression(ComplexExpression acExpr) {
		cExpr = acExpr;
		List<String> list = new ArrayList<String>();
		for (ComplexVariable cvar : cExpr.getVariables().values()) {
			list.add(cvar.name);
			cvar.setLowerRe(-15);
			cvar.setUpperRe(15);
			cvar.setLowerIm(-15);
			cvar.setUpperIm(15);
			cvar.real = 1;
			cvar.imag = 0;
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mParent, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mVarSpinner.setAdapter(dataAdapter);
		if (list.size() > 0) {
			mVarSpinner.setSelection(0);
		}
	}

	private int realToProgress(ComplexVariable cVar) {
		return (int) (1.0/(cVar.getUpperRe() - cVar.getLowerRe())*(cVar.real-cVar.getLowerRe())*mSeekbarRe.getMax());
	}
	
	private int imagToProgress(ComplexVariable cVar) {
		return (int) (1.0/(cVar.getUpperIm() - cVar.getLowerIm())*(cVar.imag-cVar.getLowerIm())*mSeekbarIm.getMax());
	}
	
	private float progressToReal(ComplexVariable cVar, int progress) {
		return (float) (1.0/mSeekbarRe.getMax()* progress * (cVar.getUpperRe()-cVar.getLowerRe()) + cVar.getLowerRe());
	}
	
	private float progressToImag(ComplexVariable cVar, int progress) {
		return (float) (1.0/mSeekbarIm.getMax()*progress * (cVar.getUpperIm()-cVar.getLowerIm()) + cVar.getLowerIm());
	}
}
