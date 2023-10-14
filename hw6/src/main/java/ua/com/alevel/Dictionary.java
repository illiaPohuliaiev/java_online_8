package ua.com.alevel;

public class Dictionary<K, V> {
    private Object[] keys;
    private Object[] values;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Dictionary() {
        keys = new Object[DEFAULT_CAPACITY];
        values = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    public V get(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return (V) values[i];
            }
        }
        return null;
    }

    public boolean put(K key, V value) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return true;
            }
        }
        ensureCapacity(size + 1);
        keys[size] = key;
        values[size] = value;
        size++;
        return true;
    }

    public boolean remove(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                for (int j = i; j < size - 1; j++) {
                    keys[j] = keys[j + 1];
                    values[j] = values[j + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }
    public boolean clear() {
        keys = new Object[DEFAULT_CAPACITY];
        values = new Object[DEFAULT_CAPACITY];
        size = 0;
        return true;
    }
    public boolean putAll(Dictionary<K, V> otherDictionary) {
        boolean modified = false;
        for (int i = 0; i < otherDictionary.size(); i++) {
            K key = (K) otherDictionary.keys[i];
            V value = (V) otherDictionary.values[i];
            if (!containsKey(key) || !get(key).equals(value)) {
                put(key, value);
                modified = true;
            }
        }
        return modified;
    }

    public Object[] keySet() {
        Object[] keyArray = new Object[size];
        System.arraycopy(keys, 0, keyArray, 0, size);
        return keyArray;
    }

    public Object[] values() {
        Object[] valueArray = new Object[size];
        System.arraycopy(values, 0, valueArray, 0, size);
        return valueArray;
    }
    private Object[] resizeArray(Object[] array, int newSize) {
        Object[] newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, Math.min(array.length, newSize));
        return newArray;
    }
    private void ensureCapacity(int capacity) {
        if (capacity > keys.length) {
            int newCapacity = Math.max(capacity, keys.length * 2);
            keys = resizeArray(keys, newCapacity);
            values = resizeArray(values, newCapacity);
        }
    }
}