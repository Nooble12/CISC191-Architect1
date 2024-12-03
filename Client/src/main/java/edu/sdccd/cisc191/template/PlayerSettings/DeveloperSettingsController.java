package edu.sdccd.cisc191.template.PlayerSettings;

import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.SceneControllers.SceneController;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Dev settings class allows for the player to gain access to infinite money and reset account statistics.
 */
public class DeveloperSettingsController extends SettingsController
{
    /**
     * Sets the player's account balance to the max integer value of approx 2.14 billion or Integer.MAX_VALUE.
     */
    public void setPlayerMaxMoney()
    {
        BankAccount account = new BankAccount();
        account.setBalance(Integer.MAX_VALUE);
    }

    /**
     * Resets the inventory of the player by clearing the hash map.
     */
    public void resetPlayerInventory()
    {
        PlayerInventory inventory = PlayerInventory.getInstance();
        inventory.deletePlayerInventory("Confirm");
    }

    /**
     * Resets the player's money by setting the balance to zero via BankAccount's account.setBalance method.
     */
    public void resetPlayerMoney()
    {
        BankAccount account = new BankAccount();
        account.setBalance(0);
    }

    /**
     * Switches the scene to Settings.fxml or the previous page.
     * @param event used to switch scene on button press.
     * @throws IOException if the .fxml file could be found.
     */
    public void switchToSettingsScene(ActionEvent event) throws IOException
    {
        SceneController controller = new SceneController();
        controller.switchScene(event, "Settings.fxml");
    }
}
