package ca.bcit.comp2526.a2a;

import java.awt.Color;

/* A Herbivore. An extension of the Animal Class.
 * 
 * @author Albert Chen
 * @version 1.0
*/
public class Herbivore extends Animal {

    /**
     * Herbivore's constant Life value.
     */
    private static final int HERBLIFE = 10;

    /**
     * Required food value.
     */
    private static final int REQFOOD = 2;

    /**
     * Required space value.
     */
    private static final int REQSPACE = 1;

    /**
     * Constructor for a Herbivore. Initializes it's starting life total to 4
     * and it's represented color to yellow.
     */
    public Herbivore() {
        super();
        red = MAXRANGE;
        green = MAXRANGE;
        blue = 0;

        requiredFood = REQFOOD;
        requiredSpace = REQSPACE;

        lifeTotal = HERBLIFE;
        lifeMax = HERBLIFE;
        tileColor = new Color(red, green, blue, MAXRANGE);
    }

    /**
     * Eats what is in the cell's terrain.
     * 
     * @param cell
     *            The Cell it's eating from
     */
    public void eat(Cell cell) {
        cell.setTerrain(null);
        lifeTotal = HERBLIFE;
        red = MAXRANGE;
        green = MAXRANGE;
        blue = 0;
        tileColor = new Color(red, green, blue, MAXRANGE);
    }

    /**
     * Taste tests the cell. tests for plant case.
     * 
     * @param cell
     *            the Cell it's scouting for food in.
     */
    public boolean tasteTest(Cell cell) {
        if (cell.getTerrainExists()) {
            return (cell.getTerrain()).taste(this);
        }
        return false;
    }

    /**
     * Used in Visitor Pattern. Allows Carnivore to consume this entity.
     * 
     * @param carnivore
     *            the Carnivore that wants to eat this entity.
     * @return true
     */
    public boolean taste(Carnivore carnivore) {
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
     * After mating the Herbivore gives birth to a child.
     * 
     * @return the new Herbivore
     */
    public Animal giveBirth() {
        return new Herbivore();
    }
}
