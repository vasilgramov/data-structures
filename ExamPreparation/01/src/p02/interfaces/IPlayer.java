package p02.interfaces;

import p02.models.Player;

public interface IPlayer extends Comparable<Player> {

    String getName();

    int getRadius();

    int getScore();
}
