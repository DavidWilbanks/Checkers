import java.util.*;

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
   
   public ArrayList<move> generateMovablePieces(boolean color)
   {
      //black is false and odd black is on top
      //white is true and even white is on bottom
      ArrayList<move> standardMoves = new ArrayList<move>();
      ArrayList<move> forceMoves = new ArrayList<move>();
      if(color == false)
      {
         for(int x = 0; x < 8; x++)
         {
            for(int y = 0; y < 8; y++)
            {
               if(board[x][y].getType() == 1)
               {  
                  if(x != 0 && y != 7)
                  {
                     if(board[x-1][y+1].getType() == 2 || board[x-1][y+1].getType() == 4)
                     {
                        if(x > 1 && y < 6)
                        {
                           if(board[x-2][y+2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,3,true));
                           }
                        }
                     } 
                     if(board[x-1][y+1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,3,false));
                     }
                  }
                  
                  if(x != 7 && y != 7)
                  {
                     if(board[x+1][y+1].getType() == 2 || board[x+1][y+1].getType() == 4)
                     {
                        if(x < 6 && y < 6)
                        {
                           if(board[x+2][y+2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,4,true));
                           }
                        }
                     } 
                     if(board[x+1][y+1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,4,false));
                     }
                  }
               }
               
               if(board[x][y].getType() == 3)
               {
                  if(x != 0 && y != 0)
                  {
                     if(board[x-1][y-1].getType() == 2 || board[x-1][y-1].getType() == 4)
                     {
                        if(x > 1 && y > 1)
                        {
                           if(board[x-2][y-2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,1,true));
                           }
                        }
                     } 
                     if(board[x-1][y-1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,1,false));
                     }
                  }
                  
                  if(x != 7 && y != 0)
                  {
                     if(board[x+1][y-1].getType() == 2 || board[x+1][y-1].getType() == 4)
                     {
                        if(x < 6 && y > 1)
                        {
                           if(board[x+2][y-2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,2,true));
                           }
                        }
                     } 
                     if(board[x+1][y-1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,2,false));
                     }
                  }
                  
                  if(x != 0 && y != 7)
                  {
                     if(board[x-1][y+1].getType() == 2 || board[x-1][y+1].getType() == 4)
                     {
                        if(x > 1 && y < 6)
                        {
                           if(board[x-2][y+2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,3,true));
                           }
                        }
                     } 
                     if(board[x-1][y+1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,3,false));
                     }
                  }
                  
                  if(x != 7 && y != 7)
                  {
                     if(board[x+1][y+1].getType() == 2 || board[x-1][y+1].getType() == 4)
                     {
                        if(x < 6 && y < 6)
                        {
                           if(board[x+2][y+2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,4,true));
                           }
                        }
                     } 
                     if(board[x+1][y+1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,4,false));
                     }
                  }
               }
            }
         }
      }
      if(color == true)
      {
         for(int x = 0; x < 8; x++)
         {
            for(int y = 0; y < 8; y++)
            {
               if(board[x][y].getType() == 2)
               {  
                  if(x != 0 && y != 0)
                  {
                     if(board[x-1][y-1].getType() == 1 || board[x-1][y-1].getType() == 3)
                     {
                        if(x > 1 && y > 1)
                        {
                           if(board[x-2][y-2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,1,true));
                           }
                        }
                     } 
                     if(board[x-1][y-1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,1,false));
                     }
                  }
                  
                  if(x != 7 && y != 0)
                  {
                     if(board[x+1][y-1].getType() == 1 || board[x+1][y-1].getType() == 3)
                     {
                        if(x < 6 && y > 1)
                        {
                           if(board[x+2][y-2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,2,true));
                           }
                        }
                     } 
                     if(board[x+1][y-1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,2,false));
                     }
                  }
               }
               
               if(board[x][y].getType() == 3)
               {
                  if(x != 0 && y != 0)
                  {
                     if(board[x-1][y-1].getType() == 1 || board[x-1][y-1].getType() == 3)
                     {
                        if(x > 1 && y > 1)
                        {
                           if(board[x-2][y-2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,1,true));
                           }
                        }
                     } 
                     if(board[x-1][y-1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,1,false));
                     }
                  }
                  
                  if(x != 7 && y != 0)
                  {
                     if(board[x+1][y-1].getType() == 1 || board[x+1][y-1].getType() == 3)
                     {
                        if(x < 6 && y > 1)
                        {
                           if(board[x+2][y-2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,2,true));
                           }
                        }
                     } 
                     if(board[x+1][y-1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,2,false));
                     }
                  }
                  
                  if(x != 0 && y != 7)
                  {
                     if(board[x-1][y+1].getType() == 1 || board[x-1][y+1].getType() == 3)
                     {
                        if(x > 1 && y < 6)
                        {
                           if(board[x-2][y+2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,3,true));
                           }
                        }
                     } 
                     if(board[x-1][y+1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,3,false));
                     }
                  }
                  
                  if(x != 7 && y != 7)
                  {
                     if(board[x+1][y+1].getType() == 1 || board[x-1][y+1].getType() == 3)
                     {
                        if(x < 6 && y < 6)
                        {
                           if(board[x+2][y+2].getTaken() == false)
                           {
                              forceMoves.add(new move(x,y,4,true));
                           }
                        }
                     } 
                     if(board[x+1][y+1].getType() == 0)
                     {
                        standardMoves.add(new move(x,y,4,false));
                     }
                  }
               }
            }
         }
      }
      
      if(forceMoves.size() > 0)
      {
         return forceMoves;
      }
      else
      {
         return standardMoves;
      }
   }
   
   public void makeMove(int x, int y, int direction, boolean take, int startingType)
   {
      if(take == true)
      {
         switch(direction)
         {
            case 1:
               board[x][y].updateTile(false,0);
               board[x-1][y-1].updateTile(true,startingType);
            break;
            case 2:
               
            break;
            case 3:
               
            break;
            case 4:
               
            break;
         }
      }
      if(take == false)
      {
         switch(direction)
         {
            case 1:
               
            break;
            case 2:
               
            break;
            case 3:
               
            break;
            case 4:
               
            break;
         }
      }
   }
}