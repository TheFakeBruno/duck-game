package Assets;

import Mechanics.EnemyMotion;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Enemy {

    private final Picture enemy;

    private static int height;

    private static int width;

    private final EnemyMotion enemyMotion;

    private boolean drawn;

    private boolean toDelete;

    public Enemy(Assets assets, Rectangle[] userPositions) {
        int randomEnemy = (int) (Math.random() * 2);
        if (randomEnemy == 0) {
            enemy = new Picture(0, 0, "rsc/Enemies/badger.png");
            enemy.grow(-380, -406);
            enemy.translate(-380, -406);
            width = enemy.getWidth();
            height = enemy.getHeight();
            enemyMotion = new EnemyMotion(this, assets, userPositions);
        } else {
            enemy = new Picture(0, 0, "rsc/Enemies/quokka_enemy.png");
            enemy.grow(-812, -910);
            enemy.translate(-812, -910);
            width = enemy.getWidth();
            height = enemy.getHeight();
            enemyMotion = new EnemyMotion(this, assets, userPositions);
        }
    }

    public Picture getEnemy() {
        return enemy;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    //Sets the current instance of enemy ready for deletion
    public void erase() {
        toDelete = true;
    }

    public void setDrawn() {
        drawn = !drawn;
    }

    public boolean isDrawn() {
        return drawn;
    }

    public boolean isToDelete() {
        return toDelete;
    }

    public EnemyMotion getEnemyMotion() {
        return enemyMotion;
    }


}
