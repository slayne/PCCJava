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
		String choix;
		
		HashMap<String, Avion> tousLesAvions = new HashMap<String, Avion>(); //HashMap de tous les avions/
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
		
		//Tache.afficherLesTaches();
		//Affectation des taches
		Agent.construirePlanning();
		Agent.afficherLesAgents();
		//System.out.println(Agent.getLesAgents().get("P0017").toString());
		System.out.println(Tache.getNbTacheNonAffecte());
		System.out.println(Tache.getNbTacheAffecte());
		System.out.println(Tache.getLesTaches().size());
		System.out.println(Tache.getTacheRepas());
		System.out.println(Agent_temps_plein.getAgentsTempsPlein().size());
		//ArrayList<Agent> lesA = new ArrayList(Agent.toArray)
		
		
		//Le système affiche le MENU ...
		//menuPrincipal();
		
		//Tache.afficherLesTaches();
				
		//Consulter planning taches agent
		//choix = "P0022"; //"interagir("Saisir le numéro d'un agent : ");
		//System.out.println("Vous avez saisi : " + choix);
	//	Agent.afficherListeTachesUnAgent(choix);
		 
		 
		//Consulter planning taches liées a un vol
		//Vol.afficherListeDesVols();
		//choix = "IT4444"; //"interagir("Saisir le numéro d'un vol : ");
		//System.out.println("Vous avez saisi : " + choix);
		//Vol.afficherListeTachesVol(choix); 
		
		/*
		 * 
		 * TESTS CEC
		 * 
		System.out.println("\n\nTest apres suppression de l'agent P002");
		// Test absence agent
		Agent.abscenceAgent("P0002");
		Agent.afficherLesAgents();
		//System.out.println(Agent.getLesAgents().get("P0017").toString());
		System.out.println(Tache.getNbTacheNonAffecte());
		System.out.println(Tache.getNbTacheAffecte());
		System.out.println(Tache.getLesTaches().size());
		System.out.println(Tache.getTacheRepas());
		System.out.println(Agent_temps_plein.getAgentsTempsPlein().size());
		//ArrayList<Agent> lesA = new ArrayList(Agent.toArray)
		
		tousLesAgents = Agent.getLesAgents();
		System.out.println("Nombre d'agents : " +tousLesAgents.size());
		System.out.println("Nombre de taches :" + Tache.toutesLesTaches().size());*/
		
		/*
		// Test deletion vol
		Vol.deletionVol("IT4444");
		Agent.afficherLesAgents();
		//System.out.println(Agent.getLesAgents().get("P0017").toString());
		System.out.println(Tache.getNbTacheNonAffecte());
		System.out.println(Tache.getNbTacheAffecte());
		System.out.println(Tache.getLesTaches().size());
		System.out.println(Tache.getTacheRepas());
		System.out.println(Agent_temps_plein.getAgentsTempsPlein().size());
		tousLesVols = Vol.getLesVols();
		System.out.println("Nombre de vols : " +tousLesVols.size());
		 
		
		
		// Test retard vol
		Vol.retardVol("IT1919",new Duree(0,30));
		Agent.afficherLesAgents();
		//System.out.println(Agent.getLesAgents().get("P0017").toString());
		System.out.println(Tache.getNbTacheNonAffecte());
		System.out.println(Tache.getNbTacheAffecte());
		System.out.println(Tache.getLesTaches().size());
		System.out.println(Tache.getTacheRepas());
		System.out.println(Agent_temps_plein.getAgentsTempsPlein().size());
		
		
		
		//Test retard vol OK
		Vol.retardVol("IT1919",new Duree(4,0));
		Agent.afficherLesAgents();
		//System.out.println(Agent.getLesAgents().get("P0017").toString());
		System.out.println(Tache.getNbTacheNonAffecte());
		System.out.println(Tache.getNbTacheAffecte());
		System.out.println(Tache.getLesTaches().size());
		System.out.println(Tache.getTacheRepas());
		System.out.println(Agent_temps_plein.getAgentsTempsPlein().size());
		*/

	

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
