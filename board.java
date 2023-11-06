import java.util.*;

public class board
{
   private tile board[][];
   private int piecesW;
   private int piecesB;
   
   public board()
   {  
      board = new tile[8][8];
      piecesW = 12;
      piecesB = 12;
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
   
   public int getPiecesW()
   {
      return piecesW;
   }
   
   public int getPiecesB()
   {
      return piecesB;
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
      System.out.println("");
   }
   
   public void moveSelect(ArrayList<move> moves)
   {
      int max = 0;
      int input = 0;
      boolean t = false;
      Scanner sc = new Scanner(System.in);
      for(int y = 0; y < 8; y++)
      {
         for(int x = 0; x < 8; x++)
         {
            t = false;
            aa:
            for(int i = 0; i < moves.size(); i++)
            {
               if((moves.get(i)).getX() == x && (moves.get(i)).getY() == y)
               {
                  t = true;
                  max ++;
                  System.out.print(max + " ");
                  (moves.get(i)).updatePseudo(max);
                  while(true)
                  {
                     if(moves.size() > i+1)
                     {
                        i++;
                        if((moves.get(i)).getX() == x && (moves.get(i)).getY() == y)
                        {
                           (moves.get(i)).updatePseudo(max);
                        }
                        else
                        {
                           break aa;
                        }
                     }
                     else
                     {
                        break aa;
                     }
                  }
               }
            }
            if(t == false)
            {
               System.out.print(board[x][y].toString());
            }
         }
         System.out.println("");
      }
      while(input < 1 || input > max)
      {
         System.out.println("Choose a valid piece to move (enter 1 - " + max + " )");
         input = sc.nextInt();
         if(input < 1 || input > max)
         {
            System.out.println("Invalid piece");
         }
      }
      
      int maxTwo = 0;
      int inputTwo = 0;
      for(int y = 0; y < 8; y++)
      {
         for(int x = 0; x < 8; x++)
         {
            t = false;
            for(int i = 0; i < moves.size(); i++)
            {
               if((moves.get(i)).getX() == x && (moves.get(i)).getY() == y && (moves.get(i)).getPseudo() == input)
               {
                  System.out.print("x ");
                  t = true;
                  break;
               }
               else if((moves.get(i)).getXf() == x && (moves.get(i)).getYf() == y && (moves.get(i)).getPseudo() == input)
               {
                  maxTwo ++;
                  System.out.print(maxTwo + " ");
                  t = true;
                  (moves.get(i)).updatePseudoFinal(maxTwo);
               }
            }
            if(t == false)
            {
               System.out.print(board[x][y].toString());
            }
         }
         System.out.println("");
      }
      
      while(inputTwo < 1 || inputTwo > maxTwo)
      {
         System.out.println("Choose a valid move to make (enter 1 - " + maxTwo + " )");
         inputTwo = sc.nextInt();
         if(inputTwo < 1 || inputTwo > maxTwo)
         {
            System.out.println("Invalid move");
         }
      }
      
      for(int i = 0; i < moves.size(); i++)
      {
         if((moves.get(i)).getPseudo() == input && (moves.get(i)).getPseudoFinal() == inputTwo)
         {
            makeMove(moves.get(i).getX(), moves.get(i).getY(), moves.get(i).getDirection(), moves.get(i).getTake(),board[moves.get(i).getX()][moves.get(i).getY()].getType());
         }
      }
   }
   
   public void moveSelectSingle(ArrayList<move> moves)
   {
      boolean t = false;
      Scanner sc = new Scanner(System.in);
      int maxTwo = 0;
      int inputTwo = 0;
      for(int y = 0; y < 8; y++)
      {
         for(int x = 0; x < 8; x++)
         {
            t = false;
            for(int i = 0; i < moves.size(); i++)
            {
               if((moves.get(i)).getX() == x && (moves.get(i)).getY() == y && (moves.get(i)).getPseudo() == 0)
               {
                  System.out.print("x ");
                  t = true;
                  break;
               }
               else if((moves.get(i)).getXf() == x && (moves.get(i)).getYf() == y && (moves.get(i)).getPseudo() == 0)
               {
                  maxTwo ++;
                  System.out.print(maxTwo + " ");
                  t = true;
                  (moves.get(i)).updatePseudoFinal(maxTwo);
               }
            }
            if(t == false)
            {
               System.out.print(board[x][y].toString());
            }
         }
         System.out.println("");
      }
      
      while(inputTwo < 1 || inputTwo > maxTwo)
      {
         System.out.println("Choose a valid move to make (enter 1 - " + maxTwo + " )");
         inputTwo = sc.nextInt();
         if(inputTwo < 1 || inputTwo > maxTwo)
         {
            System.out.println("Invalid move");
         }
      }
      
      for(int i = 0; i < moves.size(); i++)
      {
         if((moves.get(i)).getPseudoFinal() == inputTwo)
         {
            makeMove(moves.get(i).getX(), moves.get(i).getY(), moves.get(i).getDirection(), moves.get(i).getTake(),board[moves.get(i).getX()][moves.get(i).getY()].getType());
         }
      }
   }
   
   public ArrayList<move> generateTileMoves(int x, int y, boolean color)
   {
      //black is false and odd black is on top
      //white is true and even white is on bottom
      ArrayList<move> forceMoves = new ArrayList<move>();
      if(color == false)
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
            }
         }
      }
      if(color == true)
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
            }
         }
               
         if(board[x][y].getType() == 4)
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
            }
                  
            if(x != 7 && y != 7)
            {
               if(board[x+1][y+1].getType() == 1 || board[x+1][y+1].getType() == 3)
               {
                  if(x < 6 && y < 6)
                  {
                     if(board[x+2][y+2].getTaken() == false)
                     {
                        forceMoves.add(new move(x,y,4,true));
                     }
                  }
               } 
            }
         }
      }
      return forceMoves;
   }
   
   public ArrayList<move> generateMoves(boolean color)
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
               
               if(board[x][y].getType() == 4)
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
                     if(board[x+1][y+1].getType() == 1 || board[x+1][y+1].getType() == 3)
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
      if(take == false)
      {
         switch(direction)
         {
            case 1:
               board[x][y].updateTile(false,0);
               board[x-1][y-1].updateTile(true,startingType);
               x -= 1;
               y -= 1;
            break;
            case 2:
               board[x][y].updateTile(false,0);
               board[x+1][y-1].updateTile(true,startingType);
               x += 1;
               y -= 1;
            break;
            case 3:
               board[x][y].updateTile(false,0);
               board[x-1][y+1].updateTile(true,startingType);
               x -= 1;
               y += 1;
            break;
            case 4:
               board[x][y].updateTile(false,0);
               board[x+1][y+1].updateTile(true,startingType);
               x += 1;
               y += 1;
            break;
         }
      }
      if(take == true)
      {
         switch(direction)
         {
            case 1:
               board[x][y].updateTile(false,0);
               board[x-2][y-2].updateTile(true,startingType);
               board[x-1][y-1].updateTile(false,0);
               x -= 2;
               y -= 2;
            break;
            case 2:
               board[x][y].updateTile(false,0);
               board[x+2][y-2].updateTile(true,startingType);
               board[x+1][y-1].updateTile(false,0);
               x += 2;
               y -= 2;
            break;
            case 3:
               board[x][y].updateTile(false,0);
               board[x-2][y+2].updateTile(true,startingType);
               board[x-1][y+1].updateTile(false,0);
               x -= 2;
               y += 2;
            break;
            case 4:
               board[x][y].updateTile(false,0);
               board[x+2][y+2].updateTile(true,startingType);
               board[x+1][y+1].updateTile(false,0);
               x += 2;
               y += 2;
            break;
         }
         if(startingType%2 == 0)
         {
            piecesB --;
         }
         if(startingType%2 == 1)
         {
            piecesW --;
         }
      }
      if(startingType == 1)
      {
         if(y == 7)
         {
            board[x][y].updateTile(true,3);
            startingType = 3;
         }
      }
      if(startingType == 2)
      {
         if(y == 0)
         {
            board[x][y].updateTile(true,4);
            startingType = 4;
         }
      }
      if(take == true)
      {
         boolean c = false;
         ArrayList<move> a = new ArrayList<move>();
         if(startingType == 2 || startingType == 4)
         {
            c = true;
         }
         a = generateTileMoves(x,y,c);
         if(a.size() > 0)
         {
            //System.out.println(a);
            moveSelectSingle(a);
         }
      }
   }
}