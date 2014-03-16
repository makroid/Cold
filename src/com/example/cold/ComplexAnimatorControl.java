package com.example.cold;

import java.util.HashMap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

public class ComplexAnimatorControl {
	private ComplexExpression cExpr;
	private VariablesPanel vPanel;
	
	
	public ComplexAnimatorControl(ComplexExpression cExpr, VariablesPanel vPanel) {
		this.cExpr = cExpr;
		this.vPanel = vPanel;
		
		setupListeners();
	}
	
	private void setupListeners() {
		
		if (cExpr == null) return;
		
		HashMap<String, ComplexVariable> variables = cExpr.getVariables();
		
		for (ComplexVariable cVar : variables.values()) {
			cVar.getAnimator().animatorRe.removeAllListeners();
			cVar.getAnimator().animatorRe.removeAllUpdateListeners();
			cVar.getAnimator().animatorIm.removeAllListeners();
			cVar.getAnimator().animatorIm.removeAllUpdateListeners();
		}
		
		for (ComplexVariable cVar : variables.values()) {
			// listeners for real part
			cVar.getAnimator().animatorRe.addListener(new AnimatorListenerAdapter() {
				public void onAnimationStart(Animator animation) {
					
				}
				public void onAnimationEnd(Animator animation) {
					
				}
			});
			cVar.getAnimator().animatorRe.addUpdateListener(new AnimatorUpdateListener() {				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					vPanel.redrawColdView();
				}
			});
			
			// listeners for imaginary part
			cVar.getAnimator().animatorIm.addListener(new AnimatorListenerAdapter() {
				public void onAnimationStart(Animator animation) {
					
				}
				public void onAnimationEnd(Animator animation) {
					
				}
			});
			cVar.getAnimator().animatorIm.addUpdateListener(new AnimatorUpdateListener() {				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					vPanel.redrawColdView();
				}
			});
		}
	}
	
	public void updateComplexExpression(ComplexExpression cExpr) {
		this.cExpr = cExpr;
		setupListeners();
	}
	
}
