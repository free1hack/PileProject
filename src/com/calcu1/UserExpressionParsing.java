package com.calcu1;


import java.io.PrintStream;

import com.calcu1.common.ElementFactory;
import com.calcu1.common.TypeOperation;

public class UserExpressionParsing {

	private String[] userExpressionSequence;
	private PileRPL pile;
	private TypeOperation operationMode;
	PrintStream outputUser;


	public UserExpressionParsing(String userExpression, PileRPL pile, TypeOperation operationMode, PrintStream outputUser) {
		this.userExpressionSequence = userExpression.split("\\s+");
		this.pile = pile;
		this.operationMode = operationMode;
		this.outputUser = outputUser;
	}

	private boolean isNumber(String element) {
		try {
			return new Double(element) instanceof Double;
		} catch(Exception e) {
			return false;
		}
	}
	private boolean isComplex(String element) {
		String []complex = element.split("i");
		if(complex.length == 1) {
			return isNumber(complex[0]);
		}
		if(complex.length == 0) {
			return true;
		}
		return false;
	}

	private boolean isAddOperation(String element) {
		return element.equals("+");
	}
	private boolean isMulOperation(String element) {
		return element.contentEquals("*");
	}
	private boolean isSubOperation(String element) {
		return element.contentEquals("-");
	}
	private boolean isDivOperation(String element) {
		return element.contentEquals("/");
	}

	public void buildPile() throws Exception {
		for(int indiceOperation = 0; indiceOperation < userExpressionSequence.length ; indiceOperation++) {
			outputUser.println("========START OPERATION N° "+(indiceOperation+1)+"===========");
			String element = userExpressionSequence[indiceOperation];
			if(isNumber(element)) {
				pile.push(ElementFactory.createElementType(element, operationMode));
			} else if (isComplex(element) && operationMode == TypeOperation.Complex){
				pile.push(ElementFactory.createElementType(element, operationMode));
			} else if(isAddOperation(element)) {
				pile.add();
			} else if(isMulOperation(element)) {
				pile.multi();
			} else if(isSubOperation(element)) {
				pile.sub();
			} else if(isDivOperation(element)) {
				pile.div();
			} else {
				throw new Exception("Invalid Syntax User expression or Element are not compatible with operation mode");
			}
			outputUser.println(pile.pileState());
			outputUser.println("========END OPERATION N° "+(indiceOperation+1)+"===========");
		}
	}
}
