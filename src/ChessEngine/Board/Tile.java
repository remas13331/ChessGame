package ChessEngine.Board;

import ChessEngine.Pieces.Piece;
import java.util.Map;

import java.util.Collections;
import java.util.HashMap;

// Tried to make this class as immutable as possible by using (private-protected-final)
public abstract class Tile {

    protected final int tileCoordinate;
    private static final Map<Integer , EmptyTile> EMPTY_TILE_MAP = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer , EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0 ; i < 64 ; i++){
            emptyTileMap.put(i,new EmptyTile(i)); // if i wanted the first tile i will .get(o)
        }
        return Collections.unmodifiableMap(emptyTileMap);
    }
    public static Tile createTile (final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate,piece) :EMPTY_TILE_MAP.get(tileCoordinate); // if it is empty it will return the empty tile coordinate value
    }

    private Tile (int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile { // subclass for empty features while empty tile
        EmptyTile(final int  tileCoordinate){
            super (tileCoordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false; // it is an empty tile it must be false
        }

        @Override
        public Piece getPiece(){
            return null; // it is in the empty tile class
        }
    }

    public static final class OccupiedTile extends Tile { // subclass for an occupied tile with its features

        private final Piece pieceOn ;
        private OccupiedTile(int tileCoordinate, Piece pieceOn){
            super(tileCoordinate);
            this.pieceOn = pieceOn;
        }

        @Override
        public boolean isTileOccupied(){
            return true; // it is an occupied tile it must be true
        }

        @Override
        public Piece getPiece(){
            return this.pieceOn; // it is in the occupied tile class so will return an object
        }


    }


}
