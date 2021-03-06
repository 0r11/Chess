public class AI {
    boolean isWhite; //Will either be white or not white (black). Still needs to be called from model
    Board board;
    final int maxDepth = 3; // Higher max depth means exponentially longer runtime

    /**Define the AI passing the color, and where its playing*/
    public AI(boolean isWhite, Board board){
        this.isWhite = isWhite;
        this.board = board;
    }

    /**Used for storing instances of moves (instead of parallel variables / lists*/
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

    /**Finds the best move at the given depth, and the best among that, and recursion all the way up*/
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
                            if(b.isInCheckmate(isWhite)){
                                if(best.score < -999){
                                    best.setMove(-999, pCopy, x, y, i[0], i[0]);
                                } else if(b.isInCheckmate(!isWhite)){
                                    best.setMove(999, pCopy, x, y, i[0], i[0]);
                                } else if(depth == 0){
                                    int thisScore = b.evaluate(isWhite);
                                    if (thisScore > best.score) {
                                        best.setMove(thisScore, pCopy, x, y, i[0], i[1]);
                                    }
                                } else {
                                    //                                System.out.println("recursion: " + x + y + i[0] + i[1]); // 7 5 6 6
                                    int thisScore = findBestMove(depth - 1, !isWhite, b).score;
                                    if (thisScore > best.score) {
                                        best.setMove(thisScore, pCopy, x, y, i[0], i[1]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return best;
    }

    /**Takes a turn*/
    public void takeTurn(){
        Move m = findBestMove(maxDepth,isWhite,board);
        board.getPiece(m.x,m.y).movePiece(m.x1,m.y1);
    }

}
