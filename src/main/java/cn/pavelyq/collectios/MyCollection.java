package cn.pavelyq.collectios;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Pavel on 2018/3/27.
 */
public interface MyCollection<T> extends Iterable<T> {

    /**
     * 目前所包含元素的多少
     *
     * @return 元素的多少
     */
    int size();

    /**
     * 判断集合是否为空
     *
     * @return 是否为空
     */
    boolean isEmpty();

    /**
     * 是否包含某元素
     *
     * @param object 指定元素
     * @return 是否包含
     */
    boolean contains(Object object);

    /**
     * @return 返回迭代器
     */
    Iterator<T> iterator();

    /**
     * 添加元素
     *
     * @param e 添加的元素
     * @return 返回是否添加成功
     */
    boolean add(T e);

    /**
     * 移除元素
     */
    boolean remove(Object o);

    /**
     * 转换成数组
     *
     * @return 返回包含元素的数组 数组不可变
     */
    Object[] toArray();

    boolean containsAll(Collection<? extends T> collection);

    boolean removeAll(Collection<?> collection);

    void clear();

    boolean equal(Object o);

    int hashCode();


}
