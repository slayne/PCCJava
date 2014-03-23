package aeroport;
/**************************************************************************
* Source File	:  Tache_repas.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Tache_repas
**************************************************************************/



import java.util.*;



public  class Tache_repas  extends Tache 
{ 
	
		private static HashMap<Integer,Tache_repas> lesTachesRepas = new HashMap<Integer,Tache_repas>();
		private static int ident=1;
		
		
		public Tache_repas(Horaire dd, Horaire df, Agent a){
			super(dd,df,a,null);
			this.setType("Repas");
			lesTachesRepas.put(ident, this);
			ident++;
			
		}
		
		public static void lesInstances()
		{
		
				for(int i=0; i<lesTachesRepas.size();i++){
		   			System.out.println(lesTachesRepas.get(i).toString());
				}
		
		}
		
		
		public String toString(){
			
			return super.toString();
		}
		
	

} //End Class Tache_repas


