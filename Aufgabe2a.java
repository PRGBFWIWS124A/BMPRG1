public class Aufgabe2a {
    public static void main(String[] args) {

    }

    public static boolean inCircle(double px, double py, double  cx, double cy, double r) {
        double a = px - cx;
        double b = py - cy;
        double c = Math.sqrt(a*a+b*b);
        return c <= r;
    }
}
