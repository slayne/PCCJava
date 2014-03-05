/**************************************************************************
* Source File	:  Vol.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Vol
**************************************************************************/



import java.util.*;



public abstract class Vol  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private String codeVol;
		
	
	//Attributes Association
	
		private Avion lAvion;
	 
		private HashMap<Integer,Tache> lesTaches;
	 
		private HashMap<String, Vol> lesVols;
		
		public Vol(String cde){
			
			codeVol = cde;
			lesVols.put(codeVol, this);
			
		}
	
	
		public HashMap<Integer, Tache> getLesTaches(){
			return lesTaches;
		}
		public Avion getLavion(){
			return lAvion;
		}
		
		public String getCodeVol(){
			return codeVol;
		}
		
		
		public String toString(){
			return codeVol;
		}
	
		public void lesInstances()
		{
	
			Collection<Vol> col = lesVols.values();
			Iterator it = col.iterator();
			while(it.hasNext()){
				System.out.println((Vol)it.next());
			}
	
		}
		
		
	
		
	
	

} //End Class Vol


