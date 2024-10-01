package edu.sdccd.cisc191.template.items;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The PhoneItem class is a child of the Item super class.
 * The phone can be used by the player to generate money.
 * @author Tim Tran
 */
public class PhoneItem extends Item implements Serializable
{

    /**
     * Constructor that sets the price, name, and desc.
     * @param price the item's purchase price.
     * @param itemName the name of the item.
     * @param description a String about the item.
     */
    public PhoneItem(int price, String itemName, String description)
    {
        super(price, itemName, description);
    }

    /**
     * Method that is called when the player uses the item in the inventory.
     * @param inItem the selected item to be used.
     * @param actionEvent used to switch the scenes to display the item being used.
     */
    @Override
    public void useItem(Item inItem, ActionEvent actionEvent)
    {
        try
        {
            switchToItemScene(actionEvent, inItem, "UseItemResults.fxml");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the integer price of the phone object.
     */
    @Override
    public int getItemPrice()
    {
        return super.getItemPrice();
    }

    /**
     * @return the String name of the phone object.
     */
    @Override
    public String getItemName()
    {
        return super.getItemName();
    }

    /**
     * @return the String description of the phone object.
     */
    @Override
    public String getDescription()
    {
        return super.getDescription();
    }

    /**
     * @return an integer of the max reward possible when using the phone.
     */
    @Override
    public int getMaxItemReward()
    {
        int MAX_MONEY_REWARD = 400;
        return MAX_MONEY_REWARD;
    }

    /**
     * @return an integer of the percent to win when using the phone.
     */
    @Override
    public int getPercentToWin()
    {
        int PERCENT_TO_WIN = 90;
        return PERCENT_TO_WIN;
    }

    /**
     * Overrides the getUseItemMessages from the Item class.
     * Has custom phone related messages that can be displayed in the scene when used.
     * @return ArrayList of String messages to be displayed.
     */
    @Override
    public ArrayList<String> getUseItemMessages()
    {
        ArrayList<String> list = new ArrayList();
        list.add("You posted a Tiktok Video...");
        list.add("You played Warframe...");
        return list;
    }

    /**
     * @return an ArrayList to display possible messages when the player losses.
     */
    @Override
    public ArrayList<String> getLoseItemMessages()
    {
        ArrayList<String> list = new ArrayList();
        list.add("Your phone exploded!");
        list.add("Your phone was sent back to 1999!");
        list.add("The NSA took your phone!");
        list.add("In a fit of rage, you smashed your phone!");
        return list;
    }
}
