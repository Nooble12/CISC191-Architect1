package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.ShopController;
import edu.sdccd.cisc191.template.items.Item;
import edu.sdccd.cisc191.template.items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestShopItemCreation
{

    private Item[][] itemArray;

    @BeforeEach
    public void setUp()
    {
        initializeShopItems();
    }

    public void initializeShopItems()
    {
        ShopController shopController = new ShopController();
        itemArray = shopController.getShopArray();
    }

    @Test
    public void testItemArrayCreation()
    {
        // Check the first row items
        assertNotNull(itemArray[0][0]);
        assertEquals("Phone", itemArray[0][0].getItemName());
        assertEquals(1000, itemArray[0][0].getItemPrice());

        assertNotNull(itemArray[0][1]);
        assertEquals("Computer", itemArray[0][1].getItemName());
        assertEquals(1500, itemArray[0][1].getItemPrice());

        // Check the second row items
        assertNotNull(itemArray[1][0]);
        assertEquals("Investment Account", itemArray[1][0].getItemName());
        assertEquals(150000, itemArray[1][0].getItemPrice());

        assertNotNull(itemArray[1][1]);
        assertEquals("Cheese", itemArray[1][1].getItemName());
        assertEquals(10000, itemArray[1][1].getItemPrice());
    }
}
