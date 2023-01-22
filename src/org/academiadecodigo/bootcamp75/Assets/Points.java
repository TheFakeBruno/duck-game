package Assets;

import org.academiadecodigo.simplegraphics.graphics.Text;

public class Points {

    private int points;

    private final Text textPoints;

    public Points() {
        this.points = 0;
        this.textPoints = new Text(100,100, String.valueOf(0) );
        textPoints.grow(50,50);
    }

    // points counter++ and points draw
    public int pointsCounter() {
        textPoints.setText(String.valueOf(points));
        textPoints.draw();
        return points++;
    }

}
