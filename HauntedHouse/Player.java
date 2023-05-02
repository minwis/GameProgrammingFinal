import greenfoot.*;
import java.util.*;

public class Player extends Actor
{
    private double scaleFactor = 0.9;
    private int speed = 3;
    private int length = (int)(40 * scaleFactor);
    public static boolean protection = false;
    public static boolean killGhost = false;
    public static boolean bulldozer = false;
    public static int shootingCounter = 0;
    private int recentRotation = 0;
    
    private GreenfootImage originalPlayer = new GreenfootImage("termporary-player-image.png");
    private GreenfootImage protectedPlayer = new GreenfootImage("ProtectedPlayer.png");
    private GreenfootImage killerPlayer = new GreenfootImage("KillerPlayer.png");
    private GreenfootImage bulldozerPlayer = new GreenfootImage("ignoringPlayer.png");
    
    public static boolean stage1=true;
    
    public static int playerX;
    public static int playerY;
    
    public static GreenfootSound movingNextStage = new GreenfootSound("going-to-the-next-level-114480.mp3");
    public static GreenfootSound win = new GreenfootSound("WinnnigSound(Super Mario).com.mp3");
    public static GreenfootSound laser = new GreenfootSound("LaserSound.wav");
    public static GreenfootSound hurt = new GreenfootSound("HurtSound2.mp3");
    public void act()
    {
    
        playerX = getX();
        playerY = getY();
        if (Greenfoot.isKeyDown("up")) {
            setRotation(Direction.UP);
            recentRotation = Direction.UP;
            move(speed, length, "p");
        }
        else if ( Greenfoot.isKeyDown("down") ) {
            setRotation(Direction.DOWN);
            recentRotation = Direction.DOWN;
            move(speed, length, "p");
        }
        else if ( Greenfoot.isKeyDown("Left") ) {
            setRotation(Direction.LEFT);
            recentRotation = Direction.LEFT;
            move(speed, length, "p");
        }
        else if ( Greenfoot.isKeyDown("Right") ) {
            setRotation(Direction.RIGHT);
            recentRotation = Direction.RIGHT;
            move(speed, length, "p");
        }
        
        if (stage1) {
            Maze world = (Maze) getWorld();
            
            if (Greenfoot.isKeyDown("x") && killGhost) {
                laser.play();
                killingGhost();
                shootingCounter = 100;
            }
        
            if (isTouching(Exit.class)) {
                movingNextStage.play();
                removeTouching(Exit.class);
                stage1=  false;
                protection = false;
                killGhost = false;
                bulldozer = false;
                world.print("Moving to the next stage...");
                world.getScoreboard().lives = 10;
                world.makeNewWorld();
                Greenfoot.stop();
                return;
            }
               
            if (isTouching(HealingPotion.class)) {
                world.getScoreboard().increaseLive();
                removeTouching(HealingPotion.class);
            }
            
            if (isTouching(ProtectionAmulet.class)) {
                removeTouching(ProtectionAmulet.class);
                protect(25000);
            }
             
            if (isTouching(BulldozerAmulet.class)) {
                removeTouching(BulldozerAmulet.class);
                setImage(bulldozerPlayer);
                bulldozer = true;
                speed = 6;
            
                Timer timer = new Timer();
                TimerTask task = new resetAmulet();
                timer.schedule(task, 25000);
            }
               
            if (isTouching(KillerAmulet.class) ) {
                removeTouching(KillerAmulet.class);
                killGhost = true;
                setImage(killerPlayer);
                Timer timer = new Timer();
                TimerTask task = new resetAmulet();
                timer.schedule(task, 25000);
            }
           
        }
        else {
            BossFight world = (BossFight) getWorld();
            if (isTouching(Fireball.class)) {// the command "isTouching" is applied in too much area.
                if (!protection) {
                    hurt.play();
                    world.getScoreboard().decreaseLive();
                    removeTouching(Fireball.class);
                }
                
                protect(2500);
            }
            
            MouseInfo mi = Greenfoot.getMouseInfo();
            if (mi != null) {
                turnTowards(mi.getX(), mi.getY());
            }
            
            if (Greenfoot.isKeyDown("x") && shootingCounter <= 0) {
                laser.play();
                Laser laser = new Laser();
                laser.setRotation(getRotation());
                world.addObject(laser, getX(), getY());
                shootingCounter = 25;
            }
            
            if ( world.getObjects(Boss.class).size() < 1 ) {
                win.play();
                world.print("YOU WON!");
                Greenfoot.stop();
                return;
            }
            
            if (isTouching(Minion.class)) {
                world.getScoreboard().decreaseLive();
                removeTouching(Minion.class);
            }
            
            if ( protection ) {
                protect(2500);
            }
        }
        
        shootingCounter--;
    }
    
    public void killingGhost() {
        if (recentRotation == 270) {
            getWorld().addObject(new Laser2(recentRotation), getX(), getY() - 30);
        }
        else if (recentRotation == 90) {
            getWorld().addObject(new Laser2(recentRotation), getX(), getY() + 30);
        }
        else if (recentRotation == 180){
            getWorld().addObject(new Laser1(recentRotation), getX() - 30, getY());
        }
        else {
            getWorld().addObject(new Laser1(recentRotation), getX() + 30, getY());
        }
    }
    
    public void protect(int delay) {
        if (stage1) {
            setImage(protectedPlayer);
        }
        protection = true;
        
        Timer timer = new Timer();
        TimerTask task = new resetAmulet();
        timer.schedule(task, delay);
    }
    
    private class resetAmulet extends TimerTask {
        public void run() {
            setImage(originalPlayer);
            protection = false;
            killGhost = false;
            bulldozer = false;
            speed = 3;
        }
    }
    
    class Direction {
        public static final int UP= 270;
        public static final int DOWN = 90;
        public static final int LEFT = 180;
        public static final int RIGHT = 0;
    }
    
    protected int adjustOffset(int offset, int length) {
        int signOfOffset = (int)Math.signum(offset);
        int distanceToFront = length / 2;
        int adjustAmount = distanceToFront * signOfOffset;
        return offset + adjustAmount;
    }
    
    public void move(int speed, int length, String s) {
        int currentX = getX();
        int currentY = getY();
        int direction = getRotation();
        int changeX = getChangeX(direction, speed);
        int changeY = getChangeY(direction, speed);
        int adjustedChangeX = adjustOffset(changeX, length);
        int adjustedChangeY = adjustOffset(changeY, length);
        Actor obstacle = getOneObjectAtOffset(adjustedChangeX, adjustedChangeY, Brick.class);
        if ( obstacle == null ) {
            setLocation(currentX + changeX, currentY + changeY);
        }
        else {
            if ( bulldozer ) {
                Maze w = (Maze)getWorld();
                w.removeObstacle(obstacle);
            }
            else {
                setLocation(currentX - changeX, currentY - changeY);
            }
        }
    }
    
    protected int getChangeX(int direction, int speed) {
        if (direction == Direction.RIGHT) {
            return speed;
        }
        else if (direction == Direction.LEFT) {
            return -speed;
        }
        return 0;
    }
    
    protected int getChangeY(int direction, int speed) {
        if (direction == Direction.DOWN) {
            return speed;
        }
        else if (direction == Direction.UP) {
            return -speed;
        }
        return 0;
    }
}
