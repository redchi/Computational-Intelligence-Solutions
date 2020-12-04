package Main;

import java.util.ArrayList;

public class CuttingStock {

	ArrayList<Order> ordersCutByStock;
	
	public CuttingStock(float length,float cost) {
		ordersCutByStock = new ArrayList<Order>();
	}
	
	public void addOrder(Order order) {
		ordersCutByStock.add(order);
	}
	
}
