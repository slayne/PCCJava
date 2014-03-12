package aeroport;

import java.io.IOException;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Bienvenue deans le programme de Gestion des plannings !");
		
		
		// Variables .... 
		String Favions = "avions14-v1.txt";
		String FagentsMitemps = "AgentsMiTemps-14-v1.txt";
		String FagentsTempsPlein = "AgentsTempsPlein-14-v1.txt";
		String FvolA = "ProgrammeVolsArrivees14-v2.txt";
		String FvolB = "ProgrammeVolsDeparts14-v2.txt";
		
		
		HashMap<String, Avion> tousLesAvions = new HashMap<String, Avion>(); //HashMap de tous les avions
		HashMap<String, Agent> tousLesAgents = new HashMap<String, Agent>(); //HashMap de tous les agents
		
		
		
		try {
			// Création des vols
			Avion.creerTouslesAvions(Favions);
			tousLesAvions = Avion.getLesAvions();
			System.out.println("Nombre d'avions : " +tousLesAvions.size());
			
			/*//Création des agents
			Agent.creerTouslesAgents(FagentsMitemps, FagentsTempsPlein);
			tousLesAgents = Avion.getLesAgents();
			System.out.println("Nombre d'agents : " +tousLesAgents.size());*/
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
		//Affectation des taches
		
		
		
		//MENU ...

	}

}
