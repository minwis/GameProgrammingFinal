import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossBar extends Actor
{
    GreenfootImage boardImage;
    public void act()
    {
        try{
            boardImage = new GreenfootImage(getWorld().getWidth() * Boss.health / Boss.originalHealth, 40);
            boardImage.setColor(Color.WHITE);
            boardImage.fillRect(0,0,getWorld().getWidth() * Boss.health / Boss.originalHealth,40);
            setImage(boardImage);
        }
        catch(Exception e) {
            
        }
        
    }
}
