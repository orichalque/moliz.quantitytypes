package org.modelexecution.quantitytypes.java;

public class LinearThermalExpansion extends Quantity {
	static protected boolean checkUnit(Unit u) {
		int l = BaseUnits.Meter.ordinal();
		int k = BaseUnits.Kelvin.ordinal();
		if (u.dimensions[l]!=1.0) return false;
		if (u.dimensions[k]!=-1.0) return false;
		for (int i=0;i<u.dimensions.length;i++) {
			if ((i!=l)&&(i!=k)&&(u.dimensions[i]!=0.0)) return false;
		}
		return true;
	}

	public LinearThermalExpansion(Quantity q){
    	value = q.value.clone();
    	if (!checkUnit(q.unit)) throw new RuntimeException("Invalid Unit for creating a LinearThermalExpansion");
    	this.unit = new Unit (q.unit);
    }

}
