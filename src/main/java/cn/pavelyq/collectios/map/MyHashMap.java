package cn.pavelyq.collectios.map;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Pavel on 2018/3/28.
 */
public class MyHashMap<K, V> extends MyAbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 16

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final float DEFAULT_LOAD_FACTOR = 0.75f; // 扩容因子

    static final int TREEIFY_THRESHOLD = 8;

    static final int UNTREEIFY_THRESHOLD = 6;

    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * 使用node作为链表
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        int hash;
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;

        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    Node<K, V>[] table;

    Set<Entry<K, V>> entrySet;

    int size;

    //下一次扩容门槛
    int threshold;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    // 扩容的负载因子
    final float loadFactor;

    public MyHashMap(int initCap, float loadFactor) {

        if (initCap < 0) {
            throw new IllegalArgumentException("illegal initCap " + initCap);
        }
        if (initCap > MAXIMUM_CAPACITY) {
            initCap = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("illegal loadFactor " + loadFactor);
        }
        this.threshold = tableSizeFor(initCap);
        this.loadFactor = loadFactor;

    }

    public MyHashMap(int initCap) {
        this(initCap, DEFAULT_LOAD_FACTOR);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(Object key) {
        int hash = hash(key);
        Node<K, V> kvNode = table[hash];
        Node<K, V> node = getNode(hash, key);
        return node.getValue();

    }

    int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    Node<K, V> getNode(int hash, Object k) {
        if (table != null && table.length > 0) {
            for (Node<K,V> node = table[indexFor(hash,table.length)];node!=null;node = node.next) {
                if (node.hash == hash && (node.key == k || (k!=null && k.equals(node.key)))) {
                    return node;
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        int n = 9;
        System.out.println(tableSizeFor(n));
        n = n - 1;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
    }

}
