import java.util.ArrayList;

public class Board {

    private Piece[][] board;
    public int level;
    public Board(){
        board = new Piece[8][8];
    }

    public Piece getPiece(int x, int y){
        if(x < 0 || x > 7 || y < 0 || y > 7){return null;}
        return board[x][y];
    }

    public void setPiece( int x, int y, Piece p){
        board[x][y] = p;
    }

    /**Uses the value of the pieces*/
    public int evaluate(boolean isWhite){
        final int[] values = {5,3,9,3,1,1000};
        int score = 0;
        for(Piece[] t: board){
            for(Piece p: t){
                if(p != null){
                    if(p.isWhite() == isWhite){
                        score += values[p.getPiece()];
                    } else {
                        score -= values[p.getPiece()];
                    }
                }
            }
        }
        return score;
    }

    /** Copies this board to passed board */
    public void copy(Board b){
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                if(board[x][y] != null) {
                    b.setPiece(x, y, board[x][y].copy(b));
                }
            }
        }
    }

    /** Returns true if you're in check */
    public boolean isCheck(boolean isWhite){
        for(int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) { //For each piece
                if(board[x][y] != null){
                    if(board[x][y].isWhite() == isWhite){ //That's the color
                        for(Integer[] i: board[x][y].pieceCanMove()){ //Look at each possible move
//                            System.out.println(x + " " +y + "  " + i[0] + " " + i[1]);
                            if(board[i[0]][i[1]] != null){
                                if(board[i[0]][i[1]].getPiece() == 5){ //If there's a king on the square
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**Returns true if the piece will be in check if it moves from the first set*/
    public boolean willBeInCheck(int x, int y, int x1, int y1){
        Board b = new Board();
        this.copy(b);

        b.level = this.level + 1;
        if(b.level >= 2){ return false;}
        b.getPiece(x,y).movePiece(x1,y1);
        return b.isCheck(!b.getPiece(x1,y1).isWhite());

    }

    public boolean isInCheckmate(boolean isWhite){ //TODO BUg fix
        if(!isCheck(isWhite)){ return false;}
        for(int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) { //For each piece
                if(board[x][y] != null){
                    if(board[x][y].isWhite() == !isWhite){ //That's the color
                        for(Integer[] i: board[x][y].pieceCanMove()){ //Look at each possible move
                            if(!willBeInCheck(x,y,i[0],i[1])){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Check Mate ");
        return true;
    }


    /** Puts all pieces in their initial positions */
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