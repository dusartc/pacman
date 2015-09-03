import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Plateau {

  private Cellule[][] plateau;
  private int taillex;
  private int tailley;
  private ArrayList<Piece> pieces;

  public Plateau(int taillex, int tailley) {
    this.taillex = taillex;
    this.tailley = tailley;
    this.plateau = new Cellule[tailley][taillex];
    this.pieces = new ArrayList<Piece>();
    for (int i = 0; i < tailley; i++) {
      for (int j = 0; j < taillex; j++) {
        this.plateau[i][j] = new Cellule(j, i);
      }
    }
    try {
      BufferedReader br = new BufferedReader(new FileReader(new File("map")));
      String string = br.readLine();
      int i = 0;
      while (string != null) {
        for (int j = 0; j < string.length(); j++) {
          if (string.charAt(j) == 'x') {
            plateau[i][j] = new Cellule(j, i, new Wall(j, i));
          } else {
            plateau[i][j] = new Cellule(j, i, new Piece(j, i));
            this.pieces.add(new Piece(j, i));
          }
        }
        i++;
        string = br.readLine();
      }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // Maze maze = new Maze(10, 10);
    // affichage(maze);
    catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    int k = 1;
    int l = 1;
    int jj;
    Random rd = new Random();
    Personnage personnage = new Personnage(l, k);
    while (true) {
      plateau[k][l].setElement(personnage);
      jj = rd.nextInt(1);
      switch (jj) {
        case 0:
          if (!plateau[k + 1][l].estMur()) {
            plateau[k][l].setVide();
            this.pieces.remove(new Piece(l,k));
            plateau[++k][l].setElement(personnage);
          } else if (!plateau[k - 1][l].estMur()) {
            plateau[k][l].setVide();
            this.pieces.remove(new Piece(l,k));
            plateau[--k][l].setElement(personnage);
          }
          ;
        case 1:
          if (!plateau[k][l + 1].estMur()) {
            plateau[k][l].setVide();
            this.pieces.remove(new Piece(l,k));
            plateau[k][++l].setElement(personnage);
          } else if (!plateau[k][l - 1].estMur()) {
            plateau[k][l].setVide();
            this.pieces.remove(new Piece(l,k));
            plateau[k][--l].setElement(personnage);
          }
          break;
        default:
          break;
      }
      try {
        Thread.sleep(1000);
        System.out.println(this);
        System.out.println("\n\n");
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
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

  private void affichage(Maze maze) {
    int add = 0;
    Cellule[][] aff = new Cellule[20][20];
    Wall[][] ma = maze.getMaze();
    for (int i = 0; i < ma[0].length; i++) {
      for (int j = 0; j < ma.length; j++) {
        if (ma[i][j].sud) {
          aff[i + 1 + add][j] = new Cellule(j, i + 1, " x ");
        } else if (ma[i][j].est) {
          aff[i + add][j] = new Cellule(j + 1, i, " .|");
        }
        if (aff[i][j] == null) {
          aff[i][j] = new Cellule(j, i);
        }
      }
      add++;
    }
    this.plateau = aff;
  }

  public Cellule getCelluleByCoordonnees(Coordonnees coordonnees) {
    /*
     * un simple parcourt des cellules on renvoie celle possedant les memes coordonnees que param
     */
    for (Cellule[] cellules : plateau) {
      for (Cellule cellule : cellules) {
        if (cellule.getCoordonnes().equals(coordonnees)) {
          return cellule;
        }
      }
    }
    return null;
  }
  
  public boolean fini() {
    return this.pieces.isEmpty();
  }


  // public Joueur getJoueur
}
