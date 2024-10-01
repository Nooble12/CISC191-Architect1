package edu.sdccd.cisc191.template.PlayerSettings;

import edu.sdccd.cisc191.template.SceneController;
import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Controls the Settings.fxml scene.
 * @author Tim Tran
 */
public class SettingsController
{
    /**
     * Switches the scene to the main menu. Uses the SceneController class.
     * @param event used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToMainMenu(ActionEvent event) throws IOException
    {
        SceneController controller = new SceneController();
        controller.switchToMainMenu(event);
    }

    /**
     * Switches the scene to the dev settings scene.
     * @param event used to switch the scene.
     * @throws IOException if the scene could not be found.
     */
    public void switchToDeveloperSettingsScene(ActionEvent event) throws IOException
    {
        SceneController controller = new SceneController();
        controller.switchScene(event, "DeveloperSettings.fxml");
    }
}
