package aeroport;
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
	
	private static HashMap<Integer,Tache_embarquement> lesTachesEmbarquement = new HashMap<Integer,Tache_embarquement>();
	private static int ident=1;
	
	
	
	public Tache_embarquement(Agent a,Vol_depart v){
		super(v.getHoraire().retrait(new Duree(15)),v.getHoraire().ajout(new Duree(5)),a,v);
		this.setType("Embarquement");
		
		lesTachesEmbarquement.put(ident, this);
		ident++;
	}


	public static void lesInstances()
	{
	
			for(int i=0; i<lesTachesEmbarquement.size();i++){
	   			System.out.println(lesTachesEmbarquement.get(i).toString());
			}
	
	}
	
	
	public String toString(){
		
		return super.toString();
	}
	
	
		
	
	

} //End Class Tache_embarquement


