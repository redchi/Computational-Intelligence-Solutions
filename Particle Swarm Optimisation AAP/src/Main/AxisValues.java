package Main;

import java.util.ArrayList;

public class AxisValues {
	ArrayList<Double> antennaPos;
	
	public AxisValues(ArrayList<Double> antennaPos) {
		this.antennaPos = antennaPos;
	}
	
	public double[] getRaw() {
		return  antennaPos.stream().mapToDouble(x -> x).toArray();
	}
}
