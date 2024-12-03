package edu.sdccd.cisc191.template.GameTimer;
/**
 * The Timer class is used in tandem with the ActionLogger feature which will record when a player commits and action during the duration of the game.
 */
public class Timer
{
    private static Timer instance;
    private Thread timerThread;
    private boolean running;
    private int seconds;
    private int minutes;

    /**
     * Sets the seconds and minutes integers to zero when a new Timer object is created.
     */
    public Timer()
    {
        running = false;
        seconds = 0;
        minutes = 0;
    }

    /**
     * Gets the instance using a singleton method.
     * @return the single Timer instance of the class.
     */
    public static Timer getInstance()
    {
        if (instance == null)
        {
            instance = new Timer();
        }
        return instance;
    }

    /**
     * Starts the timer
     */
    public void start()
    {
        if (running)
        {
            System.out.println("Timer is already running.");
            return;
        }

        running = true;
        timerThread = new Thread(() ->
        {
            while (running)
            {
                //System.out.println("Timer: " + seconds + " seconds");
                seconds++;

                if (seconds >= 60)
                {
                    seconds = 0;
                    minutes++;
                }

                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    System.out.println("Timer interrupted!");
                    break;
                }
            }
        });
        timerThread.start();
    }

    /**
     * Stops the timer thread. Can be resumed by calling the Start method.
     */
    public void stop()
    {
        running = false;
        if (timerThread != null)
        {
            timerThread.interrupt();
        }
    }

    /**
     * Resets the time by setting seconds and minutes integers to zero.
     */
    public void resetTime()
    {
        seconds = 0;
        minutes = 0;
    }

    /**
     * @return returns the time in seconds
     */
    public int getTime()
    {
        return seconds;
    }

    /**
     * @return the time in seconds and minutes.
     */
    public String toString()
    {
        return minutes + ":" + seconds + " | ";
    }
}

