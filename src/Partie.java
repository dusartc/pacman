import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;



public class Partie implements Curses {

  private boolean listeningConsole = false;
  private Thread keyboardListener;
  private Coordonnees UserCommand;
  private Plateau lePlateau;
  private Dynamique pacBoy;
  private ArrayList<Dynamique> listeMonstre;
  private boolean depart;
  private boolean pause;
  private Dynamique monstre1;
  private Dynamique monstre2;
  private Dynamique monstre3;
  private Dynamique monstre4;

  public Partie() {
    enableKeyTypedInConsole(true);
    this.lePlateau = new Plateau(19, 15, "map");

    this.pacBoy = new Personnage(1, 1);
    this.pacBoy.setCible(this.pacBoy.getCoordonnees());
    this.lePlateau.getCelluleByCoordonnees(this.pacBoy.getCoordonnees()).setElement(this.pacBoy);

    

    this.listeMonstre = new ArrayList<Dynamique>();
    this.monstre1 = new Monstre (1,13);
    this.monstre2 = new Monstre (12,13);
    this.monstre1.setCible(this.pacBoy.getCoordonnees());
    this.lePlateau.getCelluleByCoordonnees(this.monstre1.getCoordonnees()).setElement(this.monstre1);
    this.monstre2.setCible(this.pacBoy.getCoordonnees());
    this.lePlateau.getCelluleByCoordonnees(this.monstre2.getCoordonnees()).setElement(this.monstre2);
    this.listeMonstre.add(monstre1);
    this.listeMonstre.add(monstre2);
  }

  /**
   * Gère les déplacements de toutes les entités sur une unité de temps.
   * 
   * @throws InterruptedException
   */
  public void mouvementTour() {
    this.execMouvement(this.pacBoy, this.mouvementPac());
    
    if (depart) {
      for (int i = 0; i < listeMonstre.size();i++) {
        this.designationCibleMonstre(listeMonstre.get(i));
        this.execMouvement(listeMonstre.get(i),this.mouvementMonstre(listeMonstre.get(i)));
      }
    }
  }

  /**
   * Le pacboy décide, en fonction de son ordre actuel, dans quelle direction il se dirige. Il test
   * d'abord s'il est arrêté (sur sa cible). Si c'est le cas et que son ordre est mauvais (dirige
   * vers une case mur) ou s'il ne possède pas d'ordre, le pacboy annule son ordre actuel et ne
   * bouge pas. Si l'ordre est bon, il choisit cette direction. => Conséquence : il faut redéfinir
   * la zone cible de pacBoy.
   * 
   * Si pacBoy n'est pas sur sa cible (en mouvement), on vérifie si l'ordre actuel de pacBoy
   * correspond à un déplacement possible. Si c'est le cas, pacBoy choisit ce déplacement. =>
   * Conséquence : redéfinition de la zone cible de pacBoy. Sinon, on détermine où est placé pacBoy
   * par rapport à sa zone cible actuelle. On effectue le mouvement de pacBoy vers sa zone cible.
   * 
   * @return
   */
  public Coordonnees mouvementPac() {
    Coordonnees destination = this.pacBoy.choixCroisement(this.analyseCroisement(this.pacBoy));
    if (this.pacBoy.getCoordonnees().compare(this.pacBoy.getCible())) {
      // PacBoy est sur sa cible (il est à l'arrêt).
      if (destination == null) {
        this.pacBoy.setOrdre(null);
        // Si l'ordre est mauvais, il est supprimé et il ne bouge pas.
        return null;
      } else {
        // Si l'ordre est bon, on fait le déplacement voulu par PacBoy.
        // => Redefinition de la zone cible.
        return destination;
      }
    } else {
      if (destination != null) {
        // => Redefinition de la zone cible.
        return destination;
      } else {
        if (this.pacBoy.getCoordonnees().getX() != this.pacBoy.getCible().getX()) {
          if (this.pacBoy.getCoordonnees().getX() > this.pacBoy.getCible().getX()) {
            return new Coordonnees(-1, 0);
          } else {
            return new Coordonnees(1, 0);
          }
        } else {
          if (this.pacBoy.getCoordonnees().getY() > this.pacBoy.getCible().getY()) {
            return new Coordonnees(0, 1);
          } else {
            return new Coordonnees(0, -1);
          }
        }
      }
    }
  }

  public Coordonnees mouvementMonstre(Dynamique entite) {
    if (entite.getCoordonnees().compare(entite.getCible())) {
      this.designationCibleMonstre(entite);
    }
    Coordonnees destination = entite.choixCroisement(this.analyseCroisement(entite));
    entite.setOrdre(destination);
    return destination;
  }
  
  public void designationCibleMonstre (Dynamique entite) {
    entite.setCible(this.pacBoy.getCoordonnees());
  }

  /**
   * Méthode qui, à partir de la position des coordonnées d'un dynamique, génére une liste des
   * coordonnées des mouvements possibles pour ce dynamique. On vérifie principalement si la case de
   * destination n'est pas un mur.
   * 
   * @param entite
   * @return
   */
  public ArrayList<Coordonnees> analyseCroisement(Dynamique entite) {
    ArrayList<Coordonnees> result = new ArrayList<Coordonnees>();
    Coordonnees origine = entite.getCoordonnees();
    Coordonnees tmp;

    tmp = new Coordonnees(origine.getX() + 1, origine.getY());
    if (!this.lePlateau.getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(1, 0));
    }
    tmp = new Coordonnees(origine.getX() - 1, origine.getY());
    if (!this.lePlateau.getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(-1, 0));
    }
    tmp = new Coordonnees(origine.getX(), origine.getY() + 1);
    if (!this.lePlateau.getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(0, 1));
    }
    tmp = new Coordonnees(origine.getX(), origine.getY() - 1);
    if (!this.lePlateau.getCelluleByCoordonnees(tmp).estMur()) {
      result.add(new Coordonnees(0, -1));
    }
    return result;
  }

  /**
   * Cette méthode permet de faire exécuter un mouvement à une entité. Sa conséquence est de retirer
   * l'entité de son emplacement précédent, et de le placer à son nouvel emplacement.
   * 
   * @param entite
   * @param deplacement
   */
  public void execMouvement(Dynamique entite, Coordonnees deplacement) {
    if (deplacement == null) {
      return;
    } else {
      Coordonnees tmp =
          new Coordonnees(entite.getCoordonnees().getX() + deplacement.getX(), entite
              .getCoordonnees().getY() + deplacement.getY());
      this.lePlateau.updatePersonnage(entite, tmp);
      if (entite instanceof Personnage) {
        this.designationCible(entite, deplacement);
      }
    }
  }
  
  public void designationCible(Dynamique entite, Coordonnees deplacement) {
    Coordonnees actuel = new Coordonnees (entite.getCoordonnees().getX(), entite.getCoordonnees().getY());
    Coordonnees tmp = new Coordonnees (actuel.getX(), actuel.getY());
    do {
 
      tmp.addCoord(deplacement);

      if (!this.lePlateau.getCelluleByCoordonnees(tmp).estMur()) {
        actuel.addCoord(deplacement);
      }
    } while (!this.lePlateau.getCelluleByCoordonnees(tmp).estMur());
    entite.setCible(actuel);
  }

  public Plateau getPlateau() {
    return this.lePlateau;
  }

  public Coordonnees getCommand() {
    return UserCommand;
  }



  void keyTypedInConsole(char c) {

    this.depart = true;

    switch (c) {
      case 'z':
      case 'Z':
        this.UserCommand = new Coordonnees(0, -1);
        // System.out.println(UserCommand.getX()+" "+UserCommand.getY());
        break;
      case 'q':
      case 'Q':
        this.UserCommand = new Coordonnees(-1, 0);
        // System.out.println(UserCommand.getX()+" "+UserCommand.getY());
        break;
      case 's':
      case 'S':
        this.UserCommand = new Coordonnees(0, 1);
        // System.out.println(UserCommand.getX()+" "+UserCommand.getY());
        break;
      case 'd':
      case 'D':
        this.UserCommand = new Coordonnees(1, 0);
        // System.out.println(UserCommand.getX()+" "+UserCommand.getY());
        break;
      case 'p':
      case 'P':
        this.pause = !this.pause;
    }
    this.pacBoy.setOrdre(this.UserCommand);
  }



  public void enableKeyTypedInConsole(boolean on) {
    if (!listeningConsole && on) {
      listeningConsole = true;
      keyboardListener = new Thread() {
        public void run() {
          try {
            String[] cmd = {"/bin/sh", "-c", "stty raw</dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
            Console console = System.console();
            Reader reader = console.reader();
            while (listeningConsole) {
              int c = reader.read();
              if (c == 27) { // ESC
                c = reader.read();
                if (c == 91) { // [
                  c = reader.read(); // directional arrow !
                  switch (c) { // surcharging DC1-4 ...
                    case 65:
                      keyTypedInConsole(ANSI_UP);
                      break;
                    case 66:
                      keyTypedInConsole(ANSI_DOWN);
                      break;
                    case 67:
                      keyTypedInConsole(ANSI_RIGHT);
                      break;
                    case 68:
                      keyTypedInConsole(ANSI_LEFT);
                      break;
                  }
                } else {
                  // System.err.println("Unknown ANSIcode : "+c);
                }
              } else {
                keyTypedInConsole((char) c);
              }
              Thread.sleep(100);
            }
            reader.close();
          } catch (IOException ioe) {
            ioe.printStackTrace();
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
        }
      };
      keyboardListener.start();
    } else {
      listeningConsole = false;
      try {
        String[] cmd = new String[] {"/bin/sh", "-c", "stty sane</dev/tty"};
        Runtime.getRuntime().exec(cmd).waitFor();
      } catch (IOException io) {
        io.printStackTrace();
      } catch (InterruptedException ioe) {
        ioe.printStackTrace();
      }
    }
  }

  class KeyboardThread implements Runnable {

    public String line = null;
    public final long timeout;

    public KeyboardThread(long timeout) {
      this.timeout = timeout;
    }

    @Override
    public void run() {
      final long start = System.currentTimeMillis();
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      try {
        // to flush previous inputs !
        while (br.ready()) {
          br.readLine();
        }
        while (System.currentTimeMillis() - start < timeout && line == null) {
          if (br.ready()) {
            line = br.readLine();
          }
          Thread.sleep(250);
        }
      } catch (IOException ioe) {
        throw new RuntimeException("[iJava] IO error whilewaiting input with timeout !\n"
            + ioe.getMessage());
      } catch (InterruptedException ex) {
        throw new RuntimeException("[iJava] Interruption errorwhile waiting input with timeout !\n"
            + ex.getMessage());
      }
    }
  }



  @Override
  public void reset() {
    // TODO Auto-generated method stub
  }

  @Override
  public void show() {
    // TODO Auto-generated method stub
  }

  @Override
  public void hide() {
    // TODO Auto-generated method stub
  }

  @Override
  public void up() {
    // TODO Auto-generated method stub
  }

  @Override
  public void up(int n) {
    // TODO Auto-generated method stub
  }

  @Override
  public void down() {
    // TODO Auto-generated method stub
  }

  @Override
  public void down(int n) {
    // TODO Auto-generated method stub
  }

  @Override
  public void forward() {
    // TODO Auto-generated method stub
  }

  @Override
  public void forward(int n) {
    // TODO Auto-generated method stub
  }

  @Override
  public void backward() {
    // TODO Auto-generated method stub
  }

  @Override
  public void backward(int n) {
    // TODO Auto-generated method stub
  }

  @Override
  public void cusp() {
    // TODO Auto-generated method stub
  }

  @Override
  public void curp() {
    // TODO Auto-generated method stub
  }

  @Override
  public void cursor(int line, int column) {
    // TODO Auto-generated method stub
  }

  @Override
  public void clearEOL() {
    // TODO Auto-generated method stub
  }

  @Override
  public void clearBOL() {
    // TODO Auto-generated method stub
  }

  @Override
  public void clearLine() {
    // TODO Auto-generated method stub
  }

  @Override
  public void clearScreen() {
    // TODO Auto-generated method stub
  }

  @Override
  public void text(String color) {
    // TODO Auto-generated method stub
  }

  @Override
  public String randomANSIColor() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void background(String color) {
    // TODO Auto-generated method stub
  }



  public static void main(String[] args) {
    Partie partieTest = new Partie();
    //System.out.println(partieTest.lePlateau.getCelluleByCoordonnees(new Coordonnees(9,1)).estMur());
    //System.out.print(partieTest.getPlateau().toString());
    while (!partieTest.lePlateau.fini() && !partieTest.pause) {
      try {
        partieTest.mouvementTour();
        Thread.sleep(200);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      clearScreenBis();
      partieTest.enableKeyTypedInConsole(false);
      System.out.print(partieTest.getPlateau().toString());
      partieTest.enableKeyTypedInConsole(true);
    }
    System.out.println("Bravo !");
  }

  public static void clearScreenBis() {
    System.out.print("\u001b[2J");
  }
}
