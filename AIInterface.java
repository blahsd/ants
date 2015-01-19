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
public interface AIInterface {
    public int[] getMovementVector (ArrayList targets);
    public AI getSuccessorAI ();
}
