public class board
{
   private tile board[][];
   
   public board()
   {  
      board = new tile[8][8];
      for(int x = 0; x < 8; x++)
      {
         for(int y = 0; y < 8; y++)
         {
            if((x+y)%2 == 0 && y < 3)
            {
               board[x][y] = new tile(true,1,false);
            }
            else if((x+y)%2 == 0 && y > 4)
            {
               board[x][y] = new tile(true,2,false);
            }
            else if((x+y)%2 == 1 && (y == 3 || y == 4))
            {
               board[x][y] = new tile(false,0,true);
            }
            else if((x+y)%2 == 0 && (y == 3 || y == 4))
            {
               board[x][y] = new tile(false,0,false);
            }
            else
            {
               board[x][y] = new tile(false,0,true);
            }
         }
      }
   }
   
   public void printboard()
   {
      for(int x = 0; x < 8; x++)
      {
         for(int y = 0; y < 8; y++)
         {
            System.out.print(board[y][x].toString());
         }
         System.out.println("");
      }
   }
}