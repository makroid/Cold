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
import android.widget.ImageButton;
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
	private ImageButton mAnimReButton;
	private ImageButton mAnimImButton;
	private ComplexExpression cExpr = null;
	private ComplexAnimatorControl mAnimatorControl;
	
	public VariablesPanel(MainActivity acontext) {
		super(acontext);
		initLayout(acontext);	
		
		mParent = acontext;
		mVarSpinner = (Spinner) findViewById(R.id.spinnerVar);
		mSeekbarRe = (SeekBar) findViewById(R.id.seekBarRe);
		mSeekbarIm = (SeekBar) findViewById(R.id.seekBarIm);
		mAnimReButton = (ImageButton) findViewById(R.id.buttonAnimationRe);
		mAnimImButton = (ImageButton) findViewById(R.id.buttonAnimationIm);
		mEditButton = (Button) findViewById(R.id.buttonEditVar);
	
		mAnimatorControl = new ComplexAnimatorControl(cExpr, this);
		
		setUpListeners();
	}
	
	private void setUpListeners() {
		mVarSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (cExpr==null)  return;
				
				updateProgressRe();
				updateProgressIm();
				
				// update play/pause button and seekbars of current active variable
				if (cExpr.getActiveVariable().getAnimator().animatorRe.isStarted()) {
					mAnimReButton.setImageResource(android.R.drawable.ic_media_pause);
					mSeekbarRe.setEnabled(false);
				} else {
					mAnimReButton.setImageResource(android.R.drawable.ic_media_play);
					mSeekbarRe.setEnabled(true);
				}
				
				if (cExpr.getActiveVariable().getAnimator().animatorIm.isStarted()) {
					mAnimImButton.setImageResource(android.R.drawable.ic_media_pause);
					mSeekbarIm.setEnabled(false);
				} else {
					mAnimImButton.setImageResource(android.R.drawable.ic_media_play);
					mSeekbarIm.setEnabled(true);
				}
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
				activeVar.setReal(val);
				redrawColdView();
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
				activeVar.setImag(val);
				redrawColdView();
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
				vDialog.setParent(VariablesPanel.this);
				vDialog.setVariable(curVar);
				vDialog.show(mParent.getFragmentManager().beginTransaction(), "");
			}
		});
		
		mAnimReButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (cExpr==null) return;
				if (cExpr.getActiveVariable() == null) return;
				if (cExpr.getActiveVariable().getAnimator().animatorRe.isStarted()) {
					cExpr.getActiveVariable().getAnimator().animatorRe.cancel();
					System.out.println("at end cvar=" + cExpr.getActiveVariable().getReal());
					System.out.println("at end cvar=" + cExpr.getActiveVariable().getImag());
					mAnimReButton.setImageResource(android.R.drawable.ic_media_play);
					mSeekbarRe.setEnabled(true);
					updateProgressRe();
				} else {
					cExpr.getActiveVariable().getAnimator().animatorRe.start();
					mAnimReButton.setImageResource(android.R.drawable.ic_media_pause);
					System.out.println("at start cvar=" + cExpr.getActiveVariable().getReal());
					System.out.println("at start cvar=" + cExpr.getActiveVariable().getImag());
					mSeekbarRe.setEnabled(false);
				}
			}
		});
		
		mAnimImButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (cExpr==null) return;
				if (cExpr.getActiveVariable() == null) return;
				if (cExpr.getActiveVariable().getAnimator().animatorIm.isStarted()) {
					cExpr.getActiveVariable().getAnimator().animatorIm.cancel();
					mAnimImButton.setImageResource(android.R.drawable.ic_media_play);
					System.out.println("at end cvar=" + cExpr.getActiveVariable().getReal());
					System.out.println("at end cvar=" + cExpr.getActiveVariable().getImag());
					mSeekbarIm.setEnabled(true);
					updateProgressIm();
				} else {
					cExpr.getActiveVariable().getAnimator().animatorIm.start();
					mAnimImButton.setImageResource(android.R.drawable.ic_media_pause);
					System.out.println("at start cvar=" + cExpr.getActiveVariable().getReal());
					System.out.println("at start cvar=" + cExpr.getActiveVariable().getImag());
					mSeekbarIm.setEnabled(false);
				}
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
	
	public void doPositiveEditClick() {
		updateProgressRe();
		updateProgressIm();
	}
	
	public void updateComplexExpression(ComplexExpression acExpr) {
		cExpr = acExpr;
		List<String> list = new ArrayList<String>();
		for (ComplexVariable cvar : cExpr.getVariables().values()) {
			list.add(cvar.name);
			System.out.println("cVar real=" + cvar.getReal());
			System.out.println("cVar imag=" + cvar.getImag());
		}
		// fill spinner with variables
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mParent, android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mVarSpinner.setAdapter(dataAdapter);
		if (list.size() > 0) {
			mVarSpinner.setSelection(0);
		}
		mAnimatorControl.updateComplexExpression(cExpr);
	}
	
	public void redrawColdView() {
		mParent.redrawColdView();
	}

	private int realToProgress(ComplexVariable cVar) {
		return (int) (1.0/(cVar.getUpperRe() - cVar.getLowerRe())*(cVar.getReal()-cVar.getLowerRe())*mSeekbarRe.getMax());
	}
	
	private int imagToProgress(ComplexVariable cVar) {
		return (int) (1.0/(cVar.getUpperIm() - cVar.getLowerIm())*(cVar.getImag()-cVar.getLowerIm())*mSeekbarIm.getMax());
	}
	
	private float progressToReal(ComplexVariable cVar, int progress) {
		return (float) (1.0/mSeekbarRe.getMax()* progress * (cVar.getUpperRe()-cVar.getLowerRe()) + cVar.getLowerRe());
	}
	
	private float progressToImag(ComplexVariable cVar, int progress) {
		return (float) (1.0/mSeekbarIm.getMax()*progress * (cVar.getUpperIm()-cVar.getLowerIm()) + cVar.getLowerIm());
	}
	
	private void updateProgressRe() {
		String curVarStr = mVarSpinner.getSelectedItem().toString();
		cExpr.setActiveVariable(curVarStr);
		ComplexVariable cVar = cExpr.getVariable(curVarStr);
		int progress = realToProgress(cVar);
		mSeekbarRe.setProgress(progress);
	}
	
	private void updateProgressIm() {
		String curVarStr = mVarSpinner.getSelectedItem().toString();
		cExpr.setActiveVariable(curVarStr);
		ComplexVariable cVar = cExpr.getVariable(curVarStr);
		int progress = realToProgress(cVar);
		mSeekbarIm.setProgress(progress);
	}
}
