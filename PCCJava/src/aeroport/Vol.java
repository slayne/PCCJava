package aeroport;
/**************************************************************************
* Source File	:  Vol.java
* Author                   :  DUMONT  
* Project name         :  Non enregistr�* Created                 :  03/03/2014
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
		
		abstract public String getVille();
		abstract public Horaire getHoraire();
	
	
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
					System.out.println("\nListe des taches associ�es au vol : " + v.getCodeVol());
					for(Tache t : v.getLesTaches().values()){
						System.out.println(t.toString());
					}
				}
				
			}
			
			
			
		}
		
		
		
		public static void afficherListeDesVols(){
			
			System.out.println("\nListe des vols : ");
			for(Vol v : lesVols.values()){
				v.afficherVol();
			}
		}
		
		public static void afficherVol (String code) {
			System.out.println(lesVols.get(code).toString());
		}
		
		public void afficherVol () {
			System.out.println(this.toString());
		}
		
		public static void volsArrive() {
			System.out.println("\nListe des vols arriv�e : ");
			for(Vol v : lesVols.values()){
				if(v instanceof Vol_arrive) {
					v.afficherVol();
				}
			}
			
		}
		
		public static void volsDepart() {
			System.out.println("\nListe des vols d�part : ");
			for(Vol v : lesVols.values()){
				if(v instanceof Vol_depart) {
					v.afficherVol();
				}
			}
			
		}

		
		public static void deletionVol (String s) {
			for(Tache t : lesVols.get(s).lesTaches.values()){
				if(t.getAgent()!=null) { // La tache est affect�e
					t.getAgent().lesTaches.remove(t.getId());	// suppression de la tache du planning de l'agent
					(t.getAgent()).affecterTacheAccueilAfter();			// affectation d'une tache d'accueil si besoin
				}
				(Tache.getLesTaches()).remove(t.getId());		// suppression de la tache de la liste static des taches
			}
			lesVols.remove(s);									// suppression du vol de la liste static des vols
		}
		
		public static void retardVol (String s, Duree d) {
			for(Tache t : lesVols.get(s).lesTaches.values()){
				(t.getAgent()).retardTache(t,d);
			}
		}
		

		

	
	

} //End Class Vol


