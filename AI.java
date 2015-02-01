package ants;

import java.util.ArrayList;

/**
 * 
 * @author blahsd
 */
public class AI implements AIInterface {
    
    @Override
    public double[] getMovementVector (double[] targetDirection) {
        double[] movementVector = {0,0};

        if (targetDirection[0] > targetDirection[1]) {
            movementVector[0] = 1;
            movementVector[1] = targetDirection[1]/targetDirection[0];
        } else {
            movementVector[0] = targetDirection[0]/targetDirection[1];
            movementVector[1] = 1;
        }
        
        //based on the direction, create a movementVector
        //here be smarts
        
        return movementVector;
    }

    @Override
    public AI getSuccessorAI() {
        return null;
    }

    
  
}
