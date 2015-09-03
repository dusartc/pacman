public class Wall extends Cellule {

  boolean nord;
  boolean sud;
  boolean est;
  boolean ouest;

  public Wall(int x, int y) {
    super(x, y);
    nord = true;
    sud = true;
    est = true;
    ouest = true;
  }

  public void removeNord() {
//    System.out.println(super.getCoordonnes().getX() + " " + super.getCoordonnes().getY()
//        + " mur nord supprime");
    nord = false;
  }

  public void removeSud() {
//    System.out.println(super.getCoordonnes().getX() + " " + super.getCoordonnes().getY()
//        + " mur sud supprime");
    sud = false;
  }

  public void removeEst() {
//    System.out.println(super.getCoordonnes().getX() + " " + super.getCoordonnes().getY()
//        + " mur est supprime");
    est = false;
  }

  public void removeOuest() {
//    System.out.println(super.getCoordonnes().getX() + " " + super.getCoordonnes().getY()
//        + " mur ouest supprime");
    ouest = false;
  }

}
