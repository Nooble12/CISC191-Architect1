package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.ServerRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestNetworkSendUpdateNotes
{
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;
    @Test
    public void testNetworkSendUpdateNotes() throws IOException {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            ServerRequest request = new ServerRequest("GET_UPDATE_NOTES");
            outputStream.writeObject(request);
            outputStream.flush();

            String updateNotes = (String) inputStream.readObject();
            assertNotNull("Update notes should not be null", updateNotes);
        } catch (IOException | ClassNotFoundException e)
        {
            System.err.println(e);
        }
    }
}
