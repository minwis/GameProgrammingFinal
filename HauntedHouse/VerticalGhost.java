import greenfoot.*;

public class VerticalGhost extends Actor
{
    
    private double GscaleFactor = 0.9;
    private int speed = 1;
    private int length = (int)(40 * GscaleFactor);
    private boolean down = false;
    
    public void act()
    {
        if ( down ) {
            setRotation(Direction.DOWN);
            move(speed, length, "g2");
        }
        else {
            setRotation(Direction.UP);
            move(speed, length, "g2");
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
            if ( down ) {
            down = false;
            }
            else if ( !down ) {
            down = true;
            }
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
    
    protected int getChangeY(int direction, int speed) {
        if (direction == Direction.DOWN) {
            return speed;
        }
        else if (direction == Direction.UP) {
            return -speed;
        }
        return 0;
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
    
}
