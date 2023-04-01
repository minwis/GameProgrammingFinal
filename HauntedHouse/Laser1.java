import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Laser1 extends Actor
{
    /*
     * UP= 270;
       DOWN = 90;
       LEFT = 180;
       RIGHT = 0;
     */
    private int shootingSpeed = 30;
    private int shootingDirection = 0;
    
    private GreenfootImage verticalLaser = new GreenfootImage("lazerBolt copy.png");
    
    Laser1(int recentRotation) {
        shootingDirection = recentRotation;
    }
    
    public void act()
    {
        fly();
    }
    
    public void fly() {
        if ( shootingDirection == 0 ) {//RIGHT
            setLocation(getX() + shootingSpeed, getY());
        }
        else if (shootingDirection == 180) { //LEFT
            setLocation(getX() - shootingSpeed, getY());
        }
        
        if (isTouching(VerticalGhost.class)) {
            removeTouching(VerticalGhost.class);
        }
        else if (isTouching(HorizontalGhost.class)) {
            removeTouching(HorizontalGhost.class);
        }
    }
}
