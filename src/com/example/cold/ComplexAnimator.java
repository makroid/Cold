package com.example.cold;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

public class ComplexAnimator {
	private ComplexVariable var;
	
	public ValueAnimator animatorRe;
	public ValueAnimator animatorIm;
	private long animatorDurationRe;
	private long animatorDurationIm;
	
	public ComplexAnimator(ComplexVariable var) {
		System.out.println("in constructor");
		this.var = var;	
		
		animatorRe = ObjectAnimator.ofFloat(this.var, "real", 0.0f, 1.0f);
		animatorIm = ObjectAnimator.ofFloat(this.var, "imag", 0.0f, 1.0f);
		
		System.out.println("after ofFloat");
		
		animatorRe.setInterpolator(new LinearInterpolator());
		animatorIm.setInterpolator(new LinearInterpolator());
		
		animatorRe.setRepeatCount(ValueAnimator.INFINITE);
		animatorRe.setRepeatMode(ValueAnimator.RESTART);		
		animatorIm.setRepeatCount(ValueAnimator.INFINITE);
		animatorIm.setRepeatMode(ValueAnimator.RESTART);
		
		animatorDurationRe = 1000;
		animatorDurationIm = 1000;
		
		setDurationRe(animatorDurationRe);
		setDurationIm(animatorDurationIm);
	}
	
	public void setDurationRe(long dur) {
		animatorDurationRe = dur;
		animatorRe.setDuration(dur);
	}
	
	public void setDurationIm(long dur) {
		animatorDurationIm = dur;
		animatorIm.setDuration(dur);
	}
	
	public long getDurationRe() {		
		return animatorDurationRe;
	}
	
	public long getDurationIm() {
		return animatorDurationIm;
	}
	
	public void setReverseRe(boolean doReverse) {
		if (doReverse) {
			animatorRe.setRepeatMode(ValueAnimator.REVERSE);
		} else {
			animatorRe.setRepeatMode(ValueAnimator.RESTART);
		}
	}
	
	public void setReverseIm(boolean doReverse) {
		if (doReverse) {
			animatorIm.setRepeatMode(ValueAnimator.REVERSE);
		} else {
			animatorIm.setRepeatMode(ValueAnimator.RESTART);
		}
	}
	
	public boolean getReverseRe() {
		if (animatorRe.getRepeatMode() == ValueAnimator.REVERSE) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean getReverseIm() {
		if (animatorIm.getRepeatMode() == ValueAnimator.REVERSE) {
			return true;
		} else {
			return false;
		}
	}
}
