package ants;

import org.newdawn.slick.SlickException;

/**
 * 
 * @author blahsd
 */
public class Food extends MoveableEntity {
    private final int nutritionValue = 5;

    public Food(float xPos, float yPos, String ref) throws SlickException {
        super(xPos, yPos, 0, 0, ref, 0);
    }
    
    @Override
    public void collide (MoveableEntity entity) {
        setToBeDestroyed(true);
    }
    

}
