
public class Bonus extends Element{
  
  private Effet effet;
  
  public Bonus(int x, int y, int z) {
    super(x,y);
    this.effet = new Effet(z);
  }
  
  public Effet getEffet() {
    return this.effet;
  }

}
