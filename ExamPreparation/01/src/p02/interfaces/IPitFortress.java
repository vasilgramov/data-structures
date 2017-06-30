package p02.interfaces;

import p02.models.Mine;
import p02.models.Minion;
import p02.models.Player;

public interface IPitFortress {

    int getPlayerCount();

    int getMinionCount();

    int getMineCount();

    void addPlayer(String name, int mineRadius);

    void addMinion(int xCoordinate);

    void setMine(String playerName, int xCoordinate, int delay, int damage);

    Iterable<Minion> reportMinions();

    Iterable<Player> top3PlayersByScore();

    Iterable<Player> min3PlayersByScore();

    Iterable<Mine> getMines();

    void playTurn();
}
