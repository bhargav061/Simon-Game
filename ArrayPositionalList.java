/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignments.Assignment_1.Assignment_2;

/**
 *
 * @author Bhargav Patel
 * 3098320
 */
/**
 * Implementation of a positional list with an array
 *
 * @author
 */
public class ArrayPositionalList<E> implements PositionalList<E> {

    private static class ArrPos<E> implements Position<E> {

        private int index;
        private E element;

        public ArrPos(E e, int i) {
            index = i;
            element = e;
        }

        private ArrPos() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public E getElement() throws IllegalStateException {
            if (index == -1) {
                throw new IllegalStateException("Position no longer valid");
            }
            return element;
        }

        public void setElement(E e) {
            element = e;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int i) {
            index = i;
        }

        public String toString() {
            return element.toString();
        }
    }   // end of nested ArrPos class

    public static final int CAPACITY = 16; // default array capacity
    private ArrPos<E>[] data; // generic array used for storage
    private int size = 0; // current number of elements
    // constructors

    public ArrayPositionalList() {
        this(CAPACITY);
    } // constructs list with default capacity

    public ArrayPositionalList(int capacity) {
// constructs list with given capacity
        data = (ArrPos<E>[]) new ArrPos[capacity]; // safe cast; compiler may give warning
    }

    /**
     * checks if the index i passed is valid or not.
     */
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }
/**
 * Checks if the position p passed belongs to ArrPos or not.
 */
    private ArrPos<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof ArrPos)) {
            throw new IllegalArgumentException("Invalid p");
        }
        ArrPos<E> pos = (ArrPos<E>) p; // safe cast
        if (pos.getElement() == null) // convention for defunct node
        {
            throw new IllegalArgumentException("p is no longer in the list");
        }
        return pos;
    
    }
 /**
  * This method update the indices of ArrPos with respect to data.
  */
    private void updateIndices() {
        for (int i = 0; i < size; i++) {
            if (i != data[i].getIndex()) {
                data[i].setIndex(i);
            }
        }
    }

    /**
     * Adds the element e of type E at the index i of the list and also shifts the other elements respectively. 
     */
    private void add(int i, E e) throws IndexOutOfBoundsException {

        checkIndex(i, size + 1);
        /* if (size == data.length) // not enough capacity
        {
            resize(2 * data.length);
        }*/
        for (int k = size - 1; k >= i; k--) {
            data[k + 1] = data[k];
        }
        data[i] = new ArrPos<>(e, i); // ready to place the new element
        size++;

    }

    /**
     * removes element from index i of the list and then returns that element.
     */
    private E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = (E) data[i];
        for (int k = i; k < size - 1; k++) {
            data[k] = data[k + 1];
        }
        data[size - 1] = null; // help garbage collection
        size--;
        return temp;
    }

    /**
     * This method is used to make list dynamic. It resizes the list with double capacity. 
     */
    protected void resize(int capacity) {
        ArrPos<E>[] temp = (ArrPos<E>[]) new ArrPos[capacity]; // safe cast; compiler may give warning
        for (int k = 0; k < size; k++) {
            temp[k + 1] = data[k];
        }
        data = temp; // start using the new array
    }

    @Override
    /**
     * Gives the size of the list.
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * Tells us if the list is Empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /**
     * gives the first element of the list.
     */
    public Position<E> first() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("The list is empty!");
        }
        return data[0];
    }

    @Override
    /**
     * Gives the last element of the list.
     */
    public Position<E> last() throws NullPointerException {
        if (isEmpty()) {
            throw new NullPointerException("The list is empty!");
        }
        return data[size - 1];
    }

    @Override
    /**
     * Returns the element before the position p.
     */
    public Position<E> before(Position<E> p) throws IllegalArgumentException {
        ArrPos<E> pos = validate(p);
        return data[pos.getIndex() - 1];
    }

    @Override
    /**
     * Returns the element after the position p.
     */
    public Position<E> after(Position<E> p) throws IllegalArgumentException {
        ArrPos<E> pos = validate(p);
        return data[pos.getIndex() + 1];
    }

    @Override
    /**
     * Adds element e of type E at the very beginning of the list.
     */
    public Position<E> addFirst(E e) {
        add(0, e);
        updateIndices();
        return data[0];
    }

    @Override
    /**
     * Adds element e of type E at the end of the list
     */
    public Position<E> addLast(E e) {
        add(size, e);
        updateIndices();
        return data[size - 1];
    }

    @Override
    /**
     * Adds element e of type E before position p.
     */
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        ArrPos<E> temp = validate(p);
        if (temp.getIndex() == 0) {
            addFirst(e);
            return data[0];
        } else {
            add(temp.getIndex() - 1, e);
            updateIndices();
            return data[temp.getIndex() - 1];
        }
    }

    @Override
    /**
     * Adds element e of type E after the position p.
     */
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        ArrPos<E> temp = validate(p);
        if (temp.getIndex() == size - 1) {
            addLast(e);
            return data[size - 1];
        } else {
            add(temp.getIndex() + 1, e);
            updateIndices();
            return data[temp.getIndex() + 1];
        }
    }

    @Override
    /**
     * replaces the element at position p with element e of type E.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        ArrPos<E> temp = validate(p);
        E answer = temp.getElement();
        temp.setElement(e);
        return answer;
    }

    @Override
    /**
     * Removes the position p.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        ArrPos<E> tmp = validate(p);
        E removed = remove(tmp.getIndex());
        updateIndices();
        return removed;
    }

    @Override
    /**
     * Overrides the toString method for the lists.
     */
    public String toString() {

        if (isEmpty()) {
            return "";
        }
        if (size() == 1) {
            return data[0].getElement().toString();
        }

        String result = "[" + data[0].getElement().toString();
        int i = 1;
        while (i < size()) {
            result += ", " + data[i].getElement().toString();
            i++;
        }
        return result + "]";
    }
}
