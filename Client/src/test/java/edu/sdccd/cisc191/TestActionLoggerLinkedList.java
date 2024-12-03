package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.ActionLogger.ActionLogger;
import edu.sdccd.cisc191.template.Items.CheeseItem;
import edu.sdccd.cisc191.template.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestActionLoggerLinkedList
{
    ActionLogger actionLog = new ActionLogger();
    Item cheeseItem = new CheeseItem(1000, "Cheese", "Cheese");
    @BeforeEach
    void setUp()
    {
       actionLog.logAction("Purchase Command and Conquer 3");
       actionLog.logAction("Refunded Command and Conquer 4");
       actionLog.logAction("You bought: " + cheeseItem.getItemName());
    }

    @Test
    void testLinkedList()
    {
        assertTrue(actionLog.getActionLogAtNode(2).contains("Command and Conquer 3"));

        assertTrue(actionLog.getActionLogAtNode(1).contains("Refunded Command and Conquer 4"));

        assertTrue(actionLog.getActionLogAtNode(0).contains("You bought"));
    }
}
