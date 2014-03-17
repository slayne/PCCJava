package aeroport;
/**************************************************************************
* Source File	:  Agent.java
* Author                   :  DUMONT  
* Project name         :  Non enregistr�* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent
**************************************************************************/



import java.util.ArrayList;
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

	protected HashMap<Integer,Tache> lesTaches = new HashMap<Integer,Tache>();
	private static HashMap <String,Agent> lesAgents = new HashMap <String,Agent>();
	
	//Operations
		
		public Agent (String cde, String n, String p, int c) {
			codeAgent=cde;
			nom=n;
			prenom=p;
			codeCycle = c;
			lesAgents.put(cde,this);
		}
		
		public Agent(){
			codeAgent=null;
			nom=null;
			prenom=null;
			codeCycle =0;
			
		}
		
		public abstract TrancheHoraire calculTrancheHoraire(int numSemaine);
		
		
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
		
		public int getCodeCycle(){
			return codeCycle;
		}
		
		
		/*
		 * @return un agent disponible pour le creneau indiqu� en param�tre
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
		
		public ArrayList<TrancheHoraire> getTranchesLibres(){
			ArrayList<TrancheHoraire> liste = new ArrayList<TrancheHoraire>();
			Horaire lasthorairefin=this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire(); // Horaire fin de la tache pr�c�dente
			// On parcours les taches de l'agent pour trouver tous les trous
			for(Tache t : this.lesTaches.values()){
				if(!t.gethoraireDebut().equals(this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire()) && t.gethoraireDebut().horaireEnMinutes() - this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire().horaireEnMinutes() >=30 ){
					//Si la premiere tache ne commence pas � son heure d'embauche et qu'il ne commence pas avant 30 mn on ajoute la tranche
					liste.add(new TrancheHoraire(this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire(), t.gethoraireDebut()));
				}
				if(t.gethoraireDebut().horaireEnMinutes() - lasthorairefin.horaireEnMinutes()>=30){
					//si le temps entre la fin de la derniere tache et le debut de la tache courante >=30 minutes alors on ajoute la tranche
					liste.add(new TrancheHoraire(lasthorairefin, t.gethoraireDebut()));
				}
				if(this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire().horaireEnMinutes()-t.gethoraireFin().horaireEnMinutes()>=30){
					//si le temps entre la fin de la tahce courante et l'horaire de d�bauche est plus grand que 30mn on ajoute
					liste.add(new TrancheHoraire(t.gethoraireFin(), this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire()));
				}
				lasthorairefin=t.gethoraireFin();
			}
			
			return liste;
		}
		
		public static void affecterTacheAccueil(){
			for(Agent a : lesAgents.values()){
				for(TrancheHoraire tr  : a.getTranchesLibres()){
					a.addTache(new Tache_accueil_Information(tr.getDebutTrancheHoraire(),tr.getFinTrancheHoraire(),a));
				}
			}
		}
		
		public static void construirePlanning(){
			Tache.affecterTachesVol();
			Agent_temps_plein.affecterTachesRepas();
			Agent.affecterTacheAccueil();
		}
			
		public static void creerTouslesAgents(String FagentsMitemps, String FagentsTempsPlein) throws NumberFormatException, IOException{
			Agent_temps_plein.creerlesAgentsTempsPlein(FagentsTempsPlein);
			Agent_mi_temps.creerlesAgentsMiTemps(FagentsMitemps);
		}
		
		public static HashMap<String, Agent> getLesAgents(){
			return lesAgents;
		}
		
		public String toString(){
			return codeAgent + " " + nom + " " + prenom + " " + codeCycle;
		}
		
		public ArrayList<Agent> toArrayList(){
			return new ArrayList<Agent> (lesAgents.values());
		}
			
} //End Class Agent


