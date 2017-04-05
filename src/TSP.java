import java.util.ArrayList;
import java.util.Collections;

public class TSP {

	public ArrayList<Point> listpoints;

	public TSP() {
		listpoints = new ArrayList<Point>();
	}

	public void ajoutPoints(int nbpoints) {
		Point temp = null;
		for (int i = 0; i < nbpoints; i++) {
			double abs = Math.random();
			double ord = Math.random();
			Point a = new Point(abs, ord);
			listpoints.add(a);

		}
	}

	public void circuitrandom(){
		Collections.shuffle(listpoints);
	}
	public void circuit() {
		for (int i = 0; i < listpoints.size(); i++) {
			System.out.print(
					"[" + listpoints.get(i).getAbscisse() + "," + listpoints.get(i).getOrdonnee() + "]" + "  -  ");
		}
	}

	public void longueur() {
		double distance = 0;
		for (int i = 0; i < listpoints.size() - 1; i++) {
			distance += Point.distance(listpoints.get(i), listpoints.get(i + 1));
		}
		distance += Point.distance(listpoints.get(listpoints.size() - 1), listpoints.get(1));
		System.out.println("Distance du circuit : " + distance);
	}

	public void glouton() {
		int size = listpoints.size();
		double total=0;
		int alea = (int) (Math.random() * (listpoints.size() - 1 - 0 + 1)) + 0;
		ArrayList<Point> tempo = new ArrayList<Point>();
		System.out.println(alea);
		double a = listpoints.get(alea).getAbscisse();
		double b = listpoints.get(alea).getOrdonnee();
		System.out.println(a + "   " + b);
		tempo.add(listpoints.get(alea));
		listpoints.remove(alea);
		while (tempo.size() != size) {
			double min = Point.distance(tempo.get(tempo.size() - 1), listpoints.get(0));
			for (int i = 1; i < listpoints.size(); i++) {
				double d = Point.distance(tempo.get(tempo.size() - 1), listpoints.get(i));
				if (d < min) {
					min = d;
					tempo.add(listpoints.get(i));
					listpoints.remove(listpoints.get(i));
					break;
				}

			}
			tempo.add(listpoints.get(0));
			listpoints.remove(listpoints.get(0));
			total+=min;

		}
		System.out.println("size"+tempo.size());
		System.out.println("total" + total);
		for (Point p: tempo) {
			System.out.print(
					"[" + p.getAbscisse() + "," + p.getOrdonnee() + "]" + "  -  ");
		}

	}

	public void mst(){
		Object test[]= new Object [2];
		Object tableauEntier[][] = new Object[listpoints.size()][3];
		int circuit[][][] = new int[listpoints.size()][][];
		for(int i=0;i<listpoints.size()-1;i++){
			tableauEntier[i][0]=listpoints.get(i);
			tableauEntier[i][1]=listpoints.get(i+1);
			tableauEntier[i][2]=Point.distance(listpoints.get(i), listpoints.get(i+1));
		}


		tableauEntier[tableauEntier.length-1][0]=listpoints.get(listpoints.size() - 1);
		tableauEntier[tableauEntier.length-1][1]=listpoints.get(0);
		tableauEntier[tableauEntier.length-1][2]=Point.distance(listpoints.get(listpoints.size() - 1), listpoints.get(0));
		for(int i=0;i<tableauEntier.length;i++){
			for (int j=0;j<2;j++){
				Point p = (Point) tableauEntier[i][j];
				System.out.print(p.getAbscisse() + "  " );
			}
			for (int j=2;j<3;j++){
				System.out.print(tableauEntier[i][j] + "");
			}
			System.out.println();
		}
	}

	public double voisinage(Point a, Point b, Point c, Point d){
		return Point.distance(a, b)+Point.distance(c,d)-Point.distance(a, c)-Point.distance(d, b);
	}


	public void heuristique(){
		ArrayList<Point> tempo =listpoints;
		int j=0;
		while(j!=tempo.size()*2){
		for (int i=0; i<tempo.size()-2;i++){
			double gain = voisinage(tempo.get(i),tempo.get(i+1),tempo.get(i+1),tempo.get(i+2));
			if (gain>0){
				Collections.swap(tempo, i+1, i+2);	
			}
			else 
				j+=1;
		}
		}
	}
}





