package sample;

import java.util.Random;

public class NPC {
    private int hitPoints;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int totalGold;

    public NPC() {
        Random random = new Random();
        this.hitPoints = random.nextInt(6) + 1;
        this.strength = (random.nextInt(6) + 1) * 2;
        this.dexterity = (random.nextInt(6) + 1) * 2;
        this.intelligence = (random.nextInt(6) + 1) * 2;
        this.totalGold = random.nextInt(21);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getTotalGold() {
        return totalGold;
    }

    public void reduceHitPoints(int damage) {
        hitPoints = Math.max(0, hitPoints - damage);
    }

}
