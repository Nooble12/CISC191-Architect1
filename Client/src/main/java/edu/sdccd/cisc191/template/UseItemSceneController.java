package edu.sdccd.cisc191.template;
import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Handles how using items works.
 * Controls the UseItemResults.fxml scene.
 * @author Tim Tran
 */
public class UseItemSceneController
{

    @FXML
    private Label resultsDescription;
    @FXML
    private Label rewardsDescription;
    @FXML
    private Text itemUsedLabel;

    private Item item;

    /**
     * Sets the item being used.
     * @param inItem the item being used by the player from the inventory.
     */
    public void setItem(Item inItem)
    {
        item = inItem;
        updateSceneText();
    }
    /**
     *
     * Switches back to the inventory when the player presses the back button.
     * @param event used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToInventory(ActionEvent event) throws IOException
    {
        SceneController sceneController = new SceneController();
        sceneController.switchScene(event, "Inventory.fxml");
    }

    /**
     * If the player has an item, the eat food game will run.
     */
    private void updateSceneText()
    {
        if (item != null)
        {
            runGame();
        }
        else
        {
            resultsDescription.setText("N/A");
            rewardsDescription.setText("N/A");
        }
    }

    /**
     * On item use, the check will check if the player wins or not via random number generation.
     * If the player wins, they will receive a random reward that is equal or less than the item's max reward value.
     * If the player losses, they will lose the item, and it will be removed from the inventory.
     */
    private void runGame()
    {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);

        RandomClass generator = new RandomClass();
        String randomString = generator.pickRandomString(item.getUseItemMessages());
        resultsDescription.setText(randomString);
        itemUsedLabel.setText(item.getItemName() + " " + "Results");
        if (generator.checkIfWin(item))
        {
            BankAccount account = new BankAccount();
            int reward = generator.getRandomReward(item.getMaxItemReward() * account.getCurrentMultiplier());
            account.addBalance(reward);
            rewardsDescription.setText("You made " + currencyFormat.format(reward) + " (" + account.getCurrentMultiplier() + "x Money Multiplier)" + "\n" + account.toString());
        }
        else
        {
            rewardsDescription.setText(generator.pickRandomString(item.getLoseItemMessages()) + "\n" + "-1 " + item.getItemName());
            PlayerInventory inventory = PlayerInventory.getInstance();
            inventory.removeItem(item, 1);
        }
    }
}
