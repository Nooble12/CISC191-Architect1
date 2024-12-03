package edu.sdccd.cisc191.template.PlayerInventory;
import edu.sdccd.cisc191.template.Items.Item;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The player inventory class handles the storage of items that player purchases, receives, and losses while playing the game.
 * @author Tim Tran
 */
public class PlayerInventory implements Serializable
{
    private static PlayerInventory instance;
    private Map<Item, Integer> inventoryItems = new HashMap<>();

    /**
     * Calculates the inventory value by getting all items in the HashMap and multiplying by their respective item prices.
     * Transform the HashMap to a return type of Double
     * @return the summation of all inventory items.
     */
    public double getInventorySumValue()
    {
        return inventoryItems.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getItemPrice() * entry.getValue())
                .sum();
    }

    /**
     * Adds an item to the inventory hash map to be stored.
     * @param item the item that will be added in.
     */
    public void addItem(Item item)
    {
        inventoryItems.put(item, inventoryItems.getOrDefault(item, 0) + 1);
    }

    /**
     * Removes an item from the inventory if the player losses or is used depending on the implementations of the item.
     * @param item the item to be removed.
     * @param amountToRemove the amount of the same item to be removed.
     */
    public void removeItem(Item item, int amountToRemove)
    {
        if (inventoryItems.containsKey(item))
        {
            int currentAmount = inventoryItems.get(item);

            if (amountToRemove >= currentAmount)
            {
                inventoryItems.remove(item);
            } else
            {
                inventoryItems.put(item, currentAmount - amountToRemove);
            }
        }
    }

    /**
     * Gets an instance of the inventory to be used across different classes and maintain the same inventory. Simpleton route.
     * @return the instance of the PlayerInventory class.
     */
    public static PlayerInventory getInstance()
    {
          if (instance == null)
          {
              instance = new PlayerInventory();
          }
          return instance;
    }

    /**
     * Gets the quantity of the inputted item from the inventory.
     * @param item the inputted item.
     * @return the quantity of the inputted item.
     */
    public int getItemQuantity(Item item)
    {
        return inventoryItems.getOrDefault(item, 0);
    }

    /**
     * @return the inventoryItem hash map,
     */
    public Map<Item, Integer> getInventoryMap()
    {
        return inventoryItems;
    }

    /**
     * Displays the inventory via console output. Not used.
     */
    public void displayInventory() {
        for (Map.Entry<Item, Integer > entry : inventoryItems.entrySet())
        {
            Item item = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(item.getItemName() + " x" + quantity);
        }
    }

    /**
     * Sets the instance of the inventory. Used by the PlayerDataManager class to load the inventory and set.
     * @param inventory instance that will set the inventory.
     */
    public static void setInstance(PlayerInventory inventory)
    {
        instance = inventory;
    }

    /**
     * Clears the inventory hash map.
     * Used by the dev settings class to remove data.
     * @param confirmationCode to prevent accidental deletion of the player's data.
     */
    public void deletePlayerInventory(String confirmationCode)
    {
        if (confirmationCode.equals("Confirm"))
        {
            inventoryItems.clear();
        }
    }
}