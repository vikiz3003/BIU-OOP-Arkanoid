//322907619 Victoria jeleznyak
package Arkanoid.game;


import Arkanoid.collections.GameEnvironment;
import Arkanoid.collections.SpriteCollection;
import Arkanoid.geometry.Point;
import Arkanoid.geometry.Rectangle;
import Arkanoid.geometry.Velocity;
import Arkanoid.logic.BallRemover;
import Arkanoid.logic.BlockRemover;
import Arkanoid.logic.Collidable;
import Arkanoid.logic.ScoreTrackingListener;
import Arkanoid.sprites.Sprite;
import Arkanoid.sprites.Paddle;
import Arkanoid.sprites.Block;
import Arkanoid.sprites.Ball;
import Arkanoid.sprites.ScoreIndicator;
import Arkanoid.utils.Counter;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Arkanoid.game.Game class initializes and runs the Arkanoid Arkanoid.game.
 * It manages Arkanoid.sprites, collidables, GUI components, and animation loop.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private Counter remainedBlocksCounter = new Counter();
    private Counter remainedBallsCounter = new Counter();
    private Counter score = new Counter();

    /**
     * Adds a collidable object to the Arkanoid.game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the Arkanoid.game.
     *
     * @param s the sprite object to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Constructs a Arkanoid.game.Game instance.
     * Initializes the Arkanoid.game environment and sprite collection.
     */
    public Game() {
        this.environment = new GameEnvironment(new ArrayList<>());
        this.sprites = new SpriteCollection(new ArrayList<>());
    }


    /**
     * Initializes the Arkanoid.game by creating GUI components, Arkanoid.sprites, and collidables.
     * Sets up the Arkanoid.game screen, blocks, paddles, and balls.
     */
    public void initialize() {
        // Create GUI, sleeper, and random generator
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sleeper = new Sleeper();
        Random rand = new Random();
        //create game screen
        double gameWidth = 750, gameHeight = 550; // Arkanoid.game.Game screen dimensions
        // Create and add the Arkanoid.game screen block
        Block screen = new Block(new Point(25, 25), gameWidth, gameHeight + 25, Color.blue);
        screen.addToGame(this);
        Block upperRectangle = new Block(new Point(0, 20), 800, 20, Color.gray);
        upperRectangle.addToGame(this);
        Block leftRectangle = new Block(new Point(0, 40), 25, 575, Color.gray);
        leftRectangle.addToGame(this);
        Block rightRectangle = new Block(new Point(775, 40), 25, 575, Color.gray);
        rightRectangle.addToGame(this);
        // Create and configure the balls
        Ball ball = new Ball(new Point(350, 300), 5, Color.red, environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt() * 360, 5));
        Ball seconBall = new Ball(new Point(300, 300), 5, Color.white, environment);
        seconBall.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt() * 360, 5));
        Ball thirdBall = new Ball(new Point(320, 270), 5, Color.green, environment);
        thirdBall.setVelocity(Velocity.fromAngleAndSpeed(rand.nextInt() * 360, 5));
        // create ballRemover
        BallRemover ballRemover = new BallRemover(this, remainedBallsCounter);
        // death area
        Block deathBlock = new Block(new Point(25, 575 + 25 - 1), 750, 25, Color.gray);
        deathBlock.addToGame(this);
        deathBlock.addHitListener(ballRemover);
        // create scoreindicator
        ScoreIndicator scoreIndicator =
                new ScoreIndicator(new Rectangle(new Point(0, 0), gui.getDrawSurface().getWidth(), 20), score);
        scoreIndicator.addToGame(this);
        // Add balls and paddle to the Arkanoid.game
        ball.addToGame(this);
        remainedBallsCounter.increase(1);
        seconBall.addToGame(this);
        remainedBallsCounter.increase(1);
        thirdBall.addToGame(this);
        remainedBallsCounter.increase(1);
        Paddle paddle = new Paddle(gui.getKeyboardSensor());
        paddle.addToGame(this);

        BlockRemover blockRemover = new BlockRemover(this, remainedBlocksCounter);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);

        double width = 40, height = 20; //the dimensions of each block
        // Create grid of blocks with different colors
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8 + i; j++) {
                Color color;
                switch (i) {
                    case 0:
                        color = Color.green;
                        break;
                    case 1:
                        color = Color.pink;
                        break;
                    case 2:
                        color = Color.white;
                        break;
                    case 3:
                        color = Color.yellow;
                        break;
                    case 4:
                        color = Color.red;
                        break;
                    default:
                        color = Color.cyan;
                        break;
                }
                Block block = new Block(new Point(gameWidth + 25 - width - j * width, 200 - i * height),
                        width, height, color);
                block.addToGame(this);
                remainedBlocksCounter.increase(1);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracker);
            }
        }
    }


    /**
     * Runs the Arkanoid.game animation loop.
     * Continuously updates and draws the Arkanoid.game components.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (remainedBlocksCounter.getValue() == 0) {
                score.increase(100);
                gui.close();
            }
            if (remainedBallsCounter.getValue() == 0) {
                gui.close();
            }
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes a specified {@link Collidable} object from the environment's list of collidables.
     *
     * @param c the {@link Collidable} object to be removed
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }

    /**
     * Removes a specified {@link Sprite} object from the list of sprites.
     *
     * @param s the {@link Sprite} object to be removed
     */
    public void removeSprite(Sprite s) {
        sprites.getSprites().remove(s);
    }
}