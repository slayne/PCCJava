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
			return "Code : "+codeVol+" Avion : "+lAvion;
		}
	
		
		
		public static void creerTousLesVols(String fichierArrive, String fichierDepart) throws IOException{
			Vol_arrive.creerTouslesVolsArrives(fichierArrive);
			Vol_depart.creerTouslesVolsDepart(fichierDepart);
			
			
		}
		
		
		
		public static void afficherListeTachesVol(String cde){
			
			for(Vol v : lesVols.values()){
				if(v.getCodeVol().equals(cde)){
					System.out.println("\nListe des taches associées au vol : " + v.getCodeVol());
					for(Tache t : v.getLesTaches().values()){
						System.out.println(t.toString());
					}
				}
				
			}
			
			
			
		}
		
		
		
		public static void afficherListeDesVols(){
			
			System.out.println("\nListe des vols : ");
			for(Vol v : lesVols.values()){
				System.out.println(v.toString());
			}
		}
		

	
	

} //End Class Vol


