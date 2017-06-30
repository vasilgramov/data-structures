package p02.models;

import p02.interfaces.IPlayer;

public class Player implements IPlayer {

    private int radius;
    private String name;
    private int score;

    public Player(int radius, String name) {
        this.radius = radius;
        this.name = name;

        this.score = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score = value;
    }

    @Override
    public int compareTo(Player o) {
        int result = Integer.compare(this.score, o.getScore());
        if (result == 0)
        {
            result = this.name.compareTo(o.getName());
        }

        return result;
    }
}
