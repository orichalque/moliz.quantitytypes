package org.modelexecution.quantitytypes.java;

/**
 * @author av
 *
 */
public class Quantity {
	
	protected UReal value;
	protected Unit unit;
	
    /**
     * Constructors 
     */
    public Quantity () {
        value = new UReal();
        unit = new Unit(); //dimensionless 
    }

    public Quantity(UReal u, Unit unit){
    	value = u.clone();
    	this.unit = new Unit (unit);
    }

	public Quantity(double x){ //"promotes" a real x to a dimensionless (x,0) 
		value = new UReal(x);
        unit = new Unit(); //dimensionless 
	}
  
    public Quantity (double x, double u) {
    	value = new UReal(x,u);
        unit = new Unit(); //dimensionless 
    }
    
    public Quantity(double x, double u, Unit unit){
    	value = new UReal(x,u);
    	this.unit = new Unit (unit);
    }

    public Quantity(String x) { //creates an Quantity from a string representing a real, with u=0.
    	value = new UReal(x);
        unit = new Unit(); //dimensionless 
   }
    
    public Quantity(String x, String u) { //creates an Quantity from two strings representing (x,u).
    	value = new UReal(x,u);
        unit = new Unit(); //dimensionless 
   }
   
    public Quantity(String x, String u, Unit unit) { //creates an Quantity from two strings representing (x,u).
    	value = new UReal(x,u);
    	this.unit = new Unit (unit);
   }

    /**
     * Setters and getters 
     */
    public UReal getUReal() {
 		return value; 
 	}
     public void setUReal(UReal x) {
    	 value = new UReal(x.getX(),x.getU()); 
 	}
   public double getX() {
		return value.getX(); 
	}
    public void setX(double x) {
    	value.setX(x); 
	}
    public double getU() {
		return value.getU();
	}
	public void setU(double u) {
		value.setU(u);;
	}
    public Unit getUnits() {
		return unit;
	}
	public void setUnits(Unit unit) {
        this.unit = new Unit(unit); 
	}
	
	/******
	 * Auxiliary operations. They are made publicly available to help 
	 */
	
	public boolean sameUnits(Quantity x) {
		return this.unit.equals(x.unit);
	}
	
    /*********
     * 
     * Type Operations
     */

	public Quantity add(Quantity x) {  //only works if same unit
		Quantity result = null;
		if (this.sameUnits(x)) {
			result = new Quantity(this.getUReal().add(x.getUReal()),this.getUnits());
		} else throw  new ArithmeticException("Unit mismatch: the unit of the two quantities to add do not coincide");
		return result;
	}
	
	public Quantity minus(Quantity r) { //only works if same unit
		Quantity result = null;
		if (r.sameUnits(r)) {
			result = new Quantity(this.getUReal().minus(r.getUReal()),this.getUnits());
		} else throw  new ArithmeticException("Unit mismatch: the unit of the two quantities to substract do not coincide");
		return result;
	}

	public Quantity mult(Quantity r) { //both values and unit are multiplied
		Quantity result = new Quantity();
		
		result.setUReal(this.getUReal().mult(r.getUReal()));
		result.setUnits(this.getUnits().multiplyUnits(r.getUnits()));

		return result;
	}
	
	public Quantity divideBy(Quantity r) { //both values and unit are divided
		Quantity result = new Quantity();
	
		result.setUReal(this.getUReal().divide(r.getUReal()));

		result.setUnits(this.getUnits().divideUnits(r.getUnits()));

		return result;
	}
	
	
	public Quantity abs() { //unit are maintained
		Quantity result = new Quantity();
	
		result.setUReal(this.getUReal().abs());
		result.setUnits(this.getUnits());
	
		return result;
	}
	
	public Quantity neg() { //unit are maintained
		Quantity result = new Quantity();
		
		result.setUReal(this.getUReal().neg());
		result.setUnits(this.getUnits());
	
		return result;
	}

	public Quantity power(float s) { //unit are also powered
		Quantity result = new Quantity();
		
		result.setUReal(this.getUReal().power(s));
		result.setUnits(this.getUnits().powerUnits(s));
		
		return result;
	}

	public Quantity sqrt() {  //unit are also squared
		Quantity result = new Quantity();
		
		result.setUReal(this.getUReal().sqrt());
		result.setUnits(this.getUnits().powerUnits(0.5f));
	
		return result;
	}

	public boolean lessThan(Quantity r) {  //only if same unit
		boolean result = false;
		if (r.sameUnits(this)) {
			result = this.getUReal().lessThan(r.getUReal());
		} else throw  new ArithmeticException("Unit mismatch: the unit of the two quantities to compare do not coincide");
		return result;
	}
	
	public boolean lessEq(Quantity r) {  //only if same unit
		boolean result = false;
		if (r.sameUnits(this)) {
			result = (this.lessThan(r) || this.equals(r));
		} else throw  new ArithmeticException("Unit mismatch: the unit of the two quantities to compare do not coincide");
		return result;
	}

	public boolean gt(Quantity r) {  //only if same unit
		boolean result = false;
		
		if (r.sameUnits(this)) {
			result = r.lessThan(this);		
		} else throw  new ArithmeticException("Unit mismatch: the unit of the two quantities to compare do not coincide");
	
		return result;
	}
	
	public boolean ge(Quantity r) {  //only if same unit
		boolean result = false;
		if (r.sameUnits(this)) {
			result = (this.gt(r) || this.equals(r));		
		} else throw  new ArithmeticException("Unit mismatch: the unit of the two quantities to compare do not coincide");
		
		return result;
	}
	
	public boolean equals(Quantity r) {  
		return this.getUReal().equals(r.getUReal()) 
			   && r.sameUnits(this);
	}

	public boolean distinct(Quantity r) {  
		return !(this.equals(r));
	}
	
	public Quantity inverse() { //inverse (reciprocal)
		return new Quantity(1.0).divideBy(this);
	}
	
	public Quantity floor() { //returns (i,u) with i the largest int such that (i,u)<=(x,u) -- unit maintained
		return new Quantity(Math.floor(this.getX()),this.getU(),this.getUnits());
	}
	public Quantity round(){ //returns (i,u) with i the closest int to x -- unit maintained
		return new Quantity(Math.round(this.getX()),this.getU(),this.getUnits());
	}
	public Quantity min(Quantity r) { // unit maintained
		if (r.lessThan(this)) return new Quantity(r.getX(),r.getU(),r.getUnits()); 
		return new Quantity(this.getX(),this.getU(),this.getUnits());
	}
	public Quantity max(Quantity r) { // unit maintained
		//if (r>this) r; else this;
		if (r.gt(this)) return new Quantity(r.getX(),r.getU(),r.getUnits());
		return new Quantity(this.getX(),this.getU(),this.getUnits());
	}

	/***
	 * working with constants (note that add and minus will only work if "this" is dimensionless
	 */

	public Quantity sAdd(double r) {  
		return this.add(new Quantity(r));
	}
	
	public Quantity sMinus(double r) {  
		return this.minus(new Quantity(r));
	}
	
	public Quantity sMult(double r) {  
		return this.mult(new Quantity(r));
	}
	
	public Quantity sDivideBy(double r) {  
		return this.divideBy(new Quantity(r));
	}

	/******
	 * Conversions
	 */
	
	public String toString() {
		return value.toString() + unit.toString();
	}
	
	
	public int toInteger(){ //
		return (int)Math.floor(this.getX());
	}
	
	public double toReal()  { 
		return this.getX();
	}
	
	/**
	 * Other Methods 
	 */

 	public int hashcode(){ //required for equals()
		return Math.round((float)value.getX());
	}

 	public Quantity clone() {
		return new Quantity(this.getUReal(),this.getUnits());
	}

}
