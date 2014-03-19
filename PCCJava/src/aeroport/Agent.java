package aeroport;
/**************************************************************************
* Source File	:  Agent.java
* Author                   :  DUMONT  
* Project name         :  Non enregistr�* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent
**************************************************************************/



import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
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

	protected  HashMap<Integer,Tache> lesTaches = new HashMap<Integer,Tache>();
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
		
		
		public String getNom(){
			return nom;
		}
		
		public String getPrenom(){
			return prenom;
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
		
		public int getCodeCycle(){
			return codeCycle;
		}
		
		
		/*
		 * @return un agent disponible pour le creneau indiqu� en param�tre
		 */
		public static Agent trouverAgentA(TrancheHoraire tr){
				boolean dispo;
				for(Agent a : lesAgents.values()){
					dispo=true;
					if(a.estEnFonction(tr)){
						if(a.lesTaches.size()==0){
							return a;
						}
						for(Tache t : a.lesTaches.values()){
							if (dispo && !t.chevauche(tr) && a.peutFaireRepas(t)){
								dispo=true;
								
							}else{
								dispo=false;
							}
						}
						if(dispo){
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
			boolean temp=true;
			Horaire debutHoraireAgent= this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire();
			Horaire lasthorairefin=this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire(); // Horaire fin de la tache pr�c�dente
			// On parcours les taches de l'agent pour trouver tous les trous
			for(Tache t : this.getTachesTriees()){
				if(temp){
					if(!t.gethoraireDebut().equals(debutHoraireAgent) && t.gethoraireDebut().horaireEnMinutes() - debutHoraireAgent.horaireEnMinutes() >=30){
						//Si la premiere tache ne commence pas � son heure d'embauche et qu'il ne commence pas avant 30 mn on ajoute la tranche
						liste.add(new TrancheHoraire(this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire(), t.gethoraireDebut()));
						temp=false;
					}else if(t.gethoraireDebut().horaireEnMinutes() - lasthorairefin.horaireEnMinutes()>=30){
						//si le temps entre la fin de la derniere tache et le debut de la tache courante >=30 minutes alors on ajoute la tranche
						liste.add(new TrancheHoraire(lasthorairefin, t.gethoraireDebut()));
						temp=false;
					}else if(this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire().horaireEnMinutes()-t.gethoraireFin().horaireEnMinutes()>=30){
						//si le temps entre la fin de la tahce courante et l'horaire de d�bauche est plus grand que 30mn on ajoute
						liste.add(new TrancheHoraire(t.gethoraireFin(), this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire()));
						temp=false;
					}
					lasthorairefin=t.gethoraireFin();
				}
			}	
			
			return liste;
		}
		
		public ArrayList<TrancheHoraire> getTranchesLibreAccueil(){
			ArrayList<TrancheHoraire> liste = new ArrayList<TrancheHoraire>();
			//System.out.println(toString());
			//afficherTri();
			boolean temp=true;
			Horaire debutHoraireAgent= this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire();
			Horaire finHoraireAgent =this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire();
			ListIterator<Tache> it = getTachesTriees().listIterator();
			Tache tp = null;
			Tache t = null;
			if(it.hasNext()){
				tp=it.next();
				if(!tp.gethoraireDebut().equals(debutHoraireAgent) && tp.gethoraireDebut().horaireEnMinutes() - debutHoraireAgent.horaireEnMinutes() >=30){
					//Si la premiere tache ne commence pas � son heure d'embauche et qu'il ne commence pas avant 30 mn on ajoute la tranche
					liste.add(new TrancheHoraire(debutHoraireAgent, tp.gethoraireDebut()));
					temp=false;
				} 
			
				while (it.hasNext()) {
					t=it.next();
					
					if(t.gethoraireDebut().horaireEnMinutes() - tp.gethoraireFin().horaireEnMinutes()>=30){
						//si le temps entre la fin de la derniere tache et le debut de la tache courante >=30 minutes alors on ajoute la tranche
						liste.add(new TrancheHoraire(tp.gethoraireFin(), t.gethoraireDebut()));System.out.println(toString());System.out.println("---------------------------------------------------");
						temp=false;
					}else if(!it.hasNext() && finHoraireAgent.horaireEnMinutes()-t.gethoraireFin().horaireEnMinutes()>=30){
						//Derniere Tache si le temps entre la fin de la tahce courante et l'horaire de d�bauche est plus grand que 30mn on ajoute
						liste.add(new TrancheHoraire(t.gethoraireFin(), finHoraireAgent));
						temp=false;
					}
					
					tp=t;
				}
			}else{
				//si pas de tache
				liste.add(new TrancheHoraire(debutHoraireAgent,finHoraireAgent));
			}
			
			
			
			return liste;
		}
		
		public static void affecterTacheAccueil(){
			
			for(Agent a : lesAgents.values()){
				//System.out.println(a.toString());
				//int i=0;
				for(TrancheHoraire tr  : a.getTranchesLibreAccueil()){
					//System.out.println(i);i++;
					a.addTache(new Tache_accueil_Information(tr.getDebutTrancheHoraire(),tr.getFinTrancheHoraire(),a));
				}
			}
			//System.out.println(Tache.getLesTaches().size());
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
			return ""+codeAgent + " " + nom + " " + prenom + " Horraires :" + this.calculTrancheHoraire(NUM_SEM).toString();
		}
		
		public static ArrayList<Agent> toArrayList(){
			return new ArrayList<Agent> (lesAgents.values());
		}
		
		
		public static void afficherLesAgents(){
			for(Agent a : lesAgents.values()){
				System.out.println();
				System.out.println(a.toString());
				System.out.println("Taches :");
				for(Tache t : a.getTachesTriees()){
					System.out.println(t.toString());
				}
			}
		}
		
		public ArrayList<Tache> getTachesTriees(){
			ArrayList<Tache> l =new ArrayList<Tache>(getLesTaches().values());
			Collections.sort(l);
			return l;
		}
		public void afficherTri(){
			for(Tache t : getTachesTriees()){
				System.out.println(t.toString());
			}
		}
	
		
		public HashMap<Integer,Tache> getLesTaches(){
			return lesTaches;
		}
		
		
		public static void afficherListeTachesUnAgent(String codeA){
			
			for(Agent a : lesAgents.values()){
				if(a.getCodeAgent().equals(codeA)){
					System.out.println("Liste des taches de l'agent : " + a.getCodeAgent());
					for(Tache t : a.getLesTaches().values()){
						System.out.println(t.toString());
					}
				}
				
			}
			
		}
		
			
} //End Class Agent


