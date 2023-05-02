import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laser2 extends Actor
{
    private int shootingSpeed = 30;
    private int shootingDirection = 0;
    
    private GreenfootImage verticalLaser = new GreenfootImage("lazerBolt copy.png");
    
    Laser2(int recentRotation) {
        shootingDirection = recentRotation;
    }
    
    public void act()
    {
        fly();
    }
    
    public void fly() {
        if (shootingDirection == 90) {
            setLocation(getX(), getY() + shootingSpeed);
            setImage(verticalLaser);
        }
        else if ( shootingDirection == 270 ){
            setLocation(getX(), getY() - shootingSpeed);
            setImage(verticalLaser);
        }
        
        if (isTouching( Brick.class )) {
            getWorld().removeObject(this);
            return;
        }
        if (isTouching(VerticalGhost.class)) {
            removeTouching(VerticalGhost.class);
        }
        else if (isTouching(HorizontalGhost.class)) {
            removeTouching(HorizontalGhost.class);
        }
    }
}
