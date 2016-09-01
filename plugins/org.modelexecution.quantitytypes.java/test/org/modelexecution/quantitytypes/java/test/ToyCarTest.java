/**
 * @author mw
 *
 */

package org.modelexecution.quantitytypes.java.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.modelexecution.quantitytypes.java.BaseUnits;
import org.modelexecution.quantitytypes.java.CommonUnits;
import org.modelexecution.quantitytypes.java.Quantity;
import org.modelexecution.quantitytypes.java.Unit;

public class ToyCarTest {

	@Test
	public void test() {
		
		// sensed values
		Quantity initialPosition = new Quantity(0, 0.000, CommonUnits.M);
		Quantity finalPosition = new Quantity(10, 0.000, CommonUnits.M);
		Quantity duration = new Quantity(10, 0.002, new Unit(BaseUnits.Second));
		Quantity initialVelocity = new Quantity(0, 0.000, new Unit("MeterPerSecond", "m/s", BaseUnits.Meter, 1.0, 1.0, 0.0, BaseUnits.Second, -1.0, 1.0));
		Quantity finalVelocity = new Quantity(2, 0.02, new Unit("MeterPerSecond", "m/s", BaseUnits.Meter, 1.0, 1.0, 0.0, BaseUnits.Second, -1.0, 1.0));
		
		// computed values
		Quantity distance = finalPosition.minus(initialPosition);
		Quantity avgVelocity = distance.divideBy(duration);
		Quantity avgAcceleration = (finalVelocity.minus(initialVelocity)).divideBy(duration);
		
		// Testing the value results
		assertEquals("distance=finalPostion-initialPostion must be 10", 
				10, distance.getX(), 0.1);
		assertEquals("avgVel=distance/duration must be ~1", 
				1, avgVelocity.getX(), 0.1);
		assertEquals("avgAcc=(finalVel-initialVel)/distance must be ~0.2", 
				0.2, avgAcceleration.getX(), 0.1);

		// Testing the return types
		Assert.assertArrayEquals("avgVel is in metre per second (m s-1)", 
				new double[]{1.0,0.0,-1.0,0.0,0.0,0.0,0.0,0.0,0.0}, 
				avgVelocity.getUnits().dimensions(), 0);
		
		Assert.assertArrayEquals("avgAcc is in metre per second squared (m s-2)", 
					new double[]{1.0,0.0,-2.0,0.0,0.0,0.0,0.0,0.0,0.0}, 
					avgAcceleration.getUnits().dimensions(), 0);
		

	}

}
