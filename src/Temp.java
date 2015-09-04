import java.util.ArrayList;


public class Temp {
  
  /* 1. Détection de la touche
   * 2. Assignation de l'ordre
   * 3. Lancement mouvementTour
   * 4. Boucle sur mouvementTour jusqu'à ce que le jeu soit fini
   * 
   */
  
  
  // Dans partie : liste des Dynamiques
  
  public void execMouvement (Dynamique entite, Coordonnees deplacement) {
    if (deplacement == null) {
      return;
    }else{
      deplacement.addCoord(entite.getCoordonnees());
      entite.setCoordonnees(deplacement);
    }
  }
  
  public void mouvementTour (ArrayList<Dynamique> listeDyn) {
    for (int i = 0; i < listeDyn.size(); i++) {
      if (listeDyn.get(i) == this.pacBoy) {
        this.execMouvement(listeDyn.get(i), this.mouvementPac());
      }else{
        this.execMouvement(listeDyn.get(i), this.mouvementMonstre());
      }
    }
    
    
    // A lancer dès qu'une entité change de cellule
   
  }
    
    // Détecte si l'élément dynamique se trouve à un croisement.
    // Si oui alors exécute la méthode choixCroisement de l'entité
    // Elle doit aussi faire setPrecedent (pour les monstres).
  
  public Coordonnees mouvementPac() {
    Coordonnees destination = this.pacBoy.choixCroisement (this.analyseCroisement(this.pacBoy));
    if(this.pacBoy.getCoordonnees().compare(this.pacBoy.getCible())) {
    // PacBoy est sur sa cible (il est à l'arrêt).
       if (destination == null) {
          this.pacBoy.setOrdre(null);
          // Si l'ordre est mauvais, il est supprimé et il ne bouge pas.
          return null;
        }else{
        // Si l'ordre est bon, on fait le déplacement voulu par PacBoy.
          return destination;
        }
    }else{
      if (destination != null) {
        return destination;
      }else{
        if (this.pacBoy.getCoordonnees().getX() != this.pacBoy.getCible().getX()) {
          if (this.pacBoy.getCoordonnees().getX() > this.pacBoy.getCible().getX()) {
            return new Coordonnees (-1,0);
          }else{
            return new Coordonnees (1,0);
          }
        }else{
          if (this.pacBoy.getCoordonnees().getY() > this.pacBoy.getCible().getY()) {
            return new Coordonnees (0, -1);
          }else{
            return new Coordonnees (0, 1);
          }
        }
      }
    }
  }
  
  public Coordonnees mouvementMonstre() {
    
  }
  
  
  
  /*public Coordonnees detectionCroisement (Dynamique entite) {
    if (croisement == true) {
      return entite.choixCroisement(analyseCroisement(entite.getCoordonnees()));
    }
    
  }*/
  
  public ArrayList<Coordonnees> analyseCroisement (Dynamique entite) {
    ArrayList<Coordonnees> result = new ArrayList<Coordonnees>();
    Coordonnees origine = entite.getCoordonnees();
    Coordonnees tmp;
    
    tmp = new Coordonnees (origine.getX()+1,origine.getY());
    if (!getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(1,0));
    }
    tmp = new Coordonnees (origine.getX()-1,origine.getY());
    if (!getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(-1,0));
    }
    tmp = new Coordonnees (origine.getX(),origine.getY()+1);
    if (!getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(0,1));
    }
    tmp = new Coordonnees (origine.getX(),origine.getY()-1);
    if (!getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(0,-1));
    }
    return result;
  }
  
// Dès que l'utilisateur lance une commande, on vérifie s'il ne cherche pas à reculer.

private Dynamique pacBoy = new Personnage ();

public void executionCommande(Coordonnees commande) {
  if(this.pacBoy.getOrdre() == null) {
    this.pacBoy.setOrdre(commande);
    this.detectionCroisement(pacBoy);
  } else {
    this.pacBoy.setOrdre(commande);
  }
}