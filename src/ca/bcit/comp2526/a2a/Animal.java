package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

/**
 * Abstract Class that acts as an intermediary for all future animals. Contains
 * the functions to define the behavior for finding the preferred food of each
 * animal and when an animal moves or dies.
 * 
 * @author Albert Chen
 * @version 1.0
 */
public abstract class Animal extends Entity implements Mortal {

    /**
     * Each animal must have an eat function.
     * 
     * @param the
     *            Cell the animal is attempting to eat.
     */
    public abstract void eat(Cell cell);

    /**
     * Tells a animal it kill itself within the cell. Only called when life
     * total reaches 0.
     * 
     * @param The
     *            cell the animal currently it within.
     */
    public void die(Cell cell) {
        cell.setAnimal(null);
    }

    /**
     * Runs through all adjacent cells and picks out which cells contain the
     * food the current animal wants. Handles if the cell it is looking at is
     * null or contains another animal.
     * 
     * @param adjCell
     *            an array of adjacent cells
     * @return returns the cell it has picked.
     */
    public Cell pickPreferredCell(Cell[] adjCell) {
        ArrayList<Cell> prefer = new ArrayList<Cell>();
        ArrayList<Cell> options = new ArrayList<Cell>();

        /*
         * loops through the array and finding all valid cells it may move to.
         * places them into the corresponding array list.
         */
        for (int i = 0; i <= SEVEN; i++) {
            Cell cell = adjCell[i];
            if (cell != null) {
                if ((this).tasteTest(cell)) {
                    prefer.add(cell);
                } else {
                    options.add(cell);
                }
            }
        }

        /*
         * Picks a random cell out of the array list. Preferred is of higher
         * precedence. returns null otherwise.
         */
        if (prefer.size() > 0) {
            return prefer.get(RandomGenerator.nextNumber(prefer.size()));
        } else if (options.size() > 0) {
            return options.get(RandomGenerator.nextNumber(options.size()));
        }
        return null;
    }

    /**
     * An abstract method that allows the animals to see if the cell they about
     * to move in is edible.
     * 
     * @param cell
     *            passes in the cell that needs to be tested
     */
    public abstract boolean tasteTest(Cell cell);

    /**
     * An abstract method that returns one of the children off the animal
     * sub-hierarchy
     * 
     * @return the specified animals child.
     */
    public abstract Animal giveBirth();

}
