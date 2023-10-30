public class move
{
   private int xs;
   private int ys;
   private int direction;
   private boolean taking;
   
   public move(int xstart, int ystart, int dir, boolean take)
   {
      xs = xstart;
      ys = ystart;
      direction = dir;
      taking = take;
   }
   
   public int getDirection()
   {
      return direction;
   }
   
   public boolean getTake()
   {
      return taking;
   }
   
   public int getX()
   {
      return xs;
   }
   
   public int getY()
   {
      return ys;
   }
}