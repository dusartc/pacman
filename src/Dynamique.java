import java.util.ArrayList;

public abstract class Dynamique extends Element {

  private Coordonnees cible;
  private Coordonnees ordre;

  public Dynamique(int x, int y) {
    super(x, y);
  }

  public void setOrdre(Coordonnees ordre) {
    this.ordre = ordre;
  }
  
  public Coordonnees getOrdre () {
    return this.ordre;
  }
  
  public Coordonnees getCible () {
    return this.cible;
  }
  
  public void setCible (Coordonnees cible) {
    this.cible = cible;
  }

  public abstract Coordonnees choixCroisement (ArrayList<Coordonnees> choixPossible);
    // Retourne la destination choisie
    // Le controller n'a plus qu'à répercuter le changement sur le plateau.

  
  

}
