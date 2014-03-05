/**************************************************************************
* Source File	:  Tache_embarquement.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Tache_embarquement
**************************************************************************/



import java.util.*;



public  class Tache_embarquement  extends Tache 
{ 
	public Tache_embarquement(Agent a,Vol_depart v){
		super(v.getHoraireDepart().retrait(new Duree(15)),v.getHoraireDepart().retrait(new Duree(5)),a,v);
		this.setType("Embarquement");
	}

	//Attributes
		
		
	
	
	//Attributes Association
	
	
	
	
	

	
	//Operations
	
		public 
	 void lesInstances()
		{
	
	   		// TODO: implement
	
		}
	
		
	
	

} //End Class Tache_embarquement


