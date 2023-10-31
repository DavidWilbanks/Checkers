public class player 
{
   private boolean aiControlled;
   private int pieces;
   private boolean color;
   private board board;
   
   public player(boolean ai, boolean c, board b)
   {
      aiControlled = ai;
      pieces = 12;
      color = c;
      board = b;
   }   

   public int getPieces()
   {
      return pieces;
   }
   
   public void updatePieces(int amt)
   {
      pieces += amt;
   }
}