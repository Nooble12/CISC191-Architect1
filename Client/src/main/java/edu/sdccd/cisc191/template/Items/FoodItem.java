package edu.sdccd.cisc191.template.Items;

import edu.sdccd.cisc191.template.ActionLogger.ActionLogger;
import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.SceneControllers.UseFoodSceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class FoodItem extends Item implements Serializable
{
    private static final int MONEY_MULTIPLIER = 0;

    public FoodItem(int price, String itemName, String description)
    {
        super(price, itemName, description);
    }

    /**
     * @return the custom money multiplier of the cheese item. Some food items may be able to increase the factor by more than 1.
     */
    @Override
    public int getMoneyMultiplier()
    {
        return MONEY_MULTIPLIER;
    }

    @Override
    public String toString()
    {
        BankAccount account = new BankAccount();
        String itemString = getDescription() + "\n" + "Percent to Win: " + getPercentToWin() + "%" + "\n" + "Current Multiplier Level: " + account.getCurrentMultiplier();
        return itemString;
    }

    /**
     * Overrides the Item class' useItem method which allows the player to use the item in the inventory.
     * @param inItem the selected item reference in the inventory.
     * @param actionEvent the event used to switch the scene to display the item being used.
     */
    @Override
    public void useItem(Item inItem, ActionEvent actionEvent)
    {
        ActionLogger logger = new ActionLogger();
        logger.logAction("Used " + inItem.getItemName());
        try
        {
            switchToItemScene(actionEvent, inItem, "UseFoodResults.fxml");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Switches the scene to display the item being used and the results.
     * @param event variable that allows the scene to be changed.
     * @param inItem the selected item to be used.
     * @param fxmlFile inputted fxmlFile if the method needs to use a different .fxml scene.
     * @throws IOException if the program can not find the .fxml file or if it could not be loaded.
     */
    @Override
    public void switchToItemScene(ActionEvent event, Item inItem, String fxmlFile) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/sdccd/cisc191/template/" + fxmlFile));
        Parent root = loader.load();

        // Set the controller with the item
        UseFoodSceneController controller = loader.getController();
        controller.setItem(inItem);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
