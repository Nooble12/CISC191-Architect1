package edu.sdccd.cisc191.template.items;
import java.io.Serializable;


/**
 * The SuperComputerItem class is a child of the Item class that can be used by the player to generate money.
 * @author Tim Tran
 */
public class SuperComputerItem extends ComputerItem implements Serializable
{

    /**
     * Initializes price, name, and description.
     * @param price the integer price of the item.
     * @param itemName the string name of the item.
     * @param description the string desc of the item.
     */
    public SuperComputerItem(int price, String itemName, String description)
    {
        super(price, itemName, description);
    }

    /**
     * @return an integer of the percent to win.
     */
    @Override
    public int getPercentToWin()
    {
        // percent
        int PERCENT_TO_WIN = 80;
        return PERCENT_TO_WIN;
    }

    /**
     * @return an integer of the super computer's max reward value.
     */
    @Override
    public int getMaxItemReward()
    {
        int MAX_ITEM_REWARD = 2000;
        return MAX_ITEM_REWARD;
    }
}
