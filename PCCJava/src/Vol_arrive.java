	/**************************************************************************
	* Source File	:  Vol_arrive.java
	* Author                   :  DUMONT  
	* Project name         :  Non enregistré* Created                 :  03/03/2014
	* Modified   	:  04/03/2014
	* Description	:  Definition of the class Vol_arrive
	**************************************************************************/
	
	
	
	import java.util.*;
	
	
	
	public  class Vol_arrive  extends Vol 
	{ 
		//Inners Classifiers
		
	
		//Attributes
			
			
		
			private Duree horaireArrive;
			private String villeDarrive;
			private HashMap<String, Vol_arrive> lesVolsArrives;
		
		
			//Operations
			public Duree getHoraireArrive(){
				return horaireArrive;
			}
			
			public String getVille(){
				return villeDarrive;
			}
			
			public String toString(){
				return super.toString() + " " + horaireArrive + " "+ villeDarrive;
			}
		
			public void lesInstances()
			{
		
				Collection<Vol_arrive> col = lesVolsArrives.values();
				Iterator it = col.iterator();
				while(it.hasNext()){
					System.out.println((Vol_arrive)it.next());
				}
		
			}
			
		
		
	
	} //End Class Vol_arrive
	

