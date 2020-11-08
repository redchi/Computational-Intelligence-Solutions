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
		checkCurrentPosition();
		calculateNewVelocity();
		moveToNewPosition();
		
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
		this.velocity = newVelocity;
		
	}
	private void moveToNewPosition() {
		AxisValues newPosition = addAxisValues(new AxisValues[] {position,velocity});
		position = newPosition;
		
	}
	
	private AxisValues addAxisValues(AxisValues[] values) {

		int dim = values[0].getAntennaPos().size();  // dimension
		
		ArrayList<Double> totalAddedVal = new ArrayList<Double>();
		for(int i = 0;i<dim;i++) {
			totalAddedVal.set(i, (double) 0);
		}
		
		
		for(AxisValues value:values) {
			ArrayList<Double> antennaPos = value.getAntennaPos();
			for(int i = 0;i<antennaPos.size();i++) {
				double pos = antennaPos.get(i);
				double totalpos = totalAddedVal.get(i);
				double newPos = pos + totalpos;
				totalAddedVal.set(i, newPos);
			}
		}
	
		AxisValues res = new AxisValues(totalAddedVal);
		
		return res;
	}

	// a1 - a2 
	private AxisValues subtractAxisValues(AxisValues a1,AxisValues a2) {
		ArrayList<Double> resArr = new ArrayList<Double>(a1.getAntennaPos()); // store result in this, just subtract every element by a2
		
		int size = a1.getAntennaPos().size();
		for(int i = 0;i<size;i++) {
			double res = a1.getAntennaPos().get(i) - a2.getAntennaPos().get(i);
			resArr.set(i, res);
		}
		AxisValues res = new AxisValues(resArr);
		return res;
	}
	
	private AxisValues multiplyAxisValue(double multiple,AxisValues a1) {
		ArrayList<Double> a1ArrCopy = new ArrayList<Double>(a1.getAntennaPos()); 
		for(int i = 0;i<a1ArrCopy.size();i++) {
			double res = a1.getAntennaPos().get(0) * multiple;
			a1ArrCopy.set(i, res);
		}
		AxisValues res = new AxisValues(a1ArrCopy);
		return res;
	}
}
