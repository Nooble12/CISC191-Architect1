package edu.sdccd.cisc191.template.ActionLogger;

import edu.sdccd.cisc191.template.GameTimer.Timer;

/**
 * When a player does an action, the ActionLogger class will store their action in a LinkedList.
 */
public class ActionLogger
{
    private static SinglyLinkedList<String> actionLog = new SinglyLinkedList<>();

    /**
     * Adds the player's action to the list.
     * @param action the player's action.
     */
    public void logAction(String action)
    {
        Timer currentTime = Timer.getInstance();
        Action playerAction = new Action(action, currentTime.toString());
        actionLog.add(playerAction.toString());
        checkAndClearIfNeeded();
    }

    /**
     * Clears the LinkedList when there are more than 10 actions.
     */
    public void checkAndClearIfNeeded()
    {
        if (actionLog.size() > 10)
        { // Check if the size is greater than 10
            actionLog.clear(); // Clear the logger
            System.out.println("Action log cleared because it exceeded 10 actions.");
        }
    }

    /**
     * @param index the corresponding node.
     * @return the data within the node at the specified index.
     */
    public String getActionLogAtNode(int index)
    {
        return actionLog.getNodeData(index);
    }

    /**
     * @return the LinkedList which contains the player's last 10 actions.
     */
    public String getActionLog()
    {
        return actionLog.toString();
    }

    /**
     * Searches the LinkedList and finds relevant logs based on the input.
     * @param inString the user's input string from the search bar.
     * @return a new LinkedList with the search results to be displayed.
     */
    public SinglyLinkedList<String> SearchActionLog(String inString)
    {
        SinglyLinkedList<String> searchResults = actionLog.search(inString);
        return searchResults;
    }
}
