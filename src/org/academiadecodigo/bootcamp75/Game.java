import menus.StartMenu;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import sound.Music;
import Mechanics.*;
import Assets.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Game implements KeyboardHandler {

    private static final Random random = new Random();

    private Music mainMusic;

    private boolean musicOn = true;

    //while loop end condition
    boolean stop;

    boolean end;

    public Game() throws InterruptedException {
        Keyboard keyboard = new Keyboard(this);
        KeyboardFactory.addKeys(keyboard, KeyboardEvent.KEY_R);
        KeyboardFactory.addKeys(keyboard, KeyboardEvent.KEY_M);
        KeyboardFactory.addKeys(keyboard, KeyboardEvent.KEY_Q);
        StartMenu startMenu = new StartMenu();
        startMenu.init();
    }

    public void Level1() throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        mainMusic = new Music();
        mainMusic.play();

        if (!musicOn) {
            mainMusic.stop();
        }

        Assets assets = new Assets("rsc/Levels/someLevel1.jpg", "rsc/User/userimage.png");
        UserMove userMove = new UserMove(assets, 5);
        CollisionDetection collisionDetection = new CollisionDetection(assets.getUser());
        LinkedList<Enemy> enemies = new LinkedList<>();

        // points
        Points points = new Points();
        /** These next two variables are responsible for the amount of time that passes between two enemies showing up.
         *
         * This method is based on the use of a bell-shaped curve, which by default has center in 1 and a standard deviation of 1.
         * A standard deviation is how likely something is to appear close to the center. The lower the std,
         * the more likely it is for the value obtained to be closer to the center.
         * For this implementation, a center of 1 would mean that all enemies would take on average just one loop cycle to appear, which is 33 ms.
         * A std of 1 means that enemies could take anywhere from -1 to 3 cycles to appear. Negative cycles cannot logically exist in a game.
         *
         * The guassianNormal variable raises the center to 150 cycles, about 5 seconds.
         * The gaussianStd variable makes the enemies take anywhere from 100 to 200 cycles to appear (3 to 7 seconds).
         *
         * The more enemies are dodged, the lower these numbers get, since they are updated whenever an enemy is deleted.
         *
         * As a rule of thumb, the standard deviation should be 22% of the normal, to avoid negative cycles.
         */
        int gaussianNormal = 150;
        int gaussianStd = (int) (gaussianNormal * 0.22);

        //Rectangle the same color as the backscreen to not have enemies go above the backscreen
        Rectangle lowerRectangle = new Rectangle();
        //Counter used to create new enemies randomly
        int counter = 0;
        stop = false;
        points.pointsCounter();
        while (!stop) {
            if (end) {
                stop = true;
                break;
            }
            userMove.fall();
            userMove.changePos();
            //swap normal center and std.dev. with properties
            double randomDouble = gaussianNormal + random.nextGaussian() * gaussianStd;
            int cycleToMove = (int) randomDouble;

            if (counter > cycleToMove) {
                enemies.add(new Enemy(assets, userMove.getUserPositionsDrawn()));
                counter = 0;
            }

            for (Enemy enemy : enemies) {
                //Only makes a new enemy appear if it exists
                if (!enemy.isDrawn()) {
                    enemy.getEnemyMotion().showEnemy();
                }

                //Every enemy in the LinkedList moves here. With each iteration of the for loop, they move one at a time
                //However, this occurs so fast that we can't visually notice
                enemy.getEnemyMotion().move();

                //Ends game if the enemy collides with the user
                //This is done by checking if the position of the enemy and of the user are the same, and then checking the enemy's MaxY coordinates
                if (!userMove.isJumping() && enemy.getEnemyMotion().getEnemyPosition() == userMove.getPosition()) {
                    stop = collisionDetection.checkCollision(enemy);
                }
            }

            //Deletes all enemies that have hit the end of the screen
            //Also makes the level progressively more difficult, by reducing the time between spawns
            if (enemies.size() > 0 && enemies.getFirst().isToDelete()) {
                enemies.removeFirst();
                gaussianNormal = gaussianNormal > 15 ? gaussianNormal - 3 : 15;
                points.pointsCounter(); //points
            }

            counter++;
            //Forces enemies to never be at the front of the screen
            assets.getUser().refresh();
            assets.getScreen().refreshRectangle();
            Thread.sleep(33);
        }
        for (Enemy enemy : enemies) {
            enemy.getEnemy().delete();
        }
    }

    public void retryLevel() {
        mainMusic.close();
        Canvas.pause();
    }

    public boolean getEnd() {
        return end;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_M:
                if (musicOn) {
                    mainMusic.stop();
                    musicOn = false;
                    break;
                }
                mainMusic.start();
                musicOn = true;
                break;
            case KeyboardEvent.KEY_R:
                stop = true;
                break;
            case KeyboardEvent.KEY_Q:
                end = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}
