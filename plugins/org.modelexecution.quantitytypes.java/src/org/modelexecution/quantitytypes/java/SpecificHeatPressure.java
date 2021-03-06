package org.modelexecution.quantitytypes.java;
import java.util.Arrays;
public class SpecificHeatPressure extends Quantity {
static protected boolean checkUnit(Unit u) {
double [] x = new double [BaseUnits.values().length];
x[BaseUnits.Meter.ordinal()]=3.0;
x[BaseUnits.Kilogram.ordinal()]=-1.0;
x[BaseUnits.Second.ordinal()]=0.0;
x[BaseUnits.Ampere.ordinal()]=0.0;
x[BaseUnits.Kelvin.ordinal()]=-1.0;
x[BaseUnits.Mole.ordinal()]=0.0;
x[BaseUnits.Radian.ordinal()]=0.0;
x[BaseUnits.Candela.ordinal()]=0.0;
x[BaseUnits.Bit.ordinal()]=0.0;
x[BaseUnits.Shannon.ordinal()]=0.0;
x[BaseUnits.Erlang.ordinal()]=0.0;
x[BaseUnits.Decibel.ordinal()]=0.0;
return Arrays.equals(x,u.dimensions);
}

public SpecificHeatPressure(Quantity q){
value = q.value.clone();
if (!checkUnit(q.unit)) throw new RuntimeException("Invalid Unit for creating a SpecificHeatPressure");
this.unit = new Unit (q.unit);
}
public SpecificHeatPressure () {
value = new UReal();
unit = new Unit(DerivedUnits.JoulePerKilogramKelvinPerPascal);
}
public SpecificHeatPressure(UReal u){
value = u.clone();
unit = new Unit(DerivedUnits.JoulePerKilogramKelvinPerPascal);
}
public SpecificHeatPressure(UReal u, Unit unit){
value = u.clone();
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating an SpecificHeatPressure");
this.unit = new Unit (unit);
}
public SpecificHeatPressure(double x){ //"promotes" a real x
value = new UReal(x);
unit = new Unit(DerivedUnits.JoulePerKilogramKelvinPerPascal);
}
public SpecificHeatPressure (double x, double u) {
value = new UReal(x,u);
unit = new Unit(DerivedUnits.JoulePerKilogramKelvinPerPascal);
}
public SpecificHeatPressure(double x, Unit unit){ //we only allow the same Units
value = new UReal(x);
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a SpecificHeatPressure");
this.unit = new Unit (unit);
}
public SpecificHeatPressure(double x, double u, Unit unit){
value = new UReal(x,u);
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a SpecificHeatPressure");
this.unit = new Unit (unit);
}
public SpecificHeatPressure(String x) { //creates a SpecificHeatPressure from a string representing a real, with u=0.
value = new UReal(x);
unit = new Unit(DerivedUnits.JoulePerKilogramKelvinPerPascal);
}
public SpecificHeatPressure(String x, String u) { //creates a SpecificHeatPressure from two strings representing (x,u).
value = new UReal(x,u);
unit = new Unit(DerivedUnits.JoulePerKilogramKelvinPerPascal);
}
public SpecificHeatPressure(String x, String u, Unit unit) { //creates a SpecificHeatPressure from two strings representing (x,u).
value = new UReal(x,u);
if (!checkUnit(unit)) throw new RuntimeException("Invalid Unit for creating a SpecificHeatPressure");
this.unit = new Unit (unit);
}
// Specific Type Operations
public SpecificHeatPressure add(SpecificHeatPressure r) {  //only works if compatible units && operand has no offset
return new SpecificHeatPressure(super.add(r));
}
public SpecificHeatPressure minus(SpecificHeatPressure r) { //only works if compatible units. You can subtract 2 units with offsets, but it returns a DeltaUnit (without offset)
return new SpecificHeatPressure(super.minus(r));
}
public VolumeThermalExpansion mult(Mass r) { //both values and units are multiplied. No offsets allowed in any of the units
return new VolumeThermalExpansion(super.mult(r));
}
public SpecificHeatCapacity mult(EnergyDensity r) { //both values and units are multiplied. No offsets allowed in any of the units
return new SpecificHeatCapacity(super.mult(r));
}
public Volume mult(MassTemperature r) { //both values and units are multiplied. No offsets allowed in any of the units
return new Volume(super.mult(r));
}
public LinearThermalExpansion mult(MassPerUnitArea r) { //both values and units are multiplied. No offsets allowed in any of the units
return new LinearThermalExpansion(super.mult(r));
}
public AreaThermalExpansion mult(MassPerUnitLength r) { //both values and units are multiplied. No offsets allowed in any of the units
return new AreaThermalExpansion(super.mult(r));
}
public SpecificHeatCapacity mult(Pressure r) { //both values and units are multiplied. No offsets allowed in any of the units
return new SpecificHeatCapacity(super.mult(r));
}
public UReal divideBy(SpecificHeatPressure r) { //This operation converts first both operands to SI units and then divides)
return super.divideBy(r).value.clone();
}
public SpecificHeatPressure abs() { //units are maintained
return new SpecificHeatPressure(super.abs());
}
public SpecificHeatPressure neg() { //units are maintained
return new SpecificHeatPressure(super.neg());
}
// power(s), and inverse() return Quantity
// lessThan, LessEq, gt, ge all directly from Quantity
public boolean equals(SpecificHeatPressure r) {  
return  r.compatibleUnits(this) && 
this.getUReal().equals(r.convertTo(this.getUnits()).getUReal());
}
public boolean distinct(SpecificHeatPressure r) {
return !(this.equals(r));
}
public SpecificHeatPressure floor() { //returns (i,u) with i the largest int such that (i,u)<=(x,u) -- units maintained
return new SpecificHeatPressure(Math.floor(this.getX()),this.getU(),this.getUnits());
}
public SpecificHeatPressure round(){ //returns (i,u) with i the closest int to x -- units maintained
return new SpecificHeatPressure(Math.round(this.getX()),this.getU(),this.getUnits());
}
public SpecificHeatPressure min(SpecificHeatPressure r) { // units maintained
if (r.lt(this)) return new SpecificHeatPressure(r.getX(),r.getU(),r.getUnits());
return new SpecificHeatPressure(this.getX(),this.getU(),this.getUnits());
}
public SpecificHeatPressure max(SpecificHeatPressure r) { // unit maintained
//if (r>this) r; else this;
if (r.gt(this)) return new SpecificHeatPressure(r.getX(),r.getU(),r.getUnits());
return new SpecificHeatPressure(this.getX(),this.getU(),this.getUnits());
}
// working with constants (note that add and minus do not work here
public SpecificHeatPressure mult(double r) {
return new SpecificHeatPressure(this.value.mult(new UReal(r)),this.getUnits());
}
public SpecificHeatPressure divideBy(double r) {
return new SpecificHeatPressure(this.value.divideBy(new UReal(r)),this.getUnits());
}
public SpecificHeatPressure mult(UReal r) {
return new SpecificHeatPressure(this.value.mult(r),this.getUnits());
}
public SpecificHeatPressure divideBy(UReal r) {
return new SpecificHeatPressure(this.value.divideBy(r),this.getUnits());
}
// Conversions to basic types: toString, toInteger, toReal, etc. directly from Quantity
public int hashcode(){ //required for equals()
return Math.round((float)value.getX());
}
public SpecificHeatPressure clone() {
return new SpecificHeatPressure(this.getUReal(),this.getUnits());
}
}
