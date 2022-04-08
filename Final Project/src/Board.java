public class Board {
    private Piece[][] board;
    public Board(){
        board = new Piece[8][8];
    }

    public void display(){
        StdOut.print(board.toString());

    }

    public Piece getPiece(int x, int y){
        return board[x][y];
    }
}
