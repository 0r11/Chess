import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        StdOut.println("Hello World");

        Board board = new Board();
        board.initialize();

        GUI s = new GUI();
        s.drawBoard(board);
        s.handleMouseClick(true, board);
        s.drawBoard(board);

//        board.setPiece(4,4,new Piece(1,4,4,true, board));
//        System.out.println(board.isLegal(4,4,1,1));
//        board.initialize();
//        board.display();
//        board.getPiece(0,1).movePiece(0,2);
//        board.display();
        //TODO Make main game loop
    }
}