package com.github.pathfind1103.data_structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<E>
//        implements List<E>
{

    private Object[] elementData;
    private int size; //количество элементов (занятых ячеек)
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {}; //ленивая реализация
    private static final Object[] EMPTY_ELEMENTDATA = {};

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
            this.size = 0;
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public MyArrayList(Collection<? extends E> c) {
        this.elementData = new Object[c.size()];
        int i = 0;
        for (E item: c) {
            this.elementData[i++] = item;
        }
        this.size = i;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        if (size == 0) return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(elementData[i]);
        }

        sb.append("]");
        return sb.toString();
    }

    public void add(E e) {
        if (elementData.length == size) {
            elementData = grow();
        }
        elementData[size] = e;
        size = size + 1;
    }

    public Object[] grow() {
        Object[] expandedElementData = new Object[(int) (size * 1.5)];
        for (int i = 0; i < size; i++) {
            expandedElementData[i] = elementData[i];
        }
        elementData = expandedElementData;
        return elementData;
    }
//
//    @Override
//    public int size() {
//        return 0;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public boolean contains(Object o) {
//        return false;
//    }
//
//    @Override
//    public Iterator<E> iterator() {
//        return null;
//    }
//
//    @Override
//    public Object[] toArray() {
//        return new Object[0];
//    }
//
//    @Override
//    public <T> T[] toArray(T[] a) {
//        return null;
//    }
//
//    @Override
//    public boolean add(E e) {
//        return false;
//    }
//
//    @Override
//    public boolean remove(Object o) {
//        return false;
//    }
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends E> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(int index, Collection<? extends E> c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public E get(int index) {
//        return null;
//    }
//
//    @Override
//    public E set(int index, E element) {
//        return null;
//    }
//
//    @Override
//    public void add(int index, E element) {
//
//    }
//
//    @Override
//    public E remove(int index) {
//        return null;
//    }
//
//    @Override
//    public int indexOf(Object o) {
//        return 0;
//    }
//
//    @Override
//    public int lastIndexOf(Object o) {
//        return 0;
//    }
//
//    @Override
//    public ListIterator<E> listIterator() {
//        return null;
//    }
//
//    @Override
//    public ListIterator<E> listIterator(int index) {
//        return null;
//    }
//
//    @Override
//    public List<E> subList(int fromIndex, int toIndex) {
//        return null;
//    }
}
