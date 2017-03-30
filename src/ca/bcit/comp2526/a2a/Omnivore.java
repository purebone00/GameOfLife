package ca.bcit.comp2526.a2a;

import java.awt.Color;

/* A Omnivore. An extension of the Animal Class.
 * 
 * @author Albert Chen
 * @version 1.0
*/
public class Omnivore extends Animal {

    /**
     * Omnivore's constant Life value.
     */
    private static final int OMNILIFE = 2;

    /**
     * Required food value.
     */
    private static final int REQFOOD = 3;

    /**
     * Required space value.
     */
    private static final int REQSPACE = 3;

    /**
     * Constructor for a Omnivore. Initializes it's starting life total to 2 and
     * it's represented color to blue.
     * 
     */
    public Omnivore() {
        super();
        red = 0;
        green = 0;
        blue = MAXRANGE;

        requiredFood = REQFOOD;
        requiredSpace = REQSPACE;

        lifeTotal = OMNILIFE;
        lifeMax = OMNILIFE;
        tileColor = new Color(red, green, blue);
    }

    /**
     * Eats what is in the cell's terrain.
     * 
     * @param cell
     *            The Cell it's eating from
     */
    public void eat(Cell cell) {
        cell.setAnimal(null);
        cell.setTerrain(null);
        lifeTotal = OMNILIFE;
        red = 0;
        green = 0;
        blue = MAXRANGE;
        tileColor = new Color(red, green, blue, MAXRANGE);
    }

    /**
     * Taste tests the cell. tests for both an animal and plant case.
     * 
     * @param cell
     *            the Cell it's scouting for food in.
     */
    public boolean tasteTest(Cell cell) {
        if (cell.getTerrainExists()) {
            return (cell.getTerrain()).taste(this);
        } else if (cell.getAnimalExists()) {
            return (cell.getAnimal()).taste(this);
        }
        return false;
    }

    /**
     * After mating the Omnivore gives birth to a child.
     * 
     * @return the new Omnivore
     */
    public Animal giveBirth() {
        return new Omnivore();
    }

}
