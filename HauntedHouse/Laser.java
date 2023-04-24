import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laser extends Actor
{
    public static int damage = 2;
    public void act()
    {
        move(10);
        
        BossFight world = (BossFight) getWorld();
        if ( world.getObjects(Boss.class).size() < 1 ) {
            world.removeObject(this);
            return;
        }
        
        if (isTouching(Minion.class)) {
            world.getScoreboard().increaseLive();
            removeTouching(Minion.class);
        }
    }
}
