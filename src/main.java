
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TSP tsp = new TSP();
		tsp.ajoutPoints(4);
		tsp.circuit();
		System.out.println("++");
		//tsp.longueur();
		//tsp.glouton();
		tsp.mst();
	}

}
