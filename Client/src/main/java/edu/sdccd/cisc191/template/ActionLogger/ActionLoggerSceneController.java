package edu.sdccd.cisc191.template.ActionLogger;

import edu.sdccd.cisc191.template.SceneControllers.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ActionLoggerSceneController extends SceneController
{
    @FXML
    private Label actionLogLabel;

    @FXML
    private TextField actionLogTextField;

    public void initialize()
    {
        displayActionLog();
        setUpTextFieldListener();
        //System.out.println(actionLogTextField.getText());
    }

    /**
     * Updates the actionLogLabel when a user enters a string into the search field.
     */
    private void setUpTextFieldListener()
    {
        ActionLogger actionLogger = new ActionLogger();
        actionLogTextField.setOnKeyReleased(event -> {
            String currentText = actionLogTextField.getText();

            SinglyLinkedList<String> searchResults = actionLogger.SearchActionLog(currentText);

            clearActionLog();
            if (searchResults != null)
            {
                displaySearchResults(searchResults);
            }
            else
            {
                displayActionLog();
            }
        });
    }

    /**
     * Displays the search results in the actionLogLabel.
     * @param searchResults the list of filtered search results.
     */
    private void displaySearchResults(SinglyLinkedList<String> searchResults) {
        actionLogLabel.setText(searchResults.toString());
    }

    /**
     * Updates the actionLogLabel to display a log of actions.
     */
    private void displayActionLog()
    {
        ActionLogger actionLogger = new ActionLogger();
        actionLogLabel.setText(actionLogger.getActionLog());
    }

    /**
     * Clears the action log by setting the text to "".
     */
    private void clearActionLog()
    {
        actionLogLabel.setText("");
    }
}
