//Rook   0  Move {{0,1}{0-1}{-1,0}{1,0}}  |  Castling
//Bishop 1  Move {{+-1, +-1}
//Queen  2  Move (Rook + Pawn)
//Knight 3
//Pawn   4
//King   5


import java.util.ArrayList;

public class Piece {
    private int x;
    private int y5;
    private int score;
    private int thisPiece;
    private boolean color;

    public Piece(int p, int x, int y, boolean isWhite){
        this.x = x;
        this.y = y;
        score = 3;
        thisPiece = p; // {bishop, rook, queen, king, pawn, knight}
        color = isWhite;
    }

    public ArrayList<ArrayList<Integer>> rookCanMove(){
        //TODO Make function
        return null;
    }

    public ArrayList<ArrayList<Integer>> bishopCanMove(){
        //TODO Make function
        return null;
    }

    public ArrayList<ArrayList<Integer>> knightCanMove(){
        //TODO Make function
        return null;
    }

    public ArrayList<ArrayList<Integer>> pawnCanMove(){
        //TODO Make function
        return null;
    }

    public ArrayList<ArrayList<Integer>> pieceCanMove(){
        if(thisPiece == 0){
            //Rook
            bishopCanMove();
        } else if( thisPiece == 1){//TODO do rest of functions
            //Bishop
        } else if(thisPiece == 2){
            //Queen
            ArrayList<ArrayList<Integer>> result = rookCanMove();
            ArrayList<ArrayList<Integer>> result2 = bishopCanMove();
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
