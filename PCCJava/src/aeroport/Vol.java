package aeroport;
/**************************************************************************
* Source File	:  Vol.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Vol
**************************************************************************/



import java.io.IOException;
import java.util.*;



public abstract class Vol  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private String codeVol;
		
	
	//Attributes Association
	
		private String lAvion;
	 
		protected HashMap<Integer,Tache> lesTaches = new HashMap<Integer,Tache>();
		private static HashMap<String, Vol> lesVols= new HashMap<String, Vol>();
		
		
		public Vol(String cde, String cdeAvion){
			
			codeVol = cde;
			lAvion = cdeAvion;
			lesVols.put(codeVol, this);
			
			
		}
	
	
		public HashMap<Integer, Tache> getLesTaches(){
			return lesTaches;
		}
		public String getLavion(){
			return lAvion;
		}
		
		public String getCodeVol(){
			return codeVol;
		}
		
		public static HashMap<String, Vol> getLesVols(){
			return lesVols;
		}
		

		
		public String toString(){
			return codeVol;
		}
	
		public void lesInstances()
		{
	
			Collection<Vol> col = lesVols.values();
			Iterator it = col.iterator();
			while(it.hasNext()){
				System.out.println((Vol)it.next());
			}
	
		}
		
		
		public static void creerTousLesVols(String fichierArrive, String fichierDepart) throws IOException{
			Vol_arrive.creerTouslesVolsArrives(fichierArrive);
			Vol_depart.creerTouslesVolsDepart(fichierDepart);
			
			
		}
		
		
		
		public static void afficherListeTachesUnVol(Vol v){
			
		}
		
		
		
		public static void afficherListeDesVols(){
			
		}
		
	
		
	
	

} //End Class Vol


