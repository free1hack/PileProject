package com.calcu1;


import com.calcu1.common.ComplexNumber;
import com.calcu1.common.ObjEmp;

public class ObjEmpComplex implements ObjEmp<ComplexNumber> {

	private ComplexNumber value;

	public ObjEmpComplex(ComplexNumber value) {
		this.value = value;
	}

	@Override
	public ObjEmp<ComplexNumber> add(ObjEmp<ComplexNumber> objEmp) {
		return new ObjEmpComplex(
				new ComplexNumber(
						this.getValue().getxReal() + objEmp.getValue().getxReal(),
						this.getValue().getyImg() + objEmp.getValue().getyImg()));
	}

	@Override
	public ObjEmp<ComplexNumber> substract(ObjEmp<ComplexNumber> objEmp) {
		return new ObjEmpComplex(
				new ComplexNumber(
						this.getValue().getxReal() - objEmp.getValue().getxReal(),
						this.getValue().getyImg() - objEmp.getValue().getyImg()));
	}

	@Override
	public ObjEmp<ComplexNumber> multiply(ObjEmp<ComplexNumber> objEmp) {
		return new ObjEmpComplex(
				new ComplexNumber(
						this.getValue().getxReal()*objEmp.getValue().getxReal() - this.getValue().getyImg()*objEmp.getValue().getyImg(),
						this.getValue().getxReal()*objEmp.getValue().getyImg() + this.getValue().getyImg()*objEmp.getValue().getxReal()));
	}

	@Override
	public ObjEmp<ComplexNumber> divide(ObjEmp<ComplexNumber> objEmp) {
		Double divisor = objEmp.getValue().module();
		objEmp.getValue().complexBar();

		ObjEmp<ComplexNumber> mult = this.multiply(objEmp);
		return new ObjEmpComplex(
				new ComplexNumber(
						mult.getValue().getxReal()/divisor,
						mult.getValue().getyImg()/divisor));
	}

	@Override
	public ComplexNumber getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void setValue(ComplexNumber value) {
		this.value = value;
	}

	@Override
	public String printValue() {
		return value.toString();
	}

	@Override
	public boolean isZero() {
		return value.getxReal() == 0 && value.getyImg() == 0;
	}

}
/*
	push(2)		[2]
	push(3i)	[2,3i]
	ADD			[2+3i]

	a + ib   (a+ib)(c-id)
	------ = ------------
	c + id	 c^2 + d^2

	(a+ib)(c+id) = ac + i(ad+bc) -bd
*/