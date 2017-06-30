package p02.interfaces;

import p02.models.Minion;

public interface IMinion extends Comparable<Minion> {

    int getId();

    int getX();

    int getHealth();
}
