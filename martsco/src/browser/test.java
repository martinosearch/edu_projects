package browser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;


public class test{
	
	public void getTrie1(){
		ArrayList<rgValue> liste=new ArrayList<rgValue>();
		liste.add(new rgValue("KOMLA",10));
		liste.add(new rgValue("YAWO",12.5));
		liste.add(new rgValue("AGBA",5));
		liste.add(new rgValue("TCHALA",8));
		liste.add(new rgValue("LARE",15));
		
		noteComparator nc=new noteComparator();
		Ordering<rgValue> ordering=Ordering.from(nc).reverse().nullsLast();
		
		List<rgValue> result=ordering.sortedCopy(liste);
		
		System.out.println("Rang   Nom                        Moyenne");
		System.out.println("-------------------------------------------");
		for(int i=0;i<result.size();i++){
			System.out.println((i+1)+".  "+result.get(i).nom+
					"         "+result.get(i).getNote());
		}
	}

	public static void main(String[] args) {
		
		new test().getTrie1();
	}
	
	public class noteComparator implements Comparator<rgValue>{
		public int compare(rgValue v1,rgValue v2){
			int result=0;
			ComparisonChain chain=ComparisonChain.start();
		
			chain=chain.compare(v1.getNote(),v2.getNote(),Ordering.natural().
				nullsLast())
				.compare(v1.getNom(),v2.getNom(),Ordering.natural().
				nullsLast());
		
		result=chain.result();
		return result;
		}
	}
	
	
	public class rgValue{
		private String nom;
		private double note;
	
		public rgValue(String nom1,double note1){
			this.nom=nom1;
			this.note=note1;
		}
		
		public String getNom(){
			return this.nom;
		}
		
		public double getNote(){
			return this.note;
		}
	}
}
