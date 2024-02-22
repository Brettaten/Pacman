package com.pacman.listener;

import com.pacman.gui.Colors;
import com.pacman.gui.MainPanel;
import com.pacman.logic.Game;
import com.pacman.logic.Settings;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SettingsListener implements MouseListener {
    public MainPanel mainPanel;
    public JLabel fullscreenON;
    public JLabel fullscreenOFF;
    public JLabel activeComp;
    public JLabel home;
    public JLabel defaultSettings;
    public static ArrayList<JLabel> selected;
    public SettingsListener(MainPanel mainPanel, JLabel fullscreenON, JLabel fullscreenOFF, JLabel home, JLabel defaultSettings){
        this.mainPanel = mainPanel;
        this.fullscreenON = fullscreenON;
        this.fullscreenOFF = fullscreenOFF;
        this.home = home;
        this.defaultSettings = defaultSettings;

        selected = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource().equals(fullscreenON) && e.getSource().equals(activeComp)){
            selectFullscreen(fullscreenON);
            if(!Settings.isFullscreen){
                Settings.isFullscreen = true;
                Game.setWindowMode();
            }
        }
        else if(e.getSource().equals(fullscreenOFF) && e.getSource().equals(activeComp)){
            selectFullscreen(fullscreenOFF);
            if(Settings.isFullscreen){
                Settings.isFullscreen = false;
                Game.setWindowMode();
            }
        }
        else if(e.getSource().equals(home) && e.getSource().equals(activeComp)){
            mainPanel.displayHome();
        }
        else if(e.getSource().equals(defaultSettings) && e.getSource().equals(activeComp)){
            mainPanel.settingsPanel.setDefault();
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(fullscreenON)){
            fullscreenON.setBackground(Colors.hoveredLabels);
        }
        else if(e.getSource().equals(fullscreenOFF)){
            fullscreenOFF.setBackground(Colors.hoveredLabels);
        }
        else if(e.getSource().equals(home)){
            home.setBackground(Colors.hoveredLabels);
        }
        else if(e.getSource().equals(defaultSettings)){
            defaultSettings.setBackground(Colors.hoveredLabels);
        }
        activeComp = (JLabel) e.getSource();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(fullscreenON)){
            if(!selected.contains(fullscreenON)){
                fullscreenON.setBackground(Colors.labels);
            }
            else{
                fullscreenON.setBackground(Colors.selected);
            }
        }
        else if(e.getSource().equals(fullscreenOFF)){
            if(!selected.contains(fullscreenOFF)){
                fullscreenOFF.setBackground(Colors.labels);
            }
            else{
                fullscreenOFF.setBackground(Colors.selected);
            }
        }
        else if(e.getSource().equals(home)){
            home.setBackground(Colors.labels);
        }
        else if(e.getSource().equals(defaultSettings)){
            defaultSettings.setBackground(Colors.labels);
        }
        activeComp = null;
    }
    private void selectFullscreen(JLabel label){
        if(selected.contains(label)){
            return;
        }
        if(label.equals(fullscreenON)){
            fullscreenON.setBackground(Colors.selected);
            selected.add(fullscreenON);
            fullscreenOFF.setBackground(Colors.labels);
            selected.remove(fullscreenOFF);
        }
        else if(label.equals(fullscreenOFF)){
            fullscreenOFF.setBackground(Colors.selected);
            selected.add(fullscreenOFF);
            fullscreenON.setBackground(Colors.labels);
            selected.remove(fullscreenON);
        }
    }
}
