package com.greenfox.GameObjects;

import com.greenfox.GameCharacters.Boss;
import com.greenfox.GameCharacters.Creature;
import com.greenfox.GameCharacters.Hero;
import com.greenfox.GameCharacters.Monster;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Level {
    private String name;
    private Hero hero;
    private ArrayList<Monster> monsters;
    private Tile[][] map;
    private int levelNumber;
    private int monsterLevel;

    public Level() {
        hero = new Hero(1);
        monsters = new ArrayList<>();
        levelNumber = 3;
        monsterLevel = 0;
    }

    public void readMapLevel(String fileName) {
        Path filePath = Paths.get(fileName);
        try {
            List<String> fileLines = Files.readAllLines(filePath);
            name = fileLines.get(fileLines.size() - 1);
            map = new Tile[fileLines.size() + 2][fileLines.size() + 2];

            for (Tile[] tiles : map) {
                Arrays.fill(tiles, Tile.WALLTYPE1);
            }

            for (int i = 0; i < fileLines.size() - 1; i++) {
                for (int j = 0; j < fileLines.get(i).length(); j++) {
                    if (fileLines.get(i).charAt(j) == '1') {
                        map[j + 1][i + 1] = Tile.WALLTYPE2;
                    } else if (fileLines.get(i).charAt(j) == '2') {
                        map[j + 1][i + 1] = Tile.FLOOR;
                    } else if (fileLines.get(i).charAt(j) == '3') {
                        map[j + 1][i + 1] = Tile.WATER;
                    }
                }
            }
        } catch (IOException exc) {
            System.out.println("Unable to read file");
        }
    }

    public void setNewLevel() {
        levelNumber++;
        readMapLevel("maps/lvl" + levelNumber + ".txt");
        hero.setPositionX(0);
        hero.setPositionY(0);
        hero.setCurrentHealthPoint(hero.getMaxHealthPoint());

        int creaturesCount = (levelNumber - 1) / 2 + 3;
        for (int i = 0; i < creaturesCount; i++) {
            monsters.add(new Creature(monsterLevel + i + 1));
            map = monsters.get(i).initializeRandomPosition(map, 2);
        }

        monsters.add(new Boss(monsterLevel + creaturesCount + 1));
        map = monsters.get(creaturesCount).initializeRandomPosition(map, 8);
        monsterLevel += creaturesCount + 1;
    }

    public void addDoor() {
        int random1, random2;

        do {
            random1 = new Random().nextInt(10);
            random2 = new Random().nextInt(10);
        } while (map[random1 + 1][random2 + 1] != Tile.FLOOR && map[random1 + 1][random2 + 1] != map[hero.getPositionX() + 1][hero.getPositionY() + 1]);
        map[random1 + 1][random2 + 1] = Tile.DOOR;
    }

    public String getName() {
        return name;
    }

    public Hero getHero() {
        return hero;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
