package com.greenfox.GameObjects;

import com.greenfox.Drawing.Draw;
import com.greenfox.GameCharacters.HeroDirection;
import com.greenfox.GameCharacters.Monster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JComponent implements KeyListener, ActionListener {

    private static final int WIDTH = 720;
    private static final int HEIGHT = 766;
    private final Level level;
    private final Draw draw;
    private int timeInterval;
    private boolean isNewLevel;
    private boolean doorCreated;
    private final Timer timerMoving;
    private final Timer timerHealing;

    public Board() {
        level = new Level();
        draw = new Draw();
        timeInterval = 1000;
        isNewLevel = true;
        timerMoving = new Timer(timeInterval, this);
        timerHealing = new Timer(timeInterval, this);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        if (isNewLevel) {
            level.setNewLevel();
            timerMoving.setDelay(timeInterval);
            timeInterval -= 90;
            isNewLevel = false;
            doorCreated = false;
        }

        draw.drawMapLevel(level.getMap(), level.getLevelNumber(), graphics);
        draw.drawHero(level.getHero(), graphics);
        draw.drawMonsters(level.getMonsters(), level.getLevelNumber(), graphics);

        if (level.getHero().possibleFight(level.getMap())) {
            level.getHero().getEnemy(level.getMonsters()).strike(level.getHero());
            timerMoving.stop();
            timerHealing.stop();
            draw.drawGameStats(level.getHero(), level.getLevelNumber(), level.getName(), WIDTH, HEIGHT, graphics);
            draw.drawEnemyStats(level.getHero().getEnemy(level.getMonsters()), WIDTH, HEIGHT, graphics);
        } else {
            draw.drawGameStats(level.getHero(), level.getLevelNumber(), level.getName(), WIDTH, HEIGHT, graphics);
        }

        if (level.getHero().isDead()) {
            timerMoving.stop();
            timerHealing.stop();
            draw.gameOver(graphics);
        }

        if (level.getMonsters().isEmpty()) {
            if (!doorCreated) {
                level.addDoor();
                doorCreated = true;
            } else if (level.getMap()[level.getHero().getPositionX() + 1][level.getHero().getPositionY() + 1] == Tile.DOOR) {
                isNewLevel = true;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!level.getHero().isDead()) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE && level.getHero().possibleFight(level.getMap())) {
                level.getHero().strike(level.getHero().getEnemy(level.getMonsters()));
                if (level.getHero().getEnemy(level.getMonsters()).isDead()) {
                    level.getMonsters().remove(level.getHero().getEnemy(level.getMonsters()));
                    level.getMap()[level.getHero().getPositionX() + 1][level.getHero().getPositionY() + 1] = Tile.FLOOR;
                }
                repaint();
            } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
                if (!timerMoving.isRunning()) {
                    timerMoving.start();
                    timerHealing.start();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    level.getHero().move(0, -1, level.getMap(), level.getLevelNumber());
                    level.getHero().setDirection(HeroDirection.UP);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    level.getHero().move(0, 1, level.getMap(), level.getLevelNumber());
                    level.getHero().setDirection(HeroDirection.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    level.getHero().move(1, 0, level.getMap(), level.getLevelNumber());
                    level.getHero().setDirection(HeroDirection.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    level.getHero().move(-1, 0, level.getMap(), level.getLevelNumber());
                    level.getHero().setDirection(HeroDirection.LEFT);
                }
                repaint();
            }
        }
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timerMoving) {
            for (Monster monster : level.getMonsters()) {
                level.setMap(monster.randomMove(level.getMap()));
            }
        }
        if (ev.getSource() == timerHealing) {
            level.getHero().heal(level.getMap());
            for (Monster monster : level.getMonsters()) {
                monster.heal();
            }
        }
        repaint();
    }
}