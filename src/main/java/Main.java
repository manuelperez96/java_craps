import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    // Used for take user options.
    final Scanner input = new Scanner(System.in);
    final int[] availableMenuOptions = {1, 2};

    public static void main(String[] args) {
        new Main().start();


    }

    private void start() {
        showInitialMessage();
    }

    private void showInitialMessage() {
        System.out.println("¡Bienvenidos al mejor casino del mundo!");
        System.out.println("¿¡Estás listo para ganar una gran cantidad de dinero jugando a Scraps!?");
    }

    private void showMenuOption() {
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1.- Jugar");
        System.out.println("2.- Salir");
    }

    // todo: this function should be refactor
    private int requestUserOption() {
        int userOption = -1;

        do {
            userOption = requestOption(userOption);
        }while (isNotMenuOption(userOption));

        return userOption;
    }

    private int requestOption(int userOption) {
        try {
            userOption = input.nextInt();
            if (isNotMenuOption(userOption)) {
                System.out.println("No has introducido una opción correcta");
            }
        } catch (InputMismatchException e) {
            System.out.println("No has introducido una opción correcta");
        }
        return userOption;
    }

    private boolean isNotMenuOption(int userOption) {
        return Arrays.binarySearch(availableMenuOptions, userOption) == -1;
    }
}
