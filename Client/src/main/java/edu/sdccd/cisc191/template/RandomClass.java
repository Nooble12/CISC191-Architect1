package edu.sdccd.cisc191.template;
import edu.sdccd.cisc191.template.Items.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * The random class is used to throughout the game to determine random rewards, if the player wins, and to display a random string.
 * Extends the Java random class and implements its own methods.
 * @author Tim Tran
 */
public class RandomClass extends Random
{

    /**
     * Generates a random integer reward based on an item's specified max reward int variable.
     * @param inMaxReward an integer that contains the max possible reward.
     * @return the max random reward less than or equal to the inMaxReward.
     */
    public int getRandomReward(int inMaxReward)
    {
        return nextInt(inMaxReward);
    }

    /**
     * Uses the object's .getPercentToWin method to retrieve and determine if the player wins via random number generation.
     * Every item in game has different percents to win.
     * @param inItem the item being used by the player.
     * @return a boolean if the player wins or not.
     */
    public boolean checkIfWin(Item inItem)
    {
        boolean isWin = false;
        int randomNumber = nextInt(100);
        if (inItem.getPercentToWin() >= randomNumber){
            isWin = true;
        }
        return isWin;
    }

    /**
     * Used to pick a random string that displays when a player uses an item, gets a reward, and losses an item.
     * @param inArray a String ArrayList that contains messages to be displayed.
     * @return a random string from the array.
     */
    public String pickRandomString(ArrayList<String> inArray)
    {
        int arrayLength = inArray.size();
        return String.valueOf(inArray.get(nextInt(arrayLength)));
    }

    /**
     * Gets a random number.
     * @param inNumber input number to define the upper bound.
     * @return a random integer number.
     */
    public int getRandomNumber(int inNumber)
    {
        return nextInt(inNumber);
    }
}
