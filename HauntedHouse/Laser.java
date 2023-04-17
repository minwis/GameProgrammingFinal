import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laser extends Actor
{
    private int X = 0;
    private int Y = 0;
    public static int damage = 2;
    public void act()
    {
        MouseInfo mi = Greenfoot.getMouseInfo();
        turnTowards(X,Y);
        if (!isTouching(Boss.class)) {
            move(10);
        }
        
        BossFight world = (BossFight) getWorld();
        if ( world.getObjects(Boss.class).size() < 1 ) {
            world.removeObject(this);
            return;
        }
    }
    
    Laser ( int X, int Y ) {
        this.X = X;
        this.Y = Y;
    }
}
