/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignments.Assignment_2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author Bhargav Patel
 */
public class ArrayList<E> implements List<E> {

    private class ArrayIterator implements Iterator<E> {

        private int j = 0; // index of the next element to report
        private boolean removable = false; // can remove be called at this time?

        /**
         * Tests whether the iterator has a next object.
         *
         * @return true if there are further objects, false otherwise
         */
        public boolean hasNext() {
            return j < size;
        } // size is field of outer instance

        /**
         * Returns the next object in the iterator.
         *
         * @return next object
         * @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j == size) {
                throw new NoSuchElementException("No next element");
            }
            removable = true; // this element can subsequently be removed
            return data[j++]; // post-increment j, so it is ready for future call to next
        }

        /**
         * Removes the element returned by most recent call to next.
         *
         * @throws IllegalStateException if next has not yet been called
         * @throws IllegalStateException if remove was already called since
         * recent next
         */
        public void remove() throws IllegalStateException {
            if (!removable) {
                throw new IllegalStateException("nothing to remove");
            }
            ArrayList.this.remove(j - 1); // that was the last one returned
            j--; // next element has shifted one cell to the left
            removable = false; // do not allow remove again until next is called
        }
    } //------------ end of nested ArrayIterator class ------------

    /**
     * Returns an iterator of the elements stored in the list.
     */
    public Iterator<E> iterator() {
        return new ArrayIterator(); // create a new instance of the inner class
    }

    public static final int CAPACITY = 4; // default array capacity
    private E[] data; // generic array used for storage
    private int size = 0; // current number of elements
    // constructors

    public ArrayList() {
        this(CAPACITY);
    } // constructs list with default capacity

    public ArrayList(int capacity) { // constructs list with given capacity
        data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
    }

    /**
     * One can access the size of the list
     * @return integer size 
     */
    public int size() {
        return size;
    }

   /**
    * One can know of the list is Empty or not.
    * @return boolean
    */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the element at index i.
     */
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    /**
     * Replaces the element at index i with e, and returns the replaced element.
     */
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    /**
     * Inserts element e to be at index i, shifting all subsequent elements
     * later.
     */
    public void add(int i, E e) throws IndexOutOfBoundsException {

        checkIndex(i, size + 1);
        if (size == data.length) // not enough capacity
        {
            resize(2 * data.length);
        }
        for (int k = size - 1; k >= i; k--) {
            data[k + 1] = data[k];
        }
        data[i] = e; // ready to place the new element
        size++;

    }

    public void add(E e) throws IndexOutOfBoundsException {

        if (size == data.length) // not enough capacity
        {
            resize(2 * data.length);
        }
        for (int k = size - 1; k >= 0; k--) {
            data[k + 1] = data[k];
        }
        data[0] = e; // ready to place the new element
        size++;

    }

    /**
     * Removes/returns the element at index i, shifting subsequent elements
     * earlier.
     */
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++) {
            data[k] = data[k + 1];
        }
        data[size - 1] = null; // help garbage collection
        size--;
        return temp;
    }
    // utility method

    /**
     * Checks whether the given index is in the range [0, n−1].
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }

    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity]; // safe cast; compiler may give warning
        for (int k = 0; k < size; k++) {
            temp[k] = data[k];
        }
        data = temp; // start using the new array
    }

    @Override
    /**
     * this method overrides the equal method.
     */
    public boolean equals(Object other) {
        boolean check = false;
        if (other == null) {
            return check;
        }
        if (!(other instanceof ArrayList)) {
            return check;
        }
        ArrayList otherList = (ArrayList) other;

        if (!(this.size() == otherList.size())) {
            return check;
        } else {
            for (int i = 0; i < otherList.size(); i++) {
                if (!(this.get(i).equals(otherList.get(i)))) {
                    check = false;
                    return check;
                } else {
                    check = true;
                }
            }
        }
        return check;
    }

    @Override
    /**
     * This overrides the toString method of ArrayLists.
     */
    public String toString() {
        String result = "[" + get(0).toString();
        int i = 1;
        while (i < size()) {
            result += ", " + get(i).toString();
            i++;
        }
        return result + "]";
    }
}
