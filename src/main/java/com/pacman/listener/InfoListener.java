package com.pacman.listener;

import com.pacman.gui.Colors;
import com.pacman.gui.MainPanel;
import com.pacman.logic.Game;
import com.pacman.logic.Settings;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InfoListener implements MouseListener {
    MainPanel mainPanel;
    JLabel homeLabel;
    JLabel fullscreen;
    JLabel activeComp;
    public InfoListener(MainPanel mainPanel, JLabel homeLabel, JLabel fullscreen){
        this.mainPanel = mainPanel;
        this.homeLabel = homeLabel;
        this.fullscreen = fullscreen;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource().equals(homeLabel) && e.getSource().equals(activeComp)){
          mainPanel.displayHome();
        }
        else if(e.getSource().equals(fullscreen) && e.getSource().equals(activeComp)){
            Settings.isFullscreen = !Settings.isFullscreen;
            Game.setWindowMode();
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(homeLabel)){
            homeLabel.setBackground(Colors.hoveredLabels);
        }
        else if(e.getSource().equals(fullscreen)){
            fullscreen.setBackground(Colors.hoveredLabels);
        }
        activeComp = (JLabel) e.getSource();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(homeLabel)){
            homeLabel.setBackground(Colors.labels);
        }
        else if(e.getSource().equals(fullscreen)){
            fullscreen.setBackground(Colors.labels);
        }
        activeComp = null;
    }
}
