package game;

import menu.MenuOptions;
import message.Messages;
import player.BasePlayer;
import player.Player;
import player.RichPlayer;
import util.Dices;


public class GameLogic {

    final private MenuOptions menu = new MenuOptions();
    final private GameRule rules = new GameRule();
    private double currentBet = 0;
    private Player player;

    /**
     * This function should be the first to run when create a new instance of this class.
     * Execute the whole logic of the game and setup all preparatives for it.
     */
    public void run() {
        new GameSetup().init();

        System.out.println(Messages.welcome);
        System.out.println(Messages.currentMoney(player.getMoney()));

        while (rules.canPlayerPlay(player)) {
            final int option = chooseInitialOptions();

            if (option == menu.START) {
                startGame();
            } else {
                closeGame();
            }
        }

        System.out.println(Messages.insufficientMoney);
    }

    private int chooseInitialOptions() {
        System.out.println(Messages.initialGameOptions);
        return menu.requestInitialOptions();
    }

    private void closeGame() {
        System.out.println(Messages.goodBye);
        menu.close();
        System.exit(0);
    }

    private void startGame() {
        System.out.println(Messages.betAmount);
        currentBet = menu.requestBet(rules.minBet(), player.getMoney());
        runFirstThrowLogic();
    }

    private void runFirstThrowLogic() {
        int diceThrow;

        menu.requestGameOptions(false);
        diceThrow = Dices.throwBy(2, 6);
        System.out.println(Messages.showDiceThrow(diceThrow));

        if (!isResultDecideInFirstThrow(diceThrow)) {
            startSecondLoopGame(diceThrow);
        }
    }

    private boolean isResultDecideInFirstThrow(int throwValue) {
        if (rules.hasUserWinInFirstThrow(throwValue)) {
            System.out.println(Messages.congratulations);
            updateMoney(currentBet);
            return true;
        } else if (rules.hasUserLooseInFirstThrow(throwValue)) {
            System.out.println(Messages.endGame);
            updateMoney(-currentBet);
            return true;
        } else {
            System.out.println(Messages.throwAgain);
            return false;
        }
    }

    private void startSecondLoopGame(int firstThrowValue) {
        int secondThrow;
        int userOption;

        while (true) {
            userOption = menu.requestGameOptions(rules.canUserDoBet(player, currentBet));
            if (userOption == menu.DO_BET) {
                currentBet = menu.requestBet(currentBet, player.getMoney());
                menu.requestGameOptions(false);
            }
            secondThrow = Dices.throwBy(2, 6);
            System.out.println(Messages.showDiceThrow(secondThrow));

            if (isResultDecide(firstThrowValue, secondThrow)) {
                return;
            }
        }
    }

    private boolean isResultDecide(int firstThrowValue, int secondThrowValue) {
        if (rules.hasUserLose(secondThrowValue)) {
            System.out.println(Messages.endGame);
            updateMoney(-currentBet);
            return true;
        } else if (rules.hasUserWin(firstThrowValue, secondThrowValue)) {
            System.out.println(Messages.congratulations);
            updateMoney(currentBet);
            return true;
        } else {
            System.out.println(Messages.throwAgain);
            return false;
        }
    }



    private void updateMoney(double amount) {
        player.updateMoney(amount);
        currentBet = 0;
        System.out.println(Messages.currentMoney(player.getMoney()));
    }

    class GameSetup {
        private void init() {
            final int userType = menu.requestUserType();
            final String userName = menu.requestUserName();


            if (userType == menu.BASE) {
                player = new BasePlayer(userName);
            } else {
                player = new RichPlayer(userName);
            }
        }
    }
}
