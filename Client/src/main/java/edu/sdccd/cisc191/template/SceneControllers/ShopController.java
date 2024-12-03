package edu.sdccd.cisc191.template.SceneControllers;

import edu.sdccd.cisc191.template.ActionLogger.ActionLogger;
import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerInventory.PlayerInventory;
import edu.sdccd.cisc191.template.Items.CheeseItem;
import edu.sdccd.cisc191.template.Items.ComputerItem;
import edu.sdccd.cisc191.template.Items.Item;
import edu.sdccd.cisc191.template.Items.PhoneItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * The ShopController class is used to control the Shop.fxml scene.
 * This class allows the player to purchase items from buttons displayed on a GridPane.
 * @author Tim Tran
 */
public class ShopController extends SceneController {
    @FXML
    private Text playerBalanceText;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label descriptionLabel;

    private Item[][] itemArray = new Item[2][2];

    /**
     * Creates a BankAccount object that will display the player's balance via a text on the scene.
     * Displays the items that can be purchased via GridPane buttons.
     */
    public void initialize()
    {
        BankAccount playerAccount = new BankAccount();
        playerBalanceText.setText(playerAccount.toString());
        initializeShopItems();
        displayItems();
    }

    /**
     * Creates a 2D Item[][] that will contain the row, col position of the created item.
     * Items created will pass in parameters such as price, name, and desc.
     */
    private void initializeShopItems()
    {
        itemArray[0][0] = new PhoneItem(1000, "Phone", "Well, it is a phone. What else can I say?");
        itemArray[0][1] = new ComputerItem(1500, "Computer", "Well, it is a computer. What else can I say?");
        itemArray[1][1] = new CheeseItem(10000, "Cheese", "Adds 1 Money Multiplier.");
        itemArray[1][0] = new Item(150000, "Investment Account", "Invest your money or something, idk.");
       // itemArray[1][1] = new Item(150000, "Test1", "Not worth buying");
    }

    public Item[][] getShopArray()
    {
        initializeShopItems();
        return itemArray;
    }

    /**
     * If the player's mouse is hovering over a button, a text on the scene will update with the selected item's description.
     * @param mouseEvent for the methods associated with the mouse class.
     * @param item the selected item that the mouse is hovering over.
     */
    private void handleMouseEntered(MouseEvent mouseEvent, Item item)
    {
        descriptionLabel.setText(item.toString());
    }

    /**
     * When the mouse exits the button, the desc txt will reset to "" or nothing.
     * @param mouseEvent for the methods associated with the mouse class.
     * @param item the selected item that the mouse is no longer hovering over.
     */
    private void handleMouseExit(MouseEvent mouseEvent, Item item)
    {
        descriptionLabel.setText("");
    }

    /**
     * Initializes the button with events that allow it to be selected and clicked.
     * @param itemButton the button being initialized.
     * @param item the item associated with the button.
     */
    private void initializeButton(Button itemButton, Item item)
    {
        itemButton.setMaxHeight(Double.MAX_VALUE);
        itemButton.setMaxWidth(Double.MAX_VALUE);
        itemButton.setFont((Font.font("Ariel", 30)));
        itemButton.setOnAction(e -> handleItemClick(item, itemButton));
        itemButton.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> handleMouseEntered(e, item));
        itemButton.addEventHandler(MouseEvent.MOUSE_EXITED, e -> handleMouseExit(e, item));
    }

    /**
     * Uses a nested for loop that will create buttons within each GridPane cell depending on the position of the Item defined within the itemArray.
     * Also adds events to the buttons so they can be clicked on and purchased.
     */
    private void displayItems()
    {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);

        for (int i = 0; i < itemArray.length; i++)
        {
            for (int j = 0; j < itemArray[i].length; j++)
            {
                Item item = itemArray[i][j];
                PlayerInventory inventory = PlayerInventory.getInstance();
                int quantity = inventory.getItemQuantity(item);
                Button itemButton = new Button(item.getItemName() + "\n" + currencyFormat.format(item.getItemPrice()) + "\n" + "x" + quantity);
                initializeButton(itemButton, item);
                gridPane.add(itemButton, j, i);
            }
        }
    }

    /**
     * When an item is clicked, the application will check if the player has enough money.
     * If the player has enough money, the game will then subtract the item.getItemPrice() integer value from the bank account balance.
     * Then the item will be added to the inventory.
     * Then the shop will update the balance text to display the new balance.
     */
    private void handleItemClick(Item item, Button inButton)
    {
        BankAccount playerAccount = new BankAccount();
        if (playerAccount.getBalance() >= item.getItemPrice())
        {
            PlayerInventory.getInstance().addItem(item);
            playerAccount.subtractBalance(item.getItemPrice());
            //PlayerInventory.getInstance().displayInventory();
            updateShopScene(item, inButton);
            ActionLogger actionLogger = new ActionLogger();
            actionLogger.logAction("Purchased Item: " + item.getItemName());
        }
    }

    /**
     * Updates the shop scene to show the new balance and number of item after it is purchased.
     * @param item the item bought.
     * @param inButton the button pressed.
     */
    private void updateShopScene(Item item, Button inButton)
    {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        currencyFormat.setMaximumFractionDigits(0);

        BankAccount playerAccount = new BankAccount();
        PlayerInventory inventory = PlayerInventory.getInstance();
        int quantity = inventory.getItemQuantity(item);
        inButton.setText(item.getItemName() + "\n" + currencyFormat.format(item.getItemPrice()) + "\n" + "x" + quantity);
        playerBalanceText.setText(playerAccount.toString());
    }
}
