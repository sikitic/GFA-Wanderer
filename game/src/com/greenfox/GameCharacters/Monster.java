package com.greenfox.GameCharacters;

import com.greenfox.GameObjects.Tile;

import java.util.Random;

public abstract class Monster extends Character {

    public Monster(int positionX, int positionY, int characterLevel, int currentHealthPoint, int maxHealthPoint, int defendPoint, int strikePoint, int criticalStrikeChance, boolean isDead, boolean isCriticalStrike) {
        super(positionX, positionY, characterLevel, currentHealthPoint, maxHealthPoint, defendPoint, strikePoint, criticalStrikeChance, isDead, isCriticalStrike);
    }

    public Tile[][] initializeRandomPosition(Tile[][] map, int minDistanceFromHero) {
        int random1, random2;

        do {
            random1 = new Random().nextInt(10 - minDistanceFromHero);
            random2 = new Random().nextInt(10 - minDistanceFromHero);
        } while (map[minDistanceFromHero + random1 + 1][minDistanceFromHero + random2 + 1] != Tile.FLOOR);
        positionX = minDistanceFromHero + random1;
        positionY = minDistanceFromHero + random2;
        map[positionX + 1][positionY + 1] = Tile.ENEMYFLOOR;

        return map;
    }

    public Tile[][] randomMove(Tile[][] map) {
        int random1 = new Random().nextBoolean() ? 1 : -1;
        int random2 = new Random().nextBoolean() ? 1 : -1;
        int finalPositionX = positionX;
        int finalPositionY = positionY;

        if (random1 == 1) {
            if (map[positionX + random2 + 1][positionY + 1] == Tile.FLOOR || map[positionX + random2 + 1][positionY + 1] == Tile.WATER) {
                finalPositionX = positionX + random2;
            } else if (map[positionX - random2 + 1][positionY + 1] == Tile.FLOOR || map[positionX - random2 + 1][positionY + 1] == Tile.WATER) {
                finalPositionX = positionX - random2;
            } else if (map[positionX + 1][positionY + random1 + 1] == Tile.FLOOR || map[positionX + 1][positionY + random1 + 1] == Tile.WATER) {
                finalPositionY = positionY + random1;
            } else if (map[positionX + 1][positionY - random1 + 1] == Tile.FLOOR || map[positionX + 1][positionY - random1 + 1] == Tile.WATER) {
                finalPositionY = positionY - random1;
            }
        } else {
            if (map[positionX + 1][positionY + random2 + 1] == Tile.FLOOR || map[positionX + 1][positionY + random2 + 1] == Tile.WATER) {
                finalPositionY = positionY + random2;
            } else if (map[positionX + 1][positionY - random2 + 1] == Tile.FLOOR || map[positionX + 1][positionY - random2 + 1] == Tile.WATER) {
                finalPositionY = positionY - random2;
            } else if (map[positionX + random1 + 1][positionY + 1] == Tile.FLOOR || map[positionX + random1 + 1][positionY + 1] == Tile.WATER) {
                finalPositionX = positionX + random1;
            } else if (map[positionX - random1 + 1][positionY + 1] == Tile.FLOOR || map[positionX - random1 + 1][positionY + 1] == Tile.WATER) {
                finalPositionX = positionX - random1;
            }
        }

        if (map[positionX + 1][positionY + 1] == Tile.ENEMYFLOOR) {
            map[positionX + 1][positionY + 1] = Tile.FLOOR;
        } else {
            map[positionX + 1][positionY + 1] = Tile.WATER;
        }

        if (map[finalPositionX + 1][finalPositionY + 1] == Tile.FLOOR || map[finalPositionX + 1][finalPositionY + 1] == Tile.ENEMYFLOOR) {
            positionX = finalPositionX;
            positionY = finalPositionY;
            map[positionX + 1][positionY + 1] = Tile.ENEMYFLOOR;
        } else {
            positionX = finalPositionX;
            positionY = finalPositionY;
            map[positionX + 1][positionY + 1] = Tile.ENEMYWATER;
        }

        return map;
    }
}
