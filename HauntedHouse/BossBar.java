import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BossBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossBar extends Actor
{
    GreenfootImage boardImage;
    public void act()
    {
        boardImage = new GreenfootImage(getWorld().getWidth() * Boss.health / Boss.originalHealth, 40);
        boardImage.setColor(Color.WHITE);
        boardImage.fillRect(0,0,getWorld().getWidth() * Boss.health / Boss.originalHealth,40);
        setImage(boardImage);
    }
}
