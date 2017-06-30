package p02.models;

import p02.interfaces.IMine;

public class Mine implements IMine {

    private int id;
    private int delay;
    private int x;
    private Player player;
    private int damage;

    public Mine(int id, int delay, int x, Player player, int damage) {
        this.id = id;
        this.delay = delay;
        this.x = x;
        this.player = player;
        this.damage = damage;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getDelay() {
        return delay;
    }

    public void setDelay(int value) {
        this.delay = value;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public int compareTo(Mine o) {
        int result = Integer.compare(this.delay, o.getDelay());
        if (result == 0)
        {
            result = Integer.compare(this.id, o.getId());
        }

        return result;
    }
}
