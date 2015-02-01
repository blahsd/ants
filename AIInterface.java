package ants;

import java.util.ArrayList;

/**
 * 
 * @author blahsd
 */
public interface AIInterface {
    public double[] getMovementVector (double[] targetDirection);
    public AI getSuccessorAI ();
}
