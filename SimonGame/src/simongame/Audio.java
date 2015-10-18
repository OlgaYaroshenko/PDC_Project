package simongame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 * 
 * Class used to provide methods to call sound files
 */
public class Audio {    
    /**
     * Takes a String representing the path for a sound file and produces
     * that sound during the program runtime, at the point it's called
     * 
     * @param soundFile a string representing the file path of a sound file.
     */
    public static void playSound(String soundFile) {
        InputStream in = null;
        try {
            in = new FileInputStream(soundFile);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {} 
        
    }
}
