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
		for(int i=0; i<listpoints.size(); i++){
			
		}
	}
}
