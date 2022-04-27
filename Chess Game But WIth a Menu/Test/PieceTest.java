import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    private final Board b = new Board();

    @Test
    public void pawnCanMove(){
        b.initialize();
        b.getPiece(0,1).movePiece(0,2);
        assertNotEquals(3,b.getPiece(0,2).getPiece());
    }

    @Test
    public void RookCanMove(){
        b.initialize();
        b.getPiece(0,1).movePiece(0,1);
        assertNotEquals(3,b.getPiece(0,2).getPiece());
    }

    @Test
    public void BishopCanMove(){
        b.initialize();
        b.getPiece(0,1).movePiece(0,2);
        assertNotEquals(3,b.getPiece(0,2).getPiece());
    }

    @Test
    public void KnightCanMove(){
        b.initialize();
        b.getPiece(0,1).movePiece(0,2);
        assertNotEquals(3,b.getPiece(0,2).getPiece());
    }

    @Test
    public void QueenCanMove(){
        b.initialize();
        b.getPiece(0,1).movePiece(0,2);
        assertNotEquals(3,b.getPiece(0,2).getPiece());
    }

    @Test
    public void KingCanMove(){
        b.initialize();
        b.getPiece(0,1).movePiece(0,2);
        assertNotEquals(3,b.getPiece(0,2).getPiece());
    }
}