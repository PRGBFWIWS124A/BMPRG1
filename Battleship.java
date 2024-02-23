public class Battleship {

    public static void main(String[] args) {

    }

    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(start.column() - end.column()) + Math.abs(start.row() - end.row());
    }

    static final int SIZE = 10;

    static Coordinate getRandomInt(int Random) {
        return new Coordinate(Utility.getRandomInt(SIZE), Utility.getRandomInt(SIZE));
    }

    static boolean onOneLine(final Coordinate start, final Coordinate end) {
        return (start.column() == end.column() || start.row() == end.row());
    }

    static void showSeperatorLine() {
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

    static Coordinate toCoordinate(final String input) {
        int column = input.toLowerCase().charAt(0) - 'a';
        int row = Integer.parseInt(input.substring(1)) - 1;
        return new Coordinate(column, row);
    }

    static boolean isValidCoordinate(final String input) {
        return input.toUpperCase().matches("[A-J](10|[1-9])");
    }

    static final String ENTER_SHIP_COORDINATE_PROMPT = "Geben Sie die %skoordinaten für ein Schiff der Länge %d ein: ";

    static String getStartCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "Start", length);
    }

    static String getEndCoordinatePrompt(final int length) {
        return String.format(ENTER_SHIP_COORDINATE_PROMPT, "End", length);
    }

    static void showRowNumber(final int row) {
        if (row + 1 < 10) {
            System.out.print(" ");
        }
        System.out.print(row + 1);
    }

    static Coordinate getRandomEndCoordinate(final Coordinate start, final int distance) {
        int choices = 0;
        if (start.column() - distance >= 0) {
            choices++;
        }
        if (start.column() + distance <= SIZE) {
            choices++;
        }
        if (start.row() - distance >= 0) {
            choices++;
        }
        if (start.row() + distance <= SIZE) {
            choices++;
        }
        int choice = Utility.getRandomInt(choices);
        if (start.column() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column() - distance, start.row());
            }
        }
        if (start.column() + distance <= SIZE) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column() + distance, start.row());
            }
        }
        if (start.row() - distance >= 0) {
            choice--;
            if (choice < 0) {
                return new Coordinate(start.column(), start.row() - distance);
            }
        }
        return new Coordinate(start.column(), start.row() + distance);
    }
    
    static void showField(final Field field, final boolean showShips) {
        switch (field) {
            case Field.SHIP:
                System.out.print(showShips == true ? "O" : " ");
                break;
            case Field.FREE:
                System.out.print("");
                break;
            case Field.WATER_HIT:
                System.out.print("X");
                break;
            case Field.SHIP_HIT:
                System.out.print("*");
                break;
        }
    }

    static void shot(final Coordinate shot, final Field[][] field) {
        switch (field[shot.row()][shot.column()]) {
            case Field.FREE:
                field[shot.row()][shot.column()] = Field.WATER_HIT;
                break;
            case Field.SHIP:
                field[shot.row()][shot.column()] = Field.SHIP_HIT;
                break;
                default:
        }
    }

    static void placeShip(final Coordinate start, final Coordinate end, final Field[][] field) {
        for(int i = Math.min(start.row(), end.row()); i < Math.max(start.row(), end.row()); i++) {
            for (int j = Math.min(start.column(), end.column()); j < Math.max(start.column(), end.column()); j++) {
                field[i][j] = Field.SHIP;
            }
        }
    }
}