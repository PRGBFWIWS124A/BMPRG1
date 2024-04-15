public class Aufgabe1a {
    public static void main(String[] args) {
        int number = 0;
        while (number >= 0) {
            System.out.println("Geben Sie eine Zahl ein :)");
            String eingabe = readStringFromConsole();
            number = Integer.parseInt(eingabe);
        }
        System.out.println("Geben Sie ein Wort an :)");
        String wort = readStringFromConsole();
        for(int i = 0; i < number; i++) {
            System.out.println(wort);
        }
    }

    public static String readStringFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
