package edu.sdccd.cisc191.template.PlayerInventory;
import edu.sdccd.cisc191.template.SceneController;
import edu.sdccd.cisc191.template.items.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

/**
 * Controls the inventory .fxml scene.
 * Displays the player's inventory.
 * Allows the player to use items.
 * @author Tim Tran
 */
public class InventorySceneController
{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label descriptionLabel;

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
     * Switches the scene back to the main menu when the player clicks the back button.
     * Uses the SceneController class to switch scenes.
     * @param event used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToMainMenu(ActionEvent event) throws IOException
    {
       SceneController controller = new SceneController();
       controller.switchToMainMenu(event);
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
