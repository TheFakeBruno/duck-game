package Mechanics;

import Assets.Assets;
import Assets.Enemy;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.Random;

public class EnemyMotion {

    private final Random random = new Random();

    private final Picture screen;

    private final Rectangle[] userPositions;

    private final Enemy enemy;

    private final Rectangle[] enemyPositions;

    /** The same logic for enemy spawn timing was used for enemy spawn position.
     * This way, spawn position is preferred in the center of the screen*/
    private int enemyPosition = (int) Math.abs(Math.round(2+random.nextGaussian()));

    public EnemyMotion(Enemy enemy, Assets assets, Rectangle[] userPositions) {
        this.enemy = enemy;
        screen = assets.getScreen().getScreen();
        this.userPositions = userPositions;
        enemyPositions = EnemyPositions.createPositions(screen, userPositions);
    }

    //Shows enemy when drawn, otherwise makes it disappear
    public void showEnemy() {
        if (enemyPosition > 4) {
            enemyPosition = 4;
        }
        enemy.getEnemy().translate(enemyPositions[enemyPosition].getX() - enemy.getEnemy().getX(), enemyPositions[enemyPosition].getY() - enemy.getEnemy().getY());
        Canvas.getInstance().show(enemy.getEnemy());
        enemy.setDrawn();
    }

    //Enemy has its own positions and the user positions
    //The enemy will move from its own position to the corresponding position of the user
    public void move() {
        int userPosition = enemyPosition;
        int moveX = (((userPositions[userPosition].getX() - enemyPositions[enemyPosition].getX()) / 30));
        int moveY = Math.abs(((enemyPositions[enemyPosition].getY() - userPositions[userPosition].getY()) / 30));
        enemy.getEnemy().translate(moveX, moveY);

        //Grows the enemy as it gets closer to the front of the screen
        if (enemy.getEnemy().getY() < (userPositions[userPosition].getY())) {
            enemy.getEnemy().grow((int) (65 / 30), (int) (69 / 30));
            enemy.getEnemy().translate((int) (65 / 30), (int) (69 / 30));
        } else { //Keeps the same movement of the user when growth stopped
            enemy.getEnemy().translate((int) (65/30), (int) (69 / 15));
        }
        //Hide enemy picture when it dissappears from the screen
        if (enemy.getEnemy().getY() >= screen.getMaxY() - 30 || enemy.getEnemy().getMaxX() <= screen.getX() + 30 || enemy.getEnemy().getX() >= screen.getMaxX() - 30) {
            enemy.getEnemy().delete();
            enemy.erase();
        }

    }

    public int getEnemyPosition() {
        return enemyPosition;
    }

}
