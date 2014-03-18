package aeroport;
/**************************************************************************
* Source File	:  Tache_enregistrement.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Tache_enregistrement
**************************************************************************/



import java.util.*;



public  class Tache_enregistrement  extends Tache 
{ 
	public Tache_enregistrement(Agent a, Vol_depart v){
		super( v.getHoraire().retrait(new Duree(1,30)),v.getHoraire().retrait(new Duree(15)),a,v);
		this.setType("Enregistrement");
	}
	
	
	
	
	

	
	//Operations
	
		public 
	 void lesInstances()
		{
	
	   		// TODO: implement
	
		}
	
		
	
	

} //End Class Tache_enregistrement


