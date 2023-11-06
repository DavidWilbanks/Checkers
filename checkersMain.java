import java.util.*;

public class checkersMain
{
   public static void main(String[] args)
   {
      board checkerBoard = new board();
      boolean WW = false;
      boolean BB = false;
      int turn = 0;
      //player p1 = new player(false,false,checkerBoard);
      //player p2 = new player(false,true,checkerBoard);
      ArrayList<move> moveList = new ArrayList<move>();
      while(checkerBoard.getPiecesW() > 0 && checkerBoard.getPiecesB() > 0)
      {
         checkerBoard.printboard();
         if(turn%2 == 0)
         {
            moveList = checkerBoard.generateMoves(false);
            if(moveList.size() == 0)
            {
               WW = true;
               break;
            }
         }
         else
         {
            moveList = checkerBoard.generateMoves(true);
            if(moveList.size() == 0)
            {
               BB = true;
               break;
            }
         }
         checkerBoard.moveSelect(moveList);
         turn++;
      }
      checkerBoard.printboard();
      if(checkerBoard.getPiecesB() == 0 || WW == true)
      {
         System.out.println("White Player Wins");
      }
      if(checkerBoard.getPiecesW() == 0 || BB == true)
      {
         System.out.println("Black Player Wins");
      }
   }
}