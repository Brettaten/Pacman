package com.pacman.listener;

import com.pacman.gui.Colors;
import com.pacman.gui.MainPanel;
import com.pacman.logic.Game;
import com.pacman.logic.Settings;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ThemesListener implements MouseListener {
    public MainPanel mainPanel;
    public JLabel dark;
    public JLabel bright;
    public JLabel home;
    public JLabel defaultSettings;
    public JLabel activeComp;
    public static ArrayList<JLabel> selected;
    public ThemesListener(MainPanel mainPanel, JLabel dark, JLabel bright, JLabel home, JLabel defaultSettings){
        this.mainPanel = mainPanel;
        this.dark = dark;
        this.bright = bright;
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
        if(e.getSource().equals(dark) && e.getSource().equals(activeComp)){
            if(Settings.theme != 0){
                Settings.theme = 0;
                selected.add(dark);
                selected.remove(bright);
                Game.setColors();
                mainPanel.themesPanel.repaint();
            }
        }
        else if(e.getSource().equals(bright) && e.getSource().equals(activeComp)){
            if(Settings.theme != 1){
                Settings.theme = 1;
                selected.add(bright);
                selected.remove(dark);
                Game.setColors();
                mainPanel.themesPanel.repaint();
            }
        }
        else if(e.getSource().equals(home) && e.getSource().equals(activeComp)){
            mainPanel.displayHome();
        }
        else if(e.getSource().equals(defaultSettings) && e.getSource().equals(activeComp)){
            mainPanel.themesPanel.setDefault();
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(dark)){
            dark.setBackground(Colors.hoveredLabels);
        }
        else if(e.getSource().equals(bright)){
            bright.setBackground(Colors.hoveredLabels);
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
        if(e.getSource().equals(dark)){
            if(!selected.contains(dark)){
                dark.setBackground(Colors.labels);
            }
            else{
                dark.setBackground(Colors.selected);
            }
        }
        else if(e.getSource().equals(bright)){
            if(!selected.contains(bright)){
                bright.setBackground(Colors.labels);
            }
            else{
                bright.setBackground(Colors.selected);
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

}
