package edu.sdccd.cisc191;

import edu.sdccd.cisc191.template.GameTimer.Timer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGameTimer
{
    private Timer timer;

    @BeforeEach
    void setUp()
    {
        timer = Timer.getInstance();
        timer.stop();
    }

    @Test
    void testTimerStarts()
    {
        timer.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(timer.getTime() > 0);
        timer.stop();
    }

    @Test
    void testTimerIncrementsCorrectly()
    {
        timer.resetTime();
        timer.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        int timeAfterThreeSeconds = timer.getTime();
        assertTrue(timeAfterThreeSeconds >= 2 && timeAfterThreeSeconds <= 4,
                "Expected time to be around 3 seconds, but got: " + timeAfterThreeSeconds);
        timer.stop();
    }

    @Test
    void testTimerStopsCorrectly()
    {
        timer.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop();
        int timeAfterStop = timer.getTime();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(timeAfterStop, timer.getTime());
    }

    @Test
    void testTimerResetsAfterStop()
    {
        timer.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.stop();
        timer = new Timer();
        assertEquals(0, timer.getTime());
    }
}
