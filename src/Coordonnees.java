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
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }

  public void addCoord(Coordonnees coord) {
    this.x = this.x + coord.getX();
    this.y = this.y + coord.getY();
  }
  
  public boolean compare(Coordonnees coord) {
    return ((this.x == coord.getX())&&(this.y == coord.getY()));
  }

  public String toString() {
    return this.x + " " + this.y;
  }
}
