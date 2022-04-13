public class Main {
    public static void main(String[] args){
        StdOut.println("Hello World");
        Board board = new Board();
        board.initialize();
        board.display();
        board.getPiece(0,1).movePiece(0,2);
        board.display();


        //TODO Make main game loop
    }
}