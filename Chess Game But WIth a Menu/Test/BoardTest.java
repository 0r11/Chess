import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class BoardTest {
    private final Board b = new Board();

    @Test
    public void initialize(){
        b.initialize();
        assertEquals(b.getPiece(0,0).getPiece(), 0);
    }
    @Test
    public void RookCantMove(){
        b.initialize(); //Can't move at start of game
        assertEquals(b.getPiece(0,0).pieceCanMove(), new ArrayList<Integer[]>());
    }




}