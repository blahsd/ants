/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ants;

import org.newdawn.slick.SlickException;

/**
 * 
 * @author blahsd
 */
public class Food extends MoveableEntity {

    public Food(float xPos, float yPos, String ref) throws SlickException {
        super(xPos, yPos, 0, 0, ref, 0);
    }
    

}
