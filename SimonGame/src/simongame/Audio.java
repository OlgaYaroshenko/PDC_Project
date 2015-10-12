
package simongame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Francisco
 */
public class Audio {
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
