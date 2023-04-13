import greenfoot.*;

public class Boss extends Actor
{
    private static int recentRotation = 0;
    private static int rotate = 0;
    private int count = 0;
    private static int rotateAdd = 20;
    
    public void act()
    {
        //BossFight myWorld = (BossFight)getWorld();
        rotate += 10;
        count+=1;
        getWorld().addObject(new Fireball(rotate + rotateAdd), getX(), getY());
        count = 0;
    }
}
