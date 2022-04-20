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

        while(!gameOver()){
            isWhite = true;
            if(isWhite) {
                //player 1's turn (white)
                gui.drawBoard(board);
                gui.handleMouseClick(true, board);
                isWhite = false; //set it to player 2's turn
            } else {
                //player 2's turn (black)
                gui.drawBoard(board);
                gui.handleMouseClick(false, board);
            }
        }
    }
    public boolean gameOver(){  //is the game over, we'll implement this later
        //TODO complete game over method
        return false;
    }
}