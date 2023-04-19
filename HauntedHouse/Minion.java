import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Minion extends Actor
{
    
    public void act()
    {
        turnTowards(Player.playerX, Player.playerY);
        move(2);
        BossFight world = (BossFight)getWorld();
        
        if (isTouching(Player.class)) {
            world.getScoreboard().decreaseLive();
        }
        
    }
    
}
/*
 * pattern2 is repeating.
 * pattern is not randomly generated; only patter2 is repeating.
 */