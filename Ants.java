
/**
 * @author blahsd
 * Ants 2D simulation as a platform for the development and study of genetic 
 * algorithms
 * 
 * 0.0.1
 * + Add Collision Detection
 * + Add environment management
 * + Add MoveableEntity as moving entities
 */

package ants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;

public class Ants extends BasicGame {
    private final ArrayList<MoveableEntity> entityList;
    final int START_ANT = 10;
    final int START_FOOD = 30;
    final int ENTITY_X = 8;
    final int ENTITY_Y = 8;
    final static int DISPLAY_WIDTH = 600;
    final static int DISPLAY_HEIGHT = 600;
    final static String FOOD_TEX = "res/red.png";
    final static String ANT_TEX = "res/blue.png";
    final static float ANT_SPEED = 2;
    Random random;
    

    public Ants (String title) {
        super(title);
        this.entityList = new ArrayList();
    }
    
    @Override
    /**
     * Initialize a starting population of moving entities placed
     * in random spots
     */
    public void init(GameContainer container) throws SlickException {
        random = new Random();
        for(int i=0;i<START_ANT;i++) {
            entityList.add (new Ant( random.nextInt(DISPLAY_WIDTH + 1),random.nextInt(DISPLAY_HEIGHT),0.6f,0.2f,ANT_TEX,ANT_SPEED));
        }
        for(int i=0;i<START_FOOD;i++) {
            entityList.add (new Food( random.nextInt(DISPLAY_WIDTH + 1),random.nextInt(DISPLAY_HEIGHT),FOOD_TEX));
        }
        
    }

    /**
     * Game Logic
     * @param gc        game container
     * @param delta     fps adjustment
     * @throws SlickException
     */
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
       
        ArrayList<MoveableEntity> entitiesToRemove = new ArrayList();
        
        int j = 0;
        
        for (MoveableEntity entity : entityList) {
            for (MoveableEntity collidingEntity : entityList) {
               if (entity != collidingEntity && entity.hitbox.intersects(collidingEntity.hitbox)) {
                   System.out.println(j);
                   j++;
                   entitiesToRemove.add(entity);
                   entitiesToRemove.add(collidingEntity);
                }
            }
            entity.update();
            if (entity.xPos > DISPLAY_WIDTH) {entity.xPos -= DISPLAY_WIDTH;}
            if (entity.yPos > DISPLAY_HEIGHT) {entity.yPos -= DISPLAY_HEIGHT;}
        }
        
        for (MoveableEntity entity : entitiesToRemove) {
            entityList.remove(entity);
        }
    }

    /**
     * Draw objects
     * @param gc
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        grphcs.drawString("Ants",50,50);
        
        for (MoveableEntity entity : entityList) {
            entity.draw(entity.getxPos(),entity.getyPos());
            grphcs.draw(entity.hitbox);
        }
    }
    
    public static void main (String args[]) throws SlickException {
        AppGameContainer app = new AppGameContainer (new Ants ("Ants"));
        app.setDisplayMode(DISPLAY_WIDTH,DISPLAY_HEIGHT, false);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(60);
        app.start();
    }
 
}