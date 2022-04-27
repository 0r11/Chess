public class AI {
    boolean isWhite;
    Board board;
    public AI(boolean isWhite, Board board){
        this.isWhite = isWhite;
        this.board = board;
    }

    private class Move {
        public int score;
        public Piece p;
        public int x;
        public int y;
        public int x1;
        public int y1;

        public Move(){}

        public void setMove(int score, Piece p, int x, int y, int x1, int y1){
            this.score = score;
            this.p = p;
            this.x = x;
            this.y = y;
            this.x1 = x1;
            this.y1 = y1;
        }
    }


    public Move findBestMove(int depth, boolean isWhite, Board board){
        Move best = new Move();
        best.score = -10000;
        for(int x = 0; x < 8; x ++){
            for(int y = 0; y < 8; y++){
                if(board.getPiece(x,y) != null){
                    Piece p = board.getPiece(x,y);
                    if(p.isWhite() == isWhite){
                        for(Integer[] i: p.pieceCanMove()){
                            Board b = new Board();
                            board.copy(b);
                            Piece pCopy = b.getPiece(x,y);
                            if(b.willBeInCheck(x,y,i[0],i[1])){ continue;}
                            pCopy.movePiece(i[0],i[1]);
                            if( depth == 0) {
                                int thisScore = b.evaluate(isWhite);
                                if (thisScore > best.score) {
                                    best.setMove(thisScore, pCopy, x, y, i[0], i[1]);
                                }
                            } else {
//                                System.out.println("recursion: " + x + y + i[0] + i[1]); // 7 5 6 6
                                int thisScore = findBestMove(depth - 1, !isWhite, b).score;
                                if (thisScore > best.score){
                                    best.setMove(thisScore, pCopy, x, y, i[0], i[1]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return best;
    }


    public void takeTurn(){
        Move m = findBestMove(2,isWhite,board); // Higher depth means exponentially longer runtime
        board.getPiece(m.x,m.y).movePiece(m.x1,m.y1);
        StdAudio.play("ChessAudio.wav");
    }

}