import java.io.IOException;

public class Tournament {
    static boolean finished(final TournamentNode root) {
        return root.winner() != null;
    }

    static TournamentNode setPoints(final TournamentNode node, final int points) {
        return new TournamentNode(node.left(), node.right(), node.winner(), points);
    }

    static int powerOf2(final int nonNegativeNumber) {
        int loesung = 1;
        if (nonNegativeNumber == 0) {
            return 1;
        }
        for (int i = 0; i <= nonNegativeNumber; i++) {
            loesung = loesung * 2;
        }
        return loesung;
    }

    static int rowOffset(final int level, final int hight) {
        return (powerOf2(hight) / powerOf2(level));
    }

    static int getHight(final TournamentNode node) {
        if (node == null) {
            return -1;
        }
        int leftHight = getHight(node.left());
        int rightHight = getHight(node.right());
        return Math.max(leftHight, rightHight) + 1;
    }

    static int countNames(final TournamentNode node) {
        if (node == null) {
            return 0;
        }
        int result = node.winner() != null ? 1 : 0;
        result += countNames(node.left());
        result += countNames(node.right());
        return result;
    }

    static int getNumberOfLeaves(final TournamentNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left() == null && node.right() == null) {
            return 1;
        }
        return getNumberOfLeaves(node.left()) + getNumberOfLeaves(node.right());
    }

    static TournamentNode addParticipant(final String name, final TournamentNode node) {
        if (node == null) {
            return new TournamentNode(null, null, name, 0);
        }
        if (node.winner() != null) {
            return new TournamentNode(null, null, name, 0);
        }
        int leavesLeft = getNumberOfLeaves(node.left());
        int leavesRight = getNumberOfLeaves(node.right());
        if (leavesLeft > leavesRight) {
            return new TournamentNode(node.left(), addParticipant(name, node.right()), null, 0);
        } else {
            return new TournamentNode(node.right(), addParticipant(name, node.right()), null, 0);
        }
    }

    static TournamentNode readParticipants() {
        TournamentNode result = null;
        String input = null;
        while (!"".equals(input)) {
            if (input != null) {
                result = addParticipant(input, result);
            }
            try {
                System.out.println("Geben sie den nächsten Teilnehmer ein(leere Eingabe zum Beenden) :");
                input = Utility.readStringFromConsole();
            } catch (IOException e) {
                input = "";
            }
        }
        return result;
    }

    static int readPoints(final String name) {
        int result = 0;

        boolean ok = false;
        while (!ok) {
            System.out.println("Punktzahl " + name + ":");
            try {
                result = Integer.parseInt(Utility.readStringFromConsole());
                ok = true;
            } catch (IOException | NumberFormatException e) {
                ok = false;
            }
        }
        return result;
    }

    static TournamentNode readNextResult(final TournamentNode node) {
        if (node.left().winner() != null && node.right().winner() != null) {
            int leftPoints = readPoints(node.left().winner());
            int rightPoints = readPoints(node.right().winner());
            while (leftPoints == rightPoints) {
                System.out.println("Punktzahl darf nicht gleich sein.");
                leftPoints = readPoints(node.left().winner());
                rightPoints = readPoints(node.right().winner());
            }
            return new TournamentNode(
                    setPoints(node.left(), leftPoints),
                    setPoints(node.right(), rightPoints),
                    leftPoints > rightPoints ? node.left().winner() : node.right().winner(),
                    0);
        }

        if (node.left().winner() != null) {
            return new TournamentNode(node.left(), readNextResult(node.right()), null, 0);
        }

        if (node.right().winner() != null) {
            return new TournamentNode(readNextResult(node.left()), node.right(), null, 0);
        }

        int leftNames = countNames(node.left());
        int rightNames = countNames(node.right());
        return leftNames > rightNames ? new TournamentNode(node.left(), readNextResult(node.right()), null, 0)
                : new TournamentNode(readNextResult(node.left()), node.right(), null, 0);
    }
}