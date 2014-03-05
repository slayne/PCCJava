/**************************************************************************
* Source File	:  Tache_debarquement.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Tache_debarquement
**************************************************************************/



import java.util.*;



public  class Tache_debarquement  extends Tache 
{ 
	public Tache_debarquement(Agent a,Vol_arrive v){
		super(v.getHoraireArrive().retrait(new Duree(15)),v.getHoraireArrive().retrait(new Duree(5)),a,v);
		this.setType("Débarquement");
	}
	
	
	
	
	

	
	//Operations
	
		public 
	 void lesInstances()
		{
	
	   		// TODO: implement
	
		}
	
		
	
	

} //End Class Tache_debarquement


