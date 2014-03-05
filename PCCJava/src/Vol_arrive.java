	/**************************************************************************
	* Source File	:  Vol_arrive.java
	* Author                   :  DUMONT  
	* Project name         :  Non enregistré* Created                 :  03/03/2014
	* Modified   	:  04/03/2014
	* Description	:  Definition of the class Vol_arrive
	**************************************************************************/
	
	
	
	import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
	
	
	
	public  class Vol_arrive  extends Vol 
	{ 
		//Inners Classifiers
		
	
		//Attributes
			
			
		
			private Duree horaireArrive;
			private String villeDarrive;
			private HashMap<String, Vol_arrive> lesVolsArrives;
		
		
			//Operations
			public Vol_arrive(String cde, String cdeAvion, Duree hor, String ville){
				super(cde, cdeAvion);
				horaireArrive = hor;
				villeDarrive = ville;
				lesVolsArrives.put(cde, this);
				
			}
			
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
			
			
			public static void creerTouslesVolsArrives(String cheminFichier) throws IOException{
				
				 BufferedReader entree = new BufferedReader(new FileReader(cheminFichier));
				 String ligne;
				 StringTokenizer mots;
				 String mot, cde = "null", ville = "null", cdeAvion = "null" ;
				 int cap=0; 
				 int heure = 0;
				 int minute = 0;
				 
				 while ((ligne = entree.readLine()) != null) // boucle de lecture/affichage du fichier
			      {
			    	 //System.out.println("ligne :" + ligne);
			    	 mots = new StringTokenizer(ligne);
			    	 
			    	 for(int i=0; i<5;i++){
			    		 mot = mots.nextToken();
			    		 //System.out.println("Mot :" + mot);
			    		 if(i==0){
			    			 cde = mot;
			    		 }else if(i==1) {
			    			 heure = Integer.parseInt(mot);
			    		 }else if(i==2){
			    			minute =  Integer.parseInt(mot);
			    		 }else if(i==3){
				    		ville =  mot;
			    		 }else if(i==4){
				    			cdeAvion =  mot;
			    		 }
			    	 
			    	 }
			    		
			    	 new Vol_arrive(cde, cdeAvion, new Duree(heure, minute), ville);
			    		 
			      }
				
			}
			
		
		
	
	} //End Class Vol_arrive
	

