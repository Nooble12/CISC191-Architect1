package edu.sdccd.cisc191.template.SceneControllers;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.Items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Controls the inventory .fxml scene.
 * Displays the player's inventory.
 * Allows the player to use items.
 * @author Tim Tran
 */
public class InventorySceneController extends SceneController
{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Text inventoryValueLabel;

    PlayerInventory playerInventory = PlayerInventory.getInstance();

    // Retrieve and print the inventory map
    Map<Item, Integer> inventoryMap = playerInventory.getInventoryMap();


    /**
     * sets the inventory and displays.
     */
    @FXML
    public void initialize()
    {
        playerInventory = PlayerInventory.getInstance();
        displayInventory();
        displayInventoryValue();
    }

    /**
     * Displays the total inventory value by calling the getInventorySumValue and setting the inventoryValueLabel to the return.
     */
    private void displayInventoryValue()
    {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);

        StringBuilder builder = new StringBuilder();
        builder.append("Total Value: ").append(currencyFormat.format(playerInventory.getInventorySumValue()));
        inventoryValueLabel.setText(builder.toString());
    }

    /**
     * Using the inventory hash map, the method can create buttons on a GridPane to evenly display the items within.
     * Buttons are given a handleMouseEntered to display the item's description when hovered on.
     * Binds each button with an Item object to be used.
     * Buttons / items can be clicked and used,
     */
    private void displayInventory()
    {
        int column = 0;
        int row = 0;
        for (Map.Entry<Item, Integer> entry : inventoryMap.entrySet())
        {
            Item item = entry.getKey();
            int itemQuantity = entry.getValue();
            Button itemButton = new Button(item.getItemName() + "\n" + "x" + itemQuantity);
            itemButton.setMaxHeight(Double.MAX_VALUE);
            itemButton.setMaxWidth(Double.MAX_VALUE);
            itemButton.setFont(Font.font("Arial", 30));
            itemButton.getStyleClass().add("button");
            itemButton.setOnAction(e -> handleItemClick(item, e));
            itemButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> handleMouseEntered(e, item));
            itemButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> handleMouseExit(e, item));
             gridPane.add(itemButton, column, row);
             column++;
            if (column > 1)
            {
                column = 0;
                row++;
            }
        }
    }

    /**
     * When a button / item is clicked, it will call this method and use the item.
     * @param item selected item object that the player clicks.
     * @param e event that activates on click and is used to change the scene.
     */
    private void handleItemClick(Item item, ActionEvent e)
    {
        item.useItem(item, e);
    }

    /**
     * When the mouse hovers or enters the button, it will change the description text to the selected item's message.
     * @param mouseEvent the event used for the mouse class.
     * @param item selected item.
     */
    private void handleMouseEntered(MouseEvent mouseEvent, Item item)
    {
        descriptionLabel.setText(item.toString());
    }

    /**
     * When the player mouse exits a button, it will change the text to "".
     * @param mouseEvent event used to handle the mouse methods.
     * @param item the selected item.
     */
    private void handleMouseExit(MouseEvent mouseEvent, Item item)
    {
        descriptionLabel.setText("");
    }
}
