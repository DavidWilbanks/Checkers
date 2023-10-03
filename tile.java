public class tile
{
   private boolean taken;
   private int type;
   private boolean color;
   
   public tile(boolean take, int typ, boolean colour)
   {
      taken = take;
      type = typ;
      color = colour;
   }
   
   public boolean getTaken()
   {
      return taken;
   }
   
   public int getType()
   {
      return type;
   }
   
   public boolean getColor()
   {
      return color;
   }
   
   public String toString()
   {
      if(taken == false && color == false)
      {
         return "  ";
      }
      else if(taken == false)
      {
         return "\u25A0 ";
      }
      else
      {
         if(type == 1)
         {
            return "\u25C9 ";
         }
         if(type == 2)
         {
            return "\u25CB ";
         }
         if(type == 3)
         {
            return "\u25C8 ";
         }
         else
         {
            return "\u25C7 ";
         }
      }

   }
}