
/**
 * @author blahsd
 * Ants 2D simulation as a platform for the development and study of genetic 
 * algorithms
 * 
 *
 *
 * 0.0.4 
 * Finish AI wiring
 *  
 * 0.0.3 
 * Add AI interfaces and wiring
 * 
 * 0.0.2
 * Finish environment
 * 
 * 0.0.1
 * Add Collision Detection
 * Add environment management
 * Add MoveableEntity as moving entities
 */

package ants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Environment extends BasicGame {
    private ArrayList<MoveableEntity> entityList;
    final int START_ANT = 2;
    final int START_FOOD = 30;
    final int ENTITY_X = 8;
    final int ENTITY_Y = 8;
    final static int DISPLAY_WIDTH = 600;
    final static int DISPLAY_HEIGHT = 600;
    final static String FOOD_TEX = "res/red.png";
    final static String ANT_TEX = "res/blue.png";
    final static float ANT_SPEED = 2;
    final int FOOD_FREQ = 25;
    int timeFromFood, time, antCount, foodCount;
    Random random;

    public ArrayList<MoveableEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(ArrayList<MoveableEntity> entityList) {
        this.entityList = entityList;
    }
    
    public void addEntity(MoveableEntity entity) {
        entityList.add(entity);
    }
    
    public void removeEntity(MoveableEntity entity) {
        entityList.remove(entity);
    }

    public Environment (String title) {
        super(title);
        this.entityList = new ArrayList();
        this.time = 0;
    }
    
    @Override
    /**
     * Initialize a starting population of moving entities placed
     * in random spots
     */
    public void init(GameContainer container) throws SlickException {        
        random = new Random();
        for(int i=0;i<START_ANT;i++) {
            spawnAnt(random.nextInt(DISPLAY_WIDTH),random.nextInt(DISPLAY_HEIGHT),0,0,ANT_TEX,ANT_SPEED, null);
            
        }
        for(int i=0;i<START_FOOD;i++) {
            spawnFood(random.nextInt(DISPLAY_WIDTH),random.nextInt(DISPLAY_HEIGHT),FOOD_TEX);
        }
        
    }

    public void spawnAnt(float xPos, float yPos, float xVelocity, float yVelocity, String ref, float speed, AI ai) throws SlickException {
        addEntity(new Ant(this, xPos, yPos, xVelocity, yVelocity, ref, speed, ai));
        this.antCount++;
    }
    
    public void spawnFood(float xPos, float yPos, String ref) throws SlickException {
        addEntity(new Food(xPos, yPos, ref));
        this.foodCount++;
        this.timeFromFood = 0;
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
        this.time++;
        
        ArrayList<MoveableEntity> entitiesToDestroy = new ArrayList();
        ArrayList<MoveableEntity> copyOfEntityList = entityList;
        
        for (MoveableEntity entity : entityList) {
            for (MoveableEntity collidingEntity : entityList) {
                if (entity != collidingEntity && entity.hitbox.intersects(collidingEntity.hitbox)) {
                    entity.collide(collidingEntity);
                }
            }
            entity.update();
            if (entity.isToBeDestroyed()) {
                entitiesToDestroy.add(entity);
            }
            if (entity.xPos > DISPLAY_WIDTH) {entity.xPos -= DISPLAY_WIDTH;}
            if (entity.yPos > DISPLAY_HEIGHT) {entity.yPos -= DISPLAY_HEIGHT;}
        }
        
        for (MoveableEntity entity : entitiesToDestroy) {
            if (entity instanceof Ant) {
                this.antCount--;
            } 
            else if (entity instanceof Food) {
                this.foodCount--;
            }
            removeEntity(entity);
        }
        
        if (this.timeFromFood == FOOD_FREQ) {
            spawnFood(random.nextInt(DISPLAY_WIDTH),random.nextInt(DISPLAY_HEIGHT),FOOD_TEX);
        } 
        this.timeFromFood++;
        
        
    }

    /**
     * Draw objects
     * @param gc
     * @param grphcs
     * @throws SlickException 
     */
    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        grphcs.drawString("Ants",10,30);
        grphcs.drawString(Integer.toString(antCount),60,30);
        
        grphcs.drawString("Food", 10,60);
        grphcs.drawString(Integer.toString(foodCount),60,60);
        
        grphcs.drawString("Time", 10,90);
        grphcs.drawString(Integer.toString(time),60,90);
        
        
        
        for (MoveableEntity entity : entityList) {
            entity.draw(entity.getxPos(),entity.getyPos());
            grphcs.draw(entity.hitbox);
        }
    }
    
    public static void main (String args[]) throws SlickException {
        AppGameContainer app = new AppGameContainer (new Environment ("Ants"));
        app.setDisplayMode(DISPLAY_WIDTH,DISPLAY_HEIGHT, false);
        app.setAlwaysRender(true);
        app.setTargetFrameRate(30);
        app.start();
    }
 
}