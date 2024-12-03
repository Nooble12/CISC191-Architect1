package edu.sdccd.cisc191.template.SceneControllers;

import edu.sdccd.cisc191.template.ActionLogger.ActionLogger;
import edu.sdccd.cisc191.template.RandomClass;
import edu.sdccd.cisc191.template.SearchGame.SearchGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class SearchSceneController extends SceneController
{
    @FXML
    private Label resultsDescription;
    @FXML
    private Label rewardsDescription;

    private SearchGame searchGame;

    public void initialize()
    {
        searchGame = new SearchGame();
        runGame();
    }

    /**
     * Runs the game and displays the results.
     */
    public void runGame()
    {
        ActionLogger logger = new ActionLogger();
        logger.logAction("Searched For Item");
        updateResultsDescription();
        if (searchGame.didPlayerWin())
        {
            rewardsDescription.setText(searchGame.pickRandomReward());
        }
        else
        {
            rewardsDescription.setText(searchGame.playerLose());
        }
    }

    /**
     * Updates the search description with a random search message.
     */
    private void updateResultsDescription()
    {
        RandomClass generator = new RandomClass();
        String randomString = generator.pickRandomString(getRandomSearchMessages());
        resultsDescription.setText(randomString);
    }

    /**
     * Creates an ArrayList that will store random search messages.
     * @return an ArrayList of String messages to be displayed.
     */
    private ArrayList<String> getRandomSearchMessages()
    {
        ArrayList<String> messages = new ArrayList<>();
        messages.add("Searching a warehouse...");
        messages.add("Searching a trash can...");
        messages.add("Searching a computer...");
        messages.add("Searching a park...");
        return messages;
    }
}
