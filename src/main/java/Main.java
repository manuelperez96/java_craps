import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // Used for take user options.
    final private Scanner input = new Scanner(System.in);
    final private int PLAY = 1;
    final private int EXIT = 2;
    final private int CRAP = 7;
    final private int[] AVAILABLE_MENU_OPTIONS = {PLAY, EXIT};
    final private int[] WIN_THROWS = {7, 11};
    final private int[] LOSE_THROWS = {2, 3, 12};
    final private double MIN_BET = 20;
    private double userMoney = 200;
    private double currentBet = 0;

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        showInitialMessage();
        while (_userHasSufficientMoney()) {
            showStartMenuOption();
            int userOption = requestUserOption(AVAILABLE_MENU_OPTIONS);
            if (shouldInitGame(userOption)) {
                System.out.println("Dinero actual:" + userMoney);
                System.out.println("¿Cuánto quiere apostar?");
                currentBet = requestBet(MIN_BET, userMoney);
                runFirstThrowLogic();
            } else {
                System.out.println("Nos vemos pronto. Hasta luego.");
                System.exit(0);
            }
        }

        System.out.println("Vaya, parece que te has quedado sin dinero. Vuelve cuando tengas más.");
    }

    private double requestBet(double minBet, double userMoney) {
        if (minBet > userMoney) throw new RuntimeException("User does not have sufficient money");
        double userBet = 0;

        while (true) {
            System.out.println("Apuesta mínima actual: " + minBet);
            try {
                userBet = input.nextDouble();

                if (userBet >= minBet) return userBet;
                System.out.println("La apuesta tiene que como mínimo " + minBet);
            } catch (InputMismatchException e) {
                System.out.println("apuesta inválida");
                input.nextLine();
            }
        }
    }

    private boolean _userHasSufficientMoney() {
        return userMoney >= MIN_BET;
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
            input.nextLine();
        }
        return userOption;
    }

    private boolean shouldInitGame(int userOption) {
        return userOption == PLAY;
    }

    private void runFirstThrowLogic() {
        int firstThrow;

        showDiceMessage(false);
        requestUserOption(new int[]{1});
        firstThrow = throwDices(2, 6);
        System.out.println("Has obtenido un " + firstThrow);
        if (!runFirstThrowLogic(firstThrow)) {
            startSecondLoopGame(firstThrow);
        }

    }

    private void showDiceMessage(boolean isFull) {
        System.out.println("¿Qué quieres hacer?");
        System.out.println("1. Lanzar dados");
        if (isFull) {
            System.out.println("2. Apostar");
        }
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
     * @return true if game should finish, otherwise return false.
     */
    private boolean runFirstThrowLogic(int throwValue) {
        if (isIn(WIN_THROWS, throwValue)) {
            System.out.println("Enhorabuena, has ganado.");
            runBet(currentBet);
            return true;
        } else if (isIn(LOSE_THROWS, throwValue)) {
            System.out.println("Más suerte la próxima vez, has perdido.");
            runBet(-currentBet);
            return true;
        } else {
            System.out.println("Parece que volverás a tirar.");
            return false;
        }
    }

    private void runBet(double amount) {
        userMoney += amount;
        currentBet = 0;
        System.out.println("Dinero actual: " + userMoney);
    }

    private void startSecondLoopGame(int firstThrowValue) {
        int secondThrow;
        int userOption;
        final boolean hasUserSufficientMoneyToBet = userMoney > currentBet;
        final int[] availableOptions = hasUserSufficientMoneyToBet ? new int[]{1, 2} : new int[1];
        while (true) {
            showDiceMessage(hasUserSufficientMoneyToBet);
            userOption = requestUserOption(availableOptions);
            if (userOption == 2) {
                currentBet = requestBet(currentBet, userMoney);
                showDiceMessage(false);
                requestUserOption(new int[]{1});
            }
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
     * @return true if user have to throw dices, otherwise, return false.
     */
    private boolean runSecondThrowLogin(int firstThrowValue, int secondThrowValue) {
        if (secondThrowValue == CRAP) {
            System.out.println("Has perdido. Más suerte la próxima vez.");
            runBet(-currentBet);
            return true;
        } else if (firstThrowValue == secondThrowValue) {
            System.out.println("Enhorabuena. Has ganado.");
            runBet(currentBet);
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
