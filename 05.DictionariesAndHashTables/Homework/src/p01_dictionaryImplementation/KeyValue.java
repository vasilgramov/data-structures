package p01_dictionaryImplementation;

public class KeyValue<TKey, TValue> implements Comparable<KeyValue<TKey, TValue>>{
    private TKey key;

    private TValue value;

    //-------------------------------------------------------------
    public KeyValue(TKey key, TValue value) {
        this.setKey(key);
        this.setValue(value);
    }

    public TKey getKey() {
        return key;
    }

    private void setKey(TKey key) {
        this.key = key;
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }
    //-------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return combineHashCodes(this.getKey().hashCode(), this.getValue().hashCode());
    }

    private int combineHashCodes(int h1, int h2) {
        return ((h1 << 5) + h1) ^ h2;
    }

    @Override
    public String toString() {
        return "[" + this.getKey() + "->" + this.getValue() + "]";
    }


    @Override
    public int compareTo(KeyValue<TKey, TValue> o) {
        return (this.getKey().toString())
                .compareTo(o.getKey().toString());
    }
}
