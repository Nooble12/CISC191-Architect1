package edu.sdccd.cisc191.template.SearchGame;

import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.RandomClass;
import edu.sdccd.cisc191.template.items.*;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class SearchGame
{
    private static final int MAX_MONEY_REWARD = 200;
    private RandomClass randomGenerator = new RandomClass();
    private BankAccount bankAccount = new BankAccount();

    /**
     * Checks if the player wins.
     * @return boolean if the player wins or not.
     */
    public boolean didPlayerWin()
    {
        Random generator = new Random();
        int number = generator.nextInt(100);
        // chance to lose
        return number > 10;
    }

    /**
     * Determines the reward based on the game's outcome.
     * @return a String message indicating the reward.
     */
    public String pickRandomReward()
    {
        Random generator = new Random();
        int number = generator.nextInt(100);
        if (number <= 30)
        {
            Item[] itemLootTable = new ItemLootTable().getItemLootTable();
            Item rewardItem = itemLootTable[generator.nextInt(itemLootTable.length)];
            PlayerInventory inventory = PlayerInventory.getInstance();
            inventory.addItem(rewardItem);
            return "You found a " + rewardItem.getItemName() + "!";
        }
        else
        {
            return generateMoneyReward();
        }
    }

    /**
     * Generates a random amount of money that the player wins and adds it to their bank account.
     * @return a String message indicating the money reward.
     */
    private String generateMoneyReward()
    {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);

        int reward = randomGenerator.getRandomReward(MAX_MONEY_REWARD) * bankAccount.getCurrentMultiplier();
        bankAccount.addBalance(reward);
        return "You found: " + currencyFormat.format(reward) + " (x" + bankAccount.getCurrentMultiplier() + " Money Multiplier)" + "\n" + bankAccount;
    }

    /**
     * If the player loses, subtracts a random amount from their balance.
     * @return a String message indicating the money lost.
     */
    public String playerLose()
    {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);

        int moneyLost = randomGenerator.getRandomNumber(bankAccount.getBalance() / 2);
        bankAccount.subtractBalance(moneyLost);
        return "You lost " + currencyFormat.format(moneyLost);
    }
}
