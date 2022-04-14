package player;

/**
 * This class represent a base-class for players.
 */
abstract class Player {
    final protected String name;
    protected double money;

    protected Player(String name, double money) {
        this.name = name;
        this.money = money;
    }
}
