package p01_hashTableWithChaining;

public class KeyValue<TKey, TValue> {
    private TKey key;

    private TValue value;

    //------------------------------------------------------------------
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
    //------------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        KeyValue<TKey, TValue> element = (KeyValue<TKey, TValue>)obj;

        return this.key.equals(element.key) && this.value.equals(element.value);
    }

    @Override
    public int hashCode() {
        return CombineHashCodes(this.key.hashCode(), this.value.hashCode());
    }

    private int CombineHashCodes(int h1, int h2)
    {
        return ((h1 << 5) + h1) ^ h2;
    }

    @Override
    public String toString() {
        return "["+ this.key + " -> " + this.value + "]";
    }
}
