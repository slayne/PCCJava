package aeroport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Bienvenue deans le programme de Gestion des plannings !");
		
		
		// Variables .... 
		String Favions = "avions14-v1.txt";
		String FagentsMitemps = "AgentsMiTemps-14-v1.txt";
		String FagentsTempsPlein = "AgentsTempsPlein-14-v1.txt";
		String FvolA = "ProgrammeVolsArrivees14-v2.txt";
		String FvolD = "ProgrammeVolsDeparts14-v2.txt";
		
		
		HashMap<String, Avion> tousLesAvions = new HashMap<String, Avion>(); //HashMap de tous les avions
		HashMap<String, Agent> tousLesAgents = new HashMap<String, Agent>(); //HashMap de tous les agents
		HashMap<String, Vol> tousLesVols = new HashMap<String, Vol>(); //HashMap de tous les Vols
		
		
		
		try {
			// Création des avions
			Avion.creerTouslesAvions(Favions);
			tousLesAvions = Avion.getLesAvions();
			System.out.println("Nombre d'avions : " +tousLesAvions.size());
			
			//Création des vols
			Vol.creerTousLesVols(FvolA, FvolD);
			tousLesVols = Vol.getLesVols();
			System.out.println("Nombre de vols : " +tousLesVols.size());
			
			//Affichage du nombre de taches
			System.out.println("Nombre de taches :" + Tache.toutesLesTaches().size());
			
			//Création des agents
			Agent.creerTouslesAgents(FagentsMitemps, FagentsTempsPlein);
			tousLesAgents = Agent.getLesAgents();
			System.out.println("Nombre d'agents : " +tousLesAgents.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Nombre de taches :" + Tache.toutesLesTaches().size());
		Tache.afficherLesTaches();
		//Affectation des taches
		Agent.construirePlanning();
		//ArrayList<Agent> lesA = new ArrayList(Agent.toArray)
		
		System.out.println("Nombre de taches :" + Tache.toutesLesTaches().size());
		
		
		//Le système affiche le MENU ...
		menuPrincipal();
		
		Tache.afficherLesTaches();
				
		//Consulter planning taches agent
		Agent.afficherLesAgents();
		//String choix = "P0022"; //"interagir("Saisir le numéro d'un agent : ");
		//System.out.println("Vous avez saisi : " + choix);
		//Agent.afficherListeTachesUnAgent(choix);
		 
		 
		//Consulter planning taches liées a un vol
		 
		 
		 
		 
		
		
		

	}
	
	
	public static void menuPrincipal(){
		System.out.println("MENU PRINCIPAL");
		System.out.println("1 : Consulter planning taches agent");
	}
	
	public static String interagir(String texte){
		String retour = null;
		System.out.println(texte);
		
		Scanner sc = new Scanner(System.in);
	    retour = sc.nextLine();
		//System.out.println("Vous avez saisi : " + texte);
		
		return retour; 
	}
	
	

}
