public abstract class Tile {

    //abstract to inherent the abstract methods
    int tileCoordinate;
    Tile (int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile { // subclass for empty features while empty tile
        EmptyTile(int tileCoordinate){
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

        Piece pieceOn ;
        OccupiedTile(int tileCoordinate, Piece pieceOn){
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
