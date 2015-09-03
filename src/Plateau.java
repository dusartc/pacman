import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Plateau {

  private Cellule[][] plateau;
  private int taillex;
  private int tailley;

  public Plateau(int taillex, int tailley) {
    this.taillex = taillex;
    this.tailley = tailley;
    this.plateau = new Cellule[tailley][taillex];
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
            plateau[i][j] = new Cellule(j, i, " x ");
          } else {
            plateau[i][j] = new Cellule(j, i, "   ");
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



  // public Joueur getJoueur
}
