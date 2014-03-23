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
	public static int NUM_SEM=1;
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

		public ArrayList<TrancheHoraire> getTranchesLibreAccueil(){
			ArrayList<TrancheHoraire> liste = new ArrayList<TrancheHoraire>();
			//System.out.println(toString());
			//afficherTri();

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
				} 
			
				while (it.hasNext()) {
					t=it.next();
					
					if(t.gethoraireDebut().horaireEnMinutes() - tp.gethoraireFin().horaireEnMinutes()>=30){
						//si le temps entre la fin de la derniere tache et le debut de la tache courante >=30 minutes alors on ajoute la tranche
						liste.add(new TrancheHoraire(tp.gethoraireFin(), t.gethoraireDebut()));
					}else if(!it.hasNext() && finHoraireAgent.horaireEnMinutes()-t.gethoraireFin().horaireEnMinutes()>=30){
						//Derniere Tache si le temps entre la fin de la tahce courante et l'horaire de d�bauche est plus grand que 30mn on ajoute
						liste.add(new TrancheHoraire(t.gethoraireFin(), finHoraireAgent));
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
		
	// CODE CEC
		
		public Horaire getDebutJournee () {
			return (this.calculTrancheHoraire(NUM_SEM)).getDebutTrancheHoraire();
		}
		 
		public Horaire getFinJournee () {
			return (this.calculTrancheHoraire(NUM_SEM)).getFinTrancheHoraire();
		}
		
		public void retardTache (Tache t1, Duree d) {
			
			Horaire deb = t1.gethoraireFin();
			Horaire fin = null;
			boolean res=false, reaff=false;
			// Pour pouvoir traiter le planning hors considération de la tache a retarder
			lesTaches.remove(t1.getId());
			// On modifie les horaires de la tache en fonction du retard
			t1.sethoraireDebut((t1.gethoraireDebut()).ajout(d));
			t1.sethoraireFin((t1.gethoraireFin()).ajout(d));
			

			// Calcul de la marge et mise a jour acceuil
			boolean trouve = false;
			for(Tache t2 : this.getTachesTriees()){
				if(deb.compareTo(t2.gethoraireDebut())<=0 && !trouve) {	// Dans le planning, on se situe sur la tache suivant la tache retardee
					reaff=true;
					trouve=true;
					if(t2 instanceof Tache_accueil_Information) {	// On prend en compte le cas ou la tache suivante est une tache d'acceuil et que l'on peut donc la supprimer
						fin=t2.gethoraireFin();
					}
					else {
						fin=t2.gethoraireDebut();
					}
					if(new TrancheHoraire(deb,fin).getDuree().compareTo(d)<0) {	// Marge insuffisante pour retarder la tache
						this.affecterTacheAccueilAfter(); // Rajout de tache accueil si necessaire
						res=this.affecterTache(t1); // Va ajouter la tache au premier agent disponible
					}
					else { // Marge suffisante
						if(t2 instanceof Tache_accueil_Information) { // On supprime la tache d'accueil qui suit si elle existe	
							res=true;
							lesTaches.remove(t2.getId());
							Tache.toutesLesTaches().remove(t2.getId());
							// Création d'une nouvelle tache accueil si besoin
							TrancheHoraire temp=new TrancheHoraire(t1.gethoraireFin(),fin);
							if(temp.getDuree().compareTo(new Duree(0,30))>=0) {
								this.addTache(new Tache_accueil_Information(temp,this));
							}
						}
					}
				}
				if(t2.equals(t1)) {
					trouve=true;
				}
			}
			if(!reaff) {	// La tache retardee est la derniere de la journee
				if(t1.gethoraireFin().compareTo(this.getFinJournee())>0) {	// Avec le retard, la tache termine apres la fin de journee de l'agent
					lesTaches.remove(t1.getId());
					this.affecterTacheAccueilAfter(); // Rajout de tache accueil si necessaire
					res=this.affecterTache(t1); // Va ajouter la tache au premier agent disponible
				}
			}
			if(!res) {	// On n'est pas parvenus a retarder la tache dans le planning de l'agent ni a la reaffecter a un autre
				this.lesTaches.remove(t1.getId()); // On supprime la tache du planning, elle sera donc consideree non affectee
				t1.setAgent(null);
			}
		}
		
		public boolean affecterTache (Tache t) {
			Horaire deb=null, deb_aux=null, fin=null;
			boolean reaff=false;
			for(Agent a : lesAgents.values()) {	// On regarde chaque agent
				if(!(a.equals(this)||reaff)) {	// On ne traite pas l'agent dont on reaffecte la tache ou si la tache est deja reaffectee
					deb_aux = a.getDebutJournee();	// Initialisation pour qu'a la premiere iteration des taches d'un agent, on affecte l'horaire de debut de journée à deb
					for(Tache tcour : a.getTachesTriees()) {	// On regarde son planning
						deb=deb_aux;
						if(tcour instanceof Tache_accueil_Information) {
							fin=tcour.gethoraireFin();
						}
						else {
							fin=tcour.gethoraireDebut();
						}
						if(t.getTranche().dansTrancheHoraire(deb,fin)) { 	// Espace adapté pour ajouter la tache
							if(tcour instanceof Tache_accueil_Information) {	// Suppression de l'eventuelle tache acceuil
								a.getLesTaches().remove(tcour.getId());
								Tache.toutesLesTaches().remove(tcour);
							}
							a.reorganisationEspace(t,deb, fin);
							reaff=true;
						}
						deb_aux=tcour.gethoraireFin();
					}
					if(!reaff) { // Si toujours pas affecte pour cet agent, on va tester l'espace entre la fin de la derniere tache de sa journée et la fin de sa journée
						if(t.getTranche().dansTrancheHoraire(deb_aux,a.getFinJournee())) { 	// Espace suffisant pour ajouter la tache
							a.reorganisationEspace(t,deb_aux,getFinJournee());
							reaff=true;
						}
					}
				}
				
			}
			return reaff;
		}
		
		/* Cette fonction doit etre appellée lorsqu'un agent est absent, on va alors réaffecter
		toutes ses taches aux autres agents */
		public static void abscenceAgent (String a) {
			
			if(lesAgents.get(a).lesTaches.size()!=0) { // On verifie au préalable que l'agent ait des taches d'affectées
				for(Tache t : lesAgents.get(a).lesTaches.values()) {
					if(t instanceof Tache_accueil_Information || t instanceof Tache_repas) {
						// On supprime la tache de la liste static de toutes les taches
						Tache.toutesLesTaches().remove(t.getId());
					}
					else {	// Pour les taches liées à des vols, on réaffecte
						if(!lesAgents.get(a).affecterTache(t)) {	// On n'est pas parvenus a reaffecter la tache
							t.setAgent(null);
						}
					}
				}
			}
			// On ne supprime pas les taches du planning de l'agent au fur et a mesure car on va supprimer l'agent
			lesAgents.remove(a);
		}
		
		
		// On va ajouter une tache t a un agent et reorganiser l'espace autour de cette tache
		// c'est-à-dire ajouter des taches accueil avant ET/OU apres si nécéssaire
		// deb et fin représentent l'horaire de début et fin de la tranche dans laquelle on va ajouter la tache t
		public void reorganisationEspace (Tache t, Horaire deb, Horaire fin) {
			TrancheHoraire temp = new TrancheHoraire(deb,t.gethoraireDebut());
			if(temp.getDuree().compareTo(new Duree(0,30))>=0) {
				// Nouvelle tache accueil entre la fin de la tache precedente et le debut de la nouvelle tache
				this.addTache(new Tache_accueil_Information(temp,this));
				System.out.println("AFfectation acueil avnat");
				System.out.println(temp.toString());
			}
			temp.setDebutTrancheHoraire(t.gethoraireFin());
			temp.setFinTrancheHoraire(fin);
			if(temp.getDuree().compareTo(new Duree(0,30))>=0) {
				// Nouvelle tache accueil entre la fin de la nouvelle tache et le debut de la tache courante
				this.addTache(new Tache_accueil_Information(temp,this));
				System.out.println("AFfectation acueil apres");
				System.out.println(temp.toString());
			}
			t.setAgent(this);
			this.addTache(t);
		}
		
		  public boolean equals(Object o){
			  Agent a=(Agent) o;
			  return this.getCodeAgent()==a.getCodeAgent();
		  }
			
			public void affecterTacheAccueilAfter () {
				Horaire deb=this.getDebutJournee();
				Horaire fin=null;
				Tache tprec=null;
				Tache temp=null;
				for(Tache tcour : this.getTachesTriees()) {
					temp = tcour;	// Pour sauvegarder la derniere tache du planning
					if(tcour instanceof Tache_accueil_Information) {
						fin=tcour.gethoraireFin();
						if(tprec==null) {	// La premiere tache rencontree est une tache accueil
							tcour.sethoraireDebut(deb);
						}
						if(tprec instanceof Tache_accueil_Information) {
							// On a deux taches accueil a la suite avec un espace entre crée par une délétion
							tcour.sethoraireDebut(tprec.gethoraireDebut());
							Tache.toutesLesTaches().remove(tprec.getId());
						}
					}
					else {
						fin=tcour.gethoraireDebut();
						if(tprec instanceof Tache_accueil_Information && tprec.gethoraireFin().compareTo(tcour.gethoraireDebut())!=0) {
							// La tache precedente est une tache accueil et il existe un intervalle entre elle et la suivant
							// On va donc rallonger la durée de la tache accueil
							tprec.sethoraireFin(tcour.gethoraireDebut());
						}
						else {
							if(deb.getDuree(fin).compareTo(new Duree(0,30))>=0) { // Il faut ajouter une tache accueil
									this.addTache(new Tache_accueil_Information(deb,fin,this));
							}
						}
					}
					deb=tcour.gethoraireFin();
					tprec=tcour;
				}
				// Pour traiter l'intervalle entre la derniere tache et l'horaire de fin de journée
				fin=this.getFinJournee();
				if(temp instanceof Tache_accueil_Information) {	// La derniere tache est une tache accueil
					temp.sethoraireFin(fin);
				}
				else {
					System.out.println(deb.toString() + "  " + fin.toString());
					if(deb.getDuree(fin).compareTo(new Duree(0,30))>=0) { 	// Il faut ajouter une tache accueil
						this.addTache(new Tache_accueil_Information(deb,fin,this));
					}
				}
			}

		
		

		
			
} //End Class Agent


