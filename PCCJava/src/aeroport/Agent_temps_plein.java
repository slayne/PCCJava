	package aeroport;
	/**************************************************************************
	* Source File	:  Agent_temps_plein.java
	* Author                   :  DUMONT  
	* Project name         :  Non enregistr�* Created                 :  03/03/2014
	* Modified   	:  04/03/2014
	* Description	:  Definition of the class Agent_temps_plein
	**************************************************************************/
	
	
	
	import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
	
	
	public  class Agent_temps_plein  extends Agent 
	{ 
	
		//Attributes
		private int codeCycle;
		private static HashMap <String,Agent_temps_plein> lesAgentsTempsPlein = new HashMap <String,Agent_temps_plein>();
		
		//Operations
			
		public Agent_temps_plein (String cde, String n, String p, int c) {
			super(cde,n,p,c);
			lesAgentsTempsPlein.put(cde,this);
		}
		
		public Agent_temps_plein() {
			// TODO Auto-generated constructor stub
			super();
		}
	
		public static void creerlesAgentsTempsPlein (String fichier) throws NumberFormatException, IOException {
			BufferedReader entree = new BufferedReader(new FileReader(fichier));
			 String ligne;
			 StringTokenizer mots;
			 String mot, cde = "null", nom = "null", prenom = "null";
			 int cycle=0; 
			 
			 while ((ligne = entree.readLine()) != null) // boucle de lecture/affichage du fichier
		      {
		    	 //System.out.println("ligne :" + ligne);
		    	 mots = new StringTokenizer(ligne);
		    	//System.out.println(ligne);
			    		 mot = mots.nextToken();
			    		 cde = mot;
			    		 mot = mots.nextToken();
			    		 nom = mot;
			    		 mot = mots.nextToken();
			    		 prenom = mot;
			    		 mot = mots.nextToken();
			    		 cycle =  Integer.parseInt(mot);			    	 
			    	 
			    		
			    new Agent_temps_plein(cde, nom, prenom, cycle);
			    	
		    		 
		      }
			
		}
		
		
		public String toString(){
			return "Agent temps plein :" + super.toString();
			
		}
		
		public TrancheHoraire calculTrancheHoraire(int numSemaine) {
			
			Horaire deb = null, fin = null;
			if((super.getCodeCycle()==1 && numSemaine%3==1) || (super.getCodeCycle()==2 && numSemaine%3==2) || (super.getCodeCycle()==3 && numSemaine%3==0)) {
				// horaire normale
				deb = new Horaire(9,0);
				fin = new Horaire(17,0);
			}
			if((super.getCodeCycle()==1 && numSemaine%3==2) || (super.getCodeCycle()==2 && numSemaine%3==0) || (super.getCodeCycle()==3 && numSemaine%3==1)) {
				// horaire matin
				deb = new Horaire(6,0);
				fin = new Horaire(14,0);
			}
			if((super.getCodeCycle()==1 && numSemaine%3==0) || (super.getCodeCycle()==2 && numSemaine%3==1) || (super.getCodeCycle()==3 && numSemaine%3==2)) {
				// horaire soir
				deb = new Horaire(13,30);
				fin = new Horaire(21,30);
			}
			TrancheHoraire t = new TrancheHoraire (deb,fin);
			return t;
			
		}
		
		public boolean peutFaireRepas(Tache t){
			boolean res=true;
			
		// faire toutes les conditions
			if(t.chevauche(new TrancheHoraire(new Horaire(11, 30), new Horaire(14,00)))){
				//est ce que si on ajoute la tranche horaire qu'on tente de lui affecter respect 2 conditions ?
				// 1 : Il a 1h pour manger (d�but du repas entre 11h30 et 14h, peut terminer plus tard)
				// 2: La fin de son travail se termine 1h apr�s au minimum (ex:si il fini � 15h, il doit manger au plus tard � 14)
				if(t.gethoraireDebut().horaireEnMinutes()-new Horaire(11,30).horaireEnMinutes()<60){
					res =false;
				}
			
			try{
				Horaire horFin = new ArrayList<Tache>(this.lesTaches.values()).get(this.lesTaches.size()-1).gethoraireFin();
				horFin.ajout(t.getDuree()); // On a l'horaire de fin de la derniere tache + durée tache sert pour quand on commence � 13h30
			
				
					if(horFin.compareTo(new Horaire (14,0))>= 0){
						// cas ou l'agent n'a pas le temps de faire la pause repas
						res= false;
					}
					else if(horFin.ajout(new Duree(60)).compareTo(this.calculTrancheHoraire(NUM_SEM).getFinTrancheHoraire()) <=0){
						// cas ou pas le temps de faire pause repas avant débauche
						res= false;				
						
						
					}
				//sinon il fait le repas
			
			}catch(IndexOutOfBoundsException e){
		
				if(this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire().compareTo(new Horaire(13,30)) == 0 ){
					Horaire hf = this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire();
					hf.ajout(t.getDuree());
					if(hf.compareTo(new Horaire(14,0))>=0){
						res = false;
						//On regarde si 13h30 + la tache ne dépasse pas 14h 
					}
					
					// cas ou l'agent est en décalé soir, il doit donc commencer par manger
														
				}
			}	
		}
			return res;
	}
		
	public static void affecterTachesRepas(){
		boolean affected;
		for(Agent_temps_plein a : lesAgentsTempsPlein.values()){
			affected=false;
			for(TrancheHoraire tr  : a.getTranchesLibreRepas()){
				if(!affected && tr.getDebutTrancheHoraire().compareTo(new Horaire(11,30))>=0 && tr.getDebutTrancheHoraire().compareTo(new Horaire(14,00))<=0){
					a.addTache(new Tache_repas(tr.getDebutTrancheHoraire(), tr.getDebutTrancheHoraire().ajout(new Duree(60)), a));
					affected=true;
					break;
				}
			}
			if(!affected){
				//a.addTache(new Tache_repas(new Horaire(11,30),new Horaire(12,30),a));
			}
		}
	}
	
	public ArrayList<TrancheHoraire> getTranchesLibreRepas(){
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
			if(!tp.gethoraireDebut().equals(debutHoraireAgent) && tp.gethoraireDebut().horaireEnMinutes() - debutHoraireAgent.horaireEnMinutes() >=60){
				//Si la premiere tache ne commence pas � son heure d'embauche et qu'il ne commence pas avant 60 mn on ajoute la tranche
				liste.add(new TrancheHoraire(debutHoraireAgent, tp.gethoraireDebut()));
			} 
		
			while (it.hasNext()) {
				t=it.next();
				
				if(t.gethoraireDebut().horaireEnMinutes() - tp.gethoraireFin().horaireEnMinutes()>=60 && t.gethoraireDebut().horaireEnMinutes()-new Horaire(11,30).horaireEnMinutes()>=60){
					//si le temps entre la fin de la derniere tache et le debut de la tache courante >=60 minutes alors on ajoute la tranche
					liste.add(new TrancheHoraire(tp.gethoraireFin(), t.gethoraireDebut()));
				}else if(!it.hasNext() && finHoraireAgent.horaireEnMinutes()-t.gethoraireFin().horaireEnMinutes()>=60){
					//Derniere Tache si le temps entre la fin de la tahce courante et l'horaire de d�bauche est plus grand que 60mn on ajoute
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
		
	public static HashMap <String,Agent_temps_plein> getAgentsTempsPlein(){
		return lesAgentsTempsPlein;
	}
		
	
	
	} //End Class Agent_temps_plein
	
	
