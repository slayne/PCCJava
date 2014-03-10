package aeroport;
/**************************************************************************
* Source File	:  Agent_temps_plein.java
* Author                   :  DUMONT  
* Project name         :  Non enregistrï¿½* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent_temps_plein
**************************************************************************/



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


public  class Agent_temps_plein  extends Agent 
{ 

	//Attributes
	private static Hashtable <String,Agent_temps_plein> lesAgentsTempsPlein;
	
	//Operations
		
	public Agent_temps_plein (String id, String n, String p, int c) {
		super(id,n,p,c);
		lesAgentsTempsPlein.put(super.getCodeAgent(),this);
	}
	
	public Agent_temps_plein() {
		// TODO Auto-generated constructor stub
	}

	public static void lesAgentsTempsPlein (String fichier) {
		String chaine="";
		//lecture du fichier texte	ligne par ligne
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String valeurs [];
			Agent_temps_plein temp;
			while ((ligne=br.readLine())!=null){
				valeurs=ligne.split(" "); // Recuperation des termes de la ligne dans un tableau de string
				temp = new Agent_temps_plein(valeurs[0],valeurs[1],valeurs[2],Integer.parseInt(valeurs[3]));
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public TrancheHoraire calculTrancheHoraire(int numSemaine) {
		
		Horaire deb = null, fin = null;
		if((super.getCodeCycle()==1 && numSemaine%3==1) || (super.getCodeCycle()==2 && numSemaine%3==2) || (super.getCodeCycle()==3 && numSemaine%3==0)) {
			// horaire normale
			deb = new Horaire(9,0);
			fin = new Horaire(17,0);
		}
		if((super.getCodeCycle()==1 && numSemaine%1==2) || (super.getCodeCycle()==2 && numSemaine%3==0) || (super.getCodeCycle()==3 && numSemaine%3==1)) {
			// horaire matin
			deb = new Horaire(6,0);
			fin = new Horaire(14,0);
		}
		if((super.getCodeCycle()==1 && numSemaine%3==0) || (super.getCodeCycle()==2 && numSemaine%3==1) || (super.getCodeCycle()==3 && numSemaine%3==2)) {
			// horaire soir
			deb = new Horaire(13,30);
			fin = new Horaire(21,30);
		}
		TrancheHoraire t = new TrancheHoraire (deb,fin);
		return t;
		
	}
	
	public boolean peutFaireRepas(Tache t){
		boolean res=false;
		
	// faire toutes les conditions
		if(t.chevauche(new TrancheHoraire(new Horaire(11, 30), new Horaire(14,00)))){
			//est ce que si on ajoute la tranche horaire qu'on tente de lui affecter respect 2 conditions ?
			// 1 : Il a 1h pour manger (début du repas entre 11h30 et 14h, peut terminer plus tard)
			// 2: La fin de son travail se termine 1h après au minimum (ex:si il fini à 15h, il doit manger au plus tard à 14)
		}
		
		
		return res;
	}
	
	


} //End Class Agent_temps_plein


