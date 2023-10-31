public class move
{
   private int xs;
   private int ys;
   private int xf;
   private int yf;
   private int direction;
   private boolean taking;
   private int pseudo;
   private int pseudoFinal;
   
   public move(int xstart, int ystart, int dir, boolean take)
   {
      xs = xstart;
      ys = ystart;
      direction = dir;
      taking = take;
      pseudo = 0;
      pseudoFinal = 0;
      if(taking == false)
      {
         switch(direction)
         {
            case 1:
               xf = xs-1;
               yf = ys-1;
            break;
            case 2:
               xf = xs+1;
               yf = ys-1;
            break;
            case 3:
               xf = xs-1;
               yf = ys+1;
            break;
            case 4:
               xf = xs+1;
               yf = ys+1;
            break;
         }
      }
      if(taking == true)
      {
         switch(direction)
         {
            case 1:
               xf = xs-2;
               yf = ys-2;
            break;
            case 2:
               xf = xs+2;
               yf = ys-2;
            break;
            case 3:
               xf = xs-2;
               yf = ys+2;
            break;
            case 4:
               xf = xs+2;
               yf = ys+2;
            break;
         }
      }
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
   
   public int getXf()
   {
      return xf;
   }
   
   public int getYf()
   {
      return yf;
   }
   
   public int getPseudo()
   {
      return pseudo;
   }
   
   public void updatePseudo(int c)
   {
      pseudo = c;
   }
   
   public int getPseudoFinal()
   {
      return pseudoFinal;
   }
   
   public void updatePseudoFinal(int c)
   {
      pseudoFinal = c;
   }
}