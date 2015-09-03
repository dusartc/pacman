import java.util.ArrayList;


public class Monstre extends Dynamique {
  
  public Monstre (int x, int y) {
    super (x,y);
  }

  @Override
  public Coordonnees choixCroisement(ArrayList<Coordonnees> choixPossible) {
    ArrayList<Coordonnees> placementPossible = new ArrayList<Coordonnees>();
    Coordonnees result;
    
    for (int i = choixPossible.size() - 1; i >= 0; i--) {
      if (super.getOrdre().getX() == choixPossible.get(i).getX() && super.getOrdre().getY() == choixPossible.get(i).getY()) {
        choixPossible.remove(i);
      }
    }
    if (choixPossible.isEmpty()) {
      return super.getOrdre();
    }
    
    for (int j = 0; j < choixPossible.size(); j++) {
      placementPossible.add(new Coordonnees(choixPossible.get(j).getX() + super.getCoordonnees().getX(), choixPossible.get(j).getY() + super.getCoordonnees().getY()));
    }
    int meilleurIndice = 0;
    double meilleureValeur = this.calculChoix(placementPossible.get(0));
    for (int k = 0; k < placementPossible.size(); k++) {
      if (this.calculChoix(placementPossible.get(k)) > meilleureValeur) {
        meilleureValeur = this.calculChoix(placementPossible.get(k));
        meilleurIndice = k;
      }
    }
    
    return choixPossible.get(meilleurIndice);
  }
  
  private double calculChoix (Coordonnees depart) {
    int difX = super.getCible().getX() - depart.getX();
    int difY = super.getCible().getY() - depart.getY();
    
    double result = Math.sqrt(difX*difX + difY*difY);
    return result;
  }
  
  public String toString() {
    return "M";
  }
}