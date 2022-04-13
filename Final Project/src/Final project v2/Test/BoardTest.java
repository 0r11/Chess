import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board b = new Board();

    @Test
    public void initialize(){
        b.initialize();
        assertEquals(b.getPiece(0,0).getPiece(), 0);
    }
    @Test
    public void RookCantMove(){
        b.initialize(); //Can't move at start of game
        assertEquals(b.getPiece(0,0).pieceCanMove(),null);
    }
    @Test
    public void RookCanMove(){
        b.setPiece(0,0, new Piece(0,0, 0, true, b)); //Sets 0 0 as a rook
        assertEquals(b.getPiece(0,0).pieceCanMove(), new ArrayList<Integer[]>());
    }


}