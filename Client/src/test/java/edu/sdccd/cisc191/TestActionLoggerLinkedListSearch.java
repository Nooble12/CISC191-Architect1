package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.ActionLogger.ActionLogger;
import edu.sdccd.cisc191.template.ActionLogger.SinglyLinkedList;
import edu.sdccd.cisc191.template.Items.CheeseItem;
import edu.sdccd.cisc191.template.Items.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestActionLoggerLinkedListSearch
{
    ActionLogger actionLog = new ActionLogger();
    Item cheeseItem = new CheeseItem(1000, "Cheese", "Cheese");

    @BeforeEach
    void setUp()
    {
        actionLog.logAction("War Thunder");
        actionLog.logAction("Warframe");
        actionLog.logAction("You bought: " + cheeseItem.getItemName());
    }

    @Test
    void testLinkedList()
    {
        SinglyLinkedList<String> searchResults = actionLog.SearchActionLog("Warframe");
        String resultString = searchResults.toString().toLowerCase();
        assertTrue(resultString.contains("warframe"));

        searchResults = actionLog.SearchActionLog("Cheese");
        resultString = searchResults.toString().toLowerCase();
        assertTrue(resultString.contains("cheese"));

        searchResults = actionLog.SearchActionLog("RTX 4090");
        resultString = searchResults.toString().toLowerCase();
        assertFalse(resultString.contains("rtx 4090"));
    }
}
