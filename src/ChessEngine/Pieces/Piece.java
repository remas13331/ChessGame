package ChessEngine.Pieces;

import ChessEngine.Alliance;
import ChessEngine.Board.Board;
import ChessEngine.Board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    Piece (final int piecePosition, final  Alliance pieceAlliance){
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }
    public abstract Collection<Move> calculateLegalMoves (final Board board);

}
