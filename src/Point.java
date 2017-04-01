

public class Point {
	
	 /*Commencez par développer une classe « Point ». Une telle classe devra notamment
	permettre de créer un point dans le plan (défini par ses ordonnées et abscisses), d’obtenir
	ses principales caractéristiques et de calculer la distance euclidienne entre deux points.
	*/
	public String nom;
	public double abscisse;
	public double ordonnee;
	
	public Point (String n, double a, double o){
		nom=n;
		abscisse=a;
		ordonnee=o;
	}
	public Point ( double a, double o){
		abscisse=a;
		ordonnee=o;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(double abscisse) {
		this.abscisse = abscisse;
	}

	public double getOrdonnee() {
		return ordonnee;
	}

	public void setOrdonnee(double ordonnee) {
		this.ordonnee = ordonnee;
	}
	
	public static double distance(Point a, Point b){
		double xa= a.getAbscisse();
		double xb=b.getAbscisse();
		double ya=a.getOrdonnee();
		double yb=b.getOrdonnee();
		double distance = Math.sqrt(Math.pow(xb-xa,2)+Math.pow(yb-ya,2));
		return distance;
	}
	
}



