package edu.sdccd.cisc191.template;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer
{

    /**
     * Plays a sound effect from resources folder
     * @param inFileName the file name in .wav format
     */
    public void playSound(String inFileName)
    {
        URL soundURL = getClass().getResource("/SoundFiles/" + inFileName);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioIn);
            System.out.println("Playing sound...");
            audioClip.start();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}
