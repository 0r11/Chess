import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        StdOut.println("Hello World");
        Board board = new Board();
        board.setPiece(4,4,new Piece(5,4,4,true, board));
        board.whereCanMove(4,4);
//        board.initialize();
//        board.display();
//        board.getPiece(0,1).movePiece(0,2);
//        board.display();
        //TODO Make main game loop
    }
}