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
    public static int pattern = -1;
    public static int previousPattern = -1;
    private static int ghostN = 0;
    private static int tick = 0;
    public void act()
    {
        BossFight world = (BossFight) getWorld();
        
        if (tick > 2000 && pattern == 0) {
            pattern = -1;
            tick = 0;
            rotate = 0;
        }
        
        if ( world.getObjects(Minion.class).size() < 1 && pattern == 1) {
            pattern = -1;
            ghostN = 0;
        }
        
        if (pattern == -1) {
            pattern = Greenfoot.getRandomNumber(3);
        }
        
        if (pattern == 0) {
            pattern1(world, getX(), getY());
            tick++;
        }
        else if (pattern == 1) {
            pattern2(world);
        }
        else if (pattern == 2) {
            pattern3();
        }
        
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
        if ( ghostN < 6 ) {
            world.addObject(new Minion(), Greenfoot.getRandomNumber(world.getWidth()), 
            Greenfoot.getRandomNumber(world.getHeight()));
            ghostN++;
        }
    }
    
    public static void pattern3() {
        /*
         *fireballs fly horizontally or vertically in the random position. 
         */
        pattern = -1;
    }
}
