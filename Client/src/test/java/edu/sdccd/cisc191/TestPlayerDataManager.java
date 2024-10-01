package edu.sdccd.cisc191;
import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerData.PlayerDataManager;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.items.ComputerItem;
import edu.sdccd.cisc191.template.items.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerDataManager
{
    private PlayerDataManager playerDataManager;

    private static final String BALANCE_FILE = "testBankAccountBalance.ser";
    private static final String INVENTORY_FILE = "testInventory.ser";

    @BeforeEach
    void setUp() {
        playerDataManager = new PlayerDataManager();
        // Set up initial state
        PlayerInventory.setInstance(new PlayerInventory());
    }

    @AfterEach
    void tearDown() {
        // Clean up test files
        new File(BALANCE_FILE).delete();
        new File(INVENTORY_FILE).delete();
    }

    @Test
    void testSaveUserData() {
        // Initialize PlayerDataManager
        playerDataManager = new PlayerDataManager();

        // Set up initial state
        BankAccount account = new BankAccount();
        account.setBalance(1000);
        account.setMoneyMultiplier(10);

        PlayerInventory inventory = PlayerInventory.getInstance();
        Item computer = new ComputerItem(1500, "Computer", "Well, it is a computer. What else can I say?");
        inventory.addItem(computer);

        // Save state to files
        try {
            playerDataManager.saveBalanceToFile(BALANCE_FILE);
            playerDataManager.saveInventoryToFile(INVENTORY_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //reset balance to 0 after saving to see if balance is correctly loaded.
        account.setBalance(0);
        account.setMoneyMultiplier(0);

        try {
            playerDataManager.loadBalanceFromFile(BALANCE_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Verify balance and money multiplier
        assertEquals(1000, account.getBalance(), "The balance should be 1000");
        assertEquals(10, account.getCurrentMultiplier(), "Money multiplier should be 10");

        // Verify inventory
        PlayerInventory loadedInventory = PlayerInventory.getInstance();
        assertEquals(1, loadedInventory.getItemQuantity(computer), "Computer item quantity should be 1 after adding.");
    }
}
