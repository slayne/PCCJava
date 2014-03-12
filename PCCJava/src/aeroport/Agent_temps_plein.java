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
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.util.*;
	
	
	public  class Agent_temps_plein  extends Agent 
	{ 
	
		//Attributes
		private static HashMap <String,Agent_temps_plein> lesAgentsTempsPlein;
		
		//Operations
			
		public Agent_temps_plein (String id, String n, String p, int c) {
			super(id,n,p,c);
			lesAgentsTempsPlein.put(super.getCodeAgent(),this);
		}
		
		public Agent_temps_plein() {
			// TODO Auto-generated constructor stub
		}
	
		public static void lesAgentsTempsPlein (String fichier) {
			String chaine="";
			//lecture du fichier texte	ligne par ligne
			try{
				InputStream ips=new FileInputStream(fichier); 
				InputStreamReader ipsr=new InputStreamReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				String valeurs [];
				Agent_temps_plein temp;
				while ((ligne=br.readLine())!=null){
					valeurs=ligne.split(" "); // Recuperation des termes de la ligne dans un tableau de string
					temp = new Agent_temps_plein(valeurs[0],valeurs[1],valeurs[2],Integer.parseInt(valeurs[3]));
				}
				br.close(); 
			}		
			catch (Exception e){
				System.out.println(e.toString());
			}
		}
		
		public TrancheHoraire calculTrancheHoraire(int numSemaine) {
			
			Horaire deb = null, fin = null;
			if((super.getCodeCycle()==1 && numSemaine%3==1) || (super.getCodeCycle()==2 && numSemaine%3==2) || (super.getCodeCycle()==3 && numSemaine%3==0)) {
				// horaire normale
				deb = new Horaire(9,0);
				fin = new Horaire(17,0);
			}
			if((super.getCodeCycle()==1 && numSemaine%1==2) || (super.getCodeCycle()==2 && numSemaine%3==0) || (super.getCodeCycle()==3 && numSemaine%3==1)) {
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
				//sinon il faire le repas
			
			}catch(IndexOutOfBoundsException e){
		
				if(this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire().compareTo(new Horaire(13,30)) == 0 ){
					Horaire hf = this.calculTrancheHoraire(NUM_SEM).getDebutTrancheHoraire();
					hf.ajout(t.getDuree());
					if(hf.compareTo(new Horaire(14,0))>=0){
						res = false;
					}
					
					// cas ou l'agent est en décalé soir, il doit donc commencer par manger
														
				}
			}	
		}
			return res;
	}
		
	public static void affecterTachesRepas(){
		for(Agent_temps_plein a : lesAgentsTempsPlein.values()){
			for(TrancheHoraire tr  : a.getTranchesLibres()){
				if(new TrancheHoraire(new Horaire(11,30),new Horaire(14,00)).contient(tr)){
					a.addTache(new Tache_repas(tr.getDebutTrancheHoraire(), tr.getFinTrancheHoraire(), a));
					break;
				}
			}
		}
	}
		
		
		
	
	
	} //End Class Agent_temps_plein
	
	
