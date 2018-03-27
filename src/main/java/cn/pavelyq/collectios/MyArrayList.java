package cn.pavelyq.collectios;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Pavel on 2018/3/27.
 */
public class MyArrayList<T> extends MyAbstractList<T> implements MyList<T> {

    private static final Object[] DEFAULT_EMPTY = {};

    private Object[] elements;
    private int size;

    public MyArrayList(int initCap) {
        if (initCap > 0) {
            elements = new Object[initCap];
        } else if (initCap == 0) {
            this.elements = DEFAULT_EMPTY;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initCap);
        }
    }


    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public boolean equal(Object o) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }
}
