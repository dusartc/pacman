import java.util.ArrayList;
import java.util.Random;


public class Maze {

  private Wall[][] maze;
  private ArrayList<Coordonnees> list;
  private boolean[][] visite;
  private Random rd;
  private int alea;
  private int x;
  private int y;

  public Maze(int x, int y) {
    this.x = x;
    this.y = y;
    maze = new Wall[y][x];
    visite = new boolean[y][x];
    list = new ArrayList<Coordonnees>();
    rd = new Random();
    for (int i = 0; i < y; i++) {
      for (int j = 0; j < y; j++) {
        maze[i][j] = new Wall(j, i);
        visite[i][j] = false;
        if (x == 0) {
          visite[i][j] = true;
        }
        if (y == 0) {
          visite[i][j] = true;
        }
      }
    }
    generate();
  }

  private void generate() {
    generate(5, 5);
  }

  private void generate(int x, int y) {
    visite[y][x] = true;
    System.out.println("true");
    if (x != 0 && y != 0 && x < this.x - 1 && y < this.y - 1) {
      while (!visite[y - 1][x] || !visite[y + 1][x] || !visite[y][x - 1] || !visite[y][x + 1]) {
        alea = rd.nextInt(3);
        System.out.println(alea);
        switch (alea) {
          case 0:
            if (!visite[y][x-1]) {
              maze[y][x].removeOuest();
              maze[y][x - 1].removeEst();
              generate(x - 1, y);
              break;
            }
          case 1:
            if (!visite[y][x+1]) {
              maze[y][x].removeEst();
              maze[y][x + 1].removeOuest();
              generate(x + 1, y);
              break;
            }            
          case 2:
            if (!visite[y-1][x]) {
              maze[y][x].removeNord();
              maze[y - 1][x].removeSud();
              generate(x, y - 1);
              break;
            }            
          case 3:
            if (!visite[y+1][x]) {
              maze[y][x].removeSud();
              maze[y + 1][x].removeNord();
              generate(x, y + 1);
              break;
            }            
          default:
            break;
        }
      }
    }
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (visite[i][j]) {
          System.out.print(" t ");
        }else {
          System.out.print(" f ");
        }
      }System.out.println("\n");
      
    }
  }
  
  public Wall[][] getMaze() {
    return maze;
  }
}
