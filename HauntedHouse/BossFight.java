import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossFight extends World
{
    private Scoreboard sb;
    public static boolean printInstruction = true;

    public BossFight()
    {    
        super(670, 720, 1);
        Player.stage1 = false;
        sb = new Scoreboard();
        sb.lives = 10;
        addObject(sb, getWidth()/2,695);
        if ( printInstruction ) {
            printInstruction = false;
            System.out.println("\n" + "It is the last stage! Kill the boss and win the game!" + "\n" +
            "Press X key to shoot the bullets, the bullets will be fired to the direction of your mouse!");
        }
        addObject(new Boss(), 335, 360);
    }
    
    public Scoreboard getScoreboard() {
        return sb;
    }
    
    public void print(String message) {
        GameOver gameOver = new GameOver(message, 60);
        addObject(gameOver, getWidth()/2, getHeight() / 2);
    }
}
