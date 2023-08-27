package sample;

import java.util.Random;

public class PlayerCharacter {
    private int hitPoints = 20;
    private int strength;
    private int dexterity;
    private int intelligence;
    private int totalGold;

    public PlayerCharacter() {
        rollAttributes();
    }

    public void reducehitPoints(int damage) {
        hitPoints-=damage;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
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
    public int setTotalGold(int gold) {
        return totalGold+=gold;
    }

    private int rollDice(int sides) {
        return new Random().nextInt(sides) + 1;
    }

    private void rollAttributes() {
        strength = rollAttribute();
        dexterity = rollAttribute();
        intelligence = rollAttribute();
    }

    private int rollAttribute() {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            total += rollDice(6);
        }
        return total;
    }
}
