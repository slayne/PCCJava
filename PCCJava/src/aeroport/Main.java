package aeroport;

import java.io.IOException;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Bienvenue deans le programme de Gestion des plannings !");
		
		
		// Variables .... 
		String Favions = "C:/Users/clement.notnull/Google Drive/L3_Miage/Semestre 6/[Java] Projet PCC/ProjetJava-FI-2014/DonneesProjet/avions14-v1.txt";
		
		HashMap<String, Avion> tousLesAvions = null; //HashMap de tous les avions
		
		
		
		
		//Création des agents
		
		
		// Création des vols
		try {
			Avion.creerTouslesAvions(Favions);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Avion.getLesAvions();
		
		System.out.println(tousLesAvions.size());
		
		
		//Affectation des taches
		
		
		
		//MENU ...

	}

}
