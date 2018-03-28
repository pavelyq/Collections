package cn.pavelyq.collectios.map;

import java.util.*;

/**
 * Created by Pavel on 2018/3/28.
 */
public abstract class MyAbstractMap<K, V> implements Map<K, V> {
    protected MyAbstractMap() {
    }


    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 使用迭代器去迭代key  O(n)
     *
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(Object key) {
        Iterator<Entry<K, V>> iterator = entrySet().iterator();
        if (key == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null)
                    return true;
            }
        } else {
            while (iterator.hasNext()) {
                if (key.equals(iterator.next().getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Iterator<Entry<K, V>> iterator = entrySet().iterator();
        if (value == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (value.equals(iterator.next().getValue())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        Iterator<Entry<K, V>> iterator = entrySet().iterator();
        if (key == null) {
            while (iterator.hasNext()) {
                Entry<K, V> next = iterator.next();
                if (next.getKey() == null)
                    return next.getValue();
            }
        } else {
            while (iterator.hasNext()) {
                Entry<K, V> next = iterator.next();
                if (key.equals(next.getKey()))
                    return next.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(Object key) {
        Iterator<Entry<K, V>> iterator = entrySet().iterator();
        if (key == null) {
            while (iterator.hasNext()) {
                Entry<K, V> next = iterator.next();
                if (next.getKey() == null) {
                    iterator.remove();
                    return next.getValue();
                }

            }
        } else {
            while (iterator.hasNext()) {
                Entry<K, V> next = iterator.next();
                if (key.equals(next.getKey())) {
                    iterator.remove();
                    return next.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Entry<? extends K, ? extends V>> entries = m.entrySet();
        for (Entry<? extends K, ? extends V> entry : entries) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        entrySet().clear();
    }

    transient Set<K> keySet;
    transient Collection<V> values;

    @Override
    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks == null) {
            ks = new AbstractSet<K>() {
                @Override
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        private Iterator<Entry<K,V>> iterator = entrySet().iterator();

                        @Override
                        public boolean hasNext() {
                            return iterator().hasNext();
                        }

                        @Override
                        public K next() {
                            return iterator().next();
                        }
                    };
                }

                @Override
                public int size() {
                    return MyAbstractMap.this.size();
                }
            };
        }
        return ks;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public static void main(String[] args) {

    }
}
