package edu.sdccd.cisc191;
import edu.sdccd.cisc191.template.SearchGame.SearchGame;
import edu.sdccd.cisc191.template.SearchGame.SearchSceneController;
import edu.sdccd.cisc191.template.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestItemObjectCreation
{
    private SearchGame searchGame;
    @BeforeEach
    void setUp()
    {
        searchGame = new SearchGame();  // Initialize SearchController
    }

    /**
     * Generates and array of Items that will be used as a random loot table to give the player a random item.
     * @return the Item[].
     */
    private Item[] getItemLootTable()
    {
        return new Item[]{
                new PhoneItem(1000, "Phone", "Well, it is a phone. What else can I say?"),
                new ComputerItem(1500, "Computer", "Well, it is a computer. What else can I say?"),
                new CheeseItem(10000, "Cheese", "Adds 1 Money Multiplier"),
                new SuperComputerItem(2000, "Super Computer", "A super computer of sorts."),
                new SuperCheeseItem(10000, "Super Cheese", "Adds 4 Money Multiplier."),
        };
    }

    @Test
    void testCreateItemLootTable()
    {
        Item[] itemLootTable = getItemLootTable();

        // Assert: Verify that the array is not null and has the correct size
        assertNotNull(itemLootTable, "Loot table should not be null");
        assertEquals(5, itemLootTable.length, "Loot table should have 5 items");

        // Verify each item
        assertTrue(itemLootTable[0] instanceof PhoneItem, "First item should be a PhoneItem");
        assertEquals(1000, itemLootTable[0].getItemPrice(), "First item value should be 1000");
        assertEquals("Phone", itemLootTable[0].getItemName(), "First item name should be 'Phone'");

        assertTrue(itemLootTable[1] instanceof ComputerItem, "Second item should be a ComputerItem");
        assertEquals(1500, itemLootTable[1].getItemPrice(), "Second item value should be 1500");
        assertEquals("Computer", itemLootTable[1].getItemName(), "Second item name should be 'Computer'");

        assertTrue(itemLootTable[2] instanceof CheeseItem, "Third item should be a CheeseItem");
        assertEquals(10000, itemLootTable[2].getItemPrice(), "Third item value should be 10000");
        assertEquals("Cheese", itemLootTable[2].getItemName(), "Third item name should be 'Cheese'");

        assertTrue(itemLootTable[3] instanceof SuperComputerItem, "Fourth item should be a SuperComputerItem");
        assertEquals(2000, itemLootTable[3].getItemPrice(), "Fourth item value should be 2000");
        assertEquals("Super Computer", itemLootTable[3].getItemName(), "Fourth item name should be 'Super Computer'");
    }
}
