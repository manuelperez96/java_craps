package game;

import player.Player;
import util.Utils;

/**
 * This class represent rules for tha game. For example, constants to determinate what numbers
 * win or lose.
 */
public class GameRule {
    final private static int CRAP = 7;
    final private int[] WIN_THROWS = {CRAP, 11};
    final private int[] LOSE_THROWS = {2, 3, 12};
    final private double MIN_BET = 20;

    public boolean canPlayerPlay(Player player) {
        return player.getMoney() >= MIN_BET;
    }

    public double minBet() {
        return MIN_BET;
    }

    public boolean hasUserWinInFirstThrow(int value) {
        return Utils.isIn(WIN_THROWS, value);
    }

    public boolean hasUserLooseInFirstThrow(int value) {
        return Utils.isIn(LOSE_THROWS, value);
    }

    public boolean hasUserLose(int value) {
       return value == CRAP;
    }

    public boolean hasUserWin(int firstThrow, int secondThrow) {
        return firstThrow == secondThrow;
    }

    public boolean canUserDoBet(Player player, double currentBet) {
        return player.getMoney() > currentBet;
    }
}
