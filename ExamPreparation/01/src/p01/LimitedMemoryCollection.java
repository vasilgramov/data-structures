package p01;

import java.util.*;

public class LimitedMemoryCollection<K, V> implements ILimitedMemoryCollection<K, V> {

    private Deque<Pair<K, V>> recordsInOrder;
    private Map<K, Pair<K, V>> records;

    private int capacity;

    public LimitedMemoryCollection(int capacity) {
        this.recordsInOrder = new LinkedList<>();
        this.records = new LinkedHashMap<>(capacity * 2);
        this.capacity = capacity;
    }

    @Override
    public V get(K key) {
        if (!this.records.containsKey(key)) {
            throw new IllegalArgumentException("Key not present in collection");
        }

        Pair<K, V> pair = this.records.get(key);
        this.UpdateToLatest(pair);

        return pair.getValue();
    }

    @Override
    public void set(K key, V value) {
        Pair<K, V> linkedListNode;
        if (this.records.containsKey(key)) {
            linkedListNode = this.records.get(key);
            linkedListNode.setValue(value);
            this.UpdateToLatest(linkedListNode);
        } else {
            Pair<K, V> pair = new Pair<>(key, value);
            if (this.getCount() == this.getCapacity()) {
                K firstKey = this.recordsInOrder.getFirst().getKey();
                this.records.remove(firstKey);
                this.recordsInOrder.removeFirst();
            }

            this.recordsInOrder.addLast(pair);
            this.records.put(key, pair);
        }
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public int getCount() {
        return this.recordsInOrder.size();
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return this.recordsInOrder.descendingIterator();
    }

    private void UpdateToLatest(Pair<K, V> node) {
        this.recordsInOrder.remove(node);
        this.recordsInOrder.addLast(node);
    }

}
