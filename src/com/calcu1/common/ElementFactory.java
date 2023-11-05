package com.calcu1.common;

import com.calcu1.ObjEmpComplex;
import com.calcu1.ObjEmpNumber;

public class ElementFactory {

	public static ObjEmp createElementType(String element, TypeOperation typeOperation) {
		if(typeOperation == TypeOperation.Number) {
			return new ObjEmpNumber(Double.parseDouble(element));
		} else if(typeOperation == TypeOperation.Complex) {
			String[] complex = element.split("i");
			if(!element.contains("i")) {
				return new ObjEmpComplex(new ComplexNumber(Double.parseDouble(complex[0]), 0.0));
			} else {
				return new ObjEmpComplex(new ComplexNumber(0.0, complex.length == 0 ? 1 : Double.parseDouble(complex[0])));
			}
		} else {
			return null;
		}
	}
}
