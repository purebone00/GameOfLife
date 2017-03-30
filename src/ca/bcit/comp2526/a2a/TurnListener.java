package ca.bcit.comp2526.a2a;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listens to mouse events.
 * 
 * @author Albert
 *
 */
public class TurnListener extends MouseAdapter {

    /** The GameFrame that will be held in TurnListener. */
    private GameFrame frame;

    /** Initializes the GameFrame. */
    public TurnListener(GameFrame gframe) {
        frame = gframe;
    }

    /** Performs the TakeTurn function. */
    public void mouseClicked(MouseEvent arg0) {
        frame.takeTurn();
    }

}