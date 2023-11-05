package com.calcu1;

import java.util.Arrays;

import com.calcu1.common.ObjEmp;

public class PileRPL {
	int taille;

	public PileRPL(int taille) {
		this.taille = taille;
		this.array = new ObjEmp[taille];
	}

	public boolean estVide() {
		return this.nobj == 0;
	}

	public boolean estPleine() {
		return this.nobj == this.taille;
	}

	public void clearPile() {
		Arrays.fill(this.array, null);
		this.nobj = 0;
	}
	public void push(ObjEmp objEmp) {
		if (!estPleine()) {
			// this.getPile()[this.getNobj()] = objEmp;
			array[nobj] = objEmp;
			// this.setNobj(this.getNobj() + 1);
			this.nobj++;
		}
	}

	public ObjEmp pop() {
		ObjEmp element;
		if (estVide())

			throw new RuntimeException("pop impossible car la pile est vide");
		else {
			// this.getPile()[this.getNobj()] = this.getPile()[this.getNobj()-1];
			element = array[nobj - 1];
			// array[nobj] = array[nobj-1];
			array[nobj - 1] = null;
			nobj--;
			return element;
			// this.getPile()[this.getNobj()-1] = null;
			// (this.getNobj()-1);
			// ////////////////////////////////////////////////////////////////////////////////////////////////////:////////////////////////////////////////////
			// return this.getPile()[this.getNobj()];

			// ne pas oublier de décrémenter nobj // return obj a index x;
		}
	}

	public boolean add() {
		if (nobj < 2) {
			return false;
		} else {
			ObjEmp a = pop();
			ObjEmp b = pop();
			ObjEmp c = a.add(b);
			push(c);
			return true;
		}
	}

	public boolean sub() {
		if (this.nobj < 2) {
			return false;
		} else {
			ObjEmp a = pop();
			ObjEmp b = pop();
			ObjEmp c = a.substract(b);
			push(c);
			return true;
		}
	}

	public boolean multi() {
		if (this.nobj < 2) {
			return false;
		} else {
			ObjEmp a = pop();
			ObjEmp b = pop();
			ObjEmp c = a.multiply(b);
			push(c);
			return true;
		}
	}

	public boolean div() {
		if ((this.nobj) < 2) {
			return false;
		} else {
			ObjEmp a = pop();
			ObjEmp b = pop();
			if (b.isZero()) {
				return false;
			}
			// Vérifier que b.getValue() =/= de zero

			ObjEmp c = a.divide(b);
			push(c);
		}
		return true;
	}

	public String pileState() {
		String sb = "";
		for (Integer i = 0; i < nobj; i++) {
			if(sb.length() == 0) {
				sb= "|| "
						.concat(i.toString())
						.concat(" || ")
						.concat(this.array[i].printValue());
			} else {
				sb= "|| "
						.concat(i.toString())
						.concat(" || ")
						.concat(this.array[i].printValue())
						.concat("\n")
						.concat(sb);
			}
		}
		return sb.toString();
	}

	/*
	 * public ObjEmp[] getPile() { return this.array; }
	 *
	 * public void setPile(ObjEmp[] pile) { this.array = pile; }
	 *
	 * public int getTaille(){ return this.getPile().length; }
	 *
	 * public int getNobj(){ return this.nobj; }
	 *
	 * public void setNobj(int nobj){ this.nobj = nobj; }
	 */

	private ObjEmp array[] = null;
	private int nobj = 0;

}
