package com.grzetan.jzoom;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    final double WIDTH = 1000;
    final double HEIGHT = 800;
    Thread thread;
    JZoom jZoom = new JZoom(1000,800);
    Point p = new Point(0,0);

    GamePanel(){
        this.setPreferredSize(new Dimension((int) WIDTH,(int) HEIGHT));
        jZoom.installMouseAdapter(this);
        jZoom.allowZooming(false);
        jZoom.allowDragging(false);
        this.thread = new Thread(this::run);
        thread.start();
    }

    public void paint(Graphics g){
        draw();
        jZoom.render(g,0,0,this);
    }

    public void draw() {
        jZoom.followPoint(p.x,p.y,4);
        jZoom.drawRect(0, 0, 999, 799, Color.RED);
        jZoom.fillPolygon(new double[]{100, 100, 300}, new double[]{100, 400, 300}, 3, Color.BLUE);
        jZoom.fillOval(300, 300, 100, 100, Color.RED);
        jZoom.fillRect(p.getX(),p.getY(),125,100,Color.YELLOW);
        double[] bounds = jZoom.getBounds();
        jZoom.drawRect(bounds[0], bounds[2], bounds[1] - bounds[0], bounds[3] - bounds[2], Color.BLUE);
        p.x += 1;
        p.y += 1;
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
