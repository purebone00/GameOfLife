package ca.bcit.comp2526.a2a;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * The Class that creates a cell that is added to the World. Each cell acts as a
 * miniature space that animals and plants act within it. Effectively the only
 * change that happens within the Cell is that the color it is painted with
 * changes.
 * 
 * @author Albert Chen
 * @version 1.0
 */
public class Cell extends JPanel {

    /** Holds the x-position for the cell. */
    private int xpos;

    /** Holds the y-position for the cell. */
    private int ypos;

    /** An array of all adjacent cells around itself. */
    private Cell[] adjCell;

    /** Holds the color which the tile should be colored. */
    private Color tileColor;

    /** Holds the animal that is within this cell if it exists. */
    private Animal animal;

    /** Holds the terrain that is within this cell if it exists. */
    private Terrain terrain;

    /** The Size of the Cell when its first initialized. */
    private static final int SIZE = 30;

    /**
     * Constructor for the Cell. Draws the border around each cell and
     * initializes any variables that need to be.
     * 
     * @param xlocation
     *            : The x-position it is within the world.
     * @param ylocation
     *            : The y-position it is within the world.
     */
    public Cell(int xlocation, int ylocation) {
        setPreferredSize(new Dimension(SIZE, SIZE));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setTile(xlocation, ylocation);
        adjCell = new Cell[8];
    }

    /**
     * Called when the world is finished building and asks the world what is
     * within itself and also grabs the adjacent.
     */
    public void init() {
        getAdjacentCells();
        World.chooseTile(xpos, ypos);
    }

    /** updates the x and y positions for the cells logic. */
    private void setTile(int xposition, int yposition) {
        xpos = xposition;
        ypos = yposition;
    }

    /**
     * Asks the world what is adjacent to itself and fills the array list with
     * what is returned.
     */
    public void getAdjacentCells() {
        int index = 0;
        adjCell[index++] = World.getCellAt(xpos - 1, ypos - 1);
        adjCell[index++] = World.getCellAt(xpos, ypos - 1);
        adjCell[index++] = World.getCellAt(xpos + 1, ypos - 1);
        adjCell[index++] = World.getCellAt(xpos - 1, ypos);
        adjCell[index++] = World.getCellAt(xpos + 1, ypos);
        adjCell[index++] = World.getCellAt(xpos - 1, ypos + 1);
        adjCell[index++] = World.getCellAt(xpos, ypos + 1);
        adjCell[index++] = World.getCellAt(xpos + 1, ypos + 1);
    }

    /**
     * Getter for the x-position.
     * 
     * @return x-position of this cell
     */
    public int getXPos() {
        return xpos;
    }

    /**
     * Getter for the y-position.
     * 
     * @return y-position of this cell
     */
    public int getYPos() {
        return ypos;
    }

    /**
     * Getter for whether or not a specialized Terrain exists here.
     * 
     * @return True or False value
     */
    public boolean getTerrainExists() {
        return (terrain != null) ? true : false;
    }

    /**
     * Getter for whether or not a specialized Animal exists here.
     * 
     * @return True or False value
     */
    public boolean getAnimalExists() {
        return (animal != null) ? true : false;
    }

    /**
     * Called to see if the cell contains something.
     * 
     * @return true if it is empty, false if there is something within this cell
     */
    public boolean isEmpty() {
        return (!getTerrainExists() && !getAnimalExists()) ? true : false;
    }

    /**
     * Called to set this Cell to the new terrain passed in.
     * 
     * @param newTerrain
     *            the new terrain that is help in this cell
     */
    public void setTerrain(Terrain newTerrain) {
        terrain = newTerrain;
        setColor();
    }

    /**
     * Called to set this Cell to the new animal passed in.
     * 
     * @param newAnimal
     *            the new animal that is help in this cell
     */
    public void setAnimal(Animal newAnimal) {
        animal = newAnimal;
        setColor();
    }

    /**
     * Sets the new color displayed by the cell depending on what is contained
     * within the cell. Note: Animals have precedence over what the terrain
     * color is this is on purpose. Finally it calls the update the cell color.
     */
    public void setColor() {
        if (getAnimalExists()) {
            tileColor = animal.init();
        } else if (getTerrainExists()) {
            tileColor = terrain.init();
        } else {
            tileColor = Color.white;
        }
        paintCell();
    }

    /**
     * Called from outside the Cell. An animal is moved into this Cell.
     * 
     * @param movingAnimal
     *            the animal that is being moved into this cell
     */
    public void moveHere(Animal movingAnimal) {
        setAnimal(movingAnimal);
        animal.takeTurn(this);
    }

    /** Called from within the Cell. An animal is moved out of this Cell. */
    private void leaveHere() {
        setAnimal(null);
    }

    /** Updates the color of the Cell. */
    private void paintCell() {
        setBackground(tileColor);
    }

    /**
     * Takes a turn. Determines if the space it's moving into has an instance of
     * another animal or is not null. It then asks what cells the animal would
     * prefer to go to then attempts to eat if there is food there. Finally it
     * tells the animal to move to the new cell.
     */
    public void takeTurn() {
        Cell cell = null;
        Cell spawn = null;

        if (getTerrainExists() && !terrain.getMoved()) {
            terrain.age();
            spawn = terrain.breed(adjCell);
            if (spawn != null) {
                spawn.setTerrain(terrain.spread());
            }
            terrain.takeTurn(this);
            setColor();
        }

        if (getAnimalExists() && !animal.getMoved()) {
            animal.age();
            spawn = animal.breed(adjCell);
            if (spawn != null) {
                spawn.setAnimal(animal.giveBirth());
            }
            cell = animal.pickPreferredCell(adjCell);
            if (animal.tasteTest(cell)) {
                animal.eat(cell);
            }
            cell.moveHere(animal);
            this.leaveHere();
        }
    }

    /** Resets the animal movement for the next turn. */
    public void reset() {
        if (getAnimalExists()) {
            animal.setMoved();
        }
        if (getTerrainExists()) {
            terrain.setMoved();
        }
    }

    /**
     * Returns the terrain held in the cell.
     * 
     * @return The Terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Returns the animal held in the cell.
     * 
     * @return The animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * Returns whichever entity is contained in this cell. animal are preferred.
     * 
     * @return animal or terrain
     */
    public Entity getEntity() {
        if (getAnimalExists()) {
            return animal;
        } else {
            return terrain;
        }
    }

}
