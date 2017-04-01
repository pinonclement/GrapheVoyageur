import java.util.ArrayList;

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
		    System.out.println(p.getAbscisse());
		}
	}

}
