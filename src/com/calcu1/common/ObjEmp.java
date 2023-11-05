package com.calcu1.common;

public interface ObjEmp<T> {

	public ObjEmp<T> add(ObjEmp<T> objEmp);

	public ObjEmp<T> substract(ObjEmp<T> objEmp);

	public ObjEmp<T> multiply(ObjEmp<T> objEmp);

	public ObjEmp<T> divide(ObjEmp<T> objEmp);

	public T getValue();

	public boolean isZero();

	public void setValue(T value);

	public String printValue();
}
