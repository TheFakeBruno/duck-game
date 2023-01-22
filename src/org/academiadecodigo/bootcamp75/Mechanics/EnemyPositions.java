package Mechanics;

import Assets.Enemy;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class EnemyPositions {

    public static Rectangle[] createPositions(Picture screen, Rectangle[] userPositions) {
        Rectangle[] enemyPositions = new Rectangle[userPositions.length];
        for (int i = 0; i < enemyPositions.length; i++) {
            enemyPositions[i] = new Rectangle(0, (int) (screen.getHeight() / 2) + 80, Enemy.getWidth(), Enemy.getHeight());
            if (i == 0 || i == 1) {
                enemyPositions[i].translate((int) (screen.getWidth() / 2) - 18, 0);
            } else if (i == 2) {
                enemyPositions[i].translate((int) (screen.getWidth() / 2) + 5, 0);
            } else {
                enemyPositions[i].translate((int) (screen.getWidth() / 2) + 28, 0);
            }
            enemyPositions[i].draw();
            Canvas.getInstance().hide(enemyPositions[i]);
        }
        return enemyPositions;
    }


}
