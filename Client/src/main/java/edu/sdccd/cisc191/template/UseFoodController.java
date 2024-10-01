package edu.sdccd.cisc191.template;
import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import java.io.IOException;

/**
 * The UseFoodController is responsible for handling when a player eats a food item like cheese.
 * Also controls the UseFoodResults.fxml scene.
 * @author Tim Tran
 */
public class UseFoodController
{
        @FXML
        private Label resultsDescription;
        @FXML
        private Label rewardsDescription;
        @FXML
        private Text itemUsedLabel;

        private Item item;

    /**
     * Sets the item
     * @param inItem the food item being inputted.
     */
    public void setItem(Item inItem)
    {
        item = inItem;
        updateSceneText();
    }

    /**
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
     * When the player uses the cheese item, it will randomly check if the player wins or losses.
     * If the player wins, their money multiplier will increase by 1.
     * If the player losses, their multiplier will be reset to 1.
     * Uses the RandomClass generator to determine win and lost.
     */
    private void runGame()
    {
        RandomClass generator = new RandomClass();
        PlayerInventory inventory = PlayerInventory.getInstance();
        BankAccount account = new BankAccount();
        resultsDescription.setText("You ate " + item.getItemName() + "...");
        itemUsedLabel.setText(item.getItemName() + " " + "Results");
        if (generator.checkIfWin(item))
        {
            account.addMoneyMultiplier(item.getMoneyMultiplier());
            rewardsDescription.setText("Money Multiplier Increased to " + account.getCurrentMultiplier());
        }
        else
        {
            rewardsDescription.setText(generator.pickRandomString(item.getLoseItemMessages()) + "\n" + "Money Multiplier Reset!");
            account.resetMoneyMultiplier();
        }
        inventory.removeItem(item, 1);
    }
}
