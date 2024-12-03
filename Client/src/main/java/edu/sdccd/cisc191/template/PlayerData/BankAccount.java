package edu.sdccd.cisc191.template.PlayerData;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * The BankAccount class handles the player's money in game. It stores the balance and money multiplier.
 * It uses PlayerStatistics to track total money gained and spent.
 * @author Tim Tran
 */
@Component
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    // Player-specific data (no static variables)
    private static int balance = 1000;
    private static int moneyMultiplier = 1;

    private static int totalMoneyGained = 0;
    private static int totalMoneySpent = 0;

    public int getTotalMoneyGained()
    {
        return totalMoneyGained;
    }

    public void setTotalMoneyGained(int inMoney)
    {
        totalMoneyGained = inMoney;
    }

    public void setTotalMoneySpent(int inMoney)
    {
        totalMoneySpent = inMoney;
    }

    public int getTotalMoneySpent()
    {
        return totalMoneySpent;
    }

    /**
     * Returns the player's current balance integer.
     * @return balance of the player.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Adds a specified amount of money to the player's balance and updates PlayerStatistics.
     * @param amount the inputted amount of money to add to the balance.
     */
    public void addBalance(int amount) {
        balance += amount;
        totalMoneyGained += amount;
    }

    /**
     * Removes a specified amount of money from the player's balance and updates PlayerStatistics.
     * @param amount of money to be removed from the balance.
     */
    public void subtractBalance(int amount) {
        balance -= amount;
        totalMoneySpent += amount;
    }

    /**
     * Prints a string of the player's balance and is formatted with number separators.
     * @return formatted string of the player's balance.
     */
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMinimumFractionDigits(0);
        return "Balance: " + currencyFormat.format(balance);
    }

    /**
     * Increases the money multiplier integer by the parameter amount.
     * Depending on the item, it may increase the integer by more than 1.
     * @param inValue how much to increase the player's money multiplier integer.
     */
    public void addMoneyMultiplier(int inValue) {
        moneyMultiplier += inValue;
    }

    /**
     * @return the player's current money multiplier integer.
     */
    public int getCurrentMultiplier() {
        return moneyMultiplier;
    }

    /**
     * Resets the player's money multiplier if they eat an item that rolls a loss condition.
     */
    public void resetMoneyMultiplier() {
        moneyMultiplier = 1;
    }

    /**
     * Sets the player balance to the inputted integer value.
     * @param inBalance the inputted integer value.
     */
    public void setBalance(int inBalance) {
        balance = inBalance;
    }

    /**
     * Sets the player's money multiplier. Used in the DataManager class to set on application startup.
     * @param inValue inputted value to set the player's money multiplier.
     */
    public void setMoneyMultiplier(int inValue) {
        moneyMultiplier = inValue;
    }
}


