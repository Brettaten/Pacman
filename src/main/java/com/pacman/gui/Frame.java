package com.pacman.gui;

import com.pacman.logic.Settings;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public boolean isFullscreen;
    public JPanel mainPanel;
    public Frame(){
        this.isFullscreen = Settings.isFullscreen;
        if(isFullscreen){
            setupFullscreen();
        }
        else{
            setupWindowedFrame();
        }
        mainPanel = new MainPanel();
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void setupWindowedFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Colors.mainPanel);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(600, 400));
    }

    private void setupFullscreen() {
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
    }
}
