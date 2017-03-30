package ca.bcit.comp2526.a2a;

import java.util.ArrayList;

/**
 * The plane in which all Cells exist within. It tells the Cells when to move
 * and what actions to take. Basically a CEO of the game frame, gives master
 * instructions and the Cells do most of the busy work.
 * 
 * @author Albert Chen
 * @version 1.0
 */
public class World {

    /** The width of the world. */
    private int width;

    /** The height of the world. */
    private int height;

    /** Holds all the Cells in the world. */
    private static Cell[][] cell;

    /** Holds all empty Cells. */
    private ArrayList<Cell> emptyCells;

    /** Represents 100 for percent calculations. */
    private static final int HUNDRED = 100;

    /** Represents the chance herbivore can appear for percent calculations. */
    private static final int HERBIVORECHANCE = 15;

    /** Represents the chance plant can appear for percent calculations. */
    private static final int PLANTCHANCE = 35;

    private static final int OMNIVORECHANCE = 5;

    private static final int CARNIVORECHANCE = 5;

    /**
     * Constructor for the World. Initializes any variables that need to be
     * 
     * @param width
     *            The width of the world
     * @param height
     *            The height of the world
     */
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        cell = new Cell[height][width];
        emptyCells = new ArrayList<Cell>();
    }

    /**
     * Getter for the amount of rows.
     * 
     * @return width of the world
     */
    public int getRowCount() {
        return width;
    }

    /**
     * Getter for the amount of Columns.
     * 
     * @return column of the world
     */
    public int getColumnCount() {
        return height;
    }

    /**
     * Returns what is at a certain cell location.
     * 
     * @param column
     *            the column of the cell
     * @param row
     *            the row of the cell
     * @return The Cell at the location
     */
    public static Cell getCellAt(int column, int row) {
        try {
            return cell[column][row];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }

    }

    /**
     * Initializes the world by telling the cell to set itself up.
     */
    public void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cell[i][j] = new Cell(i, j);
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cell[i][j].init();
            }
        }
    }

    /**
     * Function for allowing the cells to determine what they are. The world
     * "randomly" decides what a cell must contain.
     * 
     * @param column
     *            within the array
     * @param row
     *            within the array
     */
    public static void chooseTile(int column, int row) {
        int tileChoice = RandomGenerator.nextNumber(HUNDRED);
        if (tileChoice < PLANTCHANCE) {
            cell[column][row].setTerrain(new Plant());
            cell[column][row].setAnimal(null);
        } else if (tileChoice >= PLANTCHANCE && tileChoice < (HERBIVORECHANCE + PLANTCHANCE)) {
            cell[column][row].setAnimal(new Herbivore());
            cell[column][row].setTerrain(null);
        } else if (tileChoice >= (HERBIVORECHANCE + PLANTCHANCE)
                && tileChoice < (HERBIVORECHANCE + PLANTCHANCE + OMNIVORECHANCE)) {
            cell[column][row].setAnimal(new Omnivore());
            cell[column][row].setTerrain(null);
        } else if (tileChoice >= (HERBIVORECHANCE + PLANTCHANCE + OMNIVORECHANCE)
                && tileChoice < HERBIVORECHANCE + PLANTCHANCE + OMNIVORECHANCE + CARNIVORECHANCE) {
            cell[column][row].setAnimal(new Carnivore());
            cell[column][row].setTerrain(null);
        } else {
            cell[column][row].setAnimal(null);
            cell[column][row].setTerrain(null);
        }
    }

    /**
     * The function for taking a turn in the world. After giving the order to
     * tell each cell to make an action it resets afterwards. It also runs to
     * actOfGod() function.
     */
    public void takeTurn() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cell[i][j].takeTurn();
            }
        }
        resetWorld();
    }

    /**
     * Tells the Cells that they can reset their turn counter. Then adds empty
     * cells to the array list of all empty cells.
     */
    private void resetWorld() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell temp = cell[i][j];
                temp.reset();
                if (temp.isEmpty()) {
                    emptyCells.add(temp);
                }
            }
        }
    }

    /**
     * Selects a random Cell in the world and runs the chances to see if
     * anything is added to the Cell.
     */
    public void actOfGod() {
        int tileChoice = RandomGenerator.nextNumber(emptyCells.size());
        emptyCells.get(tileChoice).init();
        emptyCells = new ArrayList<Cell>();
    }

}
