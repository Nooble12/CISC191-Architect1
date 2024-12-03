package edu.sdccd.cisc191.template;
import edu.sdccd.cisc191.template.GameTimer.Timer;
import edu.sdccd.cisc191.template.PlayerData.BankAccount;
import edu.sdccd.cisc191.template.PlayerData.PlayerDataManager;
import edu.sdccd.cisc191.template.PlayerData.PlayerStatistics;
import edu.sdccd.cisc191.template.Repository.PlayerStatisticRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The main class that will load player data and load the JavaFx UI.
 * @author Tim Tran
 * @version 1.0
 */
@SpringBootApplication
public class Main extends Application
{
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;
    private static ApplicationContext context;

    private static Timer gameTimer = Timer.getInstance();

    private static PlayerDataManager localDataManager = new PlayerDataManager();

    public static void main(String[] args)
    {
        localDataManager.loadPlayerData();
        gameTimer.start();
        context = SpringApplication.run(Main.class, args); //database offline for now

        launch(args);
    }

    /**
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    public void start(Stage stage)
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("Launcher.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/" + "cheese_ghost.png")));
            stage.setScene(scene);
            stage.setTitle("CISC 191 Java Game");
            stage.show();

            stage.setOnCloseRequest(event -> {
                gameTimer.stop();
                sendLocalServerShutdownRequest();
                localDataManager.savePlayerData();

            });

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *Sends a shutdown request to the local server to turn off.
     */
    private static void sendLocalServerShutdownRequest()
    {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream()))
        {

            // Send shutdown request
            ServerRequest request = new ServerRequest("SHUTDOWN");
            outputStream.writeObject(request);
            outputStream.flush();
            System.out.println((String) inputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Shutting Down...");
        }
    }

    /**
     * On application stop, also disconnect the client from the AWS database.
     */
    @Override
    public void stop() {
        if (context != null)
        {
            SpringApplication.exit(context);
        }
        System.out.println("JavaFX Application Stopped. Spring Boot Application Shutting Down...");
    }

    /**
     * On application startup, the client will retrieve data from the AWS database.
     * @param repository The database repository interface.
     * @return the player's data from the database.
     */
    @Bean
    public CommandLineRunner loadPlayerStatisticData(PlayerStatisticRepository repository)
    {
        return (args) ->
        {
            PlayerStatistics stats = repository.findById(1L).orElse(null);
            BankAccount account = new BankAccount();
            if (stats != null)
            {
                account.setTotalMoneyGained(stats.getTotalMoneyGained());
                account.setTotalMoneySpent(stats.getTotalMoneySpent());
            } else
            {
                System.out.println("No existing player data found. Starting fresh.");
            }
        };
    }

    /**
     * Listens for when the game closes to disconnect the client from the database to ensure that all instances of the game are closed.
     * @param repository the player statistic repository interface.
     * @return the repository.
     */
    @Bean
    public ApplicationListener<ContextClosedEvent> shutdownListener(PlayerStatisticRepository repository)
    {
        return event ->
        {
            PlayerStatistics stats = repository.findById(1L).orElse(new PlayerStatistics());
            BankAccount account = new BankAccount();

            stats.setTotalMoneyGained(account.getTotalMoneyGained());
            stats.setTotalMoneySpent(account.getTotalMoneySpent());

            repository.save(stats);

            System.out.println("Player data saved successfully.");
        };
    }

} //end class Server