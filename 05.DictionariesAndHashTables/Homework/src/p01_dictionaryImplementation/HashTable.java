package p01_dictionaryImplementation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>>{
    private static final int INITIAL_CAPACITY = 16;

    private LinkedList<KeyValue<TKey, TValue>>[] slots;

    private int count;

    private HashSet<KeyValue<TKey, TValue>> allElements;

    //-----------------------------------------------------
    public HashTable() {
        this.slots = new LinkedList[INITIAL_CAPACITY];

        this.setCount(0);

        allElements = new HashSet<>();
    }

    public HashTable(int capacity) {
        this.slots = new LinkedList[capacity];

        this.setCount(0);

        this.allElements = new HashSet<>();
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }
    //-----------------------------------------------------

    public void put(TKey key, TValue value) {
        growIfNeeded();

        KeyValue<TKey, TValue> elementToAdd = new KeyValue(key, value);
        int slotIndex = getSlotIndex(key);

        if (slots[slotIndex] == null) {
            slots[slotIndex] = new LinkedList<>();
            slots[slotIndex].addFirst(elementToAdd);
            this.allElements.add(elementToAdd);
        } else {
            boolean doesExist = false;

            for (KeyValue<TKey, TValue> keyValue : this.slots[slotIndex]) {
                if (keyValue.getKey().equals(elementToAdd.getKey())) {
                    keyValue.setValue(elementToAdd.getValue());
                    doesExist = true;
                    this.setCount(getCount() - 1);
                    break;
                }
            }

            if (!doesExist){
                this.slots[slotIndex].addLast(elementToAdd);
                this.allElements.add(elementToAdd);
            }
        }

        setCount(getCount() + 1);
    }

    private int getSlotIndex(TKey key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }

    private void growIfNeeded() {
        // check fill factor and re-hash;
    }

    public boolean containsKey (TKey key) {
        int slotIndex = getSlotIndex(key);

        if (this.slots[slotIndex] == null) {
            return false;
        } else {
            for (KeyValue<TKey, TValue> keyValue : this.slots[slotIndex]) {
                if (keyValue.getKey().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    public TValue get(Object key) {
        TKey keyAsTKey = (TKey)key;
        int slotIndex = getSlotIndex(keyAsTKey);

        if (slots[slotIndex] == null) {
            return null;
        } else {
            for (KeyValue<TKey, TValue> keyValue : this.slots[slotIndex]) {
                if (keyValue.getKey().equals(keyAsTKey)) {
                    return keyValue.getValue();
                }
            }
        }

        return null;
    }

    public HashSet<KeyValue<TKey, TValue>> getAllElements() {
        return this.allElements;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        return this.allElements.iterator();
    }
}
