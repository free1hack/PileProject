package com.calcu1.common;

public class ComplexNumber {

	private Double xReal;
	private Double yImg;

	public ComplexNumber(Double xReal, Double yImg) {
		super();
		this.xReal = xReal;
		this.yImg = yImg;
	}
	public Double getxReal() {
		return xReal;
	}
	public void setxReal(Double xReal) {
		this.xReal = xReal;
	}
	public Double getyImg() {
		return yImg;
	}
	public void setyImg(Double yImg) {
		this.yImg = yImg;
	}

	public Double module() {
		return xReal*xReal + yImg*yImg;
	}

	public ComplexNumber complexBar() {
		this.yImg*=-1;
		return this;
	}
	@Override
	public String toString() {
		if(yImg == null || yImg == 0) {
			return xReal.toString();
		}
		if(xReal == null || xReal == 0) {
			return yImg.toString().concat("i");
		}
		return xReal.toString()
				.concat(" + ")
				.concat(yImg.toString())
				.concat("i");
	}

}
