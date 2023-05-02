import greenfoot.*;

public class BossFight extends World
{
    private Scoreboard sb;
    public static boolean printInstruction = true;

    public BossFight()
    {    
        super(670, 720, 1);
        Player p = new Player();
        
        p.stage1 = false;
        sb = new Scoreboard();
        sb.lives = 10;
        addObject(sb, getWidth()/2,695);
        if ( printInstruction ) {
            printInstruction = false;
            System.out.println("\n" + "It is the last stage! Kill the boss and win the game!" + "\n" +
            "Press X key to shoot the bullets, the bullets will be fired to the direction of your mouse! The boss' health is indicated as the bar on the above" + "\n" + 
            "+ Avoid the Minion Ghosts(they can give you one more live if you kill them!) and fireballs!");
        }
        addObject(new Boss(), 335, 360);
        addObject(new Player(), 335, 560);
    }
    
    public Scoreboard getScoreboard() {
        return sb;
    }
    
    public void print(String message) {
        GameOver gameOver = new GameOver(message, 60);
        addObject(gameOver, getWidth()/2, getHeight() / 2);
    }
}
