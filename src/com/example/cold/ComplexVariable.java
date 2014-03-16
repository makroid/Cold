package com.example.cold;

public class ComplexVariable {
	
	public String name;
	private float real;
	private float imag;

	private float lowerRe;
	private float upperRe;
	private float lowerIm;
	private float upperIm;	
	
	private ComplexAnimator animator;
	
	public ComplexVariable(String name, float real, float imag) {
		this.name = name;
		this.real = real;
		this.imag = imag;
		
		animator = new ComplexAnimator(this);
		setDefaultBounds(real, imag);	
	}
	
	public void setReal(float real) {
		this.real = real;
	}
	
	public float getReal() {
		return this.real;
	}
	
	public void setImag(float imag) {
		this.imag = imag;
	}
	
	public float getImag() {
		return this.imag;
	}
	
	private void setDefaultBounds(float defRe, float defIm) {
		float offset = 10.0f;
		this.setLowerRe(defRe - offset);
		this.setUpperRe(defRe + offset);
		this.setLowerIm(defIm - offset);
		this.setUpperIm(defIm + offset);
	}
		
	public float getLowerRe() {
		return lowerRe;
	}

	public void setLowerRe(float lowerRe) {
		this.lowerRe = lowerRe;
		updateAnimatorRe();
	}

	public float getUpperRe() {
		return upperRe;
	}

	public void setUpperRe(float upperRe) {
		this.upperRe = upperRe;
		updateAnimatorRe();
	}

	public float getLowerIm() {
		return lowerIm;
	}

	public void setLowerIm(float lowerIm) {
		this.lowerIm = lowerIm;
		updateAnimatorIm();
	}

	public float getUpperIm() {
		return upperIm;
	}

	public void setUpperIm(float upperIm) {
		this.upperIm = upperIm;
		updateAnimatorIm();
	}
	
	private void updateAnimatorRe() {
		animator.animatorRe.setFloatValues(this.lowerRe, this.upperRe);
	}
	
	private void updateAnimatorIm() {
		animator.animatorIm.setFloatValues(this.lowerIm, this.upperIm);
	}
	
	public ComplexAnimator getAnimator() {
		return animator;
	}

	public void updateAll(ComplexVariable otherVar) {
		setReal(otherVar.getReal());
		setImag(otherVar.getImag());
		setLowerRe(otherVar.getLowerRe());
		setUpperRe(otherVar.getUpperRe());
		setLowerIm(otherVar.getLowerIm());
		setUpperIm(otherVar.getUpperIm());
		animator.setReverseRe(otherVar.animator.getReverseRe());
		animator.setReverseIm(otherVar.animator.getReverseIm());
		animator.setDurationRe(otherVar.animator.getDurationRe());
		animator.setDurationIm(otherVar.animator.getDurationIm());
	}
	
}
