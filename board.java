import java.util.*;

public class board
{
   private tile board[][];
   private int piecesW;
   private int piecesB;
   
   //board constructor
   //Creates a 8x8 board of tiles and populates them with the default checker board layout of 12 white and 12 black pieces on only white tiles
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
   
   //getter methods
   public int getPiecesW()
   {
      return piecesW;
   }
   
   public int getPiecesB()
   {
      return piecesB;
   }
   
   //prints out the board
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
   
   //allows a human player to select the move they are going to make
   public void moveSelect(ArrayList<move> moves, boolean ai)
   {
      int max = 0;
      int input = 0;
      boolean t = false;
      Scanner sc = new Scanner(System.in);
      //loops through every tile and assigns a number value to each piece that is movable and prints out the move selection board with
      //the assigned number value printed instead of the piece. all non movable pieces will be printed as normal
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
      //ask the player which piece they would like to move using the assigned number value
      while(input < 1 || input > max)
      {
         System.out.println("Choose a valid piece to move (enter 1 - " + max + " )");
         input = sc.nextInt();
         if(input < 1 || input > max)
         {
            System.out.println("Invalid piece");
         }
      }
      
      //allows the user to choose what direction to move the piece they selected
      int maxTwo = 0;
      int inputTwo = 0;
      //loops through the board and prints out any tiles that the piece could move to as numbers and assigning those moves said numberic value
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
      
      //allows the user to select the direction they would like to move using assigned numberic value
      while(inputTwo < 1 || inputTwo > maxTwo)
      {
         System.out.println("Choose a valid move to make (enter 1 - " + maxTwo + " )");
         inputTwo = sc.nextInt();
         if(inputTwo < 1 || inputTwo > maxTwo)
         {
            System.out.println("Invalid move");
         }
      }
      
      //makes the selected move
      for(int i = 0; i < moves.size(); i++)
      {
         if((moves.get(i)).getPseudo() == input && (moves.get(i)).getPseudoFinal() == inputTwo)
         {
            makeMove(moves.get(i).getX(), moves.get(i).getY(), moves.get(i).getDirection(), moves.get(i).getTake(),board[moves.get(i).getX()][moves.get(i).getY()].getType(),ai);
         }
      }
   }
   
   //Allows the user to move a prechosen piece (only used for double capture moves)
   public void moveSelectSingle(ArrayList<move> moves)
   {
      boolean t = false;
      Scanner sc = new Scanner(System.in);
      int maxTwo = 0;
      int inputTwo = 0;
      //loops through the board and prints out any tiles that the piece could move to as numbers and assigning those moves said numberic value
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
      
      //ask the user what direction they would like to move
      while(inputTwo < 1 || inputTwo > maxTwo)
      {
         System.out.println("Choose a valid move to make (enter 1 - " + maxTwo + " )");
         inputTwo = sc.nextInt();
         if(inputTwo < 1 || inputTwo > maxTwo)
         {
            System.out.println("Invalid move");
         }
      }
      
      //makes the chosen move
      for(int i = 0; i < moves.size(); i++)
      {
         if((moves.get(i)).getPseudoFinal() == inputTwo)
         {
            makeMove(moves.get(i).getX(), moves.get(i).getY(), moves.get(i).getDirection(), moves.get(i).getTake(),board[moves.get(i).getX()][moves.get(i).getY()].getType(),false);
         }
      }
   }
   
   //How an AI selects their movement
   public void AIMove(ArrayList<move> moves, boolean ai)
   {
      Random r = new Random();
      //choses a random number off of the move list the function was passed and then makes the said move
      int i = r.nextInt(moves.size());
      makeMove(moves.get(i).getX(), moves.get(i).getY(), moves.get(i).getDirection(), moves.get(i).getTake(),board[moves.get(i).getX()][moves.get(i).getY()].getType(), ai);
   }
   
   //Generates all moves that capture a piece for a single tile (only used for double captures)
   public ArrayList<move> generateTileMoves(int x, int y, boolean color)
   {
      //black is false and odd black is on top
      //white is true and even white is on bottom
      ArrayList<move> forceMoves = new ArrayList<move>();
      //black moves
      if(color == false)
      {
         //regular black piece moves
         if(board[x][y].getType() == 1)
         {  
            //move towards the down/left direction
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
            //move towards the down/right direction     
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
         
         //king black piece moves      
         if(board[x][y].getType() == 3)
         {
            //move in the up/left direction
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
            //move in the up/right direction    
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
            //move in the down/left direction      
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
            //move in the down/right direction   
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
      //white piece moves
      if(color == true)
      {
         //normal white piece moves
         if(board[x][y].getType() == 2)
         {  
            //move in the up/left direction
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
            //move in the up/right direction    
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
         //white king moves  
         if(board[x][y].getType() == 4)
         {
            //move in the up/left direction
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
            //move in the up/right direction      
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
            //move in the down/left direction    
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
            //move in the down/right direction      
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
   
   //generates all possible moves that a color could make if any moves end up capturing it will only return capturing moves
   public ArrayList<move> generateMoves(boolean color)
   {
      //black is false and odd black is on top
      //white is true and even white is on bottom
      ArrayList<move> standardMoves = new ArrayList<move>();
      ArrayList<move> forceMoves = new ArrayList<move>();
      //black moves
      if(color == false)
      {
         for(int x = 0; x < 8; x++)
         {
            for(int y = 0; y < 8; y++)
            {
               //normal black piece moves
               if(board[x][y].getType() == 1)
               {  
                  //move in the down/left direction
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
                  
                  //move in the down/right
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
               
               //black king piece moves
               if(board[x][y].getType() == 3)
               {
                  //move in the up/left direction
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
                  
                  //move in the up/right direction
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
                  
                  //move in the down/left direction
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
                  
                  //move in the down/right direction
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
      
      //white piece moves
      if(color == true)
      {
         for(int x = 0; x < 8; x++)
         {
            for(int y = 0; y < 8; y++)
            {
               //normal white piece moves
               if(board[x][y].getType() == 2)
               {  
                  //move in the up/left direction
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
                  
                  //move in the up/right direction
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
               
               //white king moves
               if(board[x][y].getType() == 4)
               {
                  //move in the up/left direction
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
                  
                  //move in the up/right direction
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
                  
                  //move in the down/left direction
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
                  
                  //move in the down/right direction
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
      
      //if any moves capture it will only return capturing moves
      if(forceMoves.size() > 0)
      {
         return forceMoves;
      }
      //if no moves capture it will return normal moves only
      else
      {
         return standardMoves;
      }
   }
   
   //makes a move and updates the coresponding tiles
   public void makeMove(int x, int y, int direction, boolean take, int startingType, boolean ai)
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
            if(ai == true)
            {
               AIMove(a,true);
            }
            else
            {
               moveSelectSingle(a);
            }
         }
      }
   }
}