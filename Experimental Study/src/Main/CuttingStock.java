package Main;

import java.util.ArrayList;

public class CuttingStock {

	private ArrayList<Order> ordersCutByStock;
	private float length;
	private float cost;
	
	public CuttingStock(float length,float cost) {
		ordersCutByStock = new ArrayList<Order>();
		this.length = length;
		this.cost = cost;
	}
	
	public ArrayList<Order> getOrdersCutByStock() {
		return ordersCutByStock;
	}

	public float getLength() {
		return length;
	}

	public float getCost() {
		return cost;
	}

	public void addOrder(Order order) {
		ordersCutByStock.add(order);
		
	}
	
	public float getUsedLength() {
		float totalUsed = 0;
		for(Order order:ordersCutByStock) {
			totalUsed = totalUsed + order.getLength();
		}
		return totalUsed;
	}
	
	public boolean canCutOrder(Order orderNew) {
		float totalUsed = 0;
		for(Order order:ordersCutByStock) {
			totalUsed = totalUsed + order.getLength();
		}
		totalUsed = totalUsed + orderNew.getLength();
		return totalUsed<=this.length;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String res = "Len = "+ length+"  Orders Cut = ";
		for(Order order:ordersCutByStock) {
			res = res + order.getID() + ",";
		}
		res = res + " ";
		return res;
	}
	
	
	public CuttingStock clone() {
		CuttingStock res = new CuttingStock(this.length, this.cost);
		for(Order order:ordersCutByStock) {
			res.addOrder(order.clone());
		}
		return res;
	}
	
	public boolean orderExistsInsideStock(Order order) {
		return ordersCutByStock.contains(order);
	}
	
	public void removeOrder(Order order) {
		ordersCutByStock.remove(order);
	}
}



















