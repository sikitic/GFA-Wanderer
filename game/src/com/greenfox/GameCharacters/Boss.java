package com.greenfox.GameCharacters;

public class Boss extends Monster {
    public Boss(int characterLevel) {
        super(0,
                0,
                characterLevel,
                13 + 2 * characterLevel,
                13 + 2 * characterLevel,
                characterLevel + 1,
                3 + characterLevel,
                3 * characterLevel,
                false,
                false);
    }
}