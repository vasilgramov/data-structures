package p02.models;

import p02.interfaces.IMinion;

public class Minion implements IMinion {

    private static final int MINION_DEFAULT_HEALTH = 100;

    private int id;
    private int x;
    private int health;

    public Minion(int id, int x) {
        this.id = id;
        this.x = x;

        this.health = MINION_DEFAULT_HEALTH;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getHealth() {
        return health;
    }

    public void setHealth(int value) {
        this.health = value;
    }

    @Override
    public int compareTo(Minion o) {
        return Integer.compare(this.id, o.getId());
    }
}
