package edu.sdccd.cisc191.template.SceneControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The SceneController is responsible for handling the changing of scenes throughout the game. Mostly the main menu.
 * @author Tim Tran
 */
public class SceneController
{
    private Stage stage;
    private Scene scene;
    private Parent root;
    /**
     * A general method that can change the scene to an inputted .fxml file.
     * @param event allows the scene to be changed.
     * @param inScene a string input that determines which scene to change to via .fxml.
     * @throws IOException if the inputted scene could not be found or error.
     */
    public void switchScene(ActionEvent event, String inScene) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("/edu/sdccd/cisc191/template/" + inScene));

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * A method that switches the current scene to the main menu. Often associated with the back button.
     * @param event an ActionEvent used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToMainMenu(ActionEvent event) throws IOException
    {
        switchScene(event, "MainMenu.fxml");
    }

    /**
     * A method that switches the current scene to the launcher.
     * @param event an ActionEvent used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToLauncherPage(ActionEvent event) throws IOException
    {
        switchScene(event, "Launcher.fxml");
    }

    /**
     * A method that switches the current scene to the shop.
     * @param event an ActionEvent used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToShopMenu(ActionEvent event) throws IOException
    {
        switchScene(event, "Shop.fxml");
    }

    /**
     * A method that switches the current scene to the inventory scene.
     * @param event an ActionEvent used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToInventoryMenu(ActionEvent event) throws IOException
    {
        switchScene(event, "Inventory.fxml");
    }

    /**
     * A method that switches the current scene to the searching for item scene.
     * @param event an ActionEvent used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToSearchForItemScene(ActionEvent event) throws IOException
    {
        switchScene(event, "SearchForItem.fxml");
    }

    /**
     * A method that switches the current scene to the setting scene.
     * @param event an ActionEvent used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToSettingsScene(ActionEvent event) throws IOException
    {
        switchScene(event, "Settings.fxml");
    }

    public void switchToUpdateNotesScene(ActionEvent event) throws IOException
    {
        switchScene(event, "UpdateNotes.fxml");
    }

    public void switchToActionLoggerScene(ActionEvent event) throws IOException
    {
        switchScene(event, "ActionLogger.fxml");
    }

    public void switchToDatabaseStatsScene(ActionEvent event) throws IOException
    {
        switchScene(event, "DatabaseStats.fxml");
    }
}
