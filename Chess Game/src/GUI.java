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


        String[] whiteImages;
        whiteImages = new String[]{"whiteRook copy.png", "whiteBishop copy.png", "whiteQueen copy.png",
                "whiteHorse copy.png", "WhitePawn copy.png", "WhiteKing copy.png"};
        String[] blackImages;
        blackImages = new String[]{"blackRook copy.png", "blackBishop copy.png", "blackQueen copy.png",
                "blackHorse copy.png", "blackPawn copy.png", "blackKing copy.png"};

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
                    if(board.getPiece(x,y).isWhite()){
                        StdDraw.picture(x-.5,y-.5, whiteImages[board.getPiece(x,y).getPiece()]);
                    } else {
                        StdDraw.picture(x-.5, y-.5, blackImages[board.getPiece(x, y).getPiece()]);
                    }
                }
            }
        }
    }

    public void handleMouseClick(boolean isWhite, Board board) {
        // TODO You have to write this
        while (!StdDraw.isMousePressed()) {
            // Wait for mouse press
        }
        double x = Math.round(StdDraw.mouseX());
        double y = Math.round(StdDraw.mouseY());

        while (StdDraw.isMousePressed()) {
            // Wait for mouse release
        }

        int a = (int) x;
        int b = (int) y;

        if (board.getPiece(a,b) != null) {

            while (!StdDraw.isMousePressed()) {
                // Wait for mouse press
            }
            double x1 = Math.round(StdDraw.mouseX());
            double y1 = Math.round(StdDraw.mouseY());

            while (StdDraw.isMousePressed()) {
                // Wait for mouse release
            }

            int a1 = (int) x1;
            int b1 = (int) y1;

            if (board.getPiece(a,b).isLegal(a1,b1)) {
                board.getPiece(a,b).movePiece(a1,b1);
            } else {
                this.handleMouseClick(isWhite, board);
            }
        }
    }
}


