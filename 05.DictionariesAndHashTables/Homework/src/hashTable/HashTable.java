package hashTable;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private int size;

    private int capacity;

    private int maxElements;

    private LinkedList<KeyValue<TKey, TValue>>[] hashTable;

    public HashTable() {
        this(DEFAULT_CAPACITY);
    }

    public HashTable(int capacity) {
        this.hashTable = new LinkedList[capacity];
        this.maxElements = (int) (capacity * LOAD_FACTOR);
        this.capacity = capacity;
    }

    public int size() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int capacity() {
        return this.capacity;
    }

    public void add(TKey key, TValue value) {
        this.checkGrowth();
        int hash = Math.abs(key.hashCode()) % this.hashTable.length;

        if (this.hashTable[hash] == null) {
            this.hashTable[hash] = new LinkedList<KeyValue<TKey, TValue>>();
        }

        for (KeyValue<TKey, TValue> keyValue : this.hashTable[hash]) {
            if (keyValue.getKey().equals(key)) {
                throw new IllegalArgumentException("Key already exists: " + key);
            }
        }

        this.hashTable[hash].addLast(new KeyValue<TKey, TValue>(key, value));
        this.setSize(this.size() + 1);
    }

    public boolean addOrReplace(TKey key, TValue value) {
        this.checkGrowth();
        int hash = Math.abs(key.hashCode()) % this.capacity();

        if (this.hashTable[hash] == null) {
            this.hashTable[hash] = new LinkedList<KeyValue<TKey, TValue>>();
        }

        for (KeyValue<TKey, TValue> kvp : this.hashTable[hash]) {
            if (kvp.getKey().equals(key)) {
                kvp.setValue(value);
                return false;
            }
        }

        KeyValue<TKey, TValue> keyValue = new KeyValue<TKey, TValue>(key, value);
        this.hashTable[hash].addLast(keyValue);
        this.setSize(this.size() + 1);
        return true;
    }

    public TValue get(TKey key) {
        KeyValue<TKey, TValue> kvp = this.find(key);
        if (kvp != null) {
            return kvp.getValue();
        }

        throw new IllegalArgumentException();
    }

    // ???????
    public boolean tryGetValue(TKey key) {
        KeyValue<TKey, TValue> kvp = this.find(key);
        return kvp != null;
    }

    public KeyValue<TKey, TValue> find(TKey key) {
        int hash = Math.abs(key.hashCode()) % this.hashTable.length;

        if (this.hashTable[hash] != null) {
            for (KeyValue<TKey, TValue> kvp : this.hashTable[hash]) {
                if (kvp.getKey().equals(key)) {
                    return kvp;
                }
            }
        }

        return null;
    }

    public boolean containsKey(TKey key) {
        KeyValue<TKey, TValue> kvp = this.find(key);
        return kvp != null;
    }

    public boolean remove(TKey key) {
        int hash = Math.abs(key.hashCode()) % this.hashTable.length;

        if (this.hashTable[hash] != null) {
            KeyValue<TKey, TValue> kvToRemove = null;
            for (KeyValue<TKey, TValue> kvp : this.hashTable[hash]) {
                if (kvp.getKey().equals(key)) {
                    kvToRemove = kvp;
                }
            }

            if (kvToRemove != null) {
                this.hashTable[hash].remove(kvToRemove);
                this.setSize(this.size() - 1);
                return true;
            }
        }

        return false;
    }

    public void clear() {
        this.hashTable = new LinkedList[DEFAULT_CAPACITY];
        this.setSize(0);
    }

    public Iterable<TKey> keys() {
        throw new UnsupportedOperationException();
    }

    public Iterable<TValue> values() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        LinkedList<KeyValue<TKey, TValue>> elements = new LinkedList<>();
        for (LinkedList<KeyValue<TKey, TValue>> keyValues : hashTable) {
            if (keyValues != null) {
                for (KeyValue<TKey, TValue> keyValue : keyValues) {
                    elements.addLast(keyValue);
                }
            }
        }

        return elements.iterator();
    }

    private void checkGrowth() {
        if (this.size() >= this.maxElements) {
            this.grow();
            this.maxElements = (int) (this.capacity * LOAD_FACTOR);
        }
    }

    private void grow() {
        HashTable<TKey, TValue> newTable = new HashTable<TKey, TValue>(this.capacity * 2);
        this.capacity *= 2;

        for (KeyValue<TKey, TValue> keyValue : this) {
            newTable.add(keyValue.getKey(), keyValue.getValue());
        }

        this.hashTable = newTable.hashTable;
        this.setSize(newTable.size);
    }
}
