package com.pacman.gui;

import com.pacman.listener.ThemesListener;
import com.pacman.logic.Game;
import com.pacman.logic.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ThemesPanel extends JPanel {
    public MainPanel mainPanel;
    public JLabel dark;
    public JLabel bright;
    public JPanel themesPanel;
    public JPanel darkPanel;
    public JPanel brightPanel;
    public JLabel home;
    public JLabel defaultSettings;

    public ThemesPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        themesPanel = new JPanel();

        setSettingsUI();

        this.setLayout(new GridLayout(1,1));

        themesPanel = new JPanel();
        themesPanel.setLayout(new BoxLayout(themesPanel, BoxLayout.Y_AXIS));
        themesPanel.setBorder(new LineBorder(Colors.border, 5));

        darkPanel = new JPanel();
        darkPanel.setLayout(new BoxLayout(darkPanel, BoxLayout.X_AXIS));
        darkPanel.setBackground(Colors.labels);
        dark = new JLabel("Dark");
        dark.setOpaque(true);
        dark.setBackground(Colors.labels);
        darkPanel.add(Box.createHorizontalGlue());
        darkPanel.add(dark);
        darkPanel.add(Box.createHorizontalGlue());

        brightPanel = new JPanel();
        brightPanel.setLayout(new BoxLayout(brightPanel, BoxLayout.X_AXIS));
        brightPanel.setBackground(Colors.labels);
        bright = new JLabel("Bright");
        bright.setOpaque(true);
        bright.setBackground(Colors.labels);
        brightPanel.add(Box.createHorizontalGlue());
        brightPanel.add(bright);
        brightPanel.add(Box.createHorizontalGlue());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        home = new JLabel("Home");
        home.setOpaque(true);
        home.setBorder(new EmptyBorder(5,5,5,5));
        defaultSettings = new JLabel("Default");
        defaultSettings.setOpaque(true);
        defaultSettings.setBorder(new EmptyBorder(5,5,5,5));
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(home);
        bottomPanel.add(defaultSettings);
        bottomPanel.add(Box.createHorizontalGlue());

        themesPanel.add(darkPanel);
        themesPanel.add(brightPanel);
        themesPanel.add(bottomPanel);

        ThemesListener themesListener = new ThemesListener(mainPanel, dark, bright, home, defaultSettings);
        dark.addMouseListener(themesListener);
        bright.addMouseListener(themesListener);
        home.addMouseListener(themesListener);
        defaultSettings.addMouseListener(themesListener);

        this.add(themesPanel);

        setSettings();
    }
    private void setSettingsUI() {
        UIManager.put("Panel.background", Colors.mainPanel);
        UIManager.put("Label.background", Colors.labels);
        UIManager.put("Label.foreground", Colors.text);
        UIManager.put("Label.font", new Font(null, Font.PLAIN, 40));
    }
    private void setSettings(){
        if(Settings.theme == 0){
            dark.setBackground(Colors.selected);
            ThemesListener.selected.add(dark);
        }
        else if(Settings.theme == 1){
            bright.setBackground(Colors.selected);
            ThemesListener.selected.add(bright);
        }
    }
    public void setDefault() {
        dark.setBackground(Colors.selected);
        bright.setBackground(Colors.labels);

        ThemesListener.selected.add(dark);
        ThemesListener.selected.remove((bright));

        if(Settings.theme != 0){
            Settings.theme = 0;
            Game.setColors();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        themesPanel.setBackground(Colors.labels);
        themesPanel.setBorder(new LineBorder(Colors.border, 5));
        darkPanel.setBackground(Colors.labels);
        brightPanel.setBackground(Colors.labels);
        dark.setForeground(Colors.text);
        bright.setForeground(Colors.text);
        home.setBackground(Colors.labels);
        home.setForeground(Colors.text);
        defaultSettings.setBackground(Colors.labels);
        defaultSettings.setForeground(Colors.text);

        if(ThemesListener.selected.contains(dark)){
            dark.setBackground(Colors.selected);
            bright.setBackground(Colors.labels);
        }
        if(ThemesListener.selected.contains(bright)){
            bright.setBackground(Colors.selected);
            dark.setBackground(Colors.labels);
        }

        setSettingsUI();
    }
}
