package ChessEngine.Board;

public class BoardUtils {

    // set these four col to be true
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final int tilesNum = 64;
    public static final int tilesPerRow =8;

    private BoardUtils(){
        throw new RuntimeException("You cannot instantiate me !");
    }
    private static boolean[] initColumn (int columnNum){
        final boolean[] column = new boolean[tilesNum];
        do {
            column[columnNum] = true;
            columnNum += tilesPerRow;

        }while (columnNum < tilesNum);
        return column;
    }

    public static boolean isValidCoordinate(int coordinate){
        return coordinate >=0 && coordinate <tilesNum;
    }
}
