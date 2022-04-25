public class ChessModel {
    private boolean isWhite;
    private Board board;
    private GUI gui;

    public static void main(String[] args){
        ChessModel c = new ChessModel();
        c.startMatch();
    }
    /**The main game loop*/
    public void startMatch(){
        board = new Board();
        gui = new GUI();
        board.initialize();
        isWhite = true;
        board.level = 0;
        AI ai = new AI(false, board);


        while(!gameOver()){
            gui.drawBoard(board);
            if(board.isInCheckmate(true)){ //Ends the game if the
                System.out.println("White wins");
                return;
            }
            if(board.isInCheckmate(false)){
                System.out.println("Black wins");
                return;
            }
            if(isWhite) {
                gui.handleMouseClick(isWhite, board);
            } else {
                ai.takeTurn();
            }
            isWhite = !isWhite;
        }
    }
    /**Ends the game*/
    public boolean gameOver(){  //is the game over, we'll implement this later
        //TODO complete game over method
        return false;
    }
}