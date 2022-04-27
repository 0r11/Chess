import java.awt.*;
import java.util.ArrayList;

public class GUI {

    private static Piece piece;
    public double whitePieces = 0; //Number
    public double blackPieces = 0;
    public static String[] whiteImages;
    public static String[] blackImages;


    public GUI() {
        this.piece = piece;
        whiteImages = new String[]{"whiteRook copy.png", "whiteBishop copy.png", "whiteQueen copy.png",
                "whiteHorse copy.png", "WhitePawn copy.png", "WhiteKing copy.png"};
        blackImages = new String[]{"blackRook copy.png", "blackBishop copy.png", "blackQueen copy.png",
                "blackHorse copy.png", "blackPawn copy.png", "blackKing copy.png"};
    }

    public boolean drawMenu() {
        StdDraw.setXscale(-.5, 7.5);
        StdDraw.setYscale(-.5, 7.5);

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((x + y) % 2 == 0) {
                    StdDraw.setPenColor(Color.darkGray);
                    StdDraw.filledSquare(x, y, .5);
                } else {
                    StdDraw.setPenColor(Color.lightGray);
                    StdDraw.filledSquare(x, y, .5);
                }

            }
        }

        StdDraw.setPenColor(Color.pink);
        StdDraw.setPenRadius(0.025);
        StdDraw.square(1.75, 4, 1.4);
        StdDraw.square(5.25, 4, 1.4);
        StdDraw.picture(1.75, 4, "PvP copy.jpeg");
        StdDraw.picture(5.25, 4, "PvC copy.jpeg");


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

        if ((a > .75 && a < 3.25) && (b > 2.75 && b < 4.5)) {
            StdDraw.clear();
            return false; // 1 player
        } else if (((a > 3.75 && a < 6.25) && (b > 2.75 && b < 5.25))) {
            StdDraw.clear();
            return true; //2 player
        }

        return false;
    }

    public static void drawBoard(Board board) {
        StdDraw.setXscale(-.5, 7.5);
        StdDraw.setYscale(-1, 8);

//        //creates two string arrays that store the file names for images of each peace
//        String[] whiteImages;
//        whiteImages = new String[]{"whiteRook copy.png", "whiteBishop copy.png", "whiteQueen copy.png",
//                "whiteHorse copy.png", "WhitePawn copy.png", "WhiteKing copy.png"};
//        String[] blackImages;
//        blackImages = new String[]{"blackRook copy.png", "blackBishop copy.png", "blackQueen copy.png",
//                "blackHorse copy.png", "blackPawn copy.png", "blackKing copy.png"};

        //draws checkered board
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((x + y) % 2 == 0) {
                    StdDraw.setPenColor(Color.darkGray);
                    StdDraw.filledSquare(x, y, .5);
                } else {
                    StdDraw.setPenColor(Color.lightGray);
                    StdDraw.filledSquare(x, y, .5);
                }

                //initializes piece position and displays them on board
                if (board.getPiece(x, y) != null) {
                    if (board.getPiece(x, y).isWhite()) {
                        StdDraw.picture(x, y, whiteImages[board.getPiece(x, y).getPiece()]);
                    } else {
                        StdDraw.picture(x, y, blackImages[board.getPiece(x, y).getPiece()]);
                    }
                }
            }
        }
    }

    //(Mouse press code adapted from Domineering Lab)
    public void handleMouseClick(boolean isWhite, Board board) {
        while (true) {
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


            if (board.getPiece(a, b) == null) {
                drawBoard(board);
                StdDraw.setPenColor(Color.RED);
                StdDraw.text(3.5, 4, "You cannot move to that spot");
                continue;
            } else if (board.getPiece(a, b).isWhite() != isWhite) { //Makes it so you can't select other color's piece
                continue;
            }

            //when user selects piece it gets highlighted pink
            else if (board.getPiece(a, b).isWhite() == isWhite) {
                drawBoard(board);
                StdDraw.setPenColor(Color.pink);
                StdDraw.setPenRadius(0.01);
                StdDraw.square(a, b, .45);


                //Uses possibleMoves method from Piece class and takes the coordinates of those moves and places a dot at each
                ArrayList<Integer> circleX = new ArrayList<>();
                ArrayList<Integer> circleY = new ArrayList<>();

                ArrayList<Integer[]> dots = board.getPiece(a, b).pieceCanMove();
                for (int i = 0; i < dots.size(); i++) {
                    Integer[] num = dots.get(i);

                    for (int j = 0; j < num.length; j++) {
                        int value = num[j];
                        if (j % 2 == 0) {
                            circleX.add(value);
                        } else {
                            circleY.add(value);
                        }
                    }
                }

                for (int n = 0; n < circleX.size(); n++) {
                    StdDraw.setPenColor(Color.GRAY);
                    if (board.getPiece(circleX.get(n), circleY.get(n)) == null) {
                        StdDraw.filledCircle(circleX.get(n), circleY.get(n), .2);
                    } else {
                        StdDraw.circle(circleX.get(n), circleY.get(n), .5);
                    }
                }
            }

            //after piece is selected, wait for mouse press of desjred location (Mouse press code adapted from Domineering Lab)
            if (board.getPiece(a, b) != null) {
                while(true) {

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
//                    if(board.getPiece(a,b) == null){System.out.println(a + b);continue;}
                    //If move is legal it places piece, if else it runs the method again and allows the user to make another move
                    if (board.getPiece(a, b).isLegal(a1, b1, isWhite)) {
                        if (board.getPiece(a1, b1) != null) { //captured Black Piece
                            captured(board.getPiece(a1, b1));
                            board.getPiece(a, b).movePiece(a1, b1);

                        } else {
                            board.getPiece(a, b).movePiece(a1, b1);
                        }

                        drawBoard(board);
                        return;
                    }
                    drawBoard(board);
                }
            }
        }
    }

    public void captured(Piece p){
        if(p.isWhite()){
            StdDraw.picture(whitePieces / 2, 7.75, whiteImages[p.getPiece()],0.5,0.5);
            whitePieces += 1;
        } else {
            StdDraw.picture(blackPieces / 2, -.75, blackImages[p.getPiece()],0.5,0.5);
            blackPieces += 1;
        }
    }
}

