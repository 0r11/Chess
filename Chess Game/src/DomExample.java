//import java.awt.*;
//
//public class DomSample {
//
//    /** A domino-placing game invented by Goran Andersson. */
//
//    /**
//     * Draws the current state of the game, including instructions to the user.
//     */
//    public static void draw(boolean[][] board, boolean verticalToPlay) {
//        // TODO You have to write this
//        StdDraw.clear();
//        //StdDraw.filledSquare(x,y,0.5);
//        for(int x = 0; x < 8 ; x++){
//            for(int y = 0; y < 8; y++){
//                if(board[x][y]){
//                    StdDraw.setPenColor(Color.BLACK);
//                } else {
//                    if((x + y) % 2 == 0){
//                        StdDraw.setPenColor(Color.BLUE);
//                    } else {
//                        StdDraw.setPenColor(Color.GREEN);
//                    }
//                }
//                StdDraw.filledSquare(x,y,0.5);
//            }
//        }
//
//
//
//        StdDraw.show();
//    }
//
//    /**
//     * Returns true if the game is over, i.e., the current player has no legal
//     * move.
//     */
//    public static boolean gameOver(boolean[][] board, boolean verticalToPlay) {
//        // TODO You have to write this
//        boolean canPlay = false;
//        for(int x = 0; x < 7; x++){
//            for(int y = 0; y < 7; y++){
//                if(isLegal(x,y,board,verticalToPlay)){
//                    canPlay = true;
//                }
//            }
//        }
//        return canPlay;
//    }
//
//    /**
//     * Plays a move as specified by the user's mouse click. Returns true if
//     * vertical is to play next, false otherwise. If an illegal move is
//     * attempted, this method has no effect and returns the value passed in for
//     * verticalToPlay.
//     */
//    public static boolean[][] handleMouseClick(boolean[][] board, boolean verticalToPlay) {
//        // TODO You have to write this
//        while (!StdDraw.isMousePressed()) {
//            // Wait for mouse press
//        }
//        double x = Math.round(StdDraw.mouseX());
//        double y = Math.round(StdDraw.mouseY());
//        while (StdDraw.isMousePressed()) {
//            // Wait for mouse release
//        }
//        int a = (int) x;
//        int b = (int) y;
//        if(isLegal(a,b,board,verticalToPlay) == true) {
//            board = placeDomino(a,b,board,verticalToPlay);
////				verticalToPlay = opposite(verticalToPlay);
//        } else {
//            handleMouseClick(board,verticalToPlay);
//        }
//        return board;
//    }
//
//    /**
//     * Returns true if playing at x, y is legal for the current player. A move is illegal
//     * if either half of the domino is off the board or on an occupied square.
//     */
//    public static boolean isLegal(int x, int y, boolean[][] board, boolean verticalToPlay) {
//        // TODO You have to write this
//
//        if(verticalToPlay){
//            if (x > 7 || y > 7 || x < 0 || y < 1) {
//                return false;
//            }
//            if (board[x][y] != true && board[x][y - 1] != true) {
//                return true;
//            }
//        } else {
//            if (x > 6 || y > 7 || x < 0 || y < 0) {
//                return false;
//            }
//            if (board[x][y] != true && board[x + 1][y] != true) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//    /** Plays the game. */
//    public static void main(String[] args) {
//        // TODO You have to write this
//        boolean[][] board = new boolean[8][8];
//        boolean verticalToPlay = true;
//
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setScale(-1.5,8.5);
//        while (true) {
//            draw(board, true);
//            board = handleMouseClick(board, verticalToPlay);
//            verticalToPlay = opposite(verticalToPlay);
//            if (gameOver(board,verticalToPlay) == false){
//                StdDraw.text(4,8,"Game Over!");
//                return;
//            }
//        }
//    }
//
//    /**
//     * Returns the opposite value for verticalToPlay. For example,
//     * opposite(true) is false.
//     */
//    public static boolean opposite(boolean verticalToPlay) {
//        // TODO You have to write this
//        return !verticalToPlay;
//    }
//
//    /**
//     * Places a domino at x, y with the specified orientation. Assumes the
//     * placement is legal.
//     */
//    public static boolean[][] placeDomino(int x, int y, boolean[][] board, boolean verticalToPlay) {
//        if(verticalToPlay){
//            board[x][y] = true;
//            board[x][y - 1] = true;
//        } else {
//            board[x][y] = true;
//            board[x + 1][y] = true;
//        }
//
//        return board;
//    }
//
//}
//
