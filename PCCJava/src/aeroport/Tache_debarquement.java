package aeroport;
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
	
	
	private static HashMap<Integer,Tache_debarquement> lesTachesDebarquement = new HashMap<Integer,Tache_debarquement>();
	private static int ident=1;
	
	
	public Tache_debarquement(Agent a,Vol_arrive v){
		super(v.getHoraire().retrait(new Duree(5)),v.getHoraire().ajout(new Duree(15)),a,v);
		this.setType("Débarquement");
		
		lesTachesDebarquement.put(ident, this);
		ident++;
		
	}
	
	public static void lesInstances()
	{
	
			for(int i=0; i<lesTachesDebarquement.size();i++){
	   			System.out.println(lesTachesDebarquement.get(i).toString());
			}
	
	}
	
	
	public String toString(){
		
		return super.toString();
	}
	

	
	

} //End Class Tache_debarquement


