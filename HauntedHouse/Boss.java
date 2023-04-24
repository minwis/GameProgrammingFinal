import greenfoot.*;
import java.util.*;

public class Boss extends Actor
{
    //private static int recentRotation = 0;
    private static int fireballRotate = 0;
    private static int bossRotate = 0;
    public static int originalHealth = 100;
    public static int health = originalHealth;
    public static boolean createBossBar = true;
    public static int ghostNumber = 6;
    public static int pattern = -1;
    private static int ghostN = 0;
    private static int maximumTick = 400;
    private static int tick = 0;
    private static int[] fireballPosition = new int[6];
    private static int fireballPositionCount = 0;
    
    public void act()
    {
        BossFight world = (BossFight) getWorld();
        
        if (pattern == -1) {
            pattern = Greenfoot.getRandomNumber(3);
            System.out.println("New pattern generated: " + pattern);
        }
        
        if (pattern == 0) {
            pattern1(world, getX(), getY());
        }
        else if (pattern == 1) {
            pattern2(world);
        }
        else if (pattern == 2) {
            pattern3(world);
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
    
    public void pattern1(BossFight world, int X, int Y) { //does not terminate?
        if (tick == maximumTick) {
            pattern = -1;
            tick = 0;
            fireballRotate = 0;
        }
        else {
            setRotation(bossRotate);
            world.addObject(new Fireball(fireballRotate, 1), X, Y);
            fireballRotate += 10;
            bossRotate += 2;
            tick++;
        }
    }
    
    public void pattern2(BossFight world) {
        if ( ghostN < 6 ) {
            world.addObject(new Minion(), Greenfoot.getRandomNumber(world.getWidth()), 
            Greenfoot.getRandomNumber(world.getHeight()));
            ghostN++;
        }
        
        if ( world.getObjects(Minion.class).size() == 0 ) {
            pattern = -1;
            ghostN = 0;
        }
    }
    
    
    /*
     * pattern 3 is covering the health count
     * pattern 3 should be generated in where no boss and player health count
     */
    
    
    
    public void pattern3(BossFight world) {
        if ( fireballPositionCount < 6 ) {//generate random positions where the fireballs will come out
            fireballPosition[fireballPositionCount] 
            = Greenfoot.getRandomNumber(world.getHeight());//y-coordinate
            fireballPositionCount++;
        }
        else { //generate fireballs(sized a little bit bigger)
            if ( tick > maximumTick ) {// stop the pattern
                pattern = -1;
                tick = 0;
                fireballPositionCount = 0;
            }
            else {
                tick++;
                for (int i = 0; i < fireballPosition.length; i++ ) {
                    world.addObject(new Fireball(0, 2), 1, fireballPosition[i]);
                }
            } 
        }
    } 
}
