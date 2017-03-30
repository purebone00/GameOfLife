package ca.bcit.comp2526.a2a;

/**
 * Mortal Interface. Includes a die method that must be implemented.
 * 
 * @author Albert Chen
 * @version 1.0
 */
public interface Mortal {

    /**
     * Must be implemented Die method.
     * 
     * @param cell
     *            The cell that something is dying in.
     */
    void die(Cell cell);
}
