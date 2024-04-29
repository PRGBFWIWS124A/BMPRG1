public class Aufgabe_2 {
    public static void main(String[] args) {
        System.out.println("Gib eine Gesamtbreite ein:");
        String Eingabe = Utility.readStringFromConsole();
        int Gesamtbreite = Integer.parseInt(Eingabe);
        double Mittelpunkt = Gesamtbreite / 2;
        for(int i = 0; i <= Gesamtbreit; i++) {
            for(int j = 0; j <= Gesamtbreite; j++) {
                if(inHeart(j, i, Mittelpunkt, Mittelpunkt, Gesamtbreite) == true) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.put.println();
        }
    }

    static boolean inHeart(double px, double py, double cx, double cy, double g) {
        double b = g/(1+(2/Math.sqrt(2)));
        return inRhombus(px, py, cx, cy, b) || inCircle(px, py, cx - (b/2), cy - (b/2), b/Math.sprt(2)) || inCircle(px, py, cx + (b/2), cy + (b/2), b/Math.sprt(2));
    }

    static boolean inCircle(double px, double py, double cx, double cy, double r) {
        double a = px - cx;
        double b = py - cy;
        double e = Math.sqrt(a*a + b*b);
        return r >= e;
    }

    static boolean inRhombus(double px, double py, double cx, double cy, double b) {
        double a = Math.abs(px - cx);
        double b = Math.abs(py - cy);
        double sum = a + b;
        return sum <= b;
    }
}
