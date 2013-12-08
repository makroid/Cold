package com.example.cold;

public class ComplexVariable {
	
	public String name;
	public float real;
	public float imag;

	private float lowerRe;
	private float upperRe;
	private float lowerIm;
	private float upperIm;	
	
	public ComplexVariable(String aname, float areal, float aimag) {
		name = aname;
		real = areal;
		imag = aimag;
		setTightBounds(real, imag);
	}
	
	private void setTightBounds(float defRe, float defIm) {
		lowerRe = defRe;
		upperRe = defRe;
		lowerIm = defIm;
		upperIm = defIm;
	}
		
	public float getLowerRe() {
		return lowerRe;
	}

	public void setLowerRe(float lowerRe) {
		this.lowerRe = lowerRe;
	}

	public float getUpperRe() {
		return upperRe;
	}

	public void setUpperRe(float upperRe) {
		this.upperRe = upperRe;
	}

	public float getLowerIm() {
		return lowerIm;
	}

	public void setLowerIm(float lowerIm) {
		this.lowerIm = lowerIm;
	}

	public float getUpperIm() {
		return upperIm;
	}

	public void setUpperIm(float upperIm) {
		this.upperIm = upperIm;
	}

}
