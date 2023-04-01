import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HorizontalGhost extends Actor
{
    private double GscaleFactor = 0.9;
    private int speed = 2;
    private int length = (int)(40 * GscaleFactor);
    private boolean left = false;
    
    public void act()
    {
        if ( left ) {
            setRotation(Direction.LEFT);
            move(speed, length, "g");
        }
        else {
            setRotation(Direction.RIGHT);
            move(speed, length, "g");
        }
        
        if (!Player.protection) {
            if (isTouching(Player.class)) {
                removeTouching(Player.class);
                Maze world = (Maze)getWorld();
                world.addPlayer();
                world.getScoreboard().decreaseLive();
            }
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
            if ( left ) {
            left = false;
            }
            else if ( !left ) {
            left = true;
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
