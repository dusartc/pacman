public class Coordonnees {

  private int x;
  private int y;

  public Coordonnees(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void addCoord(Coordonnees coord) {
    this.x = this.x + coord.getX();
    this.y = this.y + coord.getY();
  }
  
  public boolean compare(Coordonnees coord) {
    return ((this.x == coord.getX())&&(this.y == coord.getY()));
  }

}
