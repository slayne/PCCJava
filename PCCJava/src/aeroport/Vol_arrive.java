package aeroport;
	/**************************************************************************
	* Source File	:  Vol_arrive.java
	* Author                   :  DUMONT  
	* Project name         :  Non enregistré* Created                 :  03/03/2014
	* Modified   	:  04/03/2014
	* Description	:  Definition of the class Vol_arrive
	**************************************************************************/
	
	
	
	import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
	
	
	
	public  class Vol_arrive  extends Vol 
	{ 
		//Inners Classifiers
		
	
		//Attributes
			
			
		
			private Horaire horaireArrive;
			private String villeDarrive;
			private static HashMap<String, Vol_arrive> lesVolsArrives = new HashMap<String, Vol_arrive>();
		
		
			//Operations
			public Vol_arrive(String cde, String cdeAvion, int heure, int minute, String ville){
				super(cde, cdeAvion);
				horaireArrive = new Horaire(heure, minute);
				villeDarrive = ville;
				lesVolsArrives.put(cde, this);
				Tache_debarquement t = new Tache_debarquement(null, this);
				lesTaches.put(t.getId(),t);
				
			}
			
			public Horaire getHoraireArrive(){
				return horaireArrive;
			}
			
			public String getVille(){
				return villeDarrive;
			}
			
			public String toString(){
				return " Arrivée du vol "+super.toString() + " Horaire : " + horaireArrive + " Ville : "+ villeDarrive;
			}
		
			public void lesInstances()
			{
		
				Collection<Vol_arrive> col = lesVolsArrives.values();
				Iterator it = col.iterator();
				while(it.hasNext()){
					System.out.println((Vol_arrive)it.next());
				}
		
			}
			
			
			public static void creerTouslesVolsArrives(String cheminFichier) throws IOException{
				
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
				    
			    	 new Vol_arrive(cde, cdeAvion, heure, minute, ville);
			    	 
			      }
				
			}
			
		
		
	
	} //End Class Vol_arrive
	

