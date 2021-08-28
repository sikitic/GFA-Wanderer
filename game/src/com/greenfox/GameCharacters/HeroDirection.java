package com.greenfox.GameCharacters;

public enum HeroDirection {
    UP("imgs/hero-up.png"),
    DOWN("imgs/hero-down.png"),
    RIGHT("imgs/hero-right.png"),
    LEFT("imgs/hero-left.png");

    private final String imagePath;

    HeroDirection(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }
}
