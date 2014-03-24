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
	
	
	private static HashMap<Integer,Tache_accueil_Information> lesTachesAccueil_Information = new HashMap<Integer,Tache_accueil_Information>();
	private static int ident=1;
	
	
	public Tache_accueil_Information (Horaire dd,Horaire df,Agent a){
		super(dd,df,a,null);
		this.setType("Accueil-Information");
		lesTachesAccueil_Information.put(ident, this);
		ident++;
		
	}
	
	public static void lesInstances()
	{
	
			for(int i=0; i<lesTachesAccueil_Information.size();i++){
	   			System.out.println(lesTachesAccueil_Information.get(i).toString());
			}
	
	}
	
	public String toString(){
		
		return super.toString();
	}
	
	
	
	// CODE CEC
	
			public Tache_accueil_Information (TrancheHoraire t, Agent a) {
				super(t,a,null);
				this.setType("Accueil-Information");
			}

		
	
	

} //End Class Tache_accueil_Information


