import greenfoot.*;
import java.util.*;

public class Boss extends Actor
{
    private static int recentRotation = 0;
    private static int rotate = 0;
    private static int rotateAdd = 20;
    public static int originalHealth = 100;
    public static int health = originalHealth;
    public static boolean createBossBar = true;
    public static int ghostNumber = 6;
    public static boolean newPattern = true;
    public static int pattern = -1;
    private static int ghostN = 6;
    private static int tick = 0;
    public void act()
    {
        BossFight world = (BossFight) getWorld();
        
        if ( world.getObjects(Minion.class).size() < 1) {
            newPattern = true;
        }
        
        if (newPattern) {
            System.out.println("New Pattern Generated");
            newPattern = false;
            int max = 2;
            int min = 0;
            pattern = (int)(Math.random()*(max-min+1)+min); //getting new pattern randomly. copied from internet
        }
        
        if (pattern == 0) {
            pattern1(world, getX(), getY());
            tick++;
            if (tick > 100) {
                newPattern = true;
                tick = 0;
            }
        }
        else if (pattern == 1) {
            pattern2(world);
        }
        /*else if (pattern == 2) {
            pattern3();
        }*/
        
        if (createBossBar) {
            getWorld().addObject(new BossBar(), 335, 20);
            createBossBar = false;
        }
        
        if (isTouching(Laser.class)) {
            health -= Laser.damage;
            removeTouching(Laser.class);
        }
        
        if (health <= 0) {
            getWorld().removeObject(this);
            return;
        }
    }
    
    public void pattern1(BossFight world, int X, int Y) {
        if ( tick <= 100 ) {
            world.addObject(new Fireball(rotate), X, Y);
            rotate += 10;
        }
    }
    
    public void pattern2(BossFight world) {
        if ( ghostN <= 6 && ghostN > 0 ) {
            world.addObject(new Minion(), Greenfoot.getRandomNumber(world.getWidth()), 
            Greenfoot.getRandomNumber(world.getHeight()));
            ghostN--;
        }
        else {
            newPattern = true;
        }
    }
    
    public static void pattern3() {
        /*
         * 
         */
        
        //newPattern = true;
        //fireballs fly horizontally or vertically in the random position.
    }
}
