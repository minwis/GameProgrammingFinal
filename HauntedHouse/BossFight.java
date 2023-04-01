import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BossFight extends World
{

    public static boolean printInstruction = true;

    public BossFight()
    {    
        super(670, 720, 1);
        if ( printInstruction ) {
            printInstruction = false;
            System.out.println("\n" + "It is the last round! Kill the boss and win the game!" + "\n" +
            "Press X key to shoot the bullets, the bullets will be fired to the direction of your mouse!");
        }
        addObject(new Boss(), 335, 360);
        if (getObjects(Player.class).size() < 1) {
            addObject(new Player(), 335, 600);
        }
    }
}
