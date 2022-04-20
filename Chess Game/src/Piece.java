//Rook   0  Move {{0,1}{0-1}{-1,0}{1,0}}  |  Castling        4
//Bishop 1  Move {{+-1, +-1}    3
//Queen  2  Move (Rook + Pawn)    9
//Knight 3    3
//Pawn   4  Move | double move | take | Promotion | En passant   1
//King   5   100000


import java.util.ArrayList;

public class Piece {
    private int x;
    private int y;
    //private int score;
    private int thisPiece;
    private final boolean isWhite;
    private Board board;
    private boolean pieceHasMoved = false;

    public Piece(int piece, int x, int y, boolean isWhite, Board board){
        this.x = x;
        this.y = y;
        thisPiece = piece; // {rook, bishop, queen, knight, pawn, king}
        this.isWhite = isWhite;
        this.board = board;
    }

    public boolean hasMoved(){return pieceHasMoved;}

    public boolean isWhite(){
        return isWhite;
    }

    public int getPiece(){
        return thisPiece;
    }


    private ArrayList<Integer[]> rookCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}}; //List of directions to go in
        for(int[] offset: directions) {
            for(int j = 1; j < 8; j ++){
                Integer[] coords = {x + offset[0] * j, y + offset[1] * j};
                if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                    Piece p = board.getPiece(coords[0],coords[1]);
                    if(p == null){
                        result.add(coords);
                    } else { //If there is a piece it can't hop over it
                        j = 8;
                        if(p.isWhite() != isWhite){
                            result.add(coords); //It can capture it though
                        }
                    }
                }
            }
        }
        return result;
    }



    private ArrayList<Integer[]> bishopCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{1,1},{-1,-1},{1,-1},{-1,1}}; //List of directions to go in
        for(int[] offset: directions) {
            for(int j = 1; j < 8; j ++){
                Integer[] coords = {x + offset[0] * j, y + offset[1] * j};
                if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                    Piece p = board.getPiece(coords[0],coords[1]);
                    if(p == null){
                        result.add(coords);
                    } else {
                        j = 8;
                        if(p.isWhite() != isWhite){
                            result.add(coords);
                        }
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<Integer[]> knightCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{1,2},{1 , -2},{-1,2},{-1, -2},{2,1},{2 , -1},{-2,1},{-2, -1}}; //List of directions to go in
        for(int[] offset: directions) {
            Integer[] coords = {x + offset[0], y + offset[1]};
            if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                Piece p = board.getPiece(coords[0],coords[1]);
                if(p == null){
                    result.add(coords);
                } else {
                    if(p.isWhite() != isWhite){
                        result.add(coords);
                    }
                }
            }

        }
        return result;
    }

    private ArrayList<Integer[]> pawnCanMove(){
        //TODO Test
        ArrayList<Integer[]> result = new ArrayList<>();
        if(isWhite){ //If white go forward
            if(board.getPiece(x,y + 1) == null){
                Integer[] t = {x, y + 1};
                result.add(t);
                if(!pieceHasMoved) { //Can move double if hasn't moved
                    if (board.getPiece(x, y + 2) == null) {
                        Integer[] t2 = {x, y + 2};
                        result.add(t2);
                    }
                }
            }
            if(board.getPiece(x - 1,y + 1) != null){ //Attack opponent diagonally
                if(!board.getPiece(x - 1,y + 1).isWhite()) {
                    Integer[] t = {x - 1, y + 1};
                    result.add(t);
                }
            }
            if(board.getPiece(x + 1,y + 1) != null){
                if(!board.getPiece(x + 1,y + 1).isWhite()) {
                    Integer[] t = {x + 1, y + 1};
                    result.add(t);
                }
            }
        } else { //If black go back
            if(board.getPiece(x,y - 1) == null){
                Integer[] t = {x, y - 1};
                result.add(t);
                if(!pieceHasMoved) {
                    if (board.getPiece(x, y - 2) == null) {
                        Integer[] t2 = {x, y - 2};
                        result.add(t2);
                    }
                }
            }
            if(board.getPiece(x - 1,y - 1) != null){
                if(board.getPiece(x - 1,y - 1).isWhite()) {
                    Integer[] t = {x - 1, y - 1};
                    result.add(t);
                }
            }
            if(board.getPiece(x + 1,y - 1) != null){
                if(board.getPiece(x + 1,y - 1).isWhite()) {
                    Integer[] t = {x + 1, y - 1};
                    result.add(t);
                }
            }
        }
        pieceHasMoved = true;
        return result;
    }

    private ArrayList<Integer[]> kingCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{1,1},{1 , -1},{-1,1},{-1,-1},{0,1},{0,-1},{-1,0},{1, 0}}; //List of directions to go in
        for(int[] offset: directions) {
            Integer[] coords = {x + offset[0], y + offset[1]};
            if( (coords[0] < 8 && coords[0] > -1) && (coords[1] < 8 && coords[1] > -1)){
                Piece p = board.getPiece(coords[0],coords[1]);
                if(p == null){
                    result.add(coords);
                } else {
                    if(p.isWhite() != isWhite){
                        result.add(coords);
                    }
                }
            }

        }
        //TODO Castling
        pieceHasMoved = true;
        return result;
    }



    public ArrayList<Integer[]> pieceCanMove(){
        if(thisPiece == 0){
            //Rook
            pieceHasMoved = true;
            return rookCanMove();
        } else if( thisPiece == 1){
            //Bishop
            return bishopCanMove();
        } else if(thisPiece == 2){
            //Queen
            ArrayList<Integer[]> result = rookCanMove();
            ArrayList<Integer[]> result2 = bishopCanMove();
            for(int i = 0; i < result2.size(); i ++){
                result.add(result2.get(i));
            }
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

    public boolean special(int x1, int y1) {
        return false;
    }

    public void movePiece(int x1, int y1){
        //TODO special moves // Pawn promotion, Check, Castle, En Passant,
        if(isLegal(x1,y1)){
            board.setPiece( x1, y1,this);
            board.setPiece( x, y,null);
            x = x1;
            y = y1;
        } else if(special(x1,y1)){
            System.out.println(x + " " + y + "to" + x1 + y1 + "is not a legal move");
        }
    }

    public boolean isLegal(int x1, int y1){
        for(Integer[] moves: pieceCanMove()){
            if( moves[0] == x1 && moves [1] == y1){
                return true;
            }
        }
        return false;
    }
}