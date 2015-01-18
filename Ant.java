/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ants;

import org.newdawn.slick.SlickException;

/**
 * Artificial Non-Trivial   AI 0.0.1
 * @author blahsd
 */
public class Ant extends MoveableEntity {

    public Ant(float xPos, float yPos, float xVelocity, float yVelocity, String ref, float speed) throws SlickException {
        super(xPos, yPos, xVelocity, yVelocity, ref, speed);
    }

}
