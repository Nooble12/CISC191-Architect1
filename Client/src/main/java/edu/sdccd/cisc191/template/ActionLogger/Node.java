package edu.sdccd.cisc191.template.ActionLogger;

/**
 * A generic class that handles multiple types of objects for action logging.
 * @param <T> generic T for data type.
 */
class Node<T>
{
    T data; // Action data
    Node<T> next; // Pointer to the next node

    Node(T data) {
        this.data = data;
        this.next = null; // Initialize next as null
    }
}
