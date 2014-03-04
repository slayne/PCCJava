import java.util.Date;
import java.util.HashMap;

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
		private Date dateDebut;
		private Date dateFin;
		
		private static int ident=0;
		private static HashMap<Integer,Tache> lesTaches;
		//Attributes Association
		Agent lAgent;
		Vol leVol;

	    public Tache(){
	    	setDateDebut(new Date());
	    	setDateFin(new Date());
	    	idTache=ident;
	    	leVol=new Vol();
	    	lAgent=new Agent();
	    	lesTaches.put(idTache, this);
	    	ident++;
	    }
	    
	    public String toString(){
	    	return("Tache : "+idTache+"\nDate début : "+dateDebut.toString()+"\nDate Fin : "+dateFin.toString()+"\nAgent : "+lAgent.toString()+"\nVol : "+leVol.toString());
	    }
	    public Tache(Date dd,Date df,Agent a,Vol v){
	    	setDateDebut(dd);
	    	setDateFin(df);
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

		public Date getDateFin() {
			return dateFin;
		}

		public void setDateFin(Date dateFin) {
			this.dateFin = dateFin;
		}

		public Date getDateDebut() {
			return dateDebut;
		}

		public void setDateDebut(Date dateDebut) {
			this.dateDebut = dateDebut;
		}
	
}


