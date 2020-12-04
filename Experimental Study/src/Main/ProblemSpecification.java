package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class ProblemSpecification {

	private ArrayList<Float> standardCuttingLengths;
	private HashMap<Float,Float> stockLengths2cost;
	private ArrayList<Order> allOrders;
	private String filePath;
	
	public ProblemSpecification(String filePath) {
		this.filePath = filePath;
		generateSpec();
	}
	
	private void generateSpec() {
	     BufferedReader br = null;
	     standardCuttingLengths = null;
	     stockLengths2cost = null;
	     allOrders = null;
	        try {
	        	ArrayList<Float> Stocklenghts = null;
	        	ArrayList<Float> Stockcosts = null;
	        	ArrayList<Float> OrderLenghts = null;
	        	ArrayList<Float> OrderQuantity = null;
	        	
	            br = new BufferedReader(new FileReader(filePath));
	            for(int i = 0;i<6;i++) {
	            	String line = br.readLine();
	            	switch(i) {
	            	case 2: // stock lenghts
	            		Stocklenghts = processLine(line);
	            		break;
	            	case 3: // stock costs
	            		Stockcosts = processLine(line);
	            		break;
	            	case 4: // stock lenghts
	            		OrderLenghts = processLine(line);
	            		break;
	            	case 5: // stock costs
	            		OrderQuantity = processLine(line);
	            		break;
	            	}
	            }
	            
	            standardCuttingLengths = Stocklenghts;
	            stockLengths2cost = generateStockKVPairs(Stocklenghts, Stockcosts);
	            allOrders = generateOrders(OrderLenghts, OrderQuantity);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	         finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	         }
	        
	        
	}
	
	
	private ArrayList<Float> processLine(String line) {
		String [] words = line.split(",");
		String[] firstWords = words[0].split(" ");
		String firstw = firstWords[firstWords.length - 1];
		
		boolean first = true;
		// lenghts or cost 
		ArrayList<Float> lenghts = new ArrayList<Float>();
		for(String word:words) {
			if(first == true) {
				first = false;
				float length = Float.parseFloat(firstw);
				lenghts.add(length);
			}
			else {
				float length = Float.parseFloat(word);
				lenghts.add(length);
			}
		}
		return lenghts;
	}
	
	
	private ArrayList<Order> generateOrders(ArrayList<Float> orderLenghts,ArrayList<Float>orderQuantity) {
		ArrayList<Order> orders = new ArrayList<Order>();
		for(int i = 0;i<orderLenghts.size();i++) {
			float orderLen = orderLenghts.get(i);
			float orderQuan = orderQuantity.get(i);
			for(int j = 0;j<orderQuan;j++) {
				String orderID = orderLen+"-"+j;
				Order order = new Order(orderID, orderLen);
				orders.add(order);
			}
		}
		return orders;
	}
	
	
	private HashMap<Float,Float> generateStockKVPairs(ArrayList<Float>lenghts,ArrayList<Float> costs){
		HashMap<Float, Float>stockLenght2Cost = new  HashMap<Float, Float>();
		for(int i = 0;i<lenghts.size();i++) {
			float length  = lenghts.get(i);
			float cost = costs.get(i);
			stockLenght2Cost.put(length, cost);
		}
		return stockLenght2Cost;
	}

	public ArrayList<Float> getStandardCuttingLengths() {
		return standardCuttingLengths;
	}

	public ArrayList<Order> getAllOrders() {
		return allOrders;
	} 
	
	public float getCostofCuttingStock(float length) {
		return stockLengths2cost.get(length);
	}
	
	
}






















