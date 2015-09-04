import static org.junit.Assert.*;

import org.junit.Test;


public class Oui {

  @Test
  public void testCoordonnees() {
    Coordonnees coord = new Coordonnees(6,9);
    Coordonnees oui = new Coordonnees(6,9);
    Coordonnees non = new Coordonnees(12,18);
    assertEquals(coord.getX(), 6);
    assertEquals(coord.getY(), 9);
    assertTrue(coord.compare(oui));
  }
  
  @Test
  public void testCellule() {
    Cellule c = new Cellule(6,9);
    Piece p = new Piece();
    Monstre m = new Monstre(1,1);
    Coordonnees coord = new Coordonnees(6,9);
    assertTrue(coord.compare(c.getCoordonnes()));
    assertTrue(c.isVide());
    c.setElement(p);
    assertFalse(c.isVide());
    assertFalse(c.contientMonstre());
    c.addMonstre(m);
    assertTrue(c.contientMonstre());
  }

  @Test
  public void testEffet() {
    Effet e = new Effet(1);
    assertTrue(e.isManger());
    assertFalse(e.isRalentissement());
    assertFalse(e.isPoints());
    
    Effet e2 = new Effet(0);
    assertFalse(e2.isManger());
    assertTrue(e2.isRalentissement());
    assertFalse(e2.isPoints());
    
    Effet e3 = new Effet(2);
    assertFalse(e3.isManger());
    assertFalse(e3.isRalentissement());
    assertTrue(e3.isPoints());

  }
  
}
