import java.util.*;

public class checkersMain
{
   public static void main(String[] args)
   {
      board checkerBoard = new board(); //game board object
      Scanner sy = new Scanner(System.in);
      boolean WW = false; //white win
      boolean BB = false; //black win 
      boolean WAI = false; //white controlled by computer
      boolean BAI = false; //black controlled by computer
      int inp = 0; //input
      //takes user input for who controls the black pieces
      while(true)
      {
         System.out.println("Input the player type for black (1 for human, 2 for AI)");
         inp = sy.nextInt();
         if(inp == 1)
         {
            break;
         }
         if(inp == 2)
         {
            BAI = true;
            break;
         }
      }
      //takes user input for who controls the white pieces
      while(true)
      {
         System.out.println("Input the player type for white (1 for human, 2 for AI)");
         inp = sy.nextInt();
         if(inp == 1)
         {
            break;
         }
         if(inp == 2)
         {
            WAI = true;
            break;
         }
      }
      int turns = 0;
      int turn = 0;
      ArrayList<move> moveList = new ArrayList<move>();
      //main game loop Black moves on even turns White moves on odd turns
      while(checkerBoard.getPiecesW() > 0 && checkerBoard.getPiecesB() > 0)
      {
         //says who's turn it is
         turns = turn+1;
         if(turn%2 == 0)
         {
            System.out.println("Turn " + turns + " Black's turn");
         }
         if(turn%2 == 1)
         {
            System.out.println("Turn " + turns + " White's turn");
         }
         
         //prints the current board
         checkerBoard.printboard();
         //black move
         if(turn%2 == 0)
         {  
            //generates moves for black
            moveList = checkerBoard.generateMoves(false);
            //if black has no valid moves white wins
            if(moveList.size() == 0)
            {
               WW = true;
               break;
            }
            //if black is controlled by ai have the ai move
            if(BAI == true)
            {
               checkerBoard.AIMove(moveList,true);
               try
               {
                  Thread.sleep(2000);
               }
               catch(InterruptedException ex)
               {
                  ex.printStackTrace();
               }
            }
            //if black is human controlled let the human choose how to move
            else
            {
               checkerBoard.moveSelect(moveList,false);
            }
         }
         //white move
         else
         {
            //generates moves for white
            moveList = checkerBoard.generateMoves(true);
            //if white has no valid moves black wins
            if(moveList.size() == 0)
            {
               BB = true;
               break;
            }
            //if white is controlled by ai have the ai move
            if(WAI == true)
            {
               checkerBoard.AIMove(moveList,true);
               try
               {
                  Thread.sleep(2000);
               }
               catch(InterruptedException ex)
               {
                  ex.printStackTrace();
               }
            }
            //if white is human controlled let the human choose how to move
            else
            {
               checkerBoard.moveSelect(moveList,false);
            }
         }
         turn++;
      }
      //end of game
      
      checkerBoard.printboard();
      //if black has no pieces or no moves white wins
      if(checkerBoard.getPiecesB() == 0 || WW == true)
      {
         System.out.println("White Player Wins");
      }
      //if white has no pieces or no moves black wins
      if(checkerBoard.getPiecesW() == 0 || BB == true)
      {
         System.out.println("Black Player Wins");
      }
      sy.close();
   }
}