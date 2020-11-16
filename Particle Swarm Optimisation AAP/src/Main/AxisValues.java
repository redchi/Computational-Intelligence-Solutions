package Main;

import java.util.ArrayList;

/*
 * holds the value of all antennas, can be any dimention,
 * can be easily converted to double[] or ArrayList<Double>
 */
public class AxisValues {
	private ArrayList<Double> antennaPos;
	
	public AxisValues(ArrayList<Double> antennaPos) {
		this.antennaPos = antennaPos;
	}
	/*
	 * get antenna value as double array, needed to use with Antenna array class
	 */
	public double[] getRaw() {
		return  antennaPos.stream().mapToDouble(x -> x).toArray();
	}
	/*
	 * get antenna positions, used with everything else
	 */
	public ArrayList<Double> getAntennaPos(){
		return antennaPos;
	}
	
}
