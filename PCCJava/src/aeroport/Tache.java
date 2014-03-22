package aeroport;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;


/**************************************************************************
* Source File	:  Tache.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  03/03/2014
* Description	:  Definition of the class Tache
**************************************************************************/






public  abstract class Tache  implements Comparable<Tache>
{ 
	
		private int idTache;
		private TrancheHoraire tranche;
		private String type;
		//private Boolean estAffectee;
		
		private static int ident=1;
		private static HashMap<Integer,Tache> lesTaches = new HashMap<Integer,Tache>();
		//Attributes Association
		private Agent lAgent;
		private Vol leVol;

	    public Tache(){
	    	//estAffectee = false;
	    	sethoraireDebut(new Horaire());
	    	sethoraireFin(new Horaire());
	    	idTache=ident;
	    	setType(new String());
	    //	leVol=new Vol();
	    //	lAgent=new Agent();
	    	getLesTaches().put(idTache, this);
	    	ident++;
	    }
	    
	    public String toString(){
	    	return "N° de la tache : "+idTache+" | Type : "+this.getType()+" | "+tranche.toString();//+"\nAgent : "+lAgent.toString()+"\nVol : "+leVol.toString();
	    }
	    
	    public Tache(Horaire dd,Horaire df,Agent a,Vol v){
	    	//estAffectee = false;
	    	tranche = new TrancheHoraire(dd, df);
	    	idTache=ident;
	    	lAgent=a;
	    	leVol=v;
	    	getLesTaches().put(idTache, this);
	    	ident++;
	    }
		
		public void lesInstances(){
			for(Tache t : getLesTaches().values()){
				System.out.println(t.toString());
			}
		}
		/*public Boolean estAffectee(){
			return estAffectee;
		}
		
		public void setAffectation(Boolean b){
			estAffectee = b;
		}*/
		
		public void afficher(){
			System.out.println(this.toString());
		}

		public Horaire gethoraireFin() {
			return tranche.getFinTrancheHoraire();
		}

		public void sethoraireFin(Horaire hf) {
			tranche.setFinTrancheHoraire(hf);
		}

		public Horaire gethoraireDebut() {
			return tranche.getDebutTrancheHoraire();
		}

		public void sethoraireDebut(Horaire hd) {
			tranche.setDebutTrancheHoraire(hd);
		}
		
		public Duree getDuree(){
			return tranche.getDuree();
		}
		
		public Vol getVol(){
			return leVol;
		}
		
		public void setVol(Vol v){
			leVol=v;
		}
		
		public Agent getAgent(){
			return lAgent;
		}
		
		public void setAgent(Agent a){
			lAgent=a;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		public int getId(){
			return this.idTache;
		}
		
		public TrancheHoraire getTranche(){
			return tranche;
		}
		
		public int compareTo(Tache t){
			return tranche.getDebutTrancheHoraire().compareTo(t.tranche.getDebutTrancheHoraire());
		}
		
		public static ArrayList<Tache> getTachesTriees(){
			ArrayList<Tache> l =new ArrayList<Tache>(getLesTaches().values());
			Collections.sort(l);
			return l;
		}
		
		public boolean chevauche(TrancheHoraire tr){
			boolean res=false;
			if (tranche.intersection(tr)!=null){
				res=true;
			}
			return res;
		}
		
		public static void affecterTachesVol(){
			for(Tache t : getTachesTriees()){
				Agent a = Agent.trouverAgentA(t.tranche);
				if (a!=null){
					t.setAgent(a);
					a.addTache(t);
				}
			}
		}
		
		public static HashMap<Integer,Tache> toutesLesTaches(){
			return getLesTaches();
		}
		
		
		
		public void afficherUneTache(){
			System.out.println(toString());
		}
		
		public static void afficherLesTaches(){
			for(Tache t : getLesTaches().values()){
				System.out.println(t.toString());
			}
		}
		
		public void afficherVolAssocieTache(){
			this.leVol.toString();
		}

		public static HashMap<Integer,Tache> getLesTaches() {
			return lesTaches;
		}

		public static void setLesTaches(HashMap<Integer,Tache> lesTaches) {
			Tache.lesTaches = lesTaches;
		}
		
		public boolean estAffecte(){
			return(lAgent!=null);
		}
		
		public static int getNbTacheNonAffecte(){
			int res=0;
			for(Tache t : lesTaches.values()){
				if(!t.estAffecte()){
					res++;
				}
			}
			return res;
		}
		
		public static int getNbTacheAffecte(){
			int res=0;
			for(Tache t : lesTaches.values()){
				if(t.estAffecte()){
					res++;
				}
			}
			return res;
		}
		
		public static int getTacheRepas(){
			int res=0;
			for(Tache t : lesTaches.values()){
				if(t instanceof Tache_repas){
					res++;
				}
			}
			return res;
		}
		
		// CODE CEC
		
		public boolean equals (Object o) {
			Tache t = (Tache) o;
			return this.getId() == t.getId();
		}

		 public Tache(TrancheHoraire t,Agent a,Vol v){
		    	//estAffectee = false;
		    	tranche = t;
		    	idTache=ident;
		    	lAgent=a;
		    	leVol=v;
		    	getLesTaches().put(idTache, this);
		    	ident++;
		    }

		

}	


