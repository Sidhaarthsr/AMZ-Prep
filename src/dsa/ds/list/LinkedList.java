package dsa.ds.list;

public class LinkedList {

    Node head;
    Node tail;

    /**
     * Insert value at the end of a LinkedList
     * 
     * @param value value of the node to be inserted
     */
    public void insert(final int value) {
        final Node node = new Node(value);

        if (head == null) {
            head = node;
            return;
        }
        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = node;
        tail = node;
    }

    /**
     * Removes the last node of the list
     * 
     * @return the last node that got deleted
     */
    public Node removeLastNode() {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Node secondLast = head;
        while (secondLast.next.next != null) {
            secondLast = secondLast.next;
        }
        Node lastElement = secondLast.next;
        secondLast.next = null;
        return lastElement;
    }

    /**
     * Deletes the first matching node if it's value matches the given value
     * 
     * @return the node that got deleted
     */
    public Node deleteByValue(final int value) {
        Node deletedNode = null;
        if (head == null) {
            return null;
        }
        if (head.value == value) {
            deletedNode = head;
            head = head.next;
            return head;
        }
        Node currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.next.value == value) {
                deletedNode = currentNode.next;
                currentNode.next = currentNode.next.next;
            }
        }
        return deletedNode;
    }

    static class Node {

        final int value;
        Node next;

        public Node(final int value) {
            this.value = value;
        }
    }

}
