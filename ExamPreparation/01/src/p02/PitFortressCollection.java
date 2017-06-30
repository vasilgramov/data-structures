package p02;

import p02.interfaces.IPitFortress;
import p02.models.Mine;
import p02.models.Minion;
import p02.models.Player;

import java.util.*;
import java.util.stream.Collectors;

public class PitFortressCollection implements IPitFortress {

    private int minionCounter;
    private int mineCounter;
    private Map<String, Player> playersByName;
    private Set<Player> playersByScore;
    private Map<Integer,List<Minion>> minionsByCoordinates;
    private Set<Mine> minesByDelay;

    public PitFortressCollection() {
        this.minionCounter = 1;
        this.mineCounter = 1;
        this.playersByName = new LinkedHashMap<>();
        this.playersByScore = new TreeSet<>();
        this.minionsByCoordinates = new TreeMap<>();
        this.minesByDelay = new TreeSet<>();
    }

    @Override
    public int getPlayerCount() {
        return this.playersByName.size();
    }

    @Override
    public int getMinionCount() {
        return this.minionCounter;
    }

    @Override
    public int getMineCount() {
        return this.mineCounter;
    }

    @Override
    public void addPlayer(String name, int mineRadius) {
        if (this.playersByName.containsKey(name)) {
            throw new IllegalArgumentException("Player already exists!");
        }

        if (mineRadius < 0)
        {
            throw new IllegalArgumentException("Radius cannot be negative!");
        }

        Player player = new Player(mineRadius, name);
        this.playersByScore.add(player);
        this.playersByName.put(name, player);
    }

    @Override
    public void addMinion(int xCoordinate) {
        if (xCoordinate < 0 || xCoordinate > 1000000)
        {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        Minion minion = new Minion(this.minionCounter++, xCoordinate);

        if (!this.minionsByCoordinates.containsKey(xCoordinate)) {
            this.minionsByCoordinates.put(xCoordinate,new ArrayList<>());
        }

        List<Minion> minions = this.minionsByCoordinates.get(xCoordinate);
        minions.add(minion);
        this.minionsByCoordinates.put(xCoordinate,minions);
    }

    @Override
    public void setMine(String playerName, int xCoordinate, int delay, int damage) {
        if (!this.playersByName.containsKey(playerName))
        {
            throw new IllegalArgumentException("No such player.");
        }

        if (xCoordinate < 0 || xCoordinate > 1000000)
        {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        if (delay < 1 || delay > 10000)
        {
            throw new IllegalArgumentException("Incorrect delay!");
        }

        if (damage < 0 || damage > 100)
        {
            throw new IllegalArgumentException("Incorrect damage!");
        }

        Player player = this.playersByName.get(playerName);
        Mine mine = new Mine(this.mineCounter++, delay, xCoordinate, player, damage);
        this.minesByDelay.add(mine);
    }

    @Override
    public Iterable<Minion> reportMinions() {
        return null;
    }

    @Override
    public Iterable<Player> top3PlayersByScore() {
        if (this.playersByName.size() < 3)
        {
            throw new IllegalArgumentException("Not enough players!");
        }

        List<Player> collect = this.playersByScore.stream().limit(3).collect(Collectors.toList());
        Collections.reverse(collect);

        return collect;
    }

    @Override
    public Iterable<Player> min3PlayersByScore() {
        return null;
    }

    @Override
    public Iterable<Mine> getMines() {
        return null;
    }

    @Override
    public void playTurn() {
    }
}
