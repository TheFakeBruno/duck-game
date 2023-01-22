/**
 * this Class is a collection of sound clips utils. All methods are static. The streams ate not buffered
 * Sounds: duck quack (see in keyboard key SPACE), dying sound (see collision method), jumping (see userMove in keyboard),
 */

package sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Sound {

    public static void quackSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Clip duckClip;
        URL quackSound = Sound.class.getResource("duckDound.wav");
        try {
            //File quackSound = new File("duckDound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(quackSound);
            duckClip = AudioSystem.getClip();
            duckClip.open(audioInputStream);
            duckClip.start();
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dyingDuckSound() {

        Clip dyingDuckClip;
        URL dyingDuckSound = Sound.class.getResource("duckdying.wav");
        //File dyingDuckSound = new File("duckdying.wav");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(dyingDuckSound);
            dyingDuckClip = AudioSystem.getClip();
            dyingDuckClip.open(audioInputStream);
            dyingDuckClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }


    public static void jumpSound() {
        Clip jumpClip;
        URL jumpSound = Sound.class.getResource("jump-sound.wav");
        //File jumpSound = new File("jump-sound.wav");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(jumpSound);
            jumpClip = AudioSystem.getClip();
            jumpClip.open(audioInputStream);
            jumpClip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

    }

}
