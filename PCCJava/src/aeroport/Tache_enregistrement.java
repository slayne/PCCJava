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
	
	private static HashMap<Integer,Tache_enregistrement> lesTachesEnregistrement = new HashMap<Integer,Tache_enregistrement>();
	private static int ident=1;
	
	
	public Tache_enregistrement(Agent a, Vol_depart v){
		super( v.getHoraire().retrait(new Duree(1,30)),v.getHoraire().retrait(new Duree(15)),a,v);
		this.setType("Enregistrement");
		lesTachesEnregistrement.put(ident, this);
		ident++;
		
	}
	
	public static void lesInstances()
	{
	
			for(int i=0; i<lesTachesEnregistrement.size();i++){
	   			System.out.println(lesTachesEnregistrement.get(i).toString());
			}
	
	}
	
	
	public String toString(){
		
		return super.toString();
	}
	

	
		
	
	

} //End Class Tache_enregistrement


