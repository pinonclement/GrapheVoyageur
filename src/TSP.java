import java.util.ArrayList;

public class TSP {
	
	public ArrayList <Point> listpoints;
	
	public TSP(){
		listpoints=new ArrayList<Point>();
	}

	public void ajoutPoints(int nbpoints){
		Point temp=null;
		for (int i=0; i<nbpoints; i++){
			double abs=Math.random();
			double ord=Math.random();
			Point a =new Point(abs,ord);
			listpoints.add(a);
			
		}
	}
	
	public void circuit () {
		for(int i=0; i<listpoints.size(); i++){
			System.out.print("["+listpoints.get(i).getAbscisse()+","+listpoints.get(i).getOrdonnee()+"]"+"  -  ");
		}
	}

	public void longueur (){
		double distance=0;
		for(int i=0; i<listpoints.size()-1; i++){
			distance+=Point.distance(listpoints.get(i),listpoints.get(i+1));		
		}
		distance+=Point.distance(listpoints.get(listpoints.size()-1),listpoints.get(1));
		System.out.println("Distance du circuit : " + distance);
	}
}
