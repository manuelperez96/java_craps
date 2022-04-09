import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // Used for take user options.
    final Scanner input = new Scanner(System.in);
    final int PLAY = 1;
    final int EXIT = 2;
    final int CRAP = 7;
    final int[] AVAILABLE_MENU_OPTIONS = {PLAY, EXIT};
    final int[] WIN_THROWS = {7, 11};
    final int[] LOSE_THROWS = {2, 3, 12};

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        showInitialMessage();
        while (true) {
            showStartMenuOption();
            int userOption = requestUserOption(AVAILABLE_MENU_OPTIONS);
            shouldInitGame(userOption);
        }
    }

    private void showInitialMessage() {
        System.out.println("¡Bienvenidos al mejor casino del mundo!");
        System.out.println("¿¡Estás listo para ganar una gran cantidad de dinero jugando a Scraps!?");
    }

    private void showStartMenuOption() {
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1.- Jugar");
        System.out.println("2.- Salir");
    }

    // todo: this function should be refactor
    private int requestUserOption(int[] availableOptions) {
        int userOption = -1;

        do {
            userOption = requestOption(userOption, availableOptions);
        } while (!isIn(availableOptions, userOption));

        return userOption;
    }

    private int requestOption(int userOption, int[] availableOptions) {
        try {
            userOption = input.nextInt();
            if (!isIn(availableOptions, userOption)) {
                System.out.println("No has introducido una opción correcta");
            }
        } catch (InputMismatchException e) {
            System.out.println("No has introducido una opción correcta");
        }
        return userOption;
    }

    private void shouldInitGame(int userOption) {
        if (userOption == PLAY) {
            runFirstThrowLogic();
        } else {
            System.out.println("Nos vemos pronto. Hasta luego.");
            System.exit(0);
        }
    }

    private void runFirstThrowLogic() {
        int firstThrow = -1;

        showDiceMessage();
        final int userOption = requestUserOption(new int[]{1});
        firstThrow = throwDices(2, 6);
        System.out.println("Has obtenido un " + firstThrow);
        if (!runFirstThrowLogic(firstThrow)) {
            startSecondLoopGame(firstThrow);
        }

    }

    private void showDiceMessage() {
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1. Lanzar dados");
    }

    private int throwDices(int numberOfDices, int maxDiceValue) {
        final Random random = new Random();
        int result = 0;
        int minDiceValue = 1;

        for (int i = 0; i < numberOfDices; i++) {
            result += random.nextInt(maxDiceValue) + minDiceValue;
        }

        return result;
    }

    /**
     * This function determine if game should finish.
     *
     * @param throwValue
     * @return true if game should finish, otherwise return false.
     */
    private boolean runFirstThrowLogic(int throwValue) {
        System.out.println("------------ " + throwValue);
        if (isIn(WIN_THROWS, throwValue)) {
            System.out.println("Enhorabuena, has ganado.");
            return true;
        } else if (isIn(LOSE_THROWS, throwValue)) {
            System.out.println("Más suerte la próxima vez, has perdido.");
            return true;
        } else {
            System.out.println("Parece que volverás a tirar.");
            return false;
        }
    }

    private void startSecondLoopGame(int firstThrowValue) {
        int secondThrow = -1;

        while (true) {
            showDiceMessage();
            final int userOption = requestUserOption(new int[]{1});
            secondThrow = throwDices(2, 6);
            System.out.println("Has obtenido un " + secondThrow);

            if (runSecondThrowLogin(firstThrowValue, secondThrow)) {
                return;
            }
        }
    }

    /**
     * This function determine if user have to rethrow the dices.
     *
     * @param firstThrowValue
     * @param secondThrowValue
     * @return true if user have to throw dices, otherwise, return false.
     */
    private boolean runSecondThrowLogin(int firstThrowValue, int secondThrowValue) {
        if (secondThrowValue == CRAP) {
            System.out.println("Has perdido. Más suerte la próxima vez.");
            return true;
        } else if (firstThrowValue == secondThrowValue) {
            System.out.println("Enhorabuena. Has ganado.");
            return true;
        } else {
            System.out.println("Tendrás que volver a tirar.");
            return false;
        }
    }


    private boolean isIn(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }
}
