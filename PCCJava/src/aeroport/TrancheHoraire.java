package aeroport;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Patrick Debord
 * @version 1.0
 */

public class TrancheHoraire {
  private Horaire debut;
  private Horaire fin;
  public TrancheHoraire(Horaire d, Horaire f) {
    debut=d;fin =f;
  }
  // accesseurs
  public Horaire getDebutTrancheHoraire(){
    return debut;
  }
  public Horaire getFinTrancheHoraire(){
    return fin;
  }

  // m�thodes
  /**
   *   indique si l'horaire h est dans la tranche horaire, objet receveur
   *   @param h:Horaire
   *   @return boolean
   *   @author debord
   */
  public boolean contientStrictement(Horaire h){
    return h.compareTo(debut)>0  && h.compareTo(fin)<0 ;   
    
  }
  public boolean contient(Horaire h){
    return h.compareTo(debut)>=0  && h.compareTo(fin)<=0 ;   
    
  }
  
  /**
   * indique si la tranche horaire objet receveur est contenue dans la tranche param�tre
   * @param t:TrancheHoraire
   * @return boolean
   */
  public boolean dansTrancheHoraire(TrancheHoraire t){
    return (debut.compareTo(t.debut)>=0)&&(fin.compareTo(t.fin)<=0);
  }
  public boolean equals(Object o){
	  TrancheHoraire t=(TrancheHoraire)o;
	  return debut.equals(t.debut)&& fin.equals(t.fin);
  }
  
  public boolean contient (TrancheHoraire t){
	  return t.dansTrancheHoraire(this);
  }
  // calcule le nombre de partitions induites par la tranche t dans la tranche this
  // A n'utiliser que si this contient t
  public int nombrePartitions(TrancheHoraire t){
	  int n=0;
	  if(this.equals(t))
		  n=0;
	  else 	if((debut.equals(t.debut))||(fin.equals(t.fin)))
	  			n=1;
	  		else
	  			n=2;
	  return n;
	  
  }
  public boolean dansTrancheHoraire(Horaire d,Horaire f){
     return (debut.compareTo(d)>=0)&&(fin.compareTo(f)<=0);
   }
  public TrancheHoraire intersection(TrancheHoraire t){
    if(this.dansTrancheHoraire(t)){
      return new TrancheHoraire(debut,fin);
    }
    else
    	if(t.dansTrancheHoraire(this))
    		return new TrancheHoraire(t.debut,t.fin);
    	else
    if(!this.contientStrictement(t.debut)&&!this.contientStrictement(t.fin)){
      return null;
    }
    else  if(this.contientStrictement(t.debut)){
              return new TrancheHoraire(t.debut,fin);
           }
           else return new TrancheHoraire(debut,t.fin);

  }
  public Duree getDuree(){
    return fin.retrait(debut);
  }
  public String toString(){
    String info=" d�but:"+debut.toString()+ " fin:"+fin.toString();
    return info;
  }
public void setFinTrancheHoraire(Horaire hf) {
	fin=hf;	
}

public void setDebutTrancheHoraire(Horaire d){
	debut= d;
}

}
