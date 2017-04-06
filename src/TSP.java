import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TSP {

	public ArrayList<Point> listpoints;

	public TSP() {
		listpoints = new ArrayList<Point>();
	}

	public void ajoutPoints(int nbpoints) { //creation de n points
		Point temp = null;
		for (int i = 0; i < nbpoints; i++) {
			double abs = Math.random();
			double ord = Math.random();
			Point a = new Point(abs, ord);
			listpoints.add(a);

		}
	}

	public void circuitrandom(){ // melange de la liste
		Collections.shuffle(listpoints);
	}
	public void circuit() { //  affichage du circuit
		for (int i = 0; i < listpoints.size(); i++) {
			System.out.print(
					"[" + listpoints.get(i).getAbscisse() + "," + listpoints.get(i).getOrdonnee() + "]" + "  -  ");
		}
		System.out.println();
	}

	public void longueur() { // donne la longueur totale du circuit 
		double distance = 0;
		for (int i = 0; i < listpoints.size() - 1; i++) {
			distance += Point.distance(listpoints.get(i), listpoints.get(i + 1));
		}
		distance += Point.distance(listpoints.get(listpoints.size() - 1), listpoints.get(1));

		System.out.println("Distance du circuit : " + distance);
	}

	public static double longueur(ArrayList<Point> p) {
		double distance = 0;
		for (int i = 0; i < p.size() - 1; i++) {
			distance += Point.distance(p.get(i), p.get(i + 1));
		}
		distance += Point.distance(p.get(p.size() - 1), p.get(1));
		System.out.println("Distance du circuit : " );
		return distance;
	}

	public void glouton() { // methode glouton 
		double tempsDebut = System.nanoTime();
		ArrayList<Point> copy = new ArrayList<>(listpoints);
		int size = copy.size();
		double total=0;
		int alea = (int) (Math.random() * (copy.size() - 1 - 0 + 1)) + 0; // valeur aléatoire dans la liste
		ArrayList<Point> tempo = new ArrayList<Point>(); 
		double a = copy.get(alea).getAbscisse();
		double b = copy.get(alea).getOrdonnee();
		tempo.add(copy.get(alea));
		copy.remove(alea);
		while (tempo.size() != size) { // 
			double min = Point.distance(tempo.get(tempo.size() - 1), copy.get(0)); 
			for (int i = 1; i < copy.size(); i++) { // parcours de la liste pour chercher la fistance minimale
				double d = Point.distance(tempo.get(tempo.size() - 1), copy.get(i));
				if (d < min) { // si la distance est minimale on supprime l''élément de notre liste de depart pour l'inserer dans notre liste finale
					min = d;
					tempo.add(copy.get(i));
					copy.remove(copy.get(i));
					break;
				}

			}
			tempo.add(copy.get(0));
			copy.remove(copy.get(0));
			total+=min;

		}
		//System.out.println("size"+tempo.size());
		System.out.println("distance glouton : " + total);
		for (Point p: tempo) {
			System.out.print(
					"[" + p.getAbscisse() + "," + p.getOrdonnee() + "]" + "  -  ");
		}
		double  tempsFin = System.nanoTime();
		double seconds = (tempsFin - tempsDebut) / 1000000F;
		System.out.println();
		System.out.println("Opération GLOUTON effectuée en: "+ Double.toString(seconds) + " secondes.");
	}

	public void mst(){ // implémentation de Krustal non terminée
		ArrayList<Integer> integer = new ArrayList<Integer>();
		Object tableauEntier[][] = new Object[listpoints.size()][3];
		Object circuit[][] = new Object[listpoints.size()][3];
		for(int i=0;i<listpoints.size()-1;i++){ // creation de tous les triplés ( Point a, Point B, distanceAB)
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
				//System.out.print(p.getAbscisse() + "  " );
			}
			for (int j=2;j<3;j++){
				//	System.out.print(tableauEntier[i][j] + "");
			}
			//System.out.println();
		}
		int k=0;
		int tempo=0;
		while(integer.size()!=tableauEntier.length){ // ajout par ordre croisant de distance dans le circuit
			for(int i=0; i<tableauEntier.length;i++){
				double min=2;
				if((double)tableauEntier[i][2]<min && !integer.contains(i)){
					min=(double)tableauEntier[i][2];
					tempo=i;				
				}	
			}
			circuit[k][0]=tableauEntier[tempo][0];
			circuit[k][1]=tableauEntier[tempo][1];
			circuit[k][2]=tableauEntier[tempo][2];
			k+=1;
			integer.add(tempo);
			tempo=0;

		}
		double distance=0;
		for(int i=0; i<circuit.length;i++){
			distance+=(double)circuit[i][2];
		}
		System.out.println("distance mst " + distance);

	}
	public double voisinage(Point a, Point b, Point c, Point d){
		return Point.distance(a, b)+Point.distance(c,d)-Point.distance(a, c)-Point.distance(d, b);
	}



	public void heuristique(){
		double tempsDebut = System.nanoTime();
		ArrayList<Point> tempo = new ArrayList<>(listpoints);
		int j=0;
		int compteur =0;
		for (int i=0; i<tempo.size()-2;i++){ //parcours des couples de villes reliées entre elles
			for (int k=0; k<tempo.size()-1;k++){
				double gain = voisinage(tempo.get(i),tempo.get(i+1),tempo.get(k),tempo.get(k+1));
				if (gain>0){ // si le gain est positif on echange les villes
					Collections.swap(tempo,i+1 ,k);					
				}
			}

		}
		System.out.println("methode heuristique:");
		for (int i = 0; i < tempo.size(); i++) {
			System.out.print(
					"[" + tempo.get(i).getAbscisse() + "," + tempo.get(i).getOrdonnee() + "]" + "  -  ");
		}

		System.out.println("Longueur totale methode heuristique :" + (double)longueur(tempo));
		double  tempsFin = System.nanoTime();
		double seconds = (tempsFin - tempsDebut) / 1000000F;
		System.out.println();
		System.out.println("Opération HEURISTIQUE effectuée en: "+ Double.toString(seconds) + " secondes.");
	}
}






