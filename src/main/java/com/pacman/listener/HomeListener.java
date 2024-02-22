package com.pacman.listener;

import com.pacman.gui.Colors;
import com.pacman.gui.MainPanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HomeListener implements MouseListener {
    private final MainPanel mainPanel;
    private final JLabel gameLabel;
    private final JLabel settingsLabel;
    private final JLabel themesLabel;
    private JLabel activeComp;
    public HomeListener(MainPanel mainPanel, JLabel gameLabel, JLabel settingsLabel, JLabel themesLabel){
        this.mainPanel = mainPanel;
        this.gameLabel = gameLabel;
        this.settingsLabel = settingsLabel;
        this.themesLabel = themesLabel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource().equals(gameLabel) && e.getSource().equals(activeComp)){
            mainPanel.displayGame();
        }
        else if(e.getSource().equals(settingsLabel) && e.getSource().equals(activeComp)){
            mainPanel.displaySettings();
        }
        else if(e.getSource().equals(themesLabel) && e.getSource().equals(activeComp)){
            mainPanel.displayThemes();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(gameLabel)){
            gameLabel.setBackground(Colors.hoveredLabels);
        }
        else if(e.getSource().equals(settingsLabel)){
            settingsLabel.setBackground(Colors.hoveredLabels);
        }
        else if(e.getSource().equals(themesLabel)){
            themesLabel.setBackground(Colors.hoveredLabels);
        }
        activeComp = (JLabel) e.getSource();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(gameLabel)){
            gameLabel.setBackground(Colors.labels);
        }
        else if(e.getSource().equals(settingsLabel)){
            settingsLabel.setBackground(Colors.labels);
        }
        else if(e.getSource().equals(themesLabel)){
            themesLabel.setBackground(Colors.labels);
        }
        activeComp = null;
    }
}
