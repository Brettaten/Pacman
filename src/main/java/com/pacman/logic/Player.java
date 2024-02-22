package com.pacman.logic;

import javax.swing.*;
import java.awt.*;

public class Player extends JLabel implements Movable{
    int direction;
    public Player(){
    }
    @Override
    public void move(int direction) {
        this.direction = direction;
    }
    @Override
    public void setInitialPos(){

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
