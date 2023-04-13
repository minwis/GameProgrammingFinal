import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Scoreboard extends Actor
{
    
    Maze myWorld1;
    BossFight myWorld2;
    int width;
    int height = 60;
    GreenfootImage boardImage;
    public int lives = 5;
    public void addedToWorld(World w) {
        if (Player.stage1) {
            myWorld1 = (Maze) w;
            width = myWorld1.getWidth();
            boardImage = new GreenfootImage(width, height);
            boardImage.setColor(Color.BLACK);
            boardImage.fillRect(0,0,width,height);
            setImage(boardImage);
        }
        else {
            myWorld2 = (BossFight) w;
            width = myWorld2.getWidth();
            boardImage = new GreenfootImage(width, height);
            boardImage.setColor(Color.BLACK);
            boardImage.fillRect(0,0,width,height);
            setImage(boardImage);
        }
        
    }
    
    public void act() {
        addedToWorld(getWorld());
        drawLives();
        if ( lives == 0 ) {
            if (Player.stage1 == true) {
                Maze world = (Maze)getWorld();
                world.getScoreboard().drawLives();
                Greenfoot.stop();
                world.print("YOU LOST!");
                lives = 5;
            }
            else {
                BossFight world = (BossFight)getWorld();
                world.getScoreboard().drawLives();
                Greenfoot.stop();
                world.print("YOU LOST!");
                lives = 10;
            }
            
        }
    }
    
    public void drawLives() {
        boardImage.setColor(Color.BLACK);
        boardImage.fillRect(getWorld().getWidth()/2, 0, width, height);
        boardImage.setColor(Color.RED);
        boardImage.setFont(new Font("Arial", 24));
        boardImage.drawString("Lives: " + lives, 30, 40);
    }
    
    public void decreaseLive() {
        lives--;
    }
    
    public void increaseLive() {
        lives++;
    }
}
