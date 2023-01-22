package Assets;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class User {

    private final Picture avatar;

    private boolean inverted;

    private final int y;

    private final int maxY;

    //Sets the picture size of the user upon instantiation
    public User(Screen screen, String userImageLink) {
        avatar = new Picture(0, 0, userImageLink);
        avatar.grow(-330, -398);
        avatar.translate(-330, -398);
        y = screen.getScreen().getMaxY() - avatar.getHeight() - 30;
        maxY = y + avatar.getHeight();
        avatar.translate(Assets.PADDING + (int) (screen.getScreen().getWidth() / 2) - (int) (avatar.getWidth() / 2), y);
        avatar.draw();
    }

    public Picture getAvatar() {
        return avatar;
    }

    //Inverts the user
    public void invertUser() {
        if (!inverted) {
            avatar.grow(-150, 0);
        } else {
            avatar.grow(150, 0);
        }
        inverted = !inverted;
    }

    public boolean isInverted() {
        return inverted;
    }

    public int getY() {
        return y;
    }

    public int getMaxY() {
        return maxY;
    }

    //Avoids the enemies from being visually on top of the user
    public void refresh() {
        Canvas.getInstance().hide(avatar);
        Canvas.getInstance().show(avatar);
    }
}
