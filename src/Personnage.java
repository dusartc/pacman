import java.util.ArrayList;


public class Personnage extends Dynamique {

  public Personnage(int x, int y) {
    super(x, y);
  }

  @Override
  public Coordonnees choixCroisement(ArrayList<Coordonnees> choixPossible) {
    if (super.getOrdre() == null) {
      return null;
    }
    for (int i = 0; i < choixPossible.size(); i++) {
      if (super.getOrdre().getX() == choixPossible.get(i).getX()
          && super.getOrdre().getY() == choixPossible.get(i).getY()) {
        return choixPossible.get(i);
      }
    }
    return null;
  }

  public String toString() {
    return " @ ";
  }
}
