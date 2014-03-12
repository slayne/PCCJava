package aeroport;
/**************************************************************************
* Source File	:  Avion.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Avion
**************************************************************************/



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public  class Avion  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private String codeAvion;
		private String modele;
		private int capacite;
		private static HashMap<String, Avion> lesAvions = new  HashMap<String, Avion>();
	
	//Attributes Association
	
		Vol lesVols[];
	 
		

		
		public Avion(String cde, String mod, int cap){
			//System.out.println("zlllllllllll");
			codeAvion = cde;
			modele = mod;
			capacite = cap;
			lesAvions.put(codeAvion, this);
			
			
		}
		

		public Avion(){
			
			codeAvion = null;
			modele = null;
			capacite = 0;
			//lesAvions.put(codeAvion, this);
			
		}
	
		public int getCapacite(){
			return capacite;
		}
		
		public String getModele(){
			return modele;
		}
		
		public String getCodeAvion(){
			return codeAvion;
		}
		
		public String toString(){
			return codeAvion + " " + modele + " " + capacite;
			
		}
		
		public static Avion getAvion(String s){
			return lesAvions.get(s);
		}
		
		public static HashMap<String, Avion> getLesAvions(){
			return lesAvions;
		}
	
		public static void lesInstances()
		{
			Collection<Avion> col = lesAvions.values();
			Iterator it = col.iterator();
			while(it.hasNext()){
				System.out.println((Avion)it.next());
			}
			
	
		}
		
		public static void creerTouslesAvions(String cheminFichier) throws IOException{
			
			 BufferedReader entree = new BufferedReader(new FileReader(cheminFichier));
			 String ligne;
			 StringTokenizer mots;
			 String mot, cde = "null", mod = "null";
			 int cap=0; 
			 
			 while ((ligne = entree.readLine()) != null) // boucle de lecture/affichage du fichier
		      {
		    	 //System.out.println("ligne :" + ligne);
		    	 mots = new StringTokenizer(ligne);
		    	//System.out.println(ligne);
			    		 mot = mots.nextToken();
			    		 cde = mot;
			    		 mot = mots.nextToken();
			    		 mod = mot;
			    		 mot = mots.nextToken();
			    		 cap =  Integer.parseInt(mot);			    	 
			    	 
			    		
			    	 new Avion(cde, mod, cap);
			    	//System.out.println(a.toString());
		 
		    	
		    		 
		      }
			
			
		}
	
		
	
	

} //End Class Avion


