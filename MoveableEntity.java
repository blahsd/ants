package ants;

import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * @author blahsd
 */
public class MoveableEntity extends Image {
    float xPos,yPos,xVelocity,yVelocity,speed;
    boolean randomMovement, toBeDestroyed;
    Random random;
    Rectangle hitbox;
    private final int nutritionValue = 0;
    
    public MoveableEntity(float xPos, float yPos, float xVelocity, float yVelocity, String ref, float speed) throws SlickException {
        super(ref);
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.speed = speed;
        this.toBeDestroyed = false; 
        this.random = new Random();
        
        this.hitbox = new Rectangle(xPos,yPos,2,2);
        
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public float getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public float getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void update() {
    }
    
    public void applyMovement(double[] movementVector) {
        //cute formula I pulled out of my ass. check.
        double x1,y1,x2,y2,m,k;
        
        x1 = movementVector[0];
        y1 = movementVector[1];
        
        m = speed;
        k = x1/y1;
        
        
        //This should get the vector down so that the sum of x and y movement
        // equals speed, but the ratio between the two (thus the direction) is 
        // preserved.
        x2 = (m*k)/(k+1);
        y2 = (m)/(k+1);
        
        this.xPos += x2;
        this.yPos += y2;
        
        this.hitbox = new Rectangle(xPos,yPos,this.getHeight(),this.getWidth());
    }

    public boolean isToBeDestroyed() {
        return toBeDestroyed;
    }

    public void setToBeDestroyed(boolean toBeDestroyed) {
        this.toBeDestroyed = toBeDestroyed;
    }
    
    public void collide(MoveableEntity entity) {
    }
    
    public int getNutritionValue() {
        return nutritionValue;
    }
    
    
}
