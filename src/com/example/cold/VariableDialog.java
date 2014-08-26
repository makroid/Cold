package com.example.cold;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;



public class VariableDialog extends DialogFragment {
	
	private VariablesPanel mParent;
	private ComplexVariable cVar;
	private EditText mRealStartEdit;
	private EditText mRealEndEdit;
	private EditText mImagStartEdit;
	private EditText mImagEndEdit;
	private EditText mRealTimeEdit;
	private EditText mImagTimeEdit;
	private CheckBox mRealTimeRevCheck;
	private CheckBox mImagTimeRevCheck;
		
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());	    
	    LayoutInflater inflater = getActivity().getLayoutInflater();	    
	    
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    View view = inflater.inflate(R.layout.variable_edit_dialog, null);
	    mRealStartEdit      = (EditText) view.findViewById(R.id.realStartEdit);
	    mRealEndEdit        = (EditText) view.findViewById(R.id.realEndEdit);
	    mImagStartEdit      = (EditText) view.findViewById(R.id.imagStartEdit);
	    mImagEndEdit        = (EditText) view.findViewById(R.id.imagEndEdit);
	    mRealTimeEdit       = (EditText) view.findViewById(R.id.realTimeEdit);
	    mImagTimeEdit       = (EditText) view.findViewById(R.id.imagTimeEdit);	  
	    mRealTimeRevCheck   = (CheckBox) view.findViewById(R.id.realTimeReverse);
	    mImagTimeRevCheck   = (CheckBox) view.findViewById(R.id.imagTimeReverse);
	    
	    updateComplexVariable();
	    
	    builder.setView(view)
	    	   .setMessage(R.string.edit_variable)
	           .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	   // read current values and check for validity
	            	   String realStart = mRealStartEdit.getText().toString();
	            	   String realEnd   = mRealEndEdit.getText().toString();
	            	   String imagStart = mImagStartEdit.getText().toString();
	            	   String imagEnd   = mImagEndEdit.getText().toString();
	            	   String realTime  = mRealTimeEdit.getText().toString();
	            	   String imagTime  = mImagTimeEdit.getText().toString();
	            	   boolean realRev  = mRealTimeRevCheck.isChecked();
	            	   boolean imagRev  = mImagTimeRevCheck.isChecked();
	            	   
	            	   if (Utils.isFloat(realStart) && Utils.isFloat(realEnd)) {
	            		   float rs = Float.parseFloat(realStart);
	            		   float re = Float.parseFloat(realEnd);
	            		   if (rs <= re) {
	            			   cVar.setLowerRe(rs);
	            			   cVar.setUpperRe(re);
	            			   cVar.setReal((re+rs)/2.0f);	            			  
	            		   }
	            	   }
	            	   if (Utils.isFloat(imagStart) && Utils.isFloat(imagEnd)) {
	            		   float rs = Float.parseFloat(imagStart);
	            		   float re = Float.parseFloat(imagEnd);
	            		   if (rs <= re) {
	            			   cVar.setLowerIm(rs);
	            			   cVar.setUpperIm(re);
	            			   cVar.setImag((re+rs)/2.0f);
	            		   }
	            	   }
	            	   if (Utils.isInteger(realTime)) {
	            		   long time = Long.parseLong(realTime);
	            		   cVar.getAnimator().setDurationRe(time);
	            	   }
	            	   if (Utils.isInteger(imagTime)) {
	            		   long time = Long.parseLong(imagTime);
	            		   cVar.getAnimator().setDurationIm(time);
	            	   }
	            	   
	            	   cVar.getAnimator().setReverseRe(realRev);
	            	   cVar.getAnimator().setReverseIm(imagRev);
	            	   
	            	   mParent.doPositiveEditClick();
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   VariableDialog.this.getDialog().cancel();
	               }
	           }); 	    	   
	   	  
	 	return builder.create();
	}
	
	public void setVariable(ComplexVariable c) {
		cVar = c;
	}
	
	public void setParent(VariablesPanel p) {
		mParent = p;
	}
	
	private void updateComplexVariable() {
		mRealStartEdit.setText(String.valueOf(cVar.getLowerRe()));
		mRealEndEdit.setText(String.valueOf(cVar.getUpperRe()));
		mImagStartEdit.setText(String.valueOf(cVar.getLowerIm()));
		mImagEndEdit.setText(String.valueOf(cVar.getUpperIm()));
		mRealTimeEdit.setText(String.valueOf(cVar.getAnimator().getDurationRe()));
		mImagTimeEdit.setText(String.valueOf(cVar.getAnimator().getDurationIm()));	
		
		mRealTimeRevCheck.setChecked(cVar.getAnimator().getReverseRe());
		mImagTimeRevCheck.setChecked(cVar.getAnimator().getReverseIm());
		
		// set cursor position to end
		mRealStartEdit.setSelection(mRealStartEdit.getText().length());
		mRealEndEdit.setSelection(mRealEndEdit.getText().length());
		mRealTimeEdit.setSelection(mRealTimeEdit.getText().length());
		
		mImagStartEdit.setSelection(mImagStartEdit.getText().length());
		mImagEndEdit.setSelection(mImagEndEdit.getText().length());
		mImagTimeEdit.setSelection(mImagTimeEdit.getText().length());
	}
	
//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		try { 
//			mParent = activity;
//		} catch (ClassCastException e) {
//			throw new ClassCastException(activity.toString()
//					+ " invalid parent");
//		}
//	}
	
	
}
