package Assets;

import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Screen {

    private Picture level1Image;

    Rectangle lowerRectangle;

    public Screen(String imageLink) {
        level1Image = new Picture(Assets.PADDING, Assets.PADDING, imageLink);
        level1Image.grow(150, 71);
        level1Image.translate(150, 71);
        lowerRectangle = new Rectangle(0, level1Image.getMaxY(), 1920, 1080- level1Image.getHeight());
        lowerRectangle.setColor(new Color(49,43,91));
    }

    public Picture getScreen() {
        return level1Image;
    }

    public void refreshRectangle() {
        Canvas.getInstance().hide(lowerRectangle);
        Canvas.getInstance().show(lowerRectangle);
    }

    public Rectangle getLowerRectangle() {
        return lowerRectangle;
    }

}

