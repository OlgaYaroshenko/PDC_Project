
package simongame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * @author Francisco Vilches 1115994 | Olga Yaroshenko 15870568
 */
public class Audio {
    /**
     * Method used to reproduce a sound at a specified point during program 
     * runtime.
     * 
     * @param soundFile is a String representing a .wav file's path name. If 
     * path is not specified it means the .wav file is embedded within the 
     * project library.
     */
    public static void playSound(String soundFile) {
        URL soundUrl = Main.class.getResource(soundFile);
        InputStream in = null;
        try {
            in = soundUrl.openStream();
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {}
    }
}
