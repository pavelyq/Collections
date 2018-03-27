package cn.pavelyq.collectios;

import java.util.*;

/**
 * Created by Pavel on 2018/3/27.
 */
public abstract class MyAbstractCollection<E> implements MyCollection<E> {

    protected MyAbstractCollection() {

    }

    public abstract Iterator<E> iterator();

    public abstract int size();

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        Iterator<E> iterator = iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                if (iterator.next() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                if (o.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Object[] toArray() {
        Object[] objects = new Object[size()];
        Iterator<E> iterator = iterator();
        for (int i = 0; i < size(); i++) {
            if (iterator.hasNext()) {
                objects[i] = iterator.next();
            } else {
                // 元素比较少时，直接返回
                return Arrays.copyOf(objects, size());
            }
        }
        // 长度和迭代器数量不同时，继续拷贝
        return iterator.hasNext() ? finishToArray(objects, iterator) : objects;
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static <E> E[] finishToArray(E[] objects, Iterator iterator) {
        int length = objects.length;
        while (iterator.hasNext()) {
            int cap = objects.length;
            if (cap == length) {
                //扩容
                int newCap = cap + (cap >> 1) + 1;
                if (newCap - MAX_ARRAY_SIZE > 0) {
                    newCap = hugeCapacity(cap + 1);
                }
                objects = Arrays.copyOf(objects, newCap);
            }
            objects[length++] = (E) iterator.next();
        }
        //长度相等，直接返回数组。 长度不等，扩容后的长度太长，移除最后的几个
        return (length == objects.length) ? objects : Arrays.copyOf(objects, length);
    }

    private static int hugeCapacity(int minCapacity) {
        //溢出
        if (minCapacity < 0) {
            throw new OutOfMemoryError("Required array size too large");
        }

        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    public boolean remove(Object o) {
        Iterator<E> iterator = iterator();
        if (o == null) {
            while (iterator.hasNext()) {
                E next = iterator.next();
                if (next == null) {
                    iterator.remove();
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                E next = iterator.next();
                if (o.equals(next)) {
                    iterator.remove();
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<? extends E> collection) {
        for (E e : collection) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> collection) {
        boolean modified = false;
        for (E e : collection) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            iterator.remove();
        }
    }

    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        List l = new ArrayList();
        l.add("1");
        list.retainAll(l);
        System.out.println(list);

    }
}
