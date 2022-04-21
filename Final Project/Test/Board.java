import java.util.ArrayList;

public class Board {

    private Piece[][] board;
    public Board(){
        board = new Piece[8][8];
    }

    public void whereCanMove(int x, int y) {
        ArrayList<Integer[]> n = this.getPiece(x,y).pieceCanMove();
        final char[] l = {'a','b','c','d','e','f','g','h'};
        for(Integer[] i: n){
            System.out.println(l[i[0]] + "" + (i[1] + 1));
        }
    }

    public Piece getPiece(int x, int y){
        return board[x][y];
    }

    public void setPiece( int x, int y, Piece p){
        board[x][y] = p;
    }

    public boolean isLegal(int x,int y, int x1, int y1){
        return board[x][y].isLegal(x1,y1, this.getPiece(x1,y1).isWhite());
    }

    public void initialize(){ // {Rook, bishop, queen, knight, pawn, king}
        //public Piece(int piece, int x, int y, boolean isWhite, Board board)

        //White
        board[0][0] = new Piece(0, 0, 0, true, this); //Going left to right  //Rook
        board[1][0] = new Piece(3, 1, 0, true, this); // Knight
        board[2][0] = new Piece(1, 2, 0, true, this); // Bishop
        board[3][0] = new Piece(2, 3, 0, true, this); // Queen
        board[4][0] = new Piece(5, 4, 0, true, this); // King
        board[5][0] = new Piece(1, 5, 0, true, this); // Bishop
        board[6][0] = new Piece(3, 6, 0, true, this); // Knight
        board[7][0] = new Piece(0, 7, 0, true, this); //Rook
        for(int i = 0; i < 8; i ++){
            board[i][1] = new Piece(4, i, 1, true, this); //Pawns
        }

        //Black
        board[0][7] = new Piece(0, 0, 7, false, this); //Going left to right  //Rook
        board[1][7] = new Piece(3, 1, 7, false, this); // Knight
        board[2][7] = new Piece(1, 2, 7, false, this); // Bishop
        board[3][7] = new Piece(2, 3, 7, false, this); // Queen
        board[4][7] = new Piece(5, 4, 7, false, this); // King
        board[5][7] = new Piece(1, 5, 7, false, this); // Bishop
        board[6][7] = new Piece(3, 6, 7, false, this); // Knight
        board[7][7] = new Piece(0, 7, 7, false, this); //Rook
        for(int i = 0; i < 8; i ++) {
            board[i][6] = new Piece(4, i, 6, false, this); //Pawns
        }
    }
}