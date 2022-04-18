package player;

/**
 * This class represent a base-class for players.
 */
abstract public class Player {
    final public String name;
    private double money;

    protected Player(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void updateMoney(double money) {
        if (this.money - money < 0) throw new RuntimeException("User can not pay the bet");
        this.money += money;
    }
}
