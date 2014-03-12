package aeroport;
/**************************************************************************
* Source File	:  Agent_mi_temps.java
* Author                   :  DUMONT  
* Project name         :  Non enregistr�* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent_mi_temps
**************************************************************************/



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;



public  class Agent_mi_temps  extends Agent 
{ 

	//Attributes
	private static HashMap <String,Agent_mi_temps> lesAgentsMiTemps = new HashMap <String,Agent_mi_temps>();

	//Operations
	
	public Agent_mi_temps (String cde, String n, String p, int c) {
		super(cde,n,p,c);
		lesAgentsMiTemps.put(cde,this);
	}
	
	public Agent_mi_temps () {
		super();
		
	}

	
	public static void creerlesAgentsMiTemps(String fichier) throws NumberFormatException, IOException {
		
		 BufferedReader entree = new BufferedReader(new FileReader(fichier));
		 String ligne;
		 StringTokenizer mots;
		 String mot, cde = "null", nom = "null", prenom = "null";
		 int cycle=0; 
		 
		 while ((ligne = entree.readLine()) != null) // boucle de lecture/affichage du fichier
	      {
	    	 //System.out.println("ligne :" + ligne);
	    	 mots = new StringTokenizer(ligne);
	    	//System.out.println(ligne);
		    		 mot = mots.nextToken();
		    		 cde = mot;
		    		 mot = mots.nextToken();
		    		 nom = mot;
		    		 mot = mots.nextToken();
		    		 prenom = mot;
		    		 mot = mots.nextToken();
		    		 cycle =  Integer.parseInt(mot);			    	 
		    	 
		    		
		    new Agent_mi_temps(cde, nom, prenom, cycle);
	      }
	}
	
	
	public String toString(){
		return "Agent mi temps :" + super.toString();
		
	}
	
	
	public TrancheHoraire calculTrancheHoraire(int numSemaine) {
		Horaire deb = null;
		Horaire fin = null;
		
		if((getCodeCycle()==1 && numSemaine%3==1) || (getCodeCycle()==2 && numSemaine%3==2) || (getCodeCycle()==3 && numSemaine%3==0)) {
			// horaire normale
			deb = new Horaire(9,0);
			fin = new Horaire(12,30);
		}
		if((getCodeCycle()==1 && numSemaine%1==2) || (getCodeCycle()==2 && numSemaine%3==0) || (getCodeCycle()==3 && numSemaine%3==1)) {
			// horaire matin
			deb = new Horaire(5,30);
			fin = new Horaire(9,0);
		}
		if((getCodeCycle()==1 && numSemaine%3==0) || (getCodeCycle()==2 && numSemaine%3==1) || (getCodeCycle()==3 && numSemaine%3==2)) {
			// horaire soir
			deb = new Horaire(20,0);
			fin = new Horaire(23,30);
		}
		TrancheHoraire t = new TrancheHoraire (deb,fin);
		return t;
		
	}

	@Override
	public boolean peutFaireRepas(Tache t) {
		// TODO Auto-generated method stub
		return true;
	}
		
	
	

} //End Class Agent_mi_temps


