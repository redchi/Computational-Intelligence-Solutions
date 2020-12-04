package JunitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import Main.CuttingStock;
import Main.Initialiser;
import Main.Order;
import Main.ProblemSpecification;
import Main.Solution;

class solutionTest {

	
	
	// tests that solution doesnt have missing or duplicate orders and that all orders are fufilled
	@Test
	void test1() {
		
		
		ProblemSpecification probSpec = new ProblemSpecification("./problems/prob1.txt");
		Initialiser ini = new Initialiser(probSpec,1233);
		Solution sol = ini.gen1Sol();
		ArrayList<CuttingStock> stocks = sol.getAllStockUsed();
		
		ArrayList<Order> allOrders = new ArrayList<Order>();
		for(CuttingStock stock:stocks) {
			allOrders.addAll(stock.getOrdersCutByStock());
		}
		
		assertEquals(probSpec.getAllOrders().size(), allOrders.size());
		
		
		
		List<Order> tempArr = allOrders;
		Set<Order> set = new HashSet<Order>(tempArr);
		
		assertEquals(true,set.size()==tempArr.size());
		
		
		
		
	}


}











