/**************************************************************************
* Source File	:  Agent.java
* Author                   :  DUMONT  
* Project name         :  Non enregistré* Created                 :  03/03/2014
* Modified   	:  04/03/2014
* Description	:  Definition of the class Agent
**************************************************************************/



import java.util.*;



public abstract class Agent  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 char codeAgent;
		private 
	 char nom;
		private 
	 char prenom;
		private 
	 int codeCycle;
	
	//Attributes Association
	
		Tache lesTaches[];
	 
	
	
	
	

	
	//Operations
	
		public 
		       abstract void calculHoraireDebut(int numSemaine);
		public 
		       abstract void calculHoraireFin(int numSemaine);
		public 
	 void lesInstances()
		{
	
	   		// TODO: implement
	
		}
	
		
	
	

} //End Class Agent


