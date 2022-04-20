public class ChessModel {
    private boolean player1turn;
    private Board board;
    private GUI gui;

    public void startMatch(){
        board = new Board();
        gui = new GUI();
        while(!gameOver()){
            player1turn = true;
            if(player1turn) {
                //player 1's turn (white)
                gui.drawBoard(board);
                gui.handleMouseClick(true, board);
                player1turn = false; //set it to player 2's turn
            }
            if(!player1turn) {
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
