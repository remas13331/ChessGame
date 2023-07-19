package ChessEngine.Pieces;

import ChessEngine.Alliance;
import ChessEngine.Board.Board;
import ChessEngine.Board.Move;
import ChessEngine.Board.Tile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {-17, -15, -10, -6, 6, 10, 15, 17};
    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }
    @Override
    public List<Move> calculateLegalMoves(Board board) {

        int destinationCoordinate; //1. created to know where to go + use it to get the tile of the destination
        final List<Move> legalMoves =new ArrayList<>();

        for (final int currentCandidate : CANDIDATE_MOVE_COORDINATE){
            destinationCoordinate = this.piecePosition + currentCandidate;//2. add to the origin pos the arraylist of the distance
            if(true /*isValidCoordinate*/){
                final Tile destinationTile = board.getTile(destinationCoordinate); //3. we got the tile ! + use it to get pieces at the certain destination
                if (!destinationTile.isTileOccupied()){// if there is not a piece in that destination
                    legalMoves.add(new Move());// move
                }else{// if there is a piece
                    final Piece pieceAtDestination = destinationTile.getPiece();//4. get the piece from the tile obj
                    final Alliance pieceAlliance = destinationTile.getPiece().pieceAlliance;//5. get the color of the piece
                    if (this.pieceAlliance != pieceAlliance){//6. knowing if it is the enemy or not then move
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }
}
