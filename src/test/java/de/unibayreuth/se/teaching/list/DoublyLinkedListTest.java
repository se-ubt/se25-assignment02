package de.unibayreuth.se.teaching.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    private DoublyLinkedList list;

    @BeforeEach
    void setUp() {
        list = new DoublyLinkedList();
    }

    @Test
    void testAppendAndLength() {
        assertEquals(0, list.getLength());

        list.append(0.9);
        assertEquals(1, list.getLength());
        assertEquals(0.9, list.getBegin().getValue());
        assertNull(list.getBegin().getPrev());
        assertNull(list.getBegin().getNext());
        assertEquals(0.9, list.getEnd().getValue());
        assertNull(list.getEnd().getPrev());
        assertNull(list.getEnd().getNext());

        list.append(0.5);
        assertEquals(2, list.getLength());
        assertEquals(0.9, list.getBegin().getValue());
        assertNull(list.getBegin().getPrev());
        assertEquals(0.5, list.getBegin().getNext().getValue());
        assertEquals(0.5, list.getEnd().getValue());
        assertEquals(0.9, list.getEnd().getPrev().getValue());
        assertNull(list.getEnd().getNext());
        testBeginEndPointers(list);

        list.append(0.4);
        assertEquals(3, list.getLength());
        assertEquals(0.9, list.getBegin().getValue());
        assertNull(list.getBegin().getPrev());
        assertEquals(0.5, list.getBegin().getNext().getValue());
        assertEquals(0.4, list.getEnd().getValue());
        assertEquals(0.5, list.getEnd().getPrev().getValue());
        assertNull(list.getEnd().getNext());
        testBeginEndPointers(list);
    }

    @Test
    void testAppendElementFromExistingList() {
        assertEquals(0, list.getLength());

        list.append(new double[]{0.9, 0.5, 0.4});

        DoublyLinkedList list2 = new DoublyLinkedList();
        list2.append(list.getBegin().getNext());
        assertNull(list2.getBegin().getPrev());
        assertNull(list2.getBegin().getNext());
    }

    @Test
    void testToArray() {
        assertArrayEquals(new double[]{}, list.asArray());
        list.append(0.9);
        list.append(0.5);
        list.append(0.4);
        assertArrayEquals(new double[]{0.9, 0.5, 0.4}, list.asArray());
    }

    @Test
    void testEmpty() {
        assertTrue(list.isEmpty());
        list.append(0.9);
        list.append(0.5);
        list.append(0.4);
        assertFalse(list.isEmpty());
        list.empty();
        assertEquals(0, list.getLength());
        assertTrue(list.isEmpty());
        assertArrayEquals(new double[]{}, list.asArray());
    }

    @Test
    void testAppendArray() {
        list.append(new double[]{0.9, 0.5, 0.4});
        assertArrayEquals(new double[]{0.9, 0.5, 0.4}, list.asArray());
    }

    @Test
    void testAppendList() {
        DoublyLinkedList otherList = new DoublyLinkedList();
        otherList.append(new double[]{0.9, 0.5, 0.4});

        // check if append of one element does not append whole list
        list.append(otherList.getBegin());
        assertEquals(list.getLength(), 1);
        assertEquals(list.getBegin().getValue(), 0.9);
        assertNull(list.getBegin().getPrev());
        assertNull(list.getBegin().getNext());

        list.empty();
        otherList.empty();
        otherList.append(new double[]{0.9, 0.5, 0.4});
        list.append(otherList);
        assertArrayEquals(new double[]{0.9, 0.5, 0.4}, list.asArray());
    }

    @Test
    void testInsert() {
        list.append(new double[]{0.2, 0.4, 0.5, 0.8});
        list.insert(new DoublyLinkedList.Element(0.1));
        assertArrayEquals(new double[]{0.1, 0.2, 0.4, 0.5, 0.8}, list.asArray());
        testBeginEndPointers(list);
        list.insert(new DoublyLinkedList.Element(0.9));
        assertArrayEquals(new double[]{0.1, 0.2, 0.4, 0.5, 0.8, 0.9}, list.asArray());
        testBeginEndPointers(list);
        list.insert(new DoublyLinkedList.Element(0.4));
        assertArrayEquals(new double[]{0.1, 0.2, 0.4, 0.4, 0.5, 0.8, 0.9}, list.asArray());
        testBeginEndPointers(list);
    }

    /**
     * Helper method to test begin and end pointers of a doubly linked list
     * @param list List to test
     */
    private static void testBeginEndPointers(DoublyLinkedList list) {
        assertSame(list.getBegin().getNext().getPrev(), list.getBegin());
        assertSame(list.getEnd().getPrev().getNext(), list.getEnd());
    }

}
