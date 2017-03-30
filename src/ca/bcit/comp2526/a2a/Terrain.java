package ca.bcit.comp2526.a2a;

/**
 * Abstract Class that acts as an intermediary for all future animals. It is
 * pretty empty right now, but that is because terrain currently has no
 * behaviors that are unique to Terrain.
 * 
 * @author Albert Chen
 * @version 1.0
 */
public abstract class Terrain extends Entity implements Mortal {
    /**
     * Tells a animal it kill itself within the cell. Only called when life
     * total reaches 0.
     * 
     * @param The
     *            cell the animal currently it within.
     */
    public void die(Cell cell) {
        cell.setTerrain(null);
    }

    /**
     * An abstract method to allow terrain to spread.
     * 
     * @return The terrain's child.
     */
    public abstract Terrain spread();

}
