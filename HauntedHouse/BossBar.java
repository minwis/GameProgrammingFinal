import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossBar extends Actor
{
    GreenfootImage boardImage;
    public static int BossBarHeight = 40;
    public void act()
    {
        try{
            boardImage = new GreenfootImage(getWorld().getWidth() * Boss.health / Boss.originalHealth, BossBarHeight);
            boardImage.setColor(Color.WHITE);
            boardImage.fillRect(0,0,getWorld().getWidth() * Boss.health / Boss.originalHealth,BossBarHeight);
            setImage(boardImage);
        }
        catch(Exception e) {
            
        }
        
    }
}
