package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.Items.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInventoryHashMap
{
    PlayerInventory inventory = new PlayerInventory();
    Item phone = new PhoneItem(1000, "Phone", "Test Phone");
    Item computer = new ComputerItem(1000, "Computer", "Test Computer");

    @BeforeEach
    void setUp()
    {
        inventory.addItem(phone);
        inventory.addItem(computer);
        inventory.addItem(computer);
    }

    @Test
    void testHashMap()
    {
        assertEquals(1, inventory.getItemQuantity(phone));
        assertEquals(2, inventory.getItemQuantity(computer));
    }
}
