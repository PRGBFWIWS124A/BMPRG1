public class Tournament {
    static boolean finished(final TournamentNode root) {
        return root.winner() != null;
    }

    static TournamentNode setPoints(final TournamentNode node, final int points) {
        return new TournamentNode(node.left(), node.right(), node.winner(), points);
    }

    static int powerOf2(final int nonNegativeNumber) {
        int loesung = 1;
        if(nonNegativeNumber == 0) {
            return 1;
        }
        for(int i = 0; i <= nonNegativeNumber; i++) {
            loesung = loesung * 2;
        }
        return loesung;
    }
}
