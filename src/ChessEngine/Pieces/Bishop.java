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

public class Bishop extends Piece{
    private static int[] CANDIDATE_MOVE_COORDINATE = {-9, -7, 7, 9};
    private Bishop(int piecePosition, Alliance pieceAlliance){
        super (piecePosition, pieceAlliance);
    }
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves =new ArrayList<>();

        for (int candidateCoordinateOffset : CANDIDATE_MOVE_COORDINATE){
            int candidateDestinationCoordinate = this.piecePosition;

            while (BoardUtils.isValidCoordinate(candidateDestinationCoordinate)){// used a while to let the destination coordinate update everytime
                if(isFirstColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)||
                        isEighthColumnExclusion(candidateDestinationCoordinate,candidateCoordinateOffset)){
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;
                if(BoardUtils.isValidCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.NormalMove(board,this,candidateDestinationCoordinate));
                    }else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = candidateDestinationTile.getPiece().pieceAlliance;
                        if (this.pieceAlliance != pieceAlliance){//6. knowing if it is the enemy or not then move
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }


        return Collections.unmodifiableList(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((candidateOffset == -9) || (candidateOffset == 7));
    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -7) || (candidateOffset == 9));
    }


}
