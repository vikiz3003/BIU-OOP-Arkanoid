package Arkanoid.logic;
/**
 * The Arkanoid.logic.HitNotifier interface should be implemented by any object that can notify
 * listeners about hit events.
 */
public interface HitNotifier {


    /**
     * Adds a hit listener to be notified about hit events.
     *
     * @param hl the Arkanoid.logic.HitListener to add
     */
    void addHitListener(HitListener hl);


    /**
     * Removes a hit listener from being notified about hit events.
     *
     * @param hl the Arkanoid.logic.HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
