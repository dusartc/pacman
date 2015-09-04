import static org.junit.Assert.*;

import org.junit.Test;


public class Oui {

  @Test
  public void testCoordonneesX() {
    Coordonnees coord = new Coordonnees(6,9);
    assertEquals(coord.getX(), 6);
  }
  
  @Test
  public void testCoordonneesY() {
    Coordonnees coord = new Coordonnees(6,9);
    assertEquals(coord.getY(), 9);
  }
  
  @Test
  public void testCoordonneesComp() {
    Coordonnees coord = new Coordonnees(6,9);
    Coordonnees oui = new Coordonnees(6,9);
    assertTrue(coord.compare(oui));
  }
  
  @Test
  public void testCelluleCoord() {
    Cellule c = new Cellule(6,9);
    Coordonnees coord = new Coordonnees(6,9);
    assertTrue(coord.compare(c.getCoordonnes()));
  }
  
  @Test
  public void testCelluleVide() {
    Cellule c = new Cellule(6,9);
    assertTrue(c.isVide());
  }
  
  @Test
  public void testCellulePasVide() {
    Cellule c = new Cellule(6,9);
    Piece p = new Piece();
    c.setElement(p);
    assertFalse(c.isVide());
  }
  
  @Test
  public void testCellulePasMonstre() {
    Cellule c = new Cellule(6,9);
    assertFalse(c.contientMonstre());
  }
  
  @Test
  public void testCelluleMonstre() {
    Cellule c = new Cellule(6,9);
    Monstre m = new Monstre(1,1);
    c.addMonstre(m);
    assertTrue(c.contientMonstre());
  }

  
  
  @Test
  public void testEffet0() {
    
    Effet e = new Effet(0);
    assertFalse(e.isManger());
    assertTrue(e.isRalentissement());
    assertFalse(e.isPoints());

  }
  
  @Test
  public void testEffet1() {
    Effet e = new Effet(1);
    assertTrue(e.isManger());
    assertFalse(e.isRalentissement());
    assertFalse(e.isPoints());

  }
  
  @Test
  public void testEffet2() {
    Effet e = new Effet(2);
    assertFalse(e.isManger());
    assertFalse(e.isRalentissement());
    assertTrue(e.isPoints());

  }
  
}
