package com.pacman.gui;


import com.pacman.logic.GameUpdate;
import com.pacman.logic.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainPanel extends JPanel {
    public HomeMenu menuPanel;
    public GamePanel gamePanel;
    public InfoPanel infoPanel;
    public SettingsPanel settingsPanel;
    public ThemesPanel themesPanel;
    public static GameUpdate gameUpdate;
    public MainPanel(){
        this.setBackground(Colors.mainPanel);

            switch (Settings.currentMenu){
                case 0:
                    displayHome();
                    break;
                case 1:
                    displayGame();
                    break;
                case 2:
                    displaySettings();
                    break;
                case 3:
                    displayThemes();
                    break;
                default:
                    throw new IllegalArgumentException();
            }

    }

    public void displayHome() {
        this.clear();
        this.setLayout(new BorderLayout());
        Settings.setCurrentMenu(0);
        menuPanel = new HomeMenu(this);
        this.add(menuPanel, BorderLayout.CENTER);
    }
    public void displayGame(){
        this.clear();
        this.setLayout(new BorderLayout());
        Settings.setCurrentMenu(1);
        infoPanel = new InfoPanel(this);
        gamePanel = new GamePanel(this, infoPanel);
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(gamePanel, BorderLayout.CENTER);
        gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gameUpdate = new GameUpdate(gamePanel, infoPanel);
                gamePanel.removeComponentListener(this);
            }
        });
    }
    public void displaySettings(){
        this.clear();
        this.setLayout(new GridBagLayout());
        Settings.setCurrentMenu(2);
        settingsPanel = new SettingsPanel(this);
        this.add(settingsPanel);
    }
    public void displayThemes(){
        this.clear();
        this.setLayout(new GridBagLayout());
        Settings.setCurrentMenu(3);
        themesPanel = new ThemesPanel(this);
        this.add(themesPanel);
    }
    private void clear(){
        this.removeAll();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        this.setBackground(Colors.mainPanel);
    }
}
