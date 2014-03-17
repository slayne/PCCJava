package aeroport;
/**************************************************************************
* Source File	:  Vol_depart.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Vol_depart
**************************************************************************/



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public  class Vol_depart  extends Vol 
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private Horaire horaireDepart;
		private String villeDepart;
		private static HashMap<String, Vol_depart> lesVolsDepart = new HashMap<String, Vol_depart>();
		
		
		//Operations
		
		public Vol_depart(String cde, String cdeAvion, int heure, int minute, String ville){
			super(cde, cdeAvion);
			horaireDepart = new Horaire(heure, minute);
			villeDepart = ville;
			lesVolsDepart.put(cde, this);
			int capa = Avion.getAvion(this.getLavion()).getCapacite()/90;
			
			for(int i=0;i<capa;i++){
				Tache_enregistrement t = new Tache_enregistrement(new Agent_temps_plein(), this);
				lesTaches.put(t.getId(), t);
			}
			
			Tache_embarquement t = new Tache_embarquement(null, this);
			lesTaches.put(t.getId(),t);
			
		}
		
		public static ArrayList<Vol_depart> getVolsDepart(){
			return new ArrayList<Vol_depart>(lesVolsDepart.values());
		}
		
		
		public Horaire getHoraireDepart(){
			return horaireDepart;
		}
		
		public String getVille(){
			return villeDepart;
		}
		
		public String toString(){
			return "Départ du vol "+super.toString() + " Horaire : " + horaireDepart + " Ville : "+ villeDepart;
		}
	
		public void lesInstances()
		{
	
			Collection<Vol_depart> col = lesVolsDepart.values();
			Iterator it = col.iterator();
			while(it.hasNext()){
				System.out.println((Vol_depart)it.next());
			}
	
		}
		
		
		public static void creerTouslesVolsDepart(String cheminFichier) throws IOException{
			
			 BufferedReader entree = new BufferedReader(new FileReader(cheminFichier));
			 String ligne;
			 StringTokenizer mots;
			 String mot, cde = "null", ville = "null", cdeAvion = "null" ;
			 int cap=0; 
			 int heure = 0;
			 int minute = 0;
			 
			 while ((ligne = entree.readLine()) != null) // boucle de lecture/affichage du fichier
		      {
		    	 //System.out.println("ligne :" + ligne);
		    	 mots = new StringTokenizer(ligne);
		    	 
		    		mot = mots.nextToken();
		    		cde = mot;
		    		mot = mots.nextToken();
		    		heure = Integer.parseInt(mot);
		    		mot = mots.nextToken();
		    		minute =  Integer.parseInt(mot);
		    		mot = mots.nextToken();
		    		ville =  mot;
		    		mot = mots.nextToken();
			    	cdeAvion =  mot;
		    		
		    	 new Vol_depart(cde, cdeAvion, heure, minute, ville);
		    	 
		      }
			
		}
	
		
	
	

} //End Class Vol_depart


