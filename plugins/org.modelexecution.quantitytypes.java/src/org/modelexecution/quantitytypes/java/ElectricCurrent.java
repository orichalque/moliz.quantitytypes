package org.modelexecution.quantitytypes.java;

// IN PROGRESS

public class ElectricCurrent extends Quantity {
	static protected boolean checkUnit(Unit u) {
		int s = BaseUnits.Ampere.ordinal();
		if (u.dimensions[s]!=1.0) return false;
		for (int i=0;i<u.dimensions.length;i++) {
			if ((i!=s)&&(u.dimensions[i]!=0.0)) return false;
		}
		return true;
	}

	public ElectricCurrent(Quantity q){
    	value = q.value.clone();
    	if (!checkUnit(q.unit)) throw new RuntimeException("Invalid Unit for creating a ElectricCurrent");
    	this.unit = new Unit (q.unit);
    }

	   public ElectricCurrent () {
	        value = new UReal();
	        unit = new Unit(BaseUnits.Ampere);
	    }

	    public ElectricCurrent(UReal u){
	    	value = u.clone();
	        unit = new Unit(BaseUnits.Ampere);
	    }

	    public ElectricCurrent(UReal u, Unit unit){
	    	value = u.clone();
	    	if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a ElectricCurrent");
	    	this.unit = new Unit (unit);
	    }

		public ElectricCurrent(double x){ //"promotes" a real x 
			value = new UReal(x,0.0);
	        unit = new Unit(BaseUnits.Ampere);
		}
		
	    public ElectricCurrent (double x, double u) {
	    	value = new UReal(x,u);
	        unit = new Unit(BaseUnits.Ampere);
	   }
	    
	    public ElectricCurrent(double x, Unit unit){ //we only allow Time Units
	    	value = new UReal(x);
	    	if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a ElectricCurrent");
	    	this.unit = new Unit (unit);
	   }

	    public ElectricCurrent(double x, double u, Unit unit){
	    	value = new UReal(x,u);
	    	if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a ElectricCurrent");
	    	this.unit = new Unit (unit);
	    }


}
