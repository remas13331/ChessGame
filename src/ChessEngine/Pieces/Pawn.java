package ChessEngine.Pieces;

import ChessEngine.Alliance;
import ChessEngine.Board.Board;
import ChessEngine.Board.BoardUtils;
import ChessEngine.Board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece{

    private static int[] CANDIDATE_MOVE_COORDINATE = {7, 8, 9, 16};
    Pawn(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves =new ArrayList<>();
        for (int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE ){
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
            //TODO please check this line here
            if (!BoardUtils.isValidCoordinate(candidateDestinationCoordinate)){
                continue;
            }
            //// when it moves forward
            if(currentCandidateOffset==8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new Move.NormalMove(board,this,candidateDestinationCoordinate));
            ////when it moves two steps forward
            }else if (currentCandidateOffset == 16 && this.isFirstMove() &&
                     (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceAlliance().isBlack()) ||
                     (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())){
                //create the step two tile obj
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if(!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new Move.NormalMove (board,this,candidateDestinationCoordinate));
                }
            //// when there is a piece in the north-east or north-west BUT *the white pawn is not in the eighth column or the black pawn is not in the first*
            }else if(currentCandidateOffset == 7 && !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
                    (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))){
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance() ){
                        legalMoves.add(new Move.NormalMove(board,this,candidateDestinationCoordinate));
                    }
                }
            } else if (currentCandidateOffset == 9 && !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite()) ||
                      (BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if(this.pieceAlliance != pieceOnCandidate.getPieceAlliance() ){
                        legalMoves.add(new Move.NormalMove(board,this,candidateDestinationCoordinate));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
}
