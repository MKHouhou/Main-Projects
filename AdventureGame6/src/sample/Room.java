package sample;


import java.util.Random;

public class Room {

    private boolean containsMonster = false;
    private NPC npc= new NPC();
    private boolean goldClaimed = false;
    Room() {
        Random random = new Random();
        if (random.nextInt(100) < 50) {
            containsMonster = true;
        }
    }
    public boolean containsMonster() {
        return containsMonster;
    }

    public NPC getMonster() {
        return npc;
    }
    public void resetMonster() {
        npc = new NPC();
    }

    public boolean isGoldClaimed () {
        return goldClaimed;
    }
    public void killMonster(boolean deadMonster) {
        containsMonster = false;
    }

}
