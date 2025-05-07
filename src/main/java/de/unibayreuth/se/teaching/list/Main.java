package de.unibayreuth.se.teaching.list;

/**
 * Main class for demo purposes.
 */
public class Main {
    public static void main(String[] args) {

        // Create list and add some values...
        DoublyLinkedList list = new DoublyLinkedList();
        list.append(new double[]{0.5, 4.2, 3.3, 0.9});

        System.out.println("\nThe list contains the following elements:");
        list.print();
    }
}
