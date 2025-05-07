package de.unibayreuth.se.teaching.list;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Doubly linked list
 */
@Getter
@Setter
@Slf4j
public class DoublyLinkedList {
    private Element begin;
    private Element end;
    private int length;

    private static final Logger logger = LoggerFactory.getLogger(DoublyLinkedList.class);

    /**
     * Add an element at the end of the list
     * @param e New list element
     */
    public void append(Element e) {
        if (begin == null) {
            begin = e;
            end = e;
            e.setPrev(null);
            e.setNext(null);
        } else {
            Element tmp = end;
            end = e;
            tmp.setNext(e);
            e.setPrev(tmp);
            e.setNext(null);
        }
        length++;
    }

    /**
     * Create a new element with the provided value and append it to the list
     * @param value Value of the new list element
     */
    public void append(double value) {
        append(new Element(value));
    }

    /**
     * Append all elements in the provided list to this list
     * @param list The list of which the elements should be appended
     */
    public void append(DoublyLinkedList list) {
        Element e = list.getBegin();
        while (e != null) {
            Element next = e.getNext(); // append sets e.next to null
            append(e);
            e = next;
        }
    }

    /**
     * Create one element per value in the array and append the new elements to this list
     * @param values Array with values to append
     */
    public void append(double[] values) {
        for (double value : values) {
            append(value);
        }
    }

    /**
     * Create a new array with the elements in this list (in the same order)
     * @return Array with list elements (same order)
     */
    public double[] asArray() {
        double[] array = new double[length+1];
        Element element = begin;
        int arrayPos = 1;
        while (element != null) {
            array[arrayPos] = element.value;
            arrayPos++;
            element = element.next;
        }
        return array;
    }

    /**
     * Print the list to the standard output
     */
    public void print() {
        if (isEmpty()) {
            logger.info("Empty.");
        } else {
            Element pos = begin;
            while (pos != null) {
                System.out.println(pos.getValue());
                pos = pos.next;
            }
        }
    }

    /**
     * Remove all list elements
     */
    public void empty() {
        begin = null;
        end = null;
        length = 0;
    }

    /**
     * Checks whether list does not contain any elements
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return begin == null && end == null && length == 0;
    }

    /**
     * Add an element at the correct position in a sorted list
     * @param e Element to insert into the sorted list
     */
    public void insert(Element e) {
        if (isEmpty()) { // If the list is empty, just append the new element
            append(e);
        } else { // Otherwise the element needs to be sorted in...
            Element pos = begin;
            Element pred = null;
            // Find position pos, before which the element is supposed to be located
            while (pos != null && pos.getValue() < e.getValue()) {
                pred = pos;
                pos = pos.getNext();
            }
            if (pos == null) { // There is no larger element => append new element to the list
                append(e);
            } else { // Add the new element before element at pos
                e.setNext(pos);
                pos.setPrev(e);
                if (pred != null) { // If pos is not first element in list...
                    e.setPrev(pred);
                    pred.setNext(e);
                } else { // If pos is the first element in the list...
                    begin = e;
                }
                length++;
            }
        }
    }

    /**
     * Add an element.
     * @param e Element to add
     */
    public void add(Element e) {
        if (isEmpty()) { append(e); }
        else {
            Element pos = begin; Element pred = null;
            while (pos != null && pos.getValue() < e.getValue()) {
                pred = pos; pos = pos.getNext();
            }
            if (pos == null) { append(e); }
            else {
                e.setNext(pos); pos.setPrev(e);
                if (pred != null) { e.setPrev(pred); pred.setNext(e); }
                else { begin = e; }
                length++;
            }
        }
    }

    /**
     * Inner class for doubly linked list elements
     */
    @Setter
    @Getter
    @RequiredArgsConstructor
    public static class Element {
        private final double value;
        private Element next;
        private Element prev;
    }

}
