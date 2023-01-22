package Mechanics;

import Assets.Enemy;
import Assets.User;
import sound.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class CollisionDetection {

    private final User user;

    int collisions = 0;

    public CollisionDetection(User user) {
        this.user = user;
    }

    public boolean checkCollision(Enemy enemy) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        //Ends game when the enemy hits the user
        //This is done by checking if the enemy overlaps the Y positions of the user
        if (enemy.getEnemy().getMaxY() >= user.getY() && enemy.getEnemy().getMaxY() < user.getMaxY()) {
            collisions++;
            if (collisions == 6) {
                Sound.dyingDuckSound();
            }
            return collisions > 6;
        }
        return false;
    }
}
