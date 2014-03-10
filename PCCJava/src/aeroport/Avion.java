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
		private static HashMap<String, Avion> lesAvions;
	
	//Attributes Association
	
		Vol lesVols[];
	 
		

		
		public Avion(String cde, String mod, int cap){
			
			codeAvion = cde;
			modele = mod;
			capacite = cap;
			lesAvions.put(codeAvion, this);
			
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
	
		public void lesInstances()
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
		    	 
		    	 for(int i=0; i<3;i++){
		    		 mot = mots.nextToken();
		    		 //System.out.println("Mot :" + mot);
		    		 if(i==0){
		    			 cde = mot;
		    		 }else if(i==1) {
		    			 mod = mot;
		    		 }else if(i==2){
		    			cap =  Integer.parseInt(mot);
		    		 }
		    	 
		    	 }
		    		
		    	 new Avion(cde, mod, cap);
		    		 
		      }
			
		}
	
		
	
	

} //End Class Avion


