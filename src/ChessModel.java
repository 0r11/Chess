public class ChessModel {
    private boolean isWhite;
    private Board board;
    private GUI gui;

    public static void main(String[] args){
        ChessModel c = new ChessModel();
        c.startMatch();
    }
    public void startMatch(){
        board = new Board();
        gui = new GUI();
        board.initialize();
        isWhite = true;
        while(!gameOver()){
            gui.drawBoard(board);
            if(board.isCheck(true)){ //Ends the game if the
                System.out.println("White wins");
                return;
            }
            if(board.isInCheckmate(false)){
                System.out.println("Black wins");
                return;
            }
            gui.handleMouseClick(isWhite, board);
            isWhite = !isWhite;
        }
    }
    public boolean gameOver(){  //is the game over, we'll implement this later
        //TODO complete game over method
        return false;
    }
}