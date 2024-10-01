package edu.sdccd.cisc191.template;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private static final int PORT = 1234;
    private static boolean isServerRunning = true;
    public static void main (String[] args)
    {
       try(ServerSocket serverSocket = new ServerSocket(PORT))
       {
           while(isServerRunning)
           {
               //listens for client
               Socket socket = serverSocket.accept();
               System.out.println("New client connected");
               handleClient(socket);
           }
       } catch (IOException e)
       {
           System.out.println("Server exception: " + e);
       }
    }

    public static void handleClient(Socket inSocket)
    {
        try(ObjectInputStream inputStream = new ObjectInputStream(inSocket.getInputStream()))
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(inSocket.getOutputStream());

            ServerRequest request = (ServerRequest) inputStream.readObject();

            if (request.getRequestType().equals("GET_UPDATE_NOTES"))
            {
                String updateNotesResponse = processUpdateNoteRequest();
                //sends response back to client
                outputStream.writeObject(updateNotesResponse);
                outputStream.flush();
            }
            else if (request.getRequestType().equals("SHUTDOWN"))
            {
                outputStream.writeObject("Shutting Down");
                outputStream.flush();
                isServerRunning = false;
            }

        }catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                inSocket.close();
            } catch (IOException e)
            {
                System.out.println("Error closing input stream");
            }
        }
    }

    public static String processUpdateNoteRequest()
    {
        UpdateNotesResponse response = new UpdateNotesResponse();
        return response.getUpdateNotes();
    }
}

