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

    public Piece(int piece, int x, int y, boolean isWhite, Board board){
        this.x = x;
        this.y = y;
        thisPiece = piece; // {rook, bishop, queen, knight, pawn, king}
        this.isWhite = isWhite;
        this.board = board;
    }

    public boolean isWhite(){
        return isWhite;
    }


    private ArrayList<Integer[]> rookCanMove(){
        ArrayList<Integer[]> result = new ArrayList<>();
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}}; //List of directions to go in
        for(int[] offset: directions) {
            for(int j = 1; j < 8; j ++){
                Integer[] coords = {x + offset[0], y + offset[1]};
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
                Integer[] coords = {x + offset[0], y + offset[1]};
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
        //TODO Make function
        return null;
    }

    private ArrayList<Integer[]> pawnCanMove(){
        //TODO Make function
        return null;
    }

    private ArrayList<Integer[]> kingCanMove(){
        //TODO Make function
        return null;
    }

    public ArrayList<Integer[]> pieceCanMove(){
        if(thisPiece == 0){
            //Rook
            bishopCanMove();
        } else if( thisPiece == 1){//TODO do rest of functions
            //Bishop
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
        } else if(thisPiece == 4){
            //Pawn
        } else if(thisPiece == 5){
            //King
        }
        return null;
    }
}
