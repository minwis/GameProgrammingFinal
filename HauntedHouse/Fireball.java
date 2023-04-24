import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fireball extends Actor
{
    public int recentRotation = 0;
    
    Fireball(int recentRotation, int scaleFactor) {
        this.recentRotation = recentRotation;
        GreenfootImage img = this.getImage();
        img.scale(img.getWidth() * scaleFactor, img.getHeight() * scaleFactor);
        setImage(img);
    }
    
    public void act()
    {
        setRotation(recentRotation);
        move(3);
        
        if ( getY() <= 45 || getX() <= 0) {
            getWorld().removeObject(this);
            return;
        }
        else if (getY() >= 719 || getX() >= 669) {
            getWorld().removeObject(this);
            return;
        }
        
    }
}
