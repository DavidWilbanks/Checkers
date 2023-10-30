public class tile
{
   private boolean taken;
   private int type; //object in tile 0 = nothing || 1 = black piece || 2 = white piece || 3 = promoted black piece || 4 = promoted white piece
   private boolean color; //background tile color || black false/white true
   
   public tile(boolean take, int typ, boolean colour)
   {
      taken = take;
      type = typ;
      color = colour;
   }
   
   public tile()
   {
      taken = false;
      type = 0;
      color = false;
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
   
   public void updateTile(boolean take, int typ)
   {
      taken = take;
      type = typ;
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
         if(type == 4)
         {
            return "\u25C7 ";
         }
      }
      return null;
   }
}