import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;



public class Partie implements Curses {

  private boolean listeningConsole = false;
  private Thread keyboardListener;
  private Coordonnees coord;

  public Partie() {
    enableKeyTypedInConsole(true);
  }

  void keyTypedInConsole(char c) {

    switch (c) {
      case 'z':
      case 'Z':
        this.coord = new Coordonnees(0, 1);
        //System.out.println(coord.getX()+" "+coord.getY());
        break;
      case 'q':
      case 'Q':
        this.coord = new Coordonnees(-1, 0);
        //System.out.println(coord.getX()+" "+coord.getY());
        break;
      case 's':
      case 'S':
        this.coord = new Coordonnees(0, -1);
        //System.out.println(coord.getX()+" "+coord.getY());
        break;
      case 'd':
      case 'D':
        this.coord = new Coordonnees(1, 0);
        //System.out.println(coord.getX()+" "+coord.getY());
        break;

    }
  }

  public Coordonnees getCoordonnees() {
    return coord;
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
    Partie partie = new Partie();
  }
}
