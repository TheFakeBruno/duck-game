package Mechanics;

import Assets.Assets;
import Assets.Screen;
import Assets.User;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class UserPositions {

    private final User user;

    private final Screen screen;

    UserPositions(Assets assets) {
        user = assets.getUser();
        screen = assets.getScreen();
    }

    public Rectangle[] createPositions(int numberOfPositions) {
        Rectangle[] userPositions = new Rectangle[numberOfPositions];
        for (int i = 0; i < userPositions.length; i++) {
            //The position of the first one will be padding + screenWidth/6
            userPositions[i] = new Rectangle(Assets.PADDING + 2 + (int) (screen.getScreen().getWidth() / numberOfPositions * (i+1)) - (int) (user.getAvatar().getWidth()*1.7), user.getY(), user.getAvatar().getWidth(), user.getAvatar().getHeight());
            userPositions[i].draw();
            Canvas.getInstance().hide(userPositions[i]);
        }
        return userPositions;
    }
}
