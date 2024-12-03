package edu.sdccd.cisc191.template.ActionLogger;
/**
 * Used to create an Action object that will encapsulate the player's action and the time in which they committed the action.
 */
public class Action
{
    private String action;
    private String time;

    /**
     * Sets the action and time.
     * @param inAction the player's action.
     * @param inTime the recorded time which the played committed the action.
     */
    public Action(String inAction, String inTime)
    {
        action = inAction;
        time = inTime;
    }

    /**
     * @return the time and player's action.
     */
    public String toString()
    {
        return time + " " + action;
    }
}
