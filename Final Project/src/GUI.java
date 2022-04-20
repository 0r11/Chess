import java.awt.*;

public class GUI {

    private static Piece piece;

    public GUI() {
        this.piece = piece;
    }

    public static void drawBoard(Board board) {
        StdDraw.clear();
        StdDraw.setXscale(-1, 7);
        StdDraw.setYscale(-1, 7);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((x + y) % 2 == 0) {
                    StdDraw.setPenColor(Color.lightGray);
                    StdDraw.filledSquare(x, y, 1);
                } else {
                    StdDraw.setPenColor(Color.darkGray);
                    StdDraw.filledSquare(x, y, 1);
                }

                if (board.getPiece(x,y) != null){
                    StdDraw.text(x,y, String.valueOf(board.getPiece(x,y).getPiece()));
                }


            }
        }
    }



//    public static Board[][] handleMouseClick(Piece[][] board, boolean isWhite, Piece piece) {
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
//
//        if (board.isLegal) {
//            // TODO (if place clicked is legal move, move piece to that spot)
//        } else {
//            handleMouseClick(board, isWhite, piece);
//        }
//
//        return board;
//    }
//
//        public static Piece[][] placePiece (int x, int y, Piece[][] board, boolean isWhite, Piece piece){
//            if (isWhite) {
//                board.setPiece(x, y, piece);
//            } else {
//                board.setPiece(x, y, piece);
//            }
//
//            return board;
//        }
}


