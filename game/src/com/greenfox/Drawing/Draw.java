package com.greenfox.Drawing;

import com.greenfox.GameCharacters.Boss;
import com.greenfox.GameCharacters.Hero;
import com.greenfox.GameCharacters.Monster;
import com.greenfox.GameObjects.Tile;

import java.awt.*;
import java.util.ArrayList;

public class Draw {

    public Draw() {
    }

    public void drawMapLevel(Tile[][] currentMap, int currentLevel, Graphics graphics) {
        for (int i = 0; i < currentMap.length - 2; i++) {
            for (int j = 0; j < currentMap[0].length - 2; j++) {
                PositionedImage image;
                graphics.fillRect(j * 72, i * 72, 72, 72);

                if (currentMap[j + 1][i + 1] == Tile.FLOOR || currentMap[j + 1][i + 1] == Tile.ENEMYFLOOR) {
                    image = new PositionedImage("imgs/lvl" + currentLevel + "/floor.png", j * 72, i * 72);
                } else if (currentMap[j + 1][i + 1] == Tile.WALLTYPE1) {
                    image = new PositionedImage("imgs//lvl" + currentLevel + "/wall1.png", j * 72, i * 72);
                } else if (currentMap[j + 1][i + 1] == Tile.WALLTYPE2) {
                    image = new PositionedImage("imgs//lvl" + currentLevel + "/wall2.png", j * 72, i * 72);
                } else if (currentMap[j + 1][i + 1] == Tile.DOOR) {
                    image = new PositionedImage("imgs//lvl" + currentLevel + "/door.png", j * 72, i * 72);
                } else {
                    image = new PositionedImage("imgs//lvl" + currentLevel + "/water.png", j * 72, i * 72);
                }
                image.draw(graphics);
            }
        }
    }

    public void drawHero(Hero hero, Graphics graphics) {
        PositionedImage image;
        image = new PositionedImage(hero.getDirection().getImagePath(), hero.getPositionX() * 72, hero.getPositionY() * 72);
        image.draw(graphics);
    }

    public void drawMonsters(ArrayList<Monster> monsters, int currentLevel, Graphics graphics) {
        PositionedImage image;
        for (Monster monster : monsters) {
            if (monster instanceof Boss) {
                image = new PositionedImage("imgs/lvl" + currentLevel + "/boss.png", monster.getPositionX() * 72, monster.getPositionY() * 72);
            } else {
                image = new PositionedImage("imgs/lvl" + currentLevel + "/monster.png", monster.getPositionX() * 72, monster.getPositionY() * 72);
            }
            image.draw(graphics);
        }
    }

    public void drawGameStats(Hero hero, int currentMapLevel, String levelName, int width, int height, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, width, width, height - width);

        String heroText1 = "Hero - Level: " + hero.getCharacterLevel() + "  |  HP: " + hero.getCurrentHealthPoint() + "/" + hero.getMaxHealthPoint();
        String heroText2 = "DP: " + hero.getDefendPoint() + "  |  SP: " + hero.getStrikePoint();
        String heroText3 = hero.printCriticalStrike();
        String mapText1 = "Map - Level: " + currentMapLevel;
        String mapText2 = levelName;

        Font font = new Font("Arial", Font.BOLD, 14);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int heroText1YCoord = width + ((height - width - metrics.getHeight() * 2) / 4) + metrics.getAscent();
        int heroText2YCoord = height - ((height - width + metrics.getHeight() * 2) / 4) + metrics.getAscent();
        int mapText1XCoord = (width - metrics.stringWidth(mapText1)) / 2;
        int mapText2XCoord = (width - metrics.stringWidth(mapText2)) / 2;
        int mapText1YCoord = width + ((height - width - metrics.getHeight() * 2) / 4) + metrics.getAscent();
        int mapText2YCoord = height - ((height - width + metrics.getHeight() * 2) / 4) + metrics.getAscent();

        graphics.setFont(font);
        graphics.setColor(new Color(0xBCFF85));
        graphics.drawString(heroText1, 5, heroText1YCoord);
        graphics.drawString(heroText2, 5, heroText2YCoord);
        graphics.setColor(new Color(0xFFEC00));
        graphics.drawString(heroText3, 8 + metrics.stringWidth(heroText2), heroText2YCoord);
        graphics.setColor(Color.WHITE);
        graphics.drawString(mapText1, mapText1XCoord, mapText1YCoord);
        graphics.drawString(mapText2, mapText2XCoord, mapText2YCoord);
    }

    public void drawEnemyStats(Monster enemy, int width, int height, Graphics graphics) {
        String monsterType = "Monster";
        if (enemy instanceof Boss) {
            monsterType = "Boss";
        }

        String enemyText1 = monsterType + " - Level: " + enemy.getCharacterLevel() + "  |  HP: " + enemy.getCurrentHealthPoint() + "/" + enemy.getMaxHealthPoint();
        String enemyText2 = "DP: " + enemy.getDefendPoint() + "  |  SP: " + enemy.getStrikePoint();
        String enemyText3 = enemy.printCriticalStrike();

        Font font = new Font("Arial", Font.BOLD, 14);
        FontMetrics metrics = graphics.getFontMetrics(font);
        int enemyText1YCoord = width + ((height - width - metrics.getHeight() * 2) / 4) + metrics.getAscent();
        int enemyText2YCoord = height - ((height - width + metrics.getHeight() * 2) / 4) + metrics.getAscent();

        graphics.setColor(new Color(0xFF8C8C));
        graphics.drawString(enemyText1, width - 5 - metrics.stringWidth(enemyText1), enemyText1YCoord);
        graphics.drawString(enemyText2, width - 5 - metrics.stringWidth(enemyText2), enemyText2YCoord);
        graphics.setColor(new Color(0xFFEC00));
        graphics.drawString(enemyText3, width - 13 - metrics.stringWidth(enemyText2) - metrics.stringWidth(enemyText3), enemyText2YCoord);
    }

    public void gameOver(Graphics graphics) {
        String gameOverText = "GAME OVER";
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 100));
        graphics.drawString(gameOverText, 50, 370);
    }
}
