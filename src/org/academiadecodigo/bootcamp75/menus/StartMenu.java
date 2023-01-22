package menus;

import Mechanics.KeyboardFactory;
import sound.Sound;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class StartMenu implements KeyboardHandler {

    private Keyboard keyboard;
    Rectangle bgRectangle;
    Picture bgPicture;
    Picture titlePicture;
    Picture startGamePicture;
    boolean isKeyPressed = false;

    public StartMenu() {
        this.keyboard = new Keyboard(this);
    }

    public void init() throws InterruptedException {

        KeyboardFactory.addKeys(keyboard, KeyboardEvent.KEY_SPACE);

        bgRectangle = new Rectangle(0, 0, 1920, 1080);
        bgRectangle.setColor(new Color(49,43,91));
        bgRectangle.fill();

        bgPicture = new Picture(0, 0, "rsc/menus/Beautiful Landscape (1920x1080).png");
        bgPicture.draw();

        titlePicture = new Picture(1920 / 2, 1080 / 2, "rsc/menus/The Ducky Duck.png");
        titlePicture.translate(-375, -400);
        titlePicture.grow(0, 0);
        titlePicture.draw();

        startGamePicture = new Picture(1920 / 2, 1080 / 2, "rsc/menus/cooltext421478493059167.png");
        startGamePicture.translate(-400, 150);
        startGamePicture.grow(-100, -12);
        startGamePicture.draw();


        while (true) {

            // without something here (like a Thread.sleep) this goes wrong!!
            Thread.sleep(33);

            if (isKeyPressed) {
                titlePicture.translate(0, -7);
                startGamePicture.translate(0, 7);

                if (startGamePicture.getMaxY() > bgPicture.getMaxY()) {
                    bgPicture.delete();
                    break;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {

            isKeyPressed = true;
            try {
                Sound.quackSound();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}