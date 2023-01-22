package sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Music {
    Clip clip;

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL file = getClass().getResource("music.wav");
        //File file = new File("rsc/sound/music.wav");
        //InputStream file = new BufferedInputStream(Objects.requireNonNull(this.getClass().getResourceAsStream("/rsc/sound/music.wav")));
        //System.out.println(intermediary.available());
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }

    public void start() {
        clip.start();
    }

    // method to stop music
    public void stop() {
        clip.stop();
    }

    //Method to stop music without allowing continuation.
    public void close() {
        clip.close();
    }
}
