package p01_hashTableWithChaining;

import java.util.*;

public class HashTable<TKey, TValue> implements Iterable<KeyValue<TKey, TValue>> {
    private static final int INITIAL_CAPACITY = 16;

    private LinkedList<KeyValue<TKey, TValue>>[] slots;

    private int count;

    //  for the custom iterator
//    private int currentIndex = 0;
//
//    private int hasReturned = 0;
//
//    private int currentIndexInLinkedList = 0;
    //------------------------------------------------------------

    private HashSet<KeyValue<TKey, TValue>> allElements = new HashSet<>();

    //---------------------------------------------------------------
    public HashTable() {
        this.slots = new LinkedList[INITIAL_CAPACITY];
        this.setCount(0);
    }

    public HashTable(int capacity) {
        this.slots = new LinkedList[capacity];
        this.setCount(0);
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }
    //---------------------------------------------------------------

    public void put(TKey key, TValue value) {
        growIfNeeded();

        KeyValue<TKey, TValue> elementToAdd = new KeyValue<TKey, TValue>(key, value);

        int slotIndex = getSlotIndex(key);      // get hashcode from the key

        if (this.slots[slotIndex] == null) {    // if current slot is empty
            this.slots[slotIndex] = new LinkedList<>();
            this.slots[slotIndex].addFirst(elementToAdd);
            allElements.add(elementToAdd);
        } else {
            boolean doesExist = false;

            for (KeyValue<TKey, TValue> element : this.slots[slotIndex]) {
                if (elementToAdd.getKey().equals(element.getKey())) {
                    element.setValue(elementToAdd.getValue());
                    doesExist = true;
                    this.setCount(getCount() - 1);
                    break;
                }
            }

            if (!doesExist) {
                this.slots[slotIndex].addLast(elementToAdd);
            }
        }

        this.setCount(this.getCount() + 1);
    }

    public TValue get(Object key) {
        TKey keyAsTKey = (TKey)key;

        int slotIndex = getSlotIndex(keyAsTKey);
        if (slots[slotIndex] == null) {
            return null;
        }

        for (KeyValue<TKey, TValue> element : slots[slotIndex]) {
            if (element.getKey().equals(keyAsTKey)) {
                return element.getValue();
            }
        }

        return null;
    }

    public void clear() {
        this.slots = new LinkedList[INITIAL_CAPACITY];
        this.allElements.clear();
        this.setCount(0);
    }

    public boolean containsKey(Object key) {
        TKey keyAsTKey = (TKey)key;

        int slotIndex = getSlotIndex(keyAsTKey);
        if (slots[slotIndex] == null) {
            return false;
        }

        for (KeyValue<TKey, TValue> element : slots[slotIndex]) {
            if (element.getKey().equals(keyAsTKey)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsValue(Object value) {
        TValue valueAsTValue = (TValue)value;

        for (KeyValue keyValue : this.allElements) {
            if (keyValue.getValue().equals(valueAsTValue)) {
                return true;
            }
        }

        return false;
    }

    public TKey[] keys() {
        TKey[] keys = (TKey[])new Object[this.allElements.size()];

        int index = 0;
        for (KeyValue<TKey, TValue> keyValue : this.allElements) {
            keys[index] = keyValue.getKey();
            index++;
        }

        return keys;
    }

    public TValue[] values() {
        TValue[] values = (TValue[])new Object[this.allElements.size()];

        int index = 0;
        for (KeyValue<TKey, TValue> keyValue : this.allElements) {
            values[index] = keyValue.getValue();
            index++;
        }

        return values;
    }

    private void growIfNeeded() {
        // TODO: grow if slots are filled 60-70% ... + re-hashing

        if (checkFillFactor()) {
            this.slots = new LinkedList[this.slots.length * 2];     // re-hashing
            for (KeyValue<TKey, TValue> keyValue : this.allElements) {
                int slotIndex = getSlotIndex(keyValue.getKey());

                if (this.slots[slotIndex] == null) {
                    this.slots[slotIndex] = new LinkedList<>();
                    this.slots[slotIndex].addFirst(keyValue);
                } else {
                    boolean doesExist = false;

                    for (KeyValue<TKey, TValue> element : this.slots[slotIndex]) {
                        if (keyValue.getKey().equals(element.getKey())) {
                            element.setValue(keyValue.getValue());
                            doesExist = true;
                            break;
                        }
                    }

                    if (!doesExist) {
                        this.slots[slotIndex].addLast(keyValue);
                    }
                }
            }
        }
    }

    private boolean checkFillFactor() { // if slots are 60-70% fully
        return this.getCount() >= this.slots.length * 70 / 100;
    }

    private int getSlotIndex(TKey key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }

    @Override
    public Iterator<KeyValue<TKey, TValue>> iterator() {
        return this.allElements.iterator();
//        this.hasReturned = 0;
//        this.currentIndex = 0;
//        this.currentIndexInLinkedList = -1;
//        return new Iterator<KeyValue<TKey, TValue>>() {
//            @Override
//            public boolean hasNext() {
//                return hasReturned < getCount();
//            }
//
//            @Override
//            public KeyValue<TKey, TValue> next() {
//                while (slots[currentIndex] == null) {
//                    currentIndex++;
//                }
//
//                hasReturned++;
//                currentIndexInLinkedList++;
//
//                if (slots[currentIndex].size() > currentIndexInLinkedList) {
//                    return slots[currentIndex].get(currentIndexInLinkedList);
//                } else {
//                    currentIndexInLinkedList = 0;
//                    currentIndex++;
//
//                    while (slots[currentIndex] == null) {
//                        currentIndex++;
//                    }
//
//                    return slots[currentIndex].get(currentIndexInLinkedList);
//                }
//            }
//        };
    }
}
