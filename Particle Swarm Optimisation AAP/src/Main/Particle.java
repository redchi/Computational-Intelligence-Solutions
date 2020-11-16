package Main;

import java.util.ArrayList;
import java.util.Random;

public class Particle {

	public static AxisValues gBest;
    private static Random rand;
    
    // Variables that gauge 0 <- exploitation / exploration -> 1;
    // defined for all particles at ini stage
    private static double w; // how much particle will remember prev velocity
	private static double c1; //how much particle favours pBest
	private static double c2;//how much particle favours gBest
	
	private AxisValues position;
	private AxisValues velocity;
	private AxisValues pBest;
	
	// reference to antenna array used for checking if particle position is valid
	private AntennaArray antennaArray;
	
	/*
	 * intisialisation of particle class
	 * sets seed and particles exploitation / exploration values
	 */
	public static void initalizeParticleClass(long seed,double w,double c1,double c2) {
		Particle.rand = new Random(seed);
		Particle.w = w;
		Particle.c1 = c1;
		Particle.c2 = c2;
	}
	
	public Particle(AxisValues position,AxisValues velocity,AntennaArray antennaArray) {
		this.position = position;
		this.velocity = multiplyAxisValue(0.5, velocity);
		this.antennaArray = antennaArray;
		pBest = position;
		if(gBest == null) {
			gBest = position;
		}
	}
	
	public AxisValues getPos() {
		return position;
	}
	
	public void update() {
		checkCurrentPosition();
		calculateNewVelocity();
		moveToNewPosition();
		
	}
	
	
	private void checkCurrentPosition() {
		// invisible wall contraint handling 
		// only updates gbest and pbest if current position is valid, 
		//insertia will slow down and particle will move back to valid region
		boolean valid = antennaArray.is_valid(position.getRaw());
		if(valid == true) {
			
			double gBestCost = antennaArray.evaluate(gBest.getRaw());
			double pBestCost = antennaArray.evaluate(pBest.getRaw());
			double currentposCost = antennaArray.evaluate(position.getRaw());
			
			if(currentposCost<gBestCost) {
				gBest = position;
				pBest = position;
			}
			else if(currentposCost<pBestCost) {
				pBest = position;
			}
		}
	}
	
	private void calculateNewVelocity() {
		
		// stocastic variables for particle to favour pbest or gbest in a single move
		double r1  = 0 + rand.nextDouble() * (1 - 0); // favors pbest
		double r2 = 1-r1; // favors gbest
		
	
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
	
	/*
	 * Axis Util methods used by particle class
	 */
	
	/*
	 * adds a array of axis values together, exept last value as it is always fixed
	 */
	private AxisValues addAxisValues(AxisValues[] values) {

		int dim = values[0].getAntennaPos().size();  // dimension
		
		ArrayList<Double> totalAddedVal = new ArrayList<Double>();
		for(int i = 0;i<dim;i++) {
			totalAddedVal.add((double) 0);
		}
		
		double lastVal = values[0].getAntennaPos().get(dim-1);
		
		for(AxisValues value:values) {
			ArrayList<Double> antennaPos = value.getAntennaPos();
			for(int i = 0;i<antennaPos.size();i++) {
				double pos = antennaPos.get(i);
				double totalpos = totalAddedVal.get(i);
				double newPos = pos + totalpos;
				totalAddedVal.set(i, newPos);
			}
		}
	
		totalAddedVal.set((dim-1), lastVal); // reset last value to original
		AxisValues res = new AxisValues(totalAddedVal);
		
		return res;
	}

	// a1 - a2 
	private AxisValues subtractAxisValues(AxisValues a1,AxisValues a2) {
		ArrayList<Double> resArr = new ArrayList<Double>(a1.getAntennaPos()); // store result in this, just subtract every element by a2
		
		int size = a1.getAntennaPos().size();
		for(int i = 0;i<size-1;i++) {
			double res = a1.getAntennaPos().get(i) - a2.getAntennaPos().get(i);
			resArr.set(i, res);
		}
		AxisValues res = new AxisValues(resArr);
		return res;
	}
	// m*a1
	private AxisValues multiplyAxisValue(double multiple,AxisValues a1) {
		ArrayList<Double> a1ArrCopy = new ArrayList<Double>(a1.getAntennaPos()); 
		for(int i = 0;i<a1ArrCopy.size()-1;i++) {
			double res = a1.getAntennaPos().get(i) * multiple;
			a1ArrCopy.set(i, res);
		}
		AxisValues res = new AxisValues(a1ArrCopy);
		return res;
	}
}
