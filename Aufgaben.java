import java.util.Scanner;

public class Aufgaben {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte eine Zahl eingeben.");
        int number = scanner.nextInt();
        if(number % 2 == 0) {
            System.out.println("Diggi ist gerade");
        } else {
            System.out.println("Diggi ist nicht gerade");
        }
        scanner.close();
    }
}