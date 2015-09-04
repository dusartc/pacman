import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;


public class Oui {

  @Test
  public void testCoordonneesX() {
    Coordonnees coord = new Coordonnees(6, 9);
    assertEquals(coord.getX(), 6);
  }

  @Test
  public void testCoordonneesY() {
    Coordonnees coord = new Coordonnees(6, 9);
    assertEquals(coord.getY(), 9);
  }

  @Test
  public void testCoordonneesComp() {
    Coordonnees coord = new Coordonnees(6, 9);
    Coordonnees oui = new Coordonnees(6, 9);
    assertTrue(coord.compare(oui));
  }

  @Test
  public void testCelluleCoord() {
    Cellule c = new Cellule(6, 9);
    Coordonnees coord = new Coordonnees(6, 9);
    assertTrue(coord.compare(c.getCoordonnes()));
  }

  @Test
  public void testCelluleVide() {
    Cellule c = new Cellule(6, 9);
    assertTrue(c.isVide());
  }

  @Test
  public void testCellulePasVide() {
    Cellule c = new Cellule(6, 9);
    Piece p = new Piece();
    c.setElement(p);
    assertFalse(c.isVide());
  }

  @Test
  public void testCellulePasMonstre() {
    Cellule c = new Cellule(6, 9);
    assertFalse(c.contientMonstre());
  }

  @Test
  public void testCelluleMonstre() {
    Cellule c = new Cellule(6, 9);
    Monstre m = new Monstre(1, 1);
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

  @Test
  public void testBonusType() {
    Bonus b = new Bonus(1, 1, 0);
    assertEquals(0, b.getEffet().getType());

  }

  @Test
  public void testBonus0() {
    Bonus b = new Bonus(1, 1, 0);
    Effet e = new Effet(0);
    assertTrue(e.equals(b.getEffet()));

  }

  @Test
  public void testBonus1() {
    Bonus b = new Bonus(1, 1, 1);
    Effet e = new Effet(1);
    assertTrue(e.equals(b.getEffet()));

  }

  @Test
  public void testBonus2() {
    Bonus b = new Bonus(1, 1, 2);
    Effet e = new Effet(2);
    assertTrue(e.equals(b.getEffet()));

  }

  @Test
  public void testDynamiqueOrdre() {

    Personnage p = new Personnage(1,1);
    p.setOrdre(new Coordonnees(2,2));
    Coordonnees c = new Coordonnees(2,2);
    assertTrue(p.getOrdre().compare(c));

  }
  
  @Test
  public void testDynamiqueCible() {

    Personnage p = new Personnage(1,1);
    p.setCible(new Coordonnees(2,2));
    Coordonnees c = new Coordonnees(2,2);
    assertTrue(p.getCible().compare(c));

  }
  
  @Test
  public void testPersonnageChoix() {

    ArrayList<Coordonnees> choixPossible = new ArrayList<Coordonnees>();
    Personnage p = new Personnage (1,1);
    p.setOrdre(new Coordonnees(1,2));
    choixPossible.add(new Coordonnees(2,3));
    choixPossible.add(new Coordonnees(3,2));
    choixPossible.add(new Coordonnees(1,2));
    
    assertTrue((p.choixCroisement(choixPossible)).compare(choixPossible.get(2)));
    
  }
  
  @Test
  public void testPersonnageChoixFaux() {

    ArrayList<Coordonnees> choixPossible = new ArrayList<Coordonnees>();
    Personnage p = new Personnage (1,1);
    p.setOrdre(new Coordonnees(1,2));
    choixPossible.add(new Coordonnees(2,3));
    choixPossible.add(new Coordonnees(3,2));
    choixPossible.add(new Coordonnees(2,2));
    
    assertTrue(p.choixCroisement(choixPossible)==null);
    
  }
  
  
  /*
  @Test
  public void testMonstreCalculChoix() {
    
    Monstre m = new Monstre(1,1);
    m.setCible(new Coordonnees(1,2));
    
    
  }
  
  @Test
  public void testMonstreChoix() {
    ArrayList<Coordonnees> choixPossible = new ArrayList<Coordonnees>();
    ArrayList<Coordonnees> placementsPossibles = new ArrayList<Coordonnees>();

    Monstre m = new Monstre(1,1);
    m.setOrdre(new Coordonnees(1,2));
    choixPossible.add(new Coordonnees(2,3));
    choixPossible.add(new Coordonnees(3,2));
    choixPossible.add(new Coordonnees(1,2));
    
    assertTrue((m.choixCroisement(choixPossible)).compare(choixPossible.get(2)));

    
  }
  */

}
