package Main;

import java.util.ArrayList;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class ParticleHandler handles all particle genaration and mangagement in simulation
 */
public class ParticleHandler {

	/** The antenna array. */
	private AntennaArray antennaArray;
	
	/** The rand. */
	private Random rand;
	
	/**
	 * Instantiates a new particle handler.
	 *
	 * @param antennaArray the antenna array
	 */
	public ParticleHandler(AntennaArray antennaArray) {
		this.antennaArray = antennaArray;
		rand = new Random(213123);
	}

	
	/**
	 * Run particle swarm optimization algorithm
	 *
	 * @param numOfParticles the num of particles you want in here
	 * @param cycles the max num of cycles - termination condition
	 * @return the axis values of GBest 
	 */
	public AxisValues runParticleSwarmOptimization(int numOfParticles,int cycles) {	
		ArrayList<Particle> particles = new ArrayList<Particle>();
		
		// customise amount of particle exploration/exploitation here
		Particle.initalizeParticleClass(34243, 0.5, 1, 1);
		
		// generate valid particles here
		for(int i = 0;i<numOfParticles;i++) {
			ArrayList<AxisValues> posAndVelocity = generateRandomPosition(2);
			AxisValues particlePos = posAndVelocity.get(0);
			AxisValues particleVel = posAndVelocity.get(1);
			Particle particle = new Particle(particlePos, particleVel, antennaArray);
			particles.add(particle);
		}
	
		// run simulation
		for(int i = 0;i<cycles;i++) {
			double cost = antennaArray.evaluate(Particle.gBest.getRaw());
			System.out.println("\n iteration "+i + "    GBest cost = "+cost);
			
			for(Particle particle:particles) {
				
				particle.update();
			}
			
		}
		return Particle.gBest;
		
	}
	
	/**
	 * Generate number of  random and valid antenna positions in 1 region,
	 * region set to lower values of 1st antenna/ lower antennas
	 * so antenna position will go in ascending order
	 * eg out of [1,0.5,1.5] and [0.5,1,1.5], the [0.5,1,1.5] will be picked,
	 * this insures no particle will be generated in different regions
	 * 
	 *
	 * @param numOfPos the amount of valid positions you want
	 * @return the array list of axisValues of the positions
	 */
	private ArrayList<AxisValues> generateRandomPosition(int numOfPos) {
	
		ArrayList<AxisValues> positions = new ArrayList<AxisValues>();
		double antenaNum = antennaArray.getNumOfAntenna();
		double minAntennaDistance = AntennaArray.MIN_SPACING;
		
		for(int i = 0;i<numOfPos;i++) {
			ArrayList<Double> values = new ArrayList<Double>();
			
			double maxValue = antenaNum/2;
			double minBound = 0;
			double maxBound = maxValue - ((antenaNum-1)*minAntennaDistance);
			
			double firstAntennaPos = minBound + rand.nextDouble() * (maxBound - minBound);
			
			values.add(firstAntennaPos);
			double prevValue  = firstAntennaPos;
			for(int j = 0;j<(antenaNum-2);j++) {
				minBound = prevValue + minAntennaDistance;
				maxBound = maxBound + minAntennaDistance;
				double nextPos  = minBound + rand.nextDouble() * (maxBound - minBound);
				prevValue= nextPos;
				values.add(nextPos);
			}
			values.add(antenaNum/2); // last antenna pos added
			
			AxisValues res = new AxisValues(values);
			positions.add(res);
		}
		
		return positions;
		
	}
}
