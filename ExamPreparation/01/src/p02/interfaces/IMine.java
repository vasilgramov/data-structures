package p02.interfaces;

import p02.models.Mine;
import p02.models.Player;

public interface IMine extends Comparable<Mine> {

    int getId();

    int getDelay();

    int getDamage();

    int getX();

    Player getPlayer();
}
