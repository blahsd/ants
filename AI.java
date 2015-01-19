/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ants;

import java.util.ArrayList;

/**
 * 
 * @author blahsd
 */
public class AI implements AIInterface {
    
    @Override
    public int[] getMovementVector (ArrayList targets) {
        int[] movementVector = {1,1};
        return movementVector;
    }

    @Override
    public AI getSuccessorAI() {
        return null;
    }

}
