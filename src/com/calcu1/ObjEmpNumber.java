package com.calcu1;


import com.calcu1.common.ObjEmp;

public class ObjEmpNumber implements ObjEmp<Double>{

	private Double value;

	public ObjEmpNumber(Double value) {
		setValue(value);
	}

	@Override
	public ObjEmp<Double> add(ObjEmp<Double> objEmp) {
		return new ObjEmpNumber(this.getValue() + objEmp.getValue());
	}

	@Override
	public ObjEmp<Double> substract(ObjEmp<Double> objEmp) {
		return new ObjEmpNumber(this.getValue() - objEmp.getValue());
	}

	@Override
	public ObjEmp<Double> multiply(ObjEmp<Double> objEmp) {
		return new ObjEmpNumber(this.getValue() * objEmp.getValue());
	}

	@Override
	public ObjEmp<Double> divide(ObjEmp<Double> objEmp) {
		return new ObjEmpNumber(this.getValue() / objEmp.getValue());
	}

	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String printValue() {
		return value.toString();
	}

	@Override
	public boolean isZero() {
		return value == 0.0;
	}

}
