package edu.sdccd.cisc191.template.PlayerData;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;

import java.io.*;

public class PlayerDataManager
{
    BankAccount account = new BankAccount();

    /**
     * Calls the loadBalanceFromFile method then prints out the account balance.
     */
    private void loadPlayerMoney()
    {
        // Load the balance from the file
        try
        {
            loadBalanceFromFile("bankAccountBalance.ser");
            System.out.println("Loaded balance: $" + account.getBalance());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Calls the saveBalanceToFile method to save the player's balance.
     * @param inFileName the .ser file location to be saved.
     */
    private void savePlayerMoney(String inFileName)
    {
        try
        {
            saveBalanceToFile(inFileName);
            System.out.println("Balance saved successfully.");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Serializable the player's balance to a file to be saved.
     * @param fileName the .ser file name.
     * @throws IOException if the FileOutPutStream could not write to the file.
     */
    public void saveBalanceToFile(String fileName) throws IOException
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            outputStream.writeInt(account.getBalance()); // Save only the balance value
            outputStream.writeInt(account.getCurrentMultiplier());
        }
    }

    /**
     * Deserializes the file and sets the player's balance by calling the setBalance method.
     * @param fileName the .ser file name.
     * @throws IOException if the .ser file name could not be found or read.
     */
    public void loadBalanceFromFile(String fileName) throws IOException
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName)))
        {
            account.setBalance(inputStream.readInt()); // Load the balance value into the static variable
            account.setMoneyMultiplier(inputStream.readInt());
        } catch (IOException e)
        {
            System.out.println("Bank account not found. Created new one.");
        }
    }

    /**
     * Serializes the player's inventory hash map.
     * @param inFileName the file where the .ser will be created.
     * @throws IOException if the file could not be saved or written to.
     */
    public void saveInventoryToFile(String inFileName) throws IOException
    {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(inFileName)))
        {
            outputStream.writeObject(PlayerInventory.getInstance());
            System.out.println("Inventory saved successfully.");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the inventory .ser file and then sets the player's inventory to the loaded hash map.
     */
    private void loadInventory()
    {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("inventory.ser")))
        {
            PlayerInventory loadedInventory = (PlayerInventory) inputStream.readObject();
            PlayerInventory.setInstance(loadedInventory);
            System.out.println("Inventory loaded successfully.");
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Inventory not found. Created new one.");
        }
    }

    /**
     * Calls the load inventory and load player money methods so that both can be done in a single method call.
     */
    public void loadPlayerData()
    {
        loadInventory();
        loadPlayerMoney();
    }

    /**
     * Calls the save inventory and money methods and passes in Strings for file names.
     * This is done to call both methods at once.
     */
    public void savePlayerData()
    {
        try
        {
            saveInventoryToFile("inventory.ser");
            savePlayerMoney("bankAccountBalance.ser");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}