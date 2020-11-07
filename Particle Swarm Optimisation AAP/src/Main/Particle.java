package Main;

import java.util.ArrayList;
import java.util.Random;

public class Particle {

	public static AxisValues gBest;
    private static Random rand = new Random(95861);
	private AxisValues position;
	private AxisValues velocity;
	private AxisValues pBest;
	private AntennaArray antennaArray;
	
	public Particle(AxisValues position,AxisValues velocity,AntennaArray antennaArray) {
		this.position = this.position;
		this.velocity = multiplyAxisValue(0.5, velocity);
		this.antennaArray = antennaArray;
	}
	
	
	public void update() {
		
		
	}
	
	
	private void checkCurrentPosition() {
		double gBestCost = antennaArray.evaluate(gBest.getRaw());
		double pBestCost = antennaArray.evaluate(pBest.getRaw());
		double currentposCost = antennaArray.evaluate(position.getRaw());
		
		// do checking here
	}
	
	private void calculateNewVelocity() {
		// Variables that gage 0 <- exploitation / exploration -> 1;
		double w =1;	//position - inertia
		double c1 =1; 	//pbest - cognitive
		double c2 = 1;	//gbest - social
		
		// stocastic variables for particle to favour pbest or gbest in a single move
		double r1  = 0 + rand.nextDouble() * (1 - 0);
		double r2 = 1-r1;
		
		AxisValues inertia = multiplyAxisValue(w,velocity);
		
		AxisValues distanceToPBest = subtractAxisValues(pBest,position);
		AxisValues cognitiveComp = multiplyAxisValue((c1*r1),distanceToPBest);
		
		AxisValues distanceToGBest = subtractAxisValues(gBest,position);
		AxisValues socialComp = multiplyAxisValue((c2*r2), distanceToGBest);
		AxisValues [] allComp = new AxisValues[] {inertia,cognitiveComp,socialComp}; 
		
		AxisValues newVelocity = addAxisValues(allComp);
		
		
	}
	private void moveToNewPosition() {
		AxisValues newPosition = addAxisValues(position,velocity);
		position = newPosition;
		
	}
	private AxisValues addAxisValues(AxisValues[] values) {
		return null;
	}
	private AxisValues addAxisValues(AxisValues a1,AxisValues a2) {
		return null;
	}
	private AxisValues subtractAxisValues(AxisValues a1,AxisValues a2) {
		return null;
	}
	private AxisValues multiplyAxisValue(double multiple,AxisValues a1) {
		return null;
	}
}
