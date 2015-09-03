public abstract class Element {

  private Coordonnees coord;
  
  public Element() {
    
  }

  public Element(int x, int y) {
    this.coord = new Coordonnees(x, y);
  }

  public Coordonnees getCoordonnees() {
    return coord;
  }

  public void setCoordonnees(int x, int y) {
    this.coord = new Coordonnees(x, y);
  }

  public void setCoordonnees(Coordonnees coord) {
    this.coord = coord;
  }

}
