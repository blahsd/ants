package ants;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Artificial Non-Trivial   AI 0.0.1
 * @author blahsd
 */
public class Ant extends MoveableEntity {
    private int hunger, age, timeToSpawn;
    private final int MAX_HUNGER = 5000;
    private final int MAX_AGE = 50000;
    private final int MAX_TIME_TO_SPAWN = 200;
    private final AI ai;
    private final Environment environment;
    private final Random random;
   

    public Ant(Environment environment, float xPos, float yPos, float xVelocity, float yVelocity, String ref, float speed, AI ai) throws SlickException {
        super(xPos, yPos, xVelocity, yVelocity, ref, speed);
        this.environment = environment;
        this.hunger = 0;
        this.age = 0;
        this.timeToSpawn = MAX_TIME_TO_SPAWN;
        this.random = new Random();
        
        if (ai == null) { this.ai = new AI(); }
        else { this.ai = ai; }
    }
    
    @Override
    public void collide (MoveableEntity entity) {
        if (entity instanceof Food) {
            entity.setToBeDestroyed(true);
            reduceHunger(entity.getNutritionValue());
            
        }
    }
    
    @Override
    public void update(){
        //Aging and hungering processes
        age();
        
        if (getTimeToSpawn() <= 0) {
            try {
                spawn();
                setTimeToSpawn(MAX_TIME_TO_SPAWN);
            } catch (SlickException ex) {
                Logger.getLogger(Ant.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        setTimeToSpawn(getTimeToSpawn() - 1);
        
        
        applyMovement(defineMovement());
    }
    
    public void age() {
        setHunger(getHunger()+1);
        setAge(getAge()+1);
        setTimeToSpawn(getTimeToSpawn()-1);
        
        if (getHunger() > MAX_HUNGER || getAge() > MAX_AGE) {
           setToBeDestroyed(true); 
        }
    }
    
    public double[] defineMovement() {
        return (ai.getMovementVector(getDirection(getTarget(scan()))));
    }
    
    public void spawn() throws SlickException {
        environment.spawnAnt(xPos, yPos, xVelocity, yVelocity, ref, speed, ai);
    }

    public int getTimeToSpawn() {
        return timeToSpawn;
    }

    public void setTimeToSpawn(int timeToSpawn) {
        this.timeToSpawn = timeToSpawn;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void reduceHunger(int factor) {
        setHunger(getHunger()+factor);
    }
    
    public ArrayList<MoveableEntity> scan() { 
        return environment.getEntityList();
    }
    
    public MoveableEntity getTarget(ArrayList<MoveableEntity> closeEntities) {
        
        MoveableEntity closestEntity = null;
        double shortestDistance = 99999999;

        for (MoveableEntity entity : closeEntities) {
            if (entity instanceof Food) { 
                double currentDistance = getDistance(entity);
                if (currentDistance < shortestDistance) {
                    shortestDistance = currentDistance;
                    closestEntity = entity;
                }
            }
        }
        return closestEntity;
    }
    
    public double[] getDirection(MoveableEntity target) {
        double[] direction = {1,1};
        
        try {
            direction[0] = (this.getxPos()-target.getxPos());
            direction[1] = (this.getyPos()-target.getyPos());
        } catch (NullPointerException ex) {
            System.out.println("No food found");
            direction[0] = random.nextInt();
            direction[1] = random.nextInt();
        }
        return direction;
    }
    
    public double getDistance(MoveableEntity target) {
        double distance = sqrt(pow((target.getxPos() -this.getxPos()),2) + pow((target.getyPos() - this.getyPos()),2));
        
        return distance;
    }


}
