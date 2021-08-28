package com.greenfox.GameCharacters;

public class Creature extends Monster {
    public Creature(int characterLevel) {
        super(0,
                0,
                characterLevel,
                13 + 2 * characterLevel,
                13 + 2 * characterLevel,
                characterLevel,
                3 + characterLevel + 1,
                2 * characterLevel,
                false,
                false);
    }
}
