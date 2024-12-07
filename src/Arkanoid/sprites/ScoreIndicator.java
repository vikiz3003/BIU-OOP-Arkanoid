package Arkanoid.sprites;//322907619 Victoria jeleznyak
import Arkanoid.utils.Counter;
import Arkanoid.game.Game;
import Arkanoid.geometry.Rectangle;
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The Arkanoid.sprites.ScoreIndicator class represents a sprite that displays the player's score on a Arkanoid.game screen.
 * It implements the Arkanoid.sprites.Sprite interface to be drawable on a DrawSurface.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle rectangle;
    private Counter score;

    /**
     * Constructs a Arkanoid.sprites.ScoreIndicator with a specified rectangle and score counter.
     *
     * @param rectangle the rectangle defining the position and size of the score indicator
     * @param score     the counter representing the player's score
     */
    public ScoreIndicator(Rectangle rectangle, Counter score) {
        this.rectangle = rectangle;
        this.score = score;
    }

    /**
     * Draws the score indicator on a given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the score indicator
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.gray);
        surface.drawRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        surface.setColor(Color.BLACK);
        surface.drawText((int) (rectangle.getUpperLeft().getX() + rectangle.getWidth() / 2),
                (int) (rectangle.getUpperLeft().getY() + rectangle.getHeight() * 9 / 10),
                String.format("Score: %d", score.getValue()), 20);
    }

    /**
     * Performs actions when time passes in the Arkanoid.game (currently does nothing for Arkanoid.sprites.ScoreIndicator).
     */
    @Override
    public void timePassed() {

    }

    /**
     * Adds this score indicator to the specified Arkanoid.game.
     *
     * @param game the Arkanoid.game.Game object to add the score indicator to
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
