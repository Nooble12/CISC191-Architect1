package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.Items.CheeseItem;
import edu.sdccd.cisc191.template.Items.ComputerItem;
import edu.sdccd.cisc191.template.Items.Item;
import edu.sdccd.cisc191.template.Items.PhoneItem;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInventoryStreamAPI
{
    PlayerInventory inventory = new PlayerInventory();
    Item phone = new PhoneItem(500, "Phone", "Test Phone");
    Item computer = new ComputerItem(1000, "Computer", "Test Computer");
    Item cheese = new CheeseItem(2000, "Cheese", "Test Cheese");

    @BeforeEach
    void setUp()
    {
        inventory.addItem(phone);
        inventory.addItem(computer);
        inventory.addItem(cheese);
    }

    @Test
    void testStream()
    {
        assertEquals(3500, inventory.getInventorySumValue());
    }
}
