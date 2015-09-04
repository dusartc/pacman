import static org.junit.Assert.*;

import org.junit.Test;


public class TestPlateau {
  
  Plateau plateau;
  
  public void setUp() {
    plateau = new Plateau(3, 4);
    plateau.loadMap("testmap");
    System.out.println(plateau);
  }

  @Test
  public void test() {
    setUp();
    assertTrue(plateau.getCelluleByCoordonnees(new Coordonnees(0, 0)).estMur());
    assertFalse(plateau.getCelluleByCoordonnees(new Coordonnees(1, 1)).isVide());
    plateau.getCelluleByCoordonnees(new Coordonnees(1, 1)).setVide();
    assertTrue(plateau.getCelluleByCoordonnees(new Coordonnees(1, 1)).isVide());
    assertNull(plateau.getCelluleByCoordonnees(new Coordonnees(-1, -1)));
  }

}
