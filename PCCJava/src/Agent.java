/**************************************************************************
* Source File	:  Agent.java
* Author                   :  DUMONT  
* Project name         :  Non enregistr�* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent
**************************************************************************/



import java.util.Date;
import java.util.Vector;
import java.util.Hashtable;
import java.util.regex.Pattern;
import java.lang.String;
import java.io.*;

public abstract class Agent  
{ 
	//Attributes
	
	private String codeAgent;
	private String nom;
	private String prenom;
	private int codeCycle;
	private Vector lesTaches;
	private static Hashtable <String,Agent> lesAgents;
	
	//Operations
		
		public Agent (String id, String n, String p, int c) {
			codeAgent=id;
			nom=n;
			prenom=p;
			codeCycle=c;
			lesAgents.put(codeAgent,this);
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
			lesTaches.add(t);
		}
		
		public void resetTache () {
			lesTaches.clear();
		}
		
		public int getNbTaches () {
			return lesTaches.capacity();
		}
		
		public int getCodeCycle () {
			return codeCycle;
		}


} //End Class Agent


