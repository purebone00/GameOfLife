package ca.bcit.comp2526.a2a;

import java.awt.Color;

/* A Plant. An extension of the Terrain Class.
 * 
 * @author Albert Chen
 * @version 1.0
*/
public class Plant extends Terrain {

    /**
     * Plant's constant Life value.
     */
    private static final int PLANTLIFE = 2;

    /**
     * Required food value.
     */
    private static final int REQMATE = 3;

    /**
     * Required space value.
     */
    private static final int REQSPACE = 2;

    /**
     * Plant Constructor. Initializes the color that represents a plant to
     * green.
     */
    public Plant() {
        red = 0;
        green = MAXRANGE;
        blue = 0;

        requiredMate = REQMATE;
        requiredSpace = REQSPACE;

        lifeTotal = PLANTLIFE;
        lifeMax = PLANTLIFE;
        tileColor = new Color(red, green, blue, MAXRANGE);
    }

    /**
     * Used in Visitor Pattern. Allows herbivore to consume this entity.
     * 
     * @param herb
     *            the herbivore that wants to eat this entity.
     * @return true
     */
    public boolean taste(Herbivore herb) {
        return true;
    }

    /**
     * Used in Visitor Pattern. Allows Omnivore to consume this entity.
     * 
     * @param omnivore
     *            the Omnivore that wants to eat this entity.
     * @return true
     */
    public boolean taste(Omnivore omnivore) {
        return true;
    }

    /**
     * After mating the plant spreads its children.
     * 
     * @return the new plant
     */
    public Terrain spread() {
        return new Plant();
    }
}
