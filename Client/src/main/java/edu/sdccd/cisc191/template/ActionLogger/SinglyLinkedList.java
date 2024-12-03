package edu.sdccd.cisc191.template.ActionLogger;

import edu.sdccd.cisc191.template.GameTimer.Timer;

/**
 * Responsible for handling the SinglyLinkedList methods such as adding a node, getting the size, and clearing the list.
 * @param <T> A generic Java placeholder to handle different types of objects.
 */
public class SinglyLinkedList<T>
{
    Node<T> head;

    /**
     * Adds a new node in front of the head.
     * @param data input data such as a String to log the action.
     */
    public void add(T data)
    {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    /**
     * Gets the number of nodes.
     * @return the number of nodes in the LinkedList.
     */
    public int size()
    {
        int count = 0; // Initialize count
        Node<T> current = head;
        while (current != null)
        {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
     * Resets the LinkedList by setting the head to null.
     */
    public void clear()
    {
        head = null; // Set head to null, effectively removing all nodes
    }

    /**
     * Gets the node data based on an input index.
     * @param inIndex an integer for the corresponding node number.
     * @return the data within the specified node.
     */
    public String getNodeData(int inIndex)
    {
        Node<T> current = head;
        int count = 0;
        while (count < inIndex)
        {
            count++;
            current = current.next;
        }
        return current.data.toString();
    }

    /**
     * Searches for all nodes containing the target string and returns a new linked list with the results.
     * @param target the string to search for in the linked list.
     * @return a new linked list containing all the nodes that match the target string.
     */
    public SinglyLinkedList<T> search(String target)
    {
        SinglyLinkedList<T> resultList = new SinglyLinkedList<>();  // New list to store search results
        Node<T> current = head;

        if (target == null || target.trim().isEmpty())
        {
            //System.out.println("Search target is empty. Returning empty list.");
            return null;
        }

        while (current != null) {
            if (current.data.toString().toLowerCase().contains(target.toLowerCase().trim())) {
                resultList.add(current.data);
            }
            current = current.next;
        }
        return resultList;
    }

    /**
     * A toString of the LinkedList
     * @return a String of the LinkedList
     */
    public String toString()
    {
        Node<T> current = head;
        StringBuilder builder = new StringBuilder();

         while (current != null)
         {
            builder.append(current.data).append("\n");
             current = current.next;
         }

        return builder.toString();
    }
}
