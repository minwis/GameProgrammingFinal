import greenfoot.*;

public class Boss extends Actor
{
    private static int recentRotation = 0;
    private static int rotate = 0;
    private int count = 0;
    
    public void act()
    {
        //BossFight myWorld = (BossFight)getWorld();
        rotate += 10;
        count+=1;
        //setRotation(6);
        if (rotate > 360) {
            rotate = 0;
            return;
        }
        
            getWorld().addObject(new Fireball(rotate), getX(), getY());
            count = 0;
        
        
    }
}
