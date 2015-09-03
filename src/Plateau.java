public class Plateau {

  private Cellule[][] plateau;
  private int taillex;
  private int tailley;

  public Plateau(int taillex, int tailley) {
    this.taillex = taillex;
    this.tailley = tailley;
    this.plateau = new Cellule[tailley][taillex];
    for (int i = 0; i < tailley; i++) {
      for (int j = 0; j < taillex; j++) {
        this.plateau[i][j] = new Cellule(j,i);
      }
    }
  }

  @Override
  public String toString() {
    String ans = "";
    for (int i = 0; i < this.tailley; i++) {
      for (int j = 0; j < this.taillex; j++) {
        ans += plateau[i][j].toString();
      }
      ans += "\n\n";
    }
    return ans;
  }

  private String quadrillage() {
    String ans = "+";
    for (int i = 0; i < this.taillex; i++) {
      ans += "---+";
    }
    return ans;

  }
}
