/**************************************************************************
* Source File	:  Vol_depart.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Vol_depart
**************************************************************************/



import java.util.*;



public  class Vol_depart  extends Vol 
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private String horaireDepart;
		private String villeDepart;
		private HashMap<String, Vol_depart> lesVolsDepart;
		
		
		//Operations
		public Duree getHoraireDepart(){
			return horaireDepart;
		}
		
		public String getVille(){
			return villeDepart;
		}
		
		public String toString(){
			return super.toString() + " " + horaireDepart + " "+ villeDarrive;
		}
	
		public void lesInstances()
		{
	
			Collection<Vol_depart> col = lesVolsDepart.values();
			Iterator it = col.iterator();
			while(it.hasNext()){
				System.out.println((Vol_depart)it.next());
			}
	
		}
		
	
		
	
	

} //End Class Vol_depart


