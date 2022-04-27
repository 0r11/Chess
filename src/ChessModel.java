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
        board.level = 0;
        AI ai = new AI(false, board);
        boolean aiPlaying = gui.drawMenu();

        while(!gameOver(board, isWhite)){

            gui.drawBoard(board);
            if(board.isInCheckmate(true)){ //Ends the game if the
                System.out.println("White wins");
                return;
            }

            if(board.isInCheckmate(false)){
                System.out.println("Black wins");
                return;
            }

            if (aiPlaying) {
                if (isWhite) {
                    gui.handleMouseClick(isWhite, board);
                } else {
                    ai.takeTurn();
                }
            }

            else {
                gui.handleMouseClick(isWhite, board);
            }

            isWhite = !isWhite;
        }
    }
    public boolean gameOver(Board b, boolean isWhite){
        if (b.isInCheckmate(isWhite)){
            return true;
        }
        else {
            return false;
        }
    }
}