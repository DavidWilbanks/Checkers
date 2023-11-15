public class move
{
   private int xs; //x intial
   private int ys; //y intial
   private int xf; //x final
   private int yf; //y final
   private int direction; //direction
   private boolean taking; //if it is capturing or not
   private int pseudo; //storage value
   private int pseudoFinal; //storage value 2
   
   //constructor
   public move(int xstart, int ystart, int dir, boolean take)
   {
      xs = xstart;
      ys = ystart;
      direction = dir;
      taking = take;
      pseudo = 0;
      pseudoFinal = 0;
      //sets the x and y final based on the direction the move is going and if it is taking a piece
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
   
   //getter methods
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