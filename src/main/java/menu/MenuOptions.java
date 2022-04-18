package menu;

import message.Messages;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuOptions {
    final public int START = 1;
    final public int EXITS = 2;
    final public int BASE = 1;
    final public int RICH = 2;
    final public int YES = 1;
    final public int NO = 2;
    final public int THROW_DICE = 1;
    final public int DO_BET = 2;

    final Scanner input = new Scanner(System.in);
    final private int[] initialMenuOptions = {START, EXITS};
    final private int[] playerOptions = {BASE, RICH};
    final private int[] binaryOptions = {YES, NO};
    final private int[] gameOptions = {THROW_DICE, DO_BET};

    public int requestInitialOptions() {
        return requestOption(initialMenuOptions);
    }

    public int requestUserType() {
        System.out.println(Messages.playerType);
        return requestOption(playerOptions);
    }

    public String requestUserName() {
        boolean isChoosingName = true;
        String name = "";
        int userOption = -1;

        while (isChoosingName) {
            System.out.println(Messages.whatIsYourName);
            name = input.nextLine();
            System.out.println(Messages.areYouSureOfYourName(name));
            userOption = requestOption(binaryOptions);
            if (userOption == YES) isChoosingName = false;
        }

        return name;
    }

    public double requestBet(double minBet, double userMoney) {
        if (minBet > userMoney) throw new RuntimeException("User does not have sufficient money");
        double userBet = 0;

        while (true) {
            System.out.println(Messages.currentMinBet(minBet));
            try {
                userBet = input.nextDouble();

                if (userBet < minBet) {
                    System.out.println(Messages.minBetShouldBe(minBet));
                } else if (userBet > userMoney) {
                    System.out.println(Messages.maxBetShouldBe);
                } else {
                    return userBet;
                }

            } catch (InputMismatchException e) {
                System.out.println(Messages.invalidValue);
                input.nextLine();
            }
        }
    }

    public int requestGameOptions(boolean isMenuFull) {
        final int[] options = isMenuFull ? gameOptions : new int[]{gameOptions[0]};
        System.out.println(Messages.showDiceMessage(isMenuFull));
        return requestOption(options);
    }

    private int requestOption(int[] availableOptions) {
        int userOption = -1;

        while(true) {
            try {
                userOption = input.nextInt();
                if (!isIn(availableOptions, userOption)) {
                    System.out.println(Messages.incorrectInputOption);
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(Messages.incorrectInputOption);
            } finally {
                input.nextLine();
            }
        }
        return userOption;

    }

    public void close() {
        input.close();
    }

    private boolean isIn(int[] array, int value) {
        for (int i : array) {
            if (i == value) return true;
        }
        return false;
    }

}
