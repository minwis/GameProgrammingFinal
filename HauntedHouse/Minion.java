import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Minion extends Actor
{
    
    public void act()
    {
        turnTowards(Player.playerX, Player.playerY);
        move(2);
        BossFight world = (BossFight)getWorld();
        
    }
    
}