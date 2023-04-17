import greenfoot.*;

public class Boss extends Actor
{
    private static int recentRotation = 0;
    private static int rotate = 0;
    private static int rotateAdd = 20;
    public static int originalHealth = 100;
    public static int health = originalHealth;
    public static boolean createBossBar = true;
    public void act()
    {
        getWorld().addObject(new Fireball(rotate), getX(), getY());
        rotate += 10;
        
        if (createBossBar) {
            getWorld().addObject(new BossBar(), 335, 20);
            createBossBar = false;
        }
        if (isTouching(Laser.class)) {
            health -= Laser.damage;
            removeTouching(Laser.class);
        }
        
        if (health <= 0) {
            getWorld().removeObject(this);
            return;
        }
        
    }
}
