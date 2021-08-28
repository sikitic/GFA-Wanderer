package com.greenfox.GameCharacters;

import com.greenfox.GameObjects.Tile;

import java.util.ArrayList;

public class Hero extends Character {
    private HeroDirection heroDirection;
    public Hero(int characterLevel) {
        super(0,
                0,
                characterLevel,
                13 + 2 * characterLevel,
                13 + 2 * characterLevel,
                characterLevel + 1,
                3 + characterLevel + 2,
                characterLevel,
                false,
                false);
        this.heroDirection = HeroDirection.DOWN;
    }

    public void move(int shiftX, int shiftY, Tile[][] map, int level) {
        if (map[positionX + shiftX + 1][positionY + shiftY + 1] != Tile.WALLTYPE1 && map[positionX + shiftX + 1][positionY + shiftY + 1] != Tile.WALLTYPE2) {
            positionX += shiftX;
            positionY += shiftY;

            if (map[positionX + 1][positionY + 1] == Tile.WATER) {
                if (level == 5) {
                    currentHealthPoint -= maxHealthPoint / 2;
                    if (currentHealthPoint <= 0) {
                        isDead = true;
                    }
                } else {
                    currentHealthPoint = 0;
                    isDead = true;
                }
            }
        }
    }

    public void heal(Tile[][] map) {
        if (map[positionX + 1][positionY + 1] != Tile.WATER)
            super.heal();
    }

    public Monster getEnemy(ArrayList<Monster> monsters) {
        for (Monster monster : monsters) {
            if (positionX == monster.positionX && positionY == monster.positionY) {
                return monster;
            }
        }
        return new Creature(0);
    }

    public boolean possibleFight(Tile[][] map) {
        return map[positionX + 1][positionY + 1] == Tile.ENEMYFLOOR || map[positionX + 1][positionY + 1] == Tile.ENEMYWATER;
    }

    public HeroDirection getDirection() {
        return heroDirection;
    }

    public void setDirection(HeroDirection heroDirection) {
        this.heroDirection = heroDirection;
    }
}
