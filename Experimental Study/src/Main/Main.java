package Main;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void start() {
		ProblemSpecification ss = new ProblemSpecification("yeet");
		ss.generateSpec("./problems/prob1.txt");


	}
}
