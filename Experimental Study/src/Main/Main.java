package Main;

public class Main {
	
	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void start() {
		ProblemSpecification probSpec = new ProblemSpecification("./problems/prob1.txt");
		Initialiser ini = new Initialiser(probSpec);
		Solution sol = ini.gen1Sol();
		System.out.println("done");
	}
}
