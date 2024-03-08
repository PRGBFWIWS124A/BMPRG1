import java.io.IOException;

public class Battleship {

    public static void main(String[] args) {

    }

    static int distance(final Coordinate start, final Coordinate end) {
        return Math.abs(start.column() - end.column()) + Math.abs(start.row() - end.row());
    }

    static final int SIZE = 10;

    static Coordinate getRandomCoordinate(int Random) {
        return new Coordinate(Utility.getRandomCoordinate(SIZE), Utility.getRandomCoordinate(SIZE));
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
                if (shipSunk(shot, field)) {
                    fillWaterHits(shot, field);
                }
                break;
            default:
        }
    }

    static void placeShip(final Coordinate start, final Coordinate end, final Field[][] field) {
        for (int i = Math.min(start.row(), end.row()); i < Math.max(start.row(), end.row()); i++) {
            for (int j = Math.min(start.column(), end.column()); j < Math.max(start.column(), end.column()); j++) {
                field[i][j] = Field.SHIP;
            }
        }
    }

    static void showRow(final int row, final Field[][] ownField, final Field[][] otherField) {
        showRowNumber(row);
        System.out.print(" |");
        for (int i = 0; i < SIZE; i++) {
            showField(ownField[row][i], true);
            System.out.print("|");
        }
        System.out.print("   ");
        showRowNumber(row);
        System.out.print(" |");
        for (int i = 0; i < SIZE; i++) {
            showField(otherField[row][i], false);
            System.out.print("|");
        }
        System.out.println();
    }

    static void showFields(final Field[][] ownField, final Field[][] otherField) {
        System.out.println("    A B C D E F G H I J        A B C D E F G H I J");
        for (int i = 0; i < SIZE; i++) {
            showSeperatorLine();
            showRow(i, ownField, otherField);
        }
        showSeperatorLine();
        System.out.println();
    }

    static boolean shipSunk(final Coordinate shot, final Field[][] field) {
        int row = shot.row();
        int column = shot.column();
        while (row > 0 && Field.SHIP_HIT == field[row][column]) {
            row--;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (row < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            row++;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (column < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            column++;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        row = shot.row();
        column = shot.column();
        while (column > 0 && Field.SHIP_HIT == field[row][column]) {
            column--;
        }
        if (Field.SHIP == field[row][column]) {
            return false;
        }
        return true;
    }

    static void setAllFree(final Field[][] field) {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                field[row][column] = Field.FREE;
            }
        }
    }

    static int countHits(final Field[][] field) {
        int result = 0;
        for (int row = 0; row <= SIZE - 1; row++) {
            for (int column = 0; column <= SIZE - 1; column++) {
                if (Field.SHIP_HIT == field[row][column]) {
                    result++;
                }
            }
        }
        return result;
    }

    static void fillWaterHits(final Coordinate shot, final Field[][] field) {
        int row = shot.row();
        int column = shot.column();
        while (row > 0 && Field.SHIP_HIT == field[row][column]) {
            row--;
        }
        int minRow = row;
        row = shot.row();
        column = shot.column();
        while (row < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            row++;
        }
        int maxRow = row;
        row = shot.row();
        column = shot.column();
        while (column < SIZE - 1 && Field.SHIP_HIT == field[row][column]) {
            column++;
        }
        int minColumn = column;
        row = shot.row();
        column = shot.column();
        while (column > 0 && Field.SHIP_HIT == field[row][column]) {
            column--;
        }
        int maxColumn = column;
        for (row = minRow; row <= maxRow; row++) {
            for (column = minColumn; column <= maxColumn; column++) {
                if (field[row][column] == Field.FREE) {
                    field[row][column] = Field.WATER_HIT;
                }
            }
        }
    }

    static boolean noConflict(final Coordinate start, final Coordinate end, final Field[][] field) {
        for (int column = getMinSurroundingColumn(start, end); column <= getMaxSurroundingColumn(start,
                end); column++) {
            for (int row = getMinSurroundingRow(start, end); row <= getMaxSurroundingRow(start, end); row++) {
                if (field[column][row] != Field.FREE) {
                    return false;
                }
            }
        }
        return true;
    }

    static Coordinate readCoordinate(final String prompt) {
        String eingabe = "";
        while (!isValidCoordinate(eingabe)) {
            System.out.println(prompt);
            try {
                eingabe = Utility.readStringFromConsole();
            } catch (IOException e) {
                eingabe = "";
            }
        }
        return toCoordinate(eingabe);
    }

    static Coordinate getRandomUnshotCoordinate(final Field[][] field) {
        int chances = 0;
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[0].length; column++) {
                if (field[row][column] == Field.FREE || field[row][column] == Field.SHIP) {
                    chances++;
                }
            }
        }
        int rand = Utility.getRandomInt(chances);
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[0].length; column++) {
                if (field[row][column] == Field.FREE || field[row][column] == Field.SHIP) {
                    rand--;
                    if (rand < 0) {
                        return new Coordinate(column, row);
                    }
                }
            }
        }
        throw new IllegalStateException();
    }

    static Coordinate readEndCoordinate(final int length) {
       return readCoordinate(getEndCoordinatePrompt(length));
    }

    static Coordinate readStartCoordinate(final int length) {
        return readCoordinate(getStartCoordinatePrompt(length));
     }

    static final int All_Hit = 14;

    static boolean allHit(final Field[][] field) {
        return countHits(field) == All_Hit;
     }

    static boolean endCondition(final Field[][] ownField, final Field[][] otherField) {
        return allHit(otherField) || allHit(ownField);
    }

    static boolean validPosition(final Coordinate start, final Coordinate end, final int length, final Field[][] field) {
        return noConflict(start, end, field) && distance(start, end) == length -1 && onOneLine(start, end);
    }

    static void turn(final Field[][] ownField, final Field[][] otherField) {
        showFields(ownField, otherField);
        shot(readCoordinate("Geben sie eine Coordinate an."), otherField);
        shot(getRandomUnshotCoordinate(ownField), ownField);
    }

    static void outputWinner(final Field[][] ownField, final Field[][] otherField) {
        showFields(ownField, otherField);
        if(allHit(otherField)) {
            System.out.println("DU HAST GEWONNEN!!!");
        } else if (allHit(ownField)) {
            System.out.println("DER COMPUTER HAT GEWONNEN!!!");
        }
    }

    static Field[][] initOtherField() {
       placeShip(get, null, null); 
    }
}