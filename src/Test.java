public class Test {


  public static void main(String[] args) {
    Plateau plateau = new Plateau(19,15);
    System.out.println(plateau);
  }

  public static void clearScreen() {
    System.out.print("\u001b[2J");
  }
}
