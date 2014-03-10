package aeroport;
/**************************************************************************
* Source File	:  Agent.java
* Author                   :  DUMONT  
* Project name         :  Non enregistrï¿½* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent
**************************************************************************/



import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.Hashtable;
import java.util.regex.Pattern;
import java.lang.String;
import java.io.*;

public abstract class Agent  
{ 
	//Attributes
	public static final int NUM_SEM=3;
	private String codeAgent;
	private String nom;
	private String prenom;
	private int codeCycle;
	private HashMap<Integer,Tache> lesTaches;
	private static Hashtable <String,Agent> lesAgents;
	
	//Operations
		
		public Agent (String id, String n, String p, int c) {
			codeAgent=id;
			nom=n;
			prenom=p;
			codeCycle=c;
			lesAgents.put(codeAgent,this);
		}
		
		public Agent(){
			codeAgent=new String();
			nom=new String();
			prenom=new String();
			
		}
		
		public abstract TrancheHoraire calculTrancheHoraire(int numSemaine);
		
		public static void lesAgents(String tp, String mt) {
			Agent_temps_plein.lesAgentsTempsPlein(tp);
			Agent_mi_temps.lesAgentsMiTemps(mt);
		}
		
		public static void resetAgent () {
			lesAgents.clear();
		}
		
		public static int getNbAgents () {
			return lesAgents.size();
		}
		
		public String getCodeAgent () {
			return codeAgent;
		}
		
		public void addTache (Tache t) {
			lesTaches.put(t.getId(),t);
		}
		
		public void resetTache () {
			lesTaches.clear();
		}
		
		public int getNbTaches () {
			return lesTaches.size();
		}
		
		public int getCodeCycle () {
			return codeCycle;
		}
		
		/*
		 * @return un agent disponible pour le creneau indiqué en paramètre
		 */
		public static Agent estDispoA(TrancheHoraire tr){
			//	Duree d=tr.getDuree();
				for(Agent a : lesAgents.values()){
					for(Tache t : a.lesTaches.values()){
						if (!t.chevauche(tr)&& a.estEnFonction(tr) && a.peutFaireRepas(t)){
							return a;
						}
					}
				}
				return null;
			}
			
			public boolean estEnFonction(TrancheHoraire tr){
				
				return this.calculTrancheHoraire(NUM_SEM).contient(tr);
			}
			public abstract boolean peutFaireRepas(Tache t);
			
} //End Class Agent


