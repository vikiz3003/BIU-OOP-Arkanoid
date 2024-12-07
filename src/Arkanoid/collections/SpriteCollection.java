//322907619 Victoria jeleznyak
package Arkanoid.collections;

import java.util.LinkedList;
import java.util.List;

import Arkanoid.sprites.Sprite;
import biuoop.DrawSurface;

/**
 * A collection of Arkanoid.sprites.Sprite objects.
 * Responsible for managing and updating all Arkanoid.sprites in the Arkanoid.game.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructs a new Arkanoid.collections.SpriteCollection with a given list of Arkanoid.sprites.
     *
     * @param sprites the initial list of Arkanoid.sprites
     */
    public SpriteCollection(List<Sprite> sprites) {
        this.sprites = sprites;
    }


    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Notifies all Arkanoid.sprites that time has passed, calling their timePassed() method.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : new LinkedList<>(sprites)) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all Arkanoid.sprites on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the Arkanoid.sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Retrieves the list of Arkanoid.sprites associated with this object.
     *
     * @return The list of Arkanoid.sprites.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
}

