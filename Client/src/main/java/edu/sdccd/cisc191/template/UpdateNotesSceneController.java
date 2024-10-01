package edu.sdccd.cisc191.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.*;
import java.net.Socket;

public class UpdateNotesSceneController
{
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    @FXML
    private Label updateNotesLabel;

    public void initialize()
    {
        updateNotesLabel.setWrapText(true);
        setUpdateNotesLabel();
    }

    /**
     * Sets the update notes label by getting the .txt string from a local server to demonstrate Java networking using sockets.
     */
    private void setUpdateNotesLabel()
    {
        try(Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT))
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            ServerRequest request = new ServerRequest("GET_UPDATE_NOTES");
            outputStream.writeObject(request);
            outputStream.flush();

            String updateNotes = (String) inputStream.readObject();
            updateNotesLabel.setText(updateNotes);

            updateNotesLabel.setText(updateNotes);
        }catch (IOException | ClassNotFoundException e)
        {
            updateNotesLabel.setText("Could not load update notes or could not find server!" + "\n" + "did you run the server before the client?");
        }
    }

    /**
     * Switches the scene back to the main menu when the player presses the back button.
     * @param event allows the scene to change.
     * @throws IOException if the main menu .fxml file could not be found.
     */
    public void switchToMainMenu(ActionEvent event) throws IOException
    {
        SceneController controller = new SceneController();
        controller.switchToMainMenu(event);
    }
}
