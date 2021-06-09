package com.grzetan.jzoom;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    final double WIDTH = 1000;
    final double HEIGHT = 800;
    Thread thread;
    JZoom jZoom = new JZoom(500,400);

    GamePanel(){
        this.setPreferredSize(new Dimension((int) WIDTH,(int) HEIGHT));
        jZoom.installMouseAdapter(this);
        this.thread = new Thread(this::run);
        thread.start();
    }

    public void paint(Graphics g){
        draw();
        jZoom.render(g,100,100,this);
    }

    public void draw() {
        jZoom.drawRect(0, 0, 999, 799, Color.RED);
        jZoom.fillPolygon(new double[]{100, 100, 300}, new double[]{100, 400, 300}, 3, Color.BLUE);
        jZoom.fillOval(300, 300, 100, 100, Color.RED);
    }

    public void run(){
        long now;
        long updateTime;
        long waitTime;
        int TARGET_FRAMES = 30;
        long OPTIMAL_TIME = 1_000_000_000 / TARGET_FRAMES;

        while(true){
            now = System.nanoTime();

            repaint();

            updateTime = now - System.nanoTime();
            waitTime = (OPTIMAL_TIME - updateTime) / 1_000_000;
            try {
                thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
