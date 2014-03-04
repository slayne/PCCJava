import java.util.Horaire;
import java.util.HashMap;

import aeroport.Horaire;

/**************************************************************************
* Source File	:  Tache.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  03/03/2014
* Description	:  Definition of the class Tache
**************************************************************************/






public  abstract class Tache  
{ 
	
		private int idTache;
		private TrancheHoraire tranche;
		
		private static int ident=0;
		private static HashMap<Integer,Tache> lesTaches;
		//Attributes Association
		Agent lAgent;
		Vol leVol;

	    public Tache(){
	    	sethoraireDebut(new Horaire());
	    	sethoraireFin(new Horaire());
	    	idTache=ident;
	    	leVol=new Vol();
	    	lAgent=new Agent();
	    	lesTaches.put(idTache, this);
	    	ident++;
	    }
	    
	    public String toString(){
	    	return("Tache : "+idTache+"\n"+tranche.toString()+"\nAgent : "+lAgent.toString()+"\nVol : "+leVol.toString());
	    }
	    public Tache(Horaire dd,Horaire df,Agent a,Vol v){
	    	sethoraireDebut(dd);
	    	sethoraireFin(df);
	    	idTache=ident;
	    	lAgent=a;
	    	leVol=v;
	    	lesTaches.put(idTache, this);
	    	ident++;
	    }
		
		public void lesInstances(){
			for(Tache t : lesTaches.values()){
				System.out.println(t.toString());
			}
		}
		
		public void afficher(){
			System.out.println(this.toString());
		}

		public Horaire gethoraireFin() {
			return tranche.getFinTrancheHoraire();
		}

		public void sethoraireFin(Horaire hf) {
			this.tranche.setFinTrancheHoraire(hf);
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
}


