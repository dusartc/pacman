
public class Effet {
  
  private int type;
  
  public Effet(int a) {
    this.type = a;
  }
  
  public boolean isRalentissement() {
    return type == 0;
  }
  
  public boolean isManger() {
    return type == 1;
  }
  
  public boolean isPoints() {
    return type == 2;
  }

}
