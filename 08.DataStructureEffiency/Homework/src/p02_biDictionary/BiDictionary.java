package p02_biDictionary;

import java.util.ArrayList;
import java.util.HashMap;

public class BiDictionary<K1, K2, V> {
    private HashMap<K1, ArrayList<V>> valuesByFirstKey;
    private HashMap<K2, ArrayList<V>> valuesBySecondKey;
    private HashMap<String, ArrayList<V>> valuesByBothKeys;

    public BiDictionary() {
        this.valuesByFirstKey = new HashMap<K1, ArrayList<V>>();
        this.valuesBySecondKey = new HashMap<K2, ArrayList<V>>();
        this.valuesByBothKeys = new HashMap<String, ArrayList<V>>();
    }

    public void add(K1 key1, K2 key2, V value) {
        toByFirstKey(key1, value);

        toBySecondKey(key2, value);

        toBothKeys(key1, key2, value);
    }

    private void toBothKeys(K1 key1, K2 key2, V value) {
        String keys = key1.toString() + "$" + key2.toString();
        if (!this.valuesByBothKeys.containsKey(keys)) {
            this.valuesByBothKeys.put(keys, new ArrayList<V>());
        }

        ArrayList<V> currentArrayList = this.valuesByBothKeys.get(keys);
        currentArrayList.add(value);
        this.valuesByBothKeys.put(keys, currentArrayList);
    }

    private void toBySecondKey(K2 key2, V value) {
        if (!this.valuesBySecondKey.containsKey(key2)) {
            this.valuesBySecondKey.put(key2, new ArrayList<V>());
        }

        ArrayList<V> currentArrayList = this.valuesBySecondKey.get(key2);
        currentArrayList.add(value);
        this.valuesBySecondKey.put(key2, currentArrayList);
    }

    private void toByFirstKey(K1 key1, V value) {
        if (!this.valuesByFirstKey.containsKey(key1)) {
            this.valuesByFirstKey.put(key1,new ArrayList<V>());
        }

        ArrayList<V> currentArrayList = this.valuesByFirstKey.get(key1);
        currentArrayList.add(value);
        this.valuesByFirstKey.put(key1, currentArrayList);
    }

    public ArrayList<V> findByFirstKey(K1 key) {
        if (!this.valuesByFirstKey.containsKey(key)) {
            return new ArrayList<V>();
        }

        return this.valuesByFirstKey.get(key);
    }

    public ArrayList<V> findBySecondKey(K2 key) {
        if (!this.valuesBySecondKey.containsKey(key)) {
            return new ArrayList<V>();
        }

        return this.valuesBySecondKey.get(key);
    }

    public ArrayList<V> findByBothKeys(K1 key1, K2 key2) {
        String keys = key1.toString() + "$" + key2.toString();
        if (!this.valuesByBothKeys.containsKey(keys)) {
            return new ArrayList<V>();
        }

        return this.valuesByBothKeys.get(keys);
    }

    public boolean remove(K1 key1, K2 key2) {
        String keys = key1.toString() + "$" + key2.toString();
        if (!this.valuesByBothKeys.containsKey(keys))
            return false;

        removeFromAllHashMaps(key1, key2, keys);

        return true;
    }

    private void removeFromAllHashMaps(K1 key1, K2 key2, String keys) {
        ArrayList<V> valuesToBeRemoved = this.valuesByBothKeys.get(keys);
        ArrayList<V> valuesByFirstKey = this.valuesByFirstKey.get(key1);
        ArrayList<V> valuesBySecondKey = this.valuesBySecondKey.get(key2);

        valuesByFirstKey.removeAll(valuesToBeRemoved);
        valuesBySecondKey.removeAll(valuesToBeRemoved);

        this.valuesByFirstKey.put(key1, valuesByFirstKey);
        this.valuesBySecondKey.put(key2, valuesBySecondKey);
        this.valuesByBothKeys.remove(keys);
    }
}
