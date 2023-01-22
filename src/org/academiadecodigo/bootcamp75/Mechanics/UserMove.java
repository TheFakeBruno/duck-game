package Mechanics;

import Assets.Assets;
import Assets.Screen;
import Assets.User;
import sound.Sound;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class UserMove implements KeyboardHandler {

    //Asset properties
    private final User user;

    private final Screen screen;

    //Properties for jumping animation
    private int verticalSpeed = 0;

    public static final int GRAVITY = 4;

    public static final int TERMINALVELOCITY = 50;

    public static final int JUMPSPEED = 50;

    private boolean hasJumped;

    //Properties for side motion animation
    private int horizontalSpeed = 0;

    public static final int MAXSIDESPEED = 51;

    public static final int SIDEACCELERATION = 8;

    public static final int SIDEDRAG = 10;

    public boolean maxSpeedHit;

    //Splits the screen into several positions for the user, changeable in Main

    //The default position of the user
    private int position = 2;

    private final Rectangle[] userPositionsDrawn;

    //Just keyboard
    private final Keyboard keyboard = new Keyboard(this);

    public UserMove(Assets assets, int numberOfPositions) {
        this.screen = assets.getScreen();
        this.user = assets.getUser();
        userPositionsDrawn = new UserPositions(assets).createPositions(numberOfPositions);
        setKeyboard();
    }

    //Gives the user the animation when changing positions
    public void changePos() {
        //When going right or left, increase speed until MAX
        //When MAX is hit, decrease speed
        if (horizontalSpeed > 0) {
            if (!maxSpeedHit) {
                horizontalSpeed += SIDEACCELERATION;
                moveRight();
                if (horizontalSpeed >= MAXSIDESPEED) {
                    horizontalSpeed = MAXSIDESPEED;
                    maxSpeedHit = true;
                    moveRight();
                }
            } else {
                if (horizontalSpeed - SIDEDRAG < 0) {
                    horizontalSpeed = 0;
                    return;
                }
                horizontalSpeed -= SIDEDRAG;
                moveRight();
            }
        } else if (horizontalSpeed < 0) {
            if (!maxSpeedHit) {
                horizontalSpeed -= SIDEACCELERATION;
                moveLeft();
                if (horizontalSpeed <= -MAXSIDESPEED) {
                    horizontalSpeed = -MAXSIDESPEED;
                    maxSpeedHit = true;
                    moveLeft();
                }
            } else {
                if (horizontalSpeed + SIDEDRAG > 0) {
                    horizontalSpeed = 0;
                    return;
                }
                horizontalSpeed += SIDEDRAG;
                moveLeft();
            }
        } else {
            maxSpeedHit = false;
            //Inverts the figure of the user
            if (!user.isInverted()) {
                user.getAvatar().translate(userPositionsDrawn[position].getX() - user.getAvatar().getX(), 0);
            } else {
                user.getAvatar().translate(userPositionsDrawn[position].getX() - user.getAvatar().getMaxX(), 0);
            }
        }
    }

    //Implements fall animation with jumps
    public void fall() {
        if ((user.getAvatar().getMaxY() + verticalSpeed) < (screen.getScreen().getMaxY() - 30)) {
            fallDown();
        } else {
            stopFalling();
            user.getAvatar().translate(0, (screen.getScreen().getMaxY()) - 30 - user.getAvatar().getMaxY());
        }
    }

    public void fallDown() {
        if (verticalSpeed < TERMINALVELOCITY) {
            verticalSpeed += GRAVITY;
        }
        moveDown();
    }

    public void stopFalling() {
        hasJumped = false;
        verticalSpeed = 0;
    }

    public void moveUp() {
        user.getAvatar().translate(0, verticalSpeed);
    }

    public void moveRight() {
        user.getAvatar().translate(horizontalSpeed, 0);
    }

    public void moveLeft() {
        user.getAvatar().translate(horizontalSpeed, 0);
    }

    public void moveDown() {
        user.getAvatar().translate(0, verticalSpeed);
    }

    //Sets the keyboard movement of the user
    public void setKeyboard() {
        KeyboardFactory.addKeys(keyboard, KeyboardEvent.KEY_LEFT);
        KeyboardFactory.addKeys(keyboard, KeyboardEvent.KEY_RIGHT);
        KeyboardFactory.addKeys(keyboard, KeyboardEvent.KEY_UP);
    }

    public boolean isJumping() {
        return hasJumped;
    }

    public int getPosition() {
        return position;
    }

    public Rectangle[] getUserPositionsDrawn() {
        return userPositionsDrawn;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        int keyNumber = keyboardEvent.getKey();
        switch (keyNumber) {
            //moveLeft
            case KeyboardEvent.KEY_LEFT:
                if (verticalSpeed != 0 || horizontalSpeed != 0 || maxSpeedHit || hasJumped || position < 1) {
                    break;
                }
                if (!user.isInverted()) {
                    user.invertUser();
                }
                position--;
                horizontalSpeed -= SIDEACCELERATION;
                moveLeft();
                break;
            //moveRight
            case KeyboardEvent.KEY_RIGHT:
                if (verticalSpeed != 0 || horizontalSpeed != 0 || maxSpeedHit || hasJumped || position > userPositionsDrawn.length-2) {
                    break;
                }
                if (user.isInverted()) {
                    user.invertUser();
                }
                position++;
                horizontalSpeed += SIDEACCELERATION;
                moveRight();
                break;
            //moveUp
            case KeyboardEvent.KEY_UP:
                if (horizontalSpeed != 0 || hasJumped) {
                    break;
                }
                verticalSpeed -= JUMPSPEED;
                hasJumped = true;
                    Sound.jumpSound(); // jumping sound

                moveUp();
                break;
            //else
            default:
                Text text = new Text(10, 10, "WTF Happened");
                text.draw();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}
