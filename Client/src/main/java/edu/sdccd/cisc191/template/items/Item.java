package edu.sdccd.cisc191.template.items;

import edu.sdccd.cisc191.template.UseItemSceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The Item class is a super class that allows child Items to be created which the player can use.
 * Has methods and variables that define, price, name, description, and percent to win.
 * @author Tim Tran
 */
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private int price;
    private String itemName;
    private String description;
    private int maxItemReward;
    private int percentToWin;
    private int moneyMultiplier;

    /**
     * Item class constructor sets the price, name, and desc when called.
     * @param price the cost to purchase the item in the shop.
     * @param itemName the name of the item.
     * @param description a String that describes the item.
     */
    public Item(int price, String itemName, String description)
    {
        this.price = price;
        this.itemName = itemName;
        this.description = description;
    }

    /**
     * @return the price of the item to purchase.
     */
    public int getItemPrice()
    {
        return price;
    }

    /**
     * @return String of the item's name.
     */
    public String getItemName()
    {
        return itemName;
    }

    /**
     * @return String of the item's description.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return the max reward in money that an item can give.
     */
    public int getMaxItemReward()
    {
        return maxItemReward;
    }

    /**
     * @return the percent that an item can give a win or lost outcome.
     */
    public int getPercentToWin()
    {
        return percentToWin;
    }

    /**
     * @return a list of messages that can be displayed on the screen after the player uses the item.
     */
    public ArrayList<String> getUseItemMessages()
    {
        ArrayList<String> list = new ArrayList<>();
        return list;
    }

    /**
     * @return an ArrayList of String messages that can be displayed if the player rolls a loss outcome.
     */
    public ArrayList<String> getLoseItemMessages()
    {
        ArrayList<String> list = new ArrayList<>();
        return list;
    }

    /**
     * Checks if 2 objects are the same. This is used to see if an item can be "stacked" in the inventory.
     * Rather than have each GridPane cell contain one instance of an Item, 2 like objects can be combined into one button and stacked.
     * Checks if the objects have the same name, price, and description.
     * @param o the input object.
     * @return a boolean if the objects are the same.
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return price == item.price &&
                Objects.equals(itemName, item.itemName) &&
                Objects.equals(description, item.description);
    }

    /**
     * @return a hash code for the item.
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(price, itemName, description);
    }

    /**
     * @return the item's name.
     */
    public String toString()
    {
        String itemString;

        itemString = getDescription() + "\n" +
                "Base Max Reward: $" + getMaxItemReward() + "\n" +
                "Percent to Win: " + getPercentToWin() + "%" + "\n";
        return itemString;
    }

    /**
     * Method to be Override when the player clicks on or uses an item.
     * @param item the selected item to be used.
     * @param actionEvent used to switch the scenes to display the item being used.
     */
    public void useItem(Item item, ActionEvent actionEvent)
    {
        // Left empty for override.
    }

    /**
     * @return the item's money multiplier.
     */
    public int getMoneyMultiplier()
    {
        return moneyMultiplier;
    }

    /**
     * Switches the scene to display the item being used and the results of the outcome.
     * @param event to be used to switch the scene.
     * @param inItem the selected input item.
     * @param fxmlFile is a String inputted to change the scene to said String.
     * @throws IOException if the .fxml file could not be found.
     */
    public void switchToItemScene(ActionEvent event, Item inItem, String fxmlFile) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/sdccd/cisc191/template/" + fxmlFile));
        Parent root = loader.load();

        // Set the controller with the item
        UseItemSceneController controller = loader.getController();
        controller.setItem(inItem);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
