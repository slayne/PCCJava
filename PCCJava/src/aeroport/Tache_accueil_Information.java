package aeroport;
/**************************************************************************
* Source File	:  Tache_accueil_Information.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Tache_accueil_Information
**************************************************************************/



import java.util.*;



public  class Tache_accueil_Information  extends Tache 
{ 
	public Tache_accueil_Information (Horaire dd,Horaire df,Agent a){
		super(dd,df,a,null);
		this.setType("Accueil-Information");
	}
	
	public void lesInstances()
	{
	
	   		// TODO: implement
	
	}
	
	// CODE CEC
	
			public Tache_accueil_Information (TrancheHoraire t, Agent a) {
				super(t,a,null);
				this.setType("Accueil-Information");
			}

		
	
	

} //End Class Tache_accueil_Information


