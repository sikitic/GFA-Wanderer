package com.greenfox;

import com.greenfox.GameObjects.Board;

import javax.swing.*;

public class GameMain {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wanderer");
        Board board = new Board();
        frame.add(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(board);
    }
}
