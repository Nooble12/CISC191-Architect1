package edu.sdccd.cisc191.template.Items;

import edu.sdccd.cisc191.template.ActionLogger.ActionLogger;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * ComputerItem class extends the Item class.
 * Computer is an item that can be used by the player to generate money.
 * @author Tim Tran
 */
public class ComputerItem extends Item implements Serializable
{

    /**
     * ComputerItem class constructor. Initializes price, name, and description.
     * @param price of the item.
     * @param itemName is the name of the item.
     * @param description is the String description of the item.
     */
    public ComputerItem(int price, String itemName, String description)
    {
        super(price, itemName, description);
    }

    /**
     * Allows the player to use the item and its methods.
     * @param item the selected item reference to be used.
     * @param actionEvent is an event that is used to change the .fxml scene to display the results after using an item.
     */
    @Override
    public void useItem(Item item, ActionEvent actionEvent)
    {
        ActionLogger logger = new ActionLogger();
        logger.logAction("Used " + item.getItemName());
        try
        {
            switchToItemScene(actionEvent, item, "UseItemResults.fxml");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the price of the item.
     */
    @Override
    public int getItemPrice()
    {
        return super.getItemPrice();
    }

    /**
     * @return the item name.
     */
    @Override
    public String getItemName()
    {
        return super.getItemName();
    }

    /**
     * @return the String description of the item.
     */
    @Override
    public String getDescription()
    {
        return super.getDescription();
    }

    /**
     * @return the percentage integer of winning while using the item.
     */
    public int getPercentToWin()
    {
        // percent
        int PERCENT_TO_WIN = 80;
        return PERCENT_TO_WIN;
    }

    /**
     * @return the max reward integer after using the item.
     */
    @Override
    public int getMaxItemReward()
    {
        int MAX_MONEY_REWARD = 1000;
        return MAX_MONEY_REWARD;
    }

    /**
     * Uses to display a random message of the player using the item on the scene text.
     * @return list of messages that can be displayed from an ArrayList.
     */
    @Override
    public ArrayList<String> getUseItemMessages()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("You posted a YouTube Video...");
        list.add("You played War Thunder...");
        list.add("You installed the JDK...");
        list.add("You installed Windows 11...");
        list.add("You installed MSI Afterburner...");
        return list;
    }

    /**
     * @return ArrayList of messages that can be displayed when the player losses the item.
     */
    @Override
    public ArrayList<String> getLoseItemMessages()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("Your computer exploded!");
        list.add("Your computer was sent back to 1999!");
        list.add("It was not the Party of Your Lifetime!");
        list.add("You tried to divide by zero!");
        list.add("This is too much voodoo!");
        return list;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
