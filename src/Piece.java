//Rook   0  Move {{0,1}{0-1}{-1,0}{1,0}}  |  Castling        4
//Bishop 1  Move {{+-1, +-1}    3
//Queen  2  Move (Rook + Pawn)    9
//Knight 3    3
//Pawn   4  Move | double move | take | Promotion | En passant   1
//King   5   100000


import java.util.ArrayList;

public class Piece {
    //Initialize variables
    private int x;
    private int y;
    private int thisPiece;
    private final boolean isWhite;
    private final Board board;
    public boolean pieceHasMoved = false;

    public Piece(int piece, int x, int y, boolean isWhite, Board board){
        this.x = x;
        this.y = y;
        thisPiece = piece; // {rook, bishop, queen, knight, pawn, king}
        this.isWhite = isWhite;
        this.board = board;
    }

    /**Duplicates a piece onto another board*/
    public Piece copy(Board b){
        Piece p = new Piece(thisPiece, x, y, isWhite, b);
        p.pieceHasMoved = pieceHasMoved;
        return p;
    }

    public boolean hasMoved(){return pieceHasMoved;}

    public boolean isWhite(){
        return isWhite;
    }

    public int getPiece(){
        return thisPiece;
    }

    /**Checks to see if the rook can move to a given square (Castling not yet implemented)*/
    private ArrayList<Integer[]> rookCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}}; //List of directions to go in
        for(int[] offset: directions) {
            for(int j = 1; j < 8; j ++){
                Integer[] coords = {x + offset[0] * j, y + offset[1] * j};
                if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                    Piece p = board.getPiece(coords[0],coords[1]);
                    if (p == null) {
                        if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                            result.add(coords);
                        }
                    } else { //If there is a piece it can't hop over it
                        j = 8;
                        if (p.isWhite() != isWhite) {
                            if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                                result.add(coords); //It can capture it though
                            }
                        }
                    }

                }
            }
        }
        return result;
    }


    /**Checks to see if the bishop can move to a given square */
    private ArrayList<Integer[]> bishopCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{1,1},{-1,-1},{1,-1},{-1,1}}; //List of directions to go in
        for(int[] offset: directions) {
            for(int j = 1; j < 8; j ++){
                Integer[] coords = {x + offset[0] * j, y + offset[1] * j};
                if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                    Piece p = board.getPiece(coords[0], coords[1]);
                    if (p == null) {
                        if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                            result.add(coords);
                        }
                    } else {
                        j = 8;
                        if (p.isWhite() != isWhite) {
                            if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                                result.add(coords);
                            }
                        }
                    }

                }
            }
        }
        return result;
    }

    /**Checks to see if the knight can move to a given square */
    private ArrayList<Integer[]> knightCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{1,2},{1 , -2},{-1,2},{-1, -2},{2,1},{2 , -1},{-2,1},{-2, -1}}; //List of directions to go in
        for(int[] offset: directions) { //Looks at all offsets
            Integer[] coords = {x + offset[0], y + offset[1]};
            if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)) {
                Piece p = board.getPiece(coords[0], coords[1]);
                if (p == null) {
                    if ((coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)) {
                        if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                            result.add(coords);
                        }
                    }
                } else {
                    if (p.isWhite() != isWhite) {
                        if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                            if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                                result.add(coords);
                            }
                        }
                    }
                }
            }

        }
        return result;
    }

    /**Checks to see if the pawn can move to a given square (en passant not yet included)*/
    private ArrayList<Integer[]> pawnCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        if(isWhite){ //If white go forward
            if (board.getPiece(x, y + 1) == null) {
                Integer[] t = {x, y + 1};
                if(!board.willBeInCheck(x,y,t[0],t[1])) {
                    result.add(t);
                }
                if (!pieceHasMoved) { //Can move double if it hasn't moved
                    if (board.getPiece(x, y + 2) == null) {
                        Integer[] t2 = {x, y + 2};
                        if(!board.willBeInCheck(x,y,t2[0],t2[1])) {
                            result.add(t2);
                        }
                    }
                }
            }

            if(x > 0) {
                if (board.getPiece(x - 1, y + 1) != null) { //Attack opponent diagonally
                    if (!board.getPiece(x - 1, y + 1).isWhite()) {
                        Integer[] t = {x - 1, y + 1};
                        if(!board.willBeInCheck(x,y,t[0],t[1])) {
                            result.add(t);
                        }
                    }
                }
            }
            if(x < 7) {
                if (board.getPiece(x + 1, y + 1) != null) {
                    if (!board.getPiece(x + 1, y + 1).isWhite()) {
                        Integer[] t = {x + 1, y + 1};
                        if(!board.willBeInCheck(x,y,t[0],t[1])) {
                            result.add(t);
                        }
                    }
                }
            }
        } else { //If black go back
            if(board.getPiece(x,y - 1) == null){
                Integer[] t = {x, y - 1};
                if(!board.willBeInCheck(x,y,t[0],t[1])) {
                    result.add(t);
                }
                if(!pieceHasMoved) {
                    if (board.getPiece(x, y - 2) == null) {
                        Integer[] t2 = {x, y - 2};
                        if(!board.willBeInCheck(x,y,t2[0],t2[1])) {
                            result.add(t2);
                        }
                    }
                }
            }
            if( x > 0) {
                if (board.getPiece(x - 1, y - 1) != null) {
                    if (board.getPiece(x - 1, y - 1).isWhite()) {
                        Integer[] t = {x - 1, y - 1};
                        if(!board.willBeInCheck(x,y,t[0],t[1])) {
                            result.add(t);
                        }
                    }
                }
            }
            if( x < 7) {
                if (board.getPiece(x + 1, y - 1) != null) {
                    if (board.getPiece(x + 1, y - 1).isWhite()) {
                        Integer[] t = {x + 1, y - 1};
                        if(!board.willBeInCheck(x,y,t[0],t[1])) {
                            result.add(t);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**Checks to see if the knight can move to a given square */
    private ArrayList<Integer[]> kingCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{1,1},{1 , -1},{-1,1},{-1,-1},{0,1},{0,-1},{-1,0},{1, 0}}; //List of directions to go in
        for(int[] offset: directions) {
            Integer[] coords = {x + offset[0], y + offset[1]};
            if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                Piece p = board.getPiece(coords[0],coords[1]);
                if(p == null){
                    if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                        result.add(coords);
                    }
                } else {
                    if(p.isWhite() != isWhite){
                        if(!board.willBeInCheck(x,y,coords[0],coords[1])) {
                            result.add(coords);
                        }
                    }
                }
            }

        }
        //Castling
        if(!pieceHasMoved/* && !board.isCheck(isWhite)*/) {
            if (board.getPiece(x - 3,y) != null){ //Checks to see if there is an unmoved piece in either of the rooks starting positions
                if(!board.getPiece(x - 3, y).hasMoved()){
                    if(board.getPiece(x - 1, y) == null && board.getPiece(x - 2, y) == null) { //If there's no pieces in the way
                        if(!board.willBeInCheck(x,y,x-2,y)) {
                            result.add(new Integer[]{x - 2, y});
                        }
                    }
                }
            }
            if (board.getPiece(x + 4,y) != null){ //Same but for Queen's side
                if(!board.getPiece(x + 4, y).hasMoved()){
                    if(board.getPiece(x + 1, y) == null && board.getPiece(x + 2, y) == null && board.getPiece(x + 3, y) == null) {
                        if(!board.willBeInCheck(x,y,x+2,y)) {
                            result.add(new Integer[]{x + 2, y});
                        }
                    }
                }
            }
        }
        return result;
    } //TODO Fix castling

    public ArrayList<Integer[]> pieceCanMove(){
        if(thisPiece == 0){
            //Rook
            return rookCanMove();
        } else if( thisPiece == 1){
            //Bishop
            return bishopCanMove();
        } else if(thisPiece == 2){
            //Queen
            ArrayList<Integer[]> result = rookCanMove();
            ArrayList<Integer[]> result2 = bishopCanMove();
            result.addAll(result2);
            return result;
        } else if(thisPiece == 3){
            //Knight
            return knightCanMove();
        } else if(thisPiece == 4){
            //Pawn
            return pawnCanMove();
        } else if(thisPiece == 5){
            //King
            return kingCanMove();
        }
        return null;
    }


    /**Moves the piece (if it can move to that square)*/

    /**Used for setting the piece to the square (called through movePiece)*/
    public void moveTo(int x1,int y1){
        pieceHasMoved = true;
        board.setPiece(x1, y1, this);
        board.setPiece(x, y, null);
        x = x1;
        y = y1;
    }

    /**Moves the piece to that square*/
    public void movePiece(int x1, int y1) {
        //TODO En Passant
        if (isLegal(x1, y1, isWhite)) {

            if (thisPiece == 5) { //Can castle if a king
                if (x - x1 == 2) { //and moves two left
                    board.getPiece(x-3,y).moveTo(x-1,y);
                } else if (x - x1 == -2) { //or two right
                    board.getPiece(x+4,y).moveTo(x+1,y);
                }
            }

            moveTo(x1,y1);

            //Pawn promotion
            if ((isWhite && y1 >= 7) || (!isWhite && y1 <= 0) ){
                if(thisPiece == 4) {
                    thisPiece = 2; //Sets it to a queen automatically
                }
            }
        }
    }

    /** Returns true if the piece can move to that square*/
    public boolean isLegal(int x1, int y1, boolean isWhite){
        if(this.isWhite != isWhite){ return false;}
        for(Integer[] move: pieceCanMove()){
            if( move[0] == x1 && move[1] == y1){
                return true;
            }
        }
        return false;
    }
}