import greenfoot.*;
import java.util.*;

public class Maze extends World
{
    private Scoreboard sb;
    private static boolean print_story = true;
    private int[] playerCoordinate = new int[2];
        public Maze()
        {
        super(670, 720, 1); /*(23 * 30)^2, 23 is the number of the blocks
        the maze has, 30 is the number of pixels each block has.*/
        
        sb = new Scoreboard();
        addObject(sb, getWidth()/2,695);
        Player.stage1 = true;
        String[][] arr = new Maze_blueprint().return_blueprint();
        for (int i = 0; i < arr.length; i++ ) {
            for (int j = 0; j < arr[0].length; j++ ) {
                if ( arr[j][i].equals("b") ) {
                    addObject(new Brick(), i * 30, j * 30);
                }
                else if ( arr[j][i].equals("s") ) {
                    addObject(new Player(), i * 30, j * 30 + 15);
                    playerCoordinate[0] = i * 30;
                    playerCoordinate[1] = j * 30;
                    }
                else if ( arr[j][i].equals("e") ) {
                    addObject(new Exit(), i * 30, j * 30);
                }
                else if ( arr[j][i].equals("g") ) {
                    addObject(new HorizontalGhost(), i * 30, j * 30);                    }
                else if ( arr[j][i].equals("g2") ) {
                    addObject(new VerticalGhost(), i * 30, j * 30);
                }
                else if ( arr[j][i].equals("h") ) {
                    addObject(new HealingPotion(), i * 30, j * 30);
                }
                else if ( arr[j][i].equals("p") ) {
                    addObject(new ProtectionAmulet(), i * 30, j * 30);
                }
                else if ( arr[j][i].equals("k") ) {
                    addObject(new KillerAmulet(), i * 30, j * 30);
                }
                else if ( arr[j][i].equals("i") ) {
                    addObject(new BulldozerAmulet(), i * 30, j * 30);
                }
            }
        
        }
        
        if (print_story) {
            System.out.print("You are the ghost hunter..." + "\n" + 
            "But you are stuck in the haunted house!" + "\n" + "Escape the maze" + "\n"
            + "And fight against the ghost boss!"+ "\n" + "Now, press the 'Run' button to start!");
            print_story = false;
        }
    }
    
    public void print(String message) {
        GameOver gameOver = new GameOver(message, 60);
        addObject(gameOver, getWidth()/2, getHeight() / 2);
    }
    
    public Scoreboard getScoreboard() {
        return sb;
    }
    
    public void removeObstacle(Actor obstacle) {
        removeObject(obstacle);
    }
    
    public void addPlayer() {
        addObject(new Player(), playerCoordinate[0], playerCoordinate[1]);
    }
    
    public void makeNewWorld() {
        Timer timer = new Timer();
        TimerTask task = new wait();
        timer.schedule(task, 300);
        return;
    }
    
    private class wait extends TimerTask {
        public void run() {
            Greenfoot.setWorld(new BossFight());
        }
    }
}
