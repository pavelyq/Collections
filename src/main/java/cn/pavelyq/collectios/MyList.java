package cn.pavelyq.collectios;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Pavel on 2018/3/27.
 */
public interface MyList<T> extends MyCollection<T> {

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<T> iterator();

    Object[] toArray();

    boolean add(T o);

    boolean remove(Object o);

    @Override
    boolean containsAll(Collection<? extends T> collection);

    boolean addAll(Collection<? extends T> collection);

    boolean addAll(int index, Collection<? extends T> collection);

    boolean removeAll(Collection<?> collection);

    @Override
    void clear();

    @Override
    boolean equal(Object o);

    @Override
    int hashCode();

    T get(int index);

    T set(int index, T t);

    void add(int index, T t);

    T remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    ListIterator<T> listIterator();

    ListIterator<T> listIterator(int index);

    List<T> subList(int fromIndex, int toIndex);
}
