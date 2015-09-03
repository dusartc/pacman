public class Cellule {

  private Coordonnees coord;
  private Element elem;
  private Element monstre;
  private String string = " . ";

  public Cellule(int x, int y) {
    this.coord = new Coordonnees(x, y);
  }

  public Cellule(int x, int y, Element elem) {
    this.coord = new Coordonnees(x, y);
    this.elem = elem;
  }
  
  public Cellule(int x,int y,String s) {
    this(x, y);
    this.string = s;
  }
  
  public Cellule(int x,int y,String s, Element elem) {
    this(x, y);
    this.string = s;
    this.elem = elem;
  }

  public Coordonnees getCoordonnes() {
    return coord;
  }

  public Element getElement() {
    return elem;
  }

  public void setElement(Element elem) {
    this.elem = elem;
  }

  public void setVide() {
    elem=null;
    monstre=null;
  }

  public boolean isVide() {
    return ((elem==null) && (monstre == null));
  }

  public void addMonstre(Element monstre) {
    this.monstre = monstre;
  }
  
  public boolean contientMonstre() {
    return monstre!=null;
  }
  
  @Override
  public String toString() {
    return this.string;
  }
  
  public void setString(String s) {
    this.string = s;
  }
  
  public boolean estMur() {
    return this.string.equals(" x ");
  }
}
