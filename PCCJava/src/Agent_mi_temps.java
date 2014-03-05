/**************************************************************************
* Source File	:  Agent_mi_temps.java
* Author                   :  DUMONT  
* Project name         :  Non enregistr�* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent_mi_temps
**************************************************************************/



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;



public  class Agent_mi_temps  extends Agent 
{ 

	//Attributes
	private static Hashtable <String,Agent_mi_temps> lesAgentsMiTemps;
	
	//Operations
	
	public Agent_mi_temps (String id, String n, String p, int c) {
		super(id,n,p,c);
		lesAgentsMiTemps.put(super.getCodeAgent(),this);
	}
	
	public static void lesAgentsMiTemps(String fichier) {
		String chaine="";
		//lecture du fichier texte	ligne par ligne
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String valeurs [];
			Agent_mi_temps temp;
			while ((ligne=br.readLine())!=null){
				valeurs=ligne.split(" "); // Recuperation des termes de la ligne dans un tableau de string
				temp = new Agent_mi_temps(valeurs[0],valeurs[1],valeurs[2],Integer.parseInt(valeurs[3]));
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public TrancheHoraire calculTrancheHoraire(int numSemaine) {
		Horaire deb = null;
		Horaire fin = null;
		
		if((super.getCodeCycle()==1 && numSemaine%3==1) || (super.getCodeCycle()==2 && numSemaine%3==2) || (super.getCodeCycle()==3 && numSemaine%3==0)) {
			// horaire normale
			deb = new Horaire(9,0);
			fin = new Horaire(12,30);
		}
		if((super.getCodeCycle()==1 && numSemaine%1==2) || (super.getCodeCycle()==2 && numSemaine%3==0) || (super.getCodeCycle()==3 && numSemaine%3==1)) {
			// horaire matin
			deb = new Horaire(5,30);
			fin = new Horaire(9,0);
		}
		if((super.getCodeCycle()==1 && numSemaine%3==0) || (super.getCodeCycle()==2 && numSemaine%3==1) || (super.getCodeCycle()==3 && numSemaine%3==2)) {
			// horaire soir
			deb = new Horaire(20,0);
			fin = new Horaire(23,30);
		}
		TrancheHoraire t = new TrancheHoraire (deb,fin);
		return t;
		
	}
		
	
	

} //End Class Agent_mi_temps


