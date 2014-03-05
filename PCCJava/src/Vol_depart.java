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
		
		
	
		private Duree horaireDepart;
		private String villeDepart;
		private HashMap<String, Vol_depart> lesVolsDepart;
		
		
		//Operations
		
		public Vol_depart(String cde, Duree hor, String ville){
			super(cde);
			horaireDepart = hor;
			villeDepart = ville;
			lesVolsDepart.put(cde, this);
			
		}
		
		public Duree getHoraireDepart(){
			return horaireDepart;
		}
		
		public String getVille(){
			return villeDepart;
		}
		
		public String toString(){
			return super.toString() + " " + horaireDepart + " "+ villeDepart;
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


