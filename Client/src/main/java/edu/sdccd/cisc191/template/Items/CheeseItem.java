package edu.sdccd.cisc191.template.Items;
import edu.sdccd.cisc191.template.PlayerData.BankAccount;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cheese item is a child of the FoodItem class. Players can eat cheese to gain a money multiplier on top of their earnings.
 * @author Tim Tran
 */
public class CheeseItem extends FoodItem implements Serializable
{
    /**
     * Constructor that initializes the price, name, and description.
     * @param price the price to purchase the item.
     * @param itemName the String name of the item.
     * @param description the String description of the item.
     */
    public CheeseItem(int price, String itemName, String description)
    {
        super(price, itemName, description);
    }

    /**
     * @return the custom money multiplier of the cheese item. Some food items may be able to increase the factor by more than 1.
     */
    @Override
    public int getMoneyMultiplier()
    {
        return 1;
    }

    /**
     * @return the percent to increase or lose all money multiplier.
     */
    @Override
    public int getPercentToWin()
    {
        return 95; // percent
    }

    /**
     * Contains multiple strings that can be displayed when the player rolls a loss and losses an item.
     * @return a list of lose messages contained within an ArrayList.
     */
    @Override
    public ArrayList<String> getLoseItemMessages()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add("While eating, you chocked on a piece and died!");
        list.add("After eating, you turned into a cheese and died!");
        return list;
    }

    @Override
    public String getItemImageFile()
    {
        return "cheese_ghost.png";
    }

    @Override
    public String toString()
    {
        BankAccount account = new BankAccount();
        String itemString = getDescription() + "\n" + "Percent to Win: " + getPercentToWin() + "%" + "\n" + "Current Multiplier Level: " + account.getCurrentMultiplier();
        return itemString;
    }
}
