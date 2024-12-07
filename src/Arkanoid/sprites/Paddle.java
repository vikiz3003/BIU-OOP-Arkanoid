package Arkanoid.sprites;//322907619 Victoria jeleznyak

import Arkanoid.logic.Collidable;
import Arkanoid.utils.Constants;
import Arkanoid.game.Game;
import Arkanoid.geometry.Point;
import Arkanoid.geometry.Rectangle;
import Arkanoid.geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Arkanoid.sprites.Paddle class represents a paddle in a Arkanoid.game.
 * It implements both the Arkanoid.sprites.Sprite and Arkanoid.logic.Collidable interfaces.
 * The paddle can move left or right based on keyboard input and can collide with other Arkanoid.game objects.
 */
public class Paddle implements Sprite, Collidable {
    private Rectangle rect;
    private biuoop.KeyboardSensor keyboard;
    private int speed;
    private double gameWidth;


    /**
     * Constructs a paddle with a specified keyboard sensor.
     *
     * @param keyboard the keyboard sensor used to control the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        this.rect = new Rectangle(new Point(375, 565), 100, 10);
        this.keyboard = keyboard;
        this.speed = 5;
        this.gameWidth = 750;
    }


    /**
     * Moves the paddle to the left by a specified speed.
     * If the paddle reaches the left edge of the screen, it wraps around to the right edge.
     */
    public void moveLeft() {
        double newX = rect.getUpperLeft().getX() - speed;
        if (newX < 25) {
            newX = gameWidth - rect.getWidth();
        }
        rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
    }

    /**
     * Moves the paddle to the right by a specified speed.
     * If the paddle reaches the right edge of the screen, it wraps around to the left edge.
     */
    public void moveRight() {
        double newX = rect.getUpperLeft().getX() + speed;
        if (newX + rect.getWidth() > gameWidth) {
            newX = 25;
        }
        rect = new Rectangle(new Point(newX, rect.getUpperLeft().getY()), rect.getWidth(), rect.getHeight());
    }

    // Arkanoid.sprites.Sprite interface methods

    /**
     * Updates the paddle's position based on keyboard input.
     * If the left or right arrow keys are pressed, the paddle moves accordingly.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }


    /**
     * Draws the paddle on a given DrawSurface.
     *
     * @param d the DrawSurface on which the paddle is drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.yellow);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    // Arkanoid.logic.Collidable interface methods

    /**
     * Returns the collision rectangle of the paddle.
     *
     * @return the collision rectangle representing the paddle's bounds
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(rect.getUpperLeft(), rect.getWidth(), rect.getHeight());
    }


    /**
     * Handles the collision between the paddle and a ball.
     * Determines the new velocity of the ball based on the region of collision with the paddle.
     *
     * @param collisionPoint  the point of collision between the paddle and the ball
     * @param currentVelocity the current velocity of the ball before the collision
     * @return the new velocity of the ball after colliding with the paddle
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleWidth = rect.getWidth();
        double paddleHeight = rect.getHeight();
        double regionWidth = paddleWidth / 5;
        double hitX = collisionPoint.getX();
        double hitY = collisionPoint.getY();

        // Check if the ball hits the left or right side of the paddle
        if (hitY >= rect.getUpperLeft().getY() && hitY <= rect.getUpperLeft().getY() + paddleHeight) {
            if (Math.abs(rect.getUpperLeft().getX() - hitX) < Constants.E) { // left side
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            } else if (Math.abs(hitX - paddleWidth) < Constants.E) { // right side
                return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
            }
        }

        // Calculate ball speed
        double ballSpeed = Math.sqrt(currentVelocity.getDx() * currentVelocity.getDx()
                + currentVelocity.getDy() * currentVelocity.getDy());

        // Determine the region where the ball hits the paddle
        double relativeHitX = hitX - rect.getUpperLeft().getX();
        int region = (int) (relativeHitX / regionWidth) + 1;

        // Adjust region if it's at the very edge
        if (region < 1) {
            region = 1;
        }
        if (region > 5) {
            region = 5;
        }

        // Determine new velocity based on the hit region
        switch (region) {
            case 1:
                return Velocity.fromAngleAndSpeed(300, ballSpeed);
            case 2:
                return Velocity.fromAngleAndSpeed(330, ballSpeed);
            case 4:
                return Velocity.fromAngleAndSpeed(30, ballSpeed);
            case 5:
                return Velocity.fromAngleAndSpeed(60, ballSpeed);
            default:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
    }


    /**
     * Returns the type of object with which the paddle collides.
     *
     * @return the string "Arkanoid.sprites.Paddle" indicating the type of collidable object
     */
    @Override
    public String collideType() {
        return "Arkanoid.sprites.Paddle";
    }

    /**
     * Adds the paddle to the Arkanoid.game by adding it as both a sprite and collidable object.
     *
     * @param g the Arkanoid.game instance to which the paddle is added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
