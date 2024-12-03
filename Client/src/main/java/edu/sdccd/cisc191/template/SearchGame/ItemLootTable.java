package edu.sdccd.cisc191.template.SearchGame;

import edu.sdccd.cisc191.template.Items.*;

/**
 * Creates a loot table with different items that the player can use.
 */
public class ItemLootTable
{
    /**
     * Generates and array of Items that will be used as a random loot table to give the player a random item.
     * @return the Item[].
     */
    public Item[] getItemLootTable()
    {
        return new Item[]{
                new PhoneItem(1000, "Phone", "Well, it is a phone. What else can I say?"),
                new ComputerItem(1500, "Computer", "Well, it is a computer. What else can I say?"),
                new CheeseItem(10000, "Cheese", "Adds 1 Money Multiplier."),
                new SuperComputerItem(2000, "Super Computer", "A super computer of sorts."),
                new SuperCheeseItem(10000, "Super Cheese", "Adds 4 Money Multiplier."),
        };
    }
}
