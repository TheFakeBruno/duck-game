
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        Game game = new Game();

        while (!game.getEnd()) {
            game.Level1();
            game.retryLevel();
        }

    }
}
