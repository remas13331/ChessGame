package ChessEngine.Pieces;

import ChessEngine.Alliance;
import ChessEngine.Board.Board;
import ChessEngine.Board.BoardUtils;
import ChessEngine.Board.Move;
import ChessEngine.Board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-17, -15, -10, -6, 6, 10, 15, 17};
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        int destinationCoordinate; //1. created to know where to go + use it to get the tile of the destination
        final List<Move> legalMoves =new ArrayList<>();

        for (final int candidateOffset : CANDIDATE_MOVE_COORDINATE){
            destinationCoordinate = this.piecePosition + candidateOffset;//2. add to the origin pos the arraylist of the distance
            if(BoardUtils.isValidCoordinate(destinationCoordinate)){
                if(isFirstColumnExclusion(this.piecePosition, candidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition, candidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, candidateOffset) ||
                        isEighthColumnExclusion(this.piecePosition, candidateOffset)){// if any of the column is included then continue
                    continue;
                }
                final Tile destinationTile = board.getTile(destinationCoordinate); //3. we got the tile ! + use it to get pieces at the certain destination
                if (!destinationTile.isTileOccupied()){// if there is not a piece in that destination
                    legalMoves.add(new Move.NormalMove(board, this, destinationCoordinate ));// move
                }else{// if there is a piece
                    final Piece pieceAtDestination = destinationTile.getPiece();//4. get the piece from the tile obj
                    final Alliance pieceAlliance = destinationTile.getPiece().pieceAlliance;//5. get the color of the piece
                    if (this.pieceAlliance != pieceAlliance){//6. knowing if it is the enemy or not then move
                        legalMoves.add(new Move.AttackMove(board, this, destinationCoordinate, pieceAtDestination));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }


    // these methods are here to check if the given position is on which column
    // and the steps are equal to the array elements or not ==> true / false
    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) || (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
    }
    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((candidateOffset == 10) || candidateOffset == 6);
    }
    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6) || (candidateOffset == 10) || (candidateOffset == 17));
    }


}
