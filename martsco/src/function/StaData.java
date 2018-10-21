package function;

public class StaData {
	//Classe d�finissant des donn�es statistisque
		String intitule="";
		double moy=0.001;
		String sexe="M";
		private boolean hasCompose;
		
		public StaData(String inti,double moy1,String sex,boolean hasComp){
			this.intitule=inti;
			this.moy=moy1;
			this.sexe=sex;
			this.hasCompose=hasComp;
		}
		
		public String getIntitule(){
			return this.intitule;
		}
		public String getSexe(){
			return this.sexe;
		}
		public double getMoyenne(){
			return this.moy;
		}
		
		public void setHasCompose(boolean bool){
			hasCompose=bool;
		}
		
		public boolean hasCompose(){
			return hasCompose;
		}

	public static void main(String[] args) {
	}

}
