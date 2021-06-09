package com.grzetan.jzoom;

import javax.swing.*;

public class GameFrame extends JFrame {
    GamePanel panel;

    GameFrame(){
        this.setVisible(true);
        this.setFocusable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("JZoom");
        this.setResizable(false);
        panel = new GamePanel();
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args){
        new GameFrame();
    }
}
