public class checkersMain
{
   public static void main(String[] args)
   {
      board checkerBoard = new board();
      player p1 = new player(false,false,checkerBoard);
      player p2 = new player(false,true,checkerBoard);
      checkerBoard.printboard();
   }
}