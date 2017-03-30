package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.util.ArrayList;

/* The Bare bones of any Entity. Each entity has a Color
 * associated with it and a method to call on what that color is.
 * each Entity is responsible for setting it's own behaviors.
 * 
 * @author Albert Chen
 * @version 1.0
*/
public abstract class Entity {

    /**
     * A boolean variable to see if this particular Entity has moved this turn.
     */
    protected boolean moved;
    /**
     * Required by each entity to allow it to show a represented color on the
     * frame.
     */
    protected Color tileColor;

    /**
     * Each Entity has a Life Maximum value which is initialized in their
     * constructors depending on the entity.
     */
    protected int lifeTotal;

    /**
     * Each Entity has a Life value which is initialized in their constructors
     * depending on the entity.
     */
    protected int lifeMax;

    /**
     * Holds the red value of RGB.
     */
    protected int red;

    /**
     * Holds the green value of RGB.
     */
    protected int green;

    /**
     * Holds the blue value of RGB.
     */
    protected int blue;

    /**
     * Used for the maximum value of a Hex value.
     */
    protected static final int MAXRANGE = 255;

    /**
     * Value for the required food an entity must have before reproduction.
     */
    protected int requiredFood;

    /**
     * Value for the required Mates an entity must have before reproduction.
     */
    protected int requiredMate;

    /**
     * Value for the required space an entity must have before reproduction.
     */
    protected int requiredSpace;

    /** A constant for the number 7. */
    static final int SEVEN = 7;

    /**
     * Constructor for each Entity. initializes the default movement, and
     * required mate.
     */
    public Entity() {
        moved = false;
        requiredMate = 1;
    }

    /**
     * Obtains the Color of the Entity.
     * 
     * @return returns the associated color
     */
    public Color init() {
        return tileColor;
    }

    /**
     * Used in Visitor Pattern. Allows Carnivore to consume this entity.
     * 
     * @param carnivore
     *            the Carnivore that wants to eat this entity.
     * @return true
     */
    public boolean taste(Carnivore carnivore) {
        return false;
    }

    /**
     * Used in Visitor Pattern. Allows Omnivore to consume this entity.
     * 
     * @param omnivore
     *            the Omnivore that wants to eat this entity.
     * @return true
     */
    public boolean taste(Omnivore omnivore) {
        return false;
    }

    /**
     * Used in Visitor Pattern. Allows herbivore to consume this entity.
     * 
     * @param herb
     *            the herbivore that wants to eat this entity.
     * @return true
     */
    public boolean taste(Herbivore herb) {
        return false;
    }

    /**
     * Called when an Entity is moved. Decrements its lifeTotal and sets the
     * moved variable to true to show that it has taken a turn.
     * 
     * @param cell
     *            the Entity currently it within.
     */
    public void takeTurn(Cell cell) {
        if (lifeTotal <= 0) {
            this.die(cell);
        } else {
            lifeTotal--;
        }
        moved = true;
    }

    /**
     * Every entity must be able to die.
     */
    public abstract void die(Cell cell);

    /**
     * Sets the boolean moved to false to show that this animal has not taken a
     * turn.
     */
    public void setMoved() {
        moved = false;
    }

    /**
     * This function is expected for every animal to prevent animals from moving
     * twice or more within the same turn.
     * 
     * @return Returns if this animal has moved or not.
     */
    public boolean getMoved() {
        return moved;
    }

    /**
     * Ages the animal by decrementing the alpha value proportioned to an
     * entities life and maxmum life.
     */
    public void age() {
        int alphaValue = (int) (MAXRANGE * ((double) lifeTotal / lifeMax));
        tileColor = new Color(tileColor.getRed(), 
                tileColor.getGreen(), tileColor.getBlue(), alphaValue);
    }

    /**
     * Breeding function for entities.
     * @param adjCell
     *            an array of all adjacent cells
     * @return the cell a child could be born in
     */
    public Cell breed(Cell[] adjCell) {
        ArrayList<Cell> options = new ArrayList<Cell>();
        int mate = 0;
        int food = 0;
        int space = 0;
        for (int i = 0; i <= SEVEN && adjCell[i] != null; i++) {
            if (adjCell[i].getEntity() == null) {
                options.add(adjCell[i]);
                space++;
            } else if (adjCell[i].getEntity().getClass().equals(this.getClass())) {
                mate++;
            } else {
                food++;
            }
        }
        if (mate >= requiredMate && food >= requiredFood && space >= requiredSpace) {
            return options.get(RandomGenerator.nextNumber(options.size()));
        }
        return null;
    }

}
