package edu.sdccd.cisc191.template.SceneControllers;

import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.util.Locale;

public class DatabaseSceneController extends SceneController
{
    @FXML
    private Label totalMoneyGained;

    @FXML
    private Label totalMoneySpent;

    public void initialize()
    {
        BankAccount account = new BankAccount();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMinimumFractionDigits(0);

        totalMoneyGained.setText("Total Money Gained: " + (currencyFormat.format(account.getTotalMoneyGained())));
        totalMoneySpent.setText("Total Money Spent: " + (currencyFormat.format(account.getTotalMoneySpent())));
    }
}
