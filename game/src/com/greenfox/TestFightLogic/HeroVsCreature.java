package com.greenfox.TestFightLogic;

import java.util.Random;

public class HeroVsCreature {
    public static void main(String[] args) {
        int heroCounter = 0;
        int creatureCounter = 0;

        int numberOfFights = 10000;
        int heroLevel = 29;
        int creatureLevel = heroLevel;

        for (int i = 0; i < numberOfFights; i++) {

            int heroCurrentHealthPoint = 13 + 2 * heroLevel;
            int heroDefendPoint = heroLevel + 1;
            int heroStrikePoint = 3 + heroLevel + 2;
            int heroCriticalStrikeChance = heroLevel;

            int creatureCurrentHealthPoint = 13 + 2 * creatureLevel;
            int creatureDefendPoint = creatureLevel;
            int creatureStrikePoint = 3 + creatureLevel + 1;
            int creatureCriticalStrikeChance = 2 * creatureLevel;

            while (creatureCurrentHealthPoint > 0 && heroCurrentHealthPoint > 0) {
                if (creatureCriticalStrikeChance > new Random().nextInt(100)) {
                    heroCurrentHealthPoint -= (int) Math.round(2.3 * creatureStrikePoint) - (int) Math.round(2.3 * heroDefendPoint);
                } else {
                    heroCurrentHealthPoint -= creatureStrikePoint - heroDefendPoint;
                }
                if (heroCurrentHealthPoint > 0) {
                    if (heroCriticalStrikeChance > new Random().nextInt(100)) {
                        creatureCurrentHealthPoint -= (int) Math.round(2.3 * heroStrikePoint) - (int) Math.round(2.3 * creatureDefendPoint);
                    } else {
                        creatureCurrentHealthPoint -= heroStrikePoint - creatureDefendPoint;
                    }
                }
            }

            if (heroCurrentHealthPoint > creatureCurrentHealthPoint) {
//                System.out.println("Hero " + heroCurrentHealthPoint);
                heroCounter++;
            } else {
//               System.out.println("creature " + creatureCurrentHealthPoint);
                creatureCounter++;
            }
        }
        System.out.println(heroLevel + " lvl hero wins in " + (double) heroCounter / (numberOfFights / 100) + "%");
        System.out.println(creatureLevel + " lvl creature wins in " + (double) creatureCounter / (numberOfFights / 100) + "%");

/*        int heroLevel = 29;
        int mapLevel = 1;
        int currentHealthPoint = 8 + 2 * heroLevel;
        int defendPoint = heroLevel + mapLevel;
        int strikePoint = 3 + heroLevel + 1;
        int criticalStrikeChance = heroLevel;

        int creatureLevel = heroLevel;
        int creatureCurrentHealthPoint = 8 + 2 * creatureLevel;
        int creatureDefendPoint = creatureLevel;
        int creatureStrikePoint = 3 + creatureLevel;
        int creatureCriticalStrikeChance = 3 * creatureLevel;

        System.out.println("Hero " + currentHealthPoint + " : " + creatureCurrentHealthPoint + " creature");

        while (creatureCurrentHealthPoint > 0 && currentHealthPoint > 0) {

            if (creatureCriticalStrikeChance > new Random().nextInt(150)) {
                System.out.println("Critic " + (3 * creatureStrikePoint - 3 * defendPoint));
                currentHealthPoint -= 3 * creatureStrikePoint - 3 * defendPoint;
            } else {
                System.out.println(creatureStrikePoint - defendPoint);
                currentHealthPoint -= creatureStrikePoint - defendPoint;
            }

            if (criticalStrikeChance > new Random().nextInt(150)) {
                System.out.println("Critic " + (3 * strikePoint - 3 * creatureDefendPoint));
                creatureCurrentHealthPoint -= 3 * strikePoint - 3 * creatureDefendPoint;
            } else {
                System.out.println(strikePoint - creatureDefendPoint);
                creatureCurrentHealthPoint -= strikePoint - creatureDefendPoint;
            }
            System.out.println("Hero " + currentHealthPoint + " : " + creatureCurrentHealthPoint + " creature");
        }*/
    }
}
