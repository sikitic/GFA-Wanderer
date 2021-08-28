package com.greenfox.GameCharacters;

import java.util.Random;

public abstract class Character {
    protected int positionX;
    protected int positionY;
    protected int characterLevel;
    protected int currentHealthPoint;
    protected int maxHealthPoint;
    protected int defendPoint;
    protected int strikePoint;
    protected int criticalStrikeChance;
    protected boolean isDead;
    protected boolean isCriticalStrike;

    public Character(int positionX, int positionY, int characterLevel, int currentHealthPoint, int maxHealthPoint, int defendPoint, int strikePoint, int criticalStrikeChance, boolean isDead, boolean isCriticalStrike) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.characterLevel = characterLevel;
        this.currentHealthPoint = currentHealthPoint;
        this.maxHealthPoint = maxHealthPoint;
        this.defendPoint = defendPoint;
        this.strikePoint = strikePoint;
        this.criticalStrikeChance = criticalStrikeChance;
        this.isDead = isDead;
        this.isCriticalStrike = isCriticalStrike;
    }

    public void heal() {
        if (currentHealthPoint < maxHealthPoint) {
            currentHealthPoint++;
        }
    }

    public void strike(Character another) {
        if (criticalStrikeChance > new Random().nextInt(100)) {
            isCriticalStrike = true;
            another.currentHealthPoint -= (int) Math.round(2.3 * strikePoint) - (int) Math.round(2.3 * another.defendPoint);
        } else {
            another.currentHealthPoint -= strikePoint - another.defendPoint;
        }

        if (another.currentHealthPoint <= 0) {
            another.isDead = true;
            if (another instanceof Monster) {
                characterLevel++;
                maxHealthPoint = 13 + 2 * characterLevel;
                defendPoint = characterLevel + 1;
                strikePoint = 3 + characterLevel + 2;
                criticalStrikeChance = characterLevel;
            }
        }
    }

    public String printCriticalStrike() {
        if (isCriticalStrike) {
            isCriticalStrike = false;
            return "Critical Strike";
        }
        return "";
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public int getCurrentHealthPoint() {
        return currentHealthPoint;
    }

    public void setCurrentHealthPoint(int currentHealthPoint) {
        this.currentHealthPoint = currentHealthPoint;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public int getDefendPoint() {
        return defendPoint;
    }

    public int getStrikePoint() {
        return strikePoint;
    }

    public boolean isDead() {
        return isDead;
    }
}
