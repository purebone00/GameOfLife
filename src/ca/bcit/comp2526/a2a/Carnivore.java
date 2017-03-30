package ca.bcit.comp2526.a2a;

import java.awt.Color;

/* A Carnivore. An extension of the Animal Class.
 * 
 * @author Albert Chen
 * @version 1.0
*/
public class Carnivore extends Animal {

    /**
     * Carnivore's Life constant value.
     */
    private static final int CARNLIFE = 3;

    /**
     * Required food value.
     */
    private static final int REQFOOD = 2;

    /**
     * Required space value.
     */
    private static final int REQSPACE = 3;

    /**
     * Constructor for a Carnivore. Initializes it's starting life total to 3
     * and it's represented color to Magenta.
     * 
     */
    public Carnivore() {
        red = MAXRANGE;
        green = 0;
        blue = MAXRANGE;

        requiredFood = REQFOOD;
        requiredSpace = REQSPACE;

        lifeTotal = CARNLIFE;
        lifeMax = CARNLIFE;
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
        lifeTotal = CARNLIFE;
        red = MAXRANGE;
        green = 0;
        blue = MAXRANGE;
        tileColor = new Color(red, green, blue, MAXRANGE);
    }

    /**
     * Taste tests the cell. tests for plant case.
     * 
     * @param cell
     *            the Cell it's scouting for food in.
     */
    public boolean tasteTest(Cell cell) {
        if (cell.getAnimalExists()) {
            return (cell.getAnimal()).taste(this);
        }
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
        return true;
    }

    /**
     * After mating the Carnivore gives birth to a child.
     * 
     * @return the new Carnivore
     */
    public Animal giveBirth() {
        return new Carnivore();
    }
}
