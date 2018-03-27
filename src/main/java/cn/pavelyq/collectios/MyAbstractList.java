package cn.pavelyq.collectios;

import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Pavel on 2018/3/27.
 */
public abstract class MyAbstractList<E> extends MyAbstractCollection<E> implements MyList<E> {

    protected MyAbstractList() {

    }

    @Override
    public boolean add(E e) {
        add(size(), e);
        return true;
    }

    /**
     * This implementation always throws an UnsupportedOperationException
     */
    @Override
    public void add(int index, E e) {
        throw new UnsupportedOperationException();
    }

    abstract public E get(int index);

    @Override
    public E set(int index, E e) {
        throw new UnsupportedOperationException();
    }

    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        int size = size();
        for (int i = 0; i < size; i++) {
            E e = get(i);
            if (o == null) {
                if (e == null) {
                    return i;
                }
            } else {
                if (o.equals(e)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int size = size();
        for (int i = size-1; i >= 0; i--) {
            E e = get(i);
            if (o == null) {
                if (e == null) {
                    return i;
                }
            } else {
                if (o.equals(e)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private class MyItr implements Iterator<E>{
        /**
         * 游标 当前扫描的元素位置
         */
        private int corsor = 0;

        /**
         *
         */
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

    private class MyListItr extends  MyItr implements ListIterator<E>{

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }
}
