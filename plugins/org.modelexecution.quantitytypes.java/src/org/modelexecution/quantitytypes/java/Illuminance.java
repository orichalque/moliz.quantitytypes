package org.modelexecution.quantitytypes.java;
import java.util.Arrays;
public class Illuminance extends Quantity {
static protected boolean checkUnit(Unit u) {
double [] x = new double [BaseUnits.values().length];
x[BaseUnits.Meter.ordinal()]=-2.0;
x[BaseUnits.Kilogram.ordinal()]=0.0;
x[BaseUnits.Second.ordinal()]=0.0;
x[BaseUnits.Ampere.ordinal()]=0.0;
x[BaseUnits.Kelvin.ordinal()]=0.0;
x[BaseUnits.Mole.ordinal()]=0.0;
x[BaseUnits.Radian.ordinal()]=1.0;
x[BaseUnits.Candela.ordinal()]=1.0;
x[BaseUnits.Bit.ordinal()]=0.0;
x[BaseUnits.Shannon.ordinal()]=0.0;
x[BaseUnits.Erlang.ordinal()]=0.0;
x[BaseUnits.Decibel.ordinal()]=0.0;
return Arrays.equals(x,u.dimensions);
}

public Illuminance(Quantity q){
value = q.value.clone();
if (!checkUnit(q.unit)) throw new RuntimeException("Invalid Unit for creating a Illuminance");
this.unit = new Unit (q.unit);
}
public Illuminance () {
value = new UReal();
unit = new Unit(DerivedUnits.Lux);
}
public Illuminance(UReal u){
value = u.clone();
unit = new Unit(DerivedUnits.Lux);
}
public Illuminance(UReal u, Unit unit){
value = u.clone();
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating an Illuminance");
this.unit = new Unit (unit);
}
public Illuminance(double x){ //"promotes" a real x
value = new UReal(x);
unit = new Unit(DerivedUnits.Lux);
}
public Illuminance (double x, double u) {
value = new UReal(x,u);
unit = new Unit(DerivedUnits.Lux);
}
public Illuminance(double x, Unit unit){ //we only allow the same Units
value = new UReal(x);
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a Illuminance");
this.unit = new Unit (unit);
}
public Illuminance(double x, double u, Unit unit){
value = new UReal(x,u);
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a Illuminance");
this.unit = new Unit (unit);
}
public Illuminance(String x) { //creates a Illuminance from a string representing a real, with u=0.
value = new UReal(x);
unit = new Unit(DerivedUnits.Lux);
}
public Illuminance(String x, String u) { //creates a Illuminance from two strings representing (x,u).
value = new UReal(x,u);
unit = new Unit(DerivedUnits.Lux);
}
public Illuminance(String x, String u, Unit unit) { //creates a Illuminance from two strings representing (x,u).
value = new UReal(x,u);
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a Illuminance");
this.unit = new Unit (unit);
}
// Specific Type Operations
public Illuminance add(Illuminance r) {  //only works if compatible units && operand has no offset
return new Illuminance(super.add(r));
}
public Illuminance minus(Illuminance r) { //only works if compatible units. You can subtract 2 units with offsets, but it returns a DeltaUnit (without offset)
return new Illuminance(super.minus(r));
}
public LuminousFlux mult(Area r) { //both values and units are multiplied. No offsets allowed in any of the units
return new LuminousFlux(super.mult(r));
}
public Luminance divideBy(Angle r) { //both values and units are divided. No offsets allowed in any of the units
return new Luminance(super.divideBy(r));
}
public Angle divideBy(Luminance r) { //both values and units are divided. No offsets allowed in any of the units
return new Angle(super.divideBy(r));
}
public UReal divideBy(Illuminance r) { //This operation converts first both operands to SI units and then divides)
return super.divideBy(r).value.clone();
}
public Illuminance abs() { //units are maintained
return new Illuminance(super.abs());
}
public Illuminance neg() { //units are maintained
return new Illuminance(super.neg());
}
// power(s), and inverse() return Quantity
// lessThan, LessEq, gt, ge all directly from Quantity
public boolean equals(Illuminance r) {  
return  r.compatibleUnits(this) && 
this.getUReal().equals(r.convertTo(this.getUnits()).getUReal());
}
public boolean distinct(Illuminance r) {
return !(this.equals(r));
}
public Illuminance floor() { //returns (i,u) with i the largest int such that (i,u)<=(x,u) -- units maintained
return new Illuminance(Math.floor(this.getX()),this.getU(),this.getUnits());
}
public Illuminance round(){ //returns (i,u) with i the closest int to x -- units maintained
return new Illuminance(Math.round(this.getX()),this.getU(),this.getUnits());
}
public Illuminance min(Illuminance r) { // units maintained
if (r.lt(this)) return new Illuminance(r.getX(),r.getU(),r.getUnits());
return new Illuminance(this.getX(),this.getU(),this.getUnits());
}
public Illuminance max(Illuminance r) { // unit maintained
//if (r>this) r; else this;
if (r.gt(this)) return new Illuminance(r.getX(),r.getU(),r.getUnits());
return new Illuminance(this.getX(),this.getU(),this.getUnits());
}
// working with constants (note that add and minus do not work here
public Illuminance mult(double r) {
return new Illuminance(this.value.mult(new UReal(r)),this.getUnits());
}
public Illuminance divideBy(double r) {
return new Illuminance(this.value.divideBy(new UReal(r)),this.getUnits());
}
public Illuminance mult(UReal r) {
return new Illuminance(this.value.mult(r),this.getUnits());
}
public Illuminance divideBy(UReal r) {
return new Illuminance(this.value.divideBy(r),this.getUnits());
}
// Conversions to basic types: toString, toInteger, toReal, etc. directly from Quantity
public int hashcode(){ //required for equals()
return Math.round((float)value.getX());
}
public Illuminance clone() {
return new Illuminance(this.getUReal(),this.getUnits());
}
}
