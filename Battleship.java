public class Battleship {

    public static void main(String[] args) {

    }
    
    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(start.column() - end.column()) + Math.abs(start.row() - end.row());
    }
    
    static final int SIZE = 10;
    
    static Coordinate getRandomInt (int Random) {
        return new Coordinate(Utility.getRandomInt(SIZE), Utility.getRandomInt(SIZE));
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end) {
        return(start.column() == end.column() || start.row() == end.row());
    }

    static void showSeperatorLine(){
        System.out.println("   +-+-+-+-+-+-+-+-+-+-+      +-+-+-+-+-+-+-+-+-+-+");
    }

    static int getMaxSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.column(), end.column()) + 1, SIZE - 1);
    }

    static int getMaxSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.min(Math.max(start.row(), end.row()) + 1, SIZE - 1);
    }

    static int getMinSurroundingColumn(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.column(), end.column()) - 1, 0);
    }

    static int getMinSurroundingRow(final Coordinate start, final Coordinate end) {
        return Math.max(Math.min(start.row(), end.row()) - 1, 0);
    }

    static Coordinate toCoordinate(final String input){
        int column = input.toLowerCase().charAt(0) - 'a';
        int row = Integer.parseInt(input.substring(1)) - 1;
                return new Coordinate(column, row);
    }

    static boolean isValidCoordinate(final String input) {
        return input.toUpperCase().matches("[A-J](10|[1-9])");
    }

    static final String ENTER_SHIP_COORDINATE_PROMPT =
    "Geben Sie die %skoordinaten für ein Schiff der Länge %d ein: ";
    
    static String getStartCoordinatePrompt( final int length) {
    return String.format (ENTER_SHIP_COORDINATE_PROMPT,"Start",length);
}

    static String getEndCoordinatePrompt( final int length) {
    return String.format (ENTER_SHIP_COORDINATE_PROMPT,"End",length);
}

}