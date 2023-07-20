package ChessEngine.Board;

import ChessEngine.Pieces.Piece;

public class Move {

    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board,
                 final Piece movedPiece,
                 final int destinationCoordinate){
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class NormalMove extends Move {
        public NormalMove (final Board board,
                           final Piece movedPiece,
                           final int destinationCoordinate){
            super(board, movedPiece, destinationCoordinate);
        }
    }
    public static final class AttackMove extends Move{

        final Piece attackedPiece ; // if the same tile was occupied
        public AttackMove (final Board board,
                           final Piece movedPiece,
                           final int destinationCoordinate,
                           final Piece attackedPiece){
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }

}
