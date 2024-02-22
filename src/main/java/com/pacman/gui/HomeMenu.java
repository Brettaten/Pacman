package com.pacman.gui;

import com.pacman.listener.HomeListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomeMenu extends JPanel {
    public MainPanel mainPanel;
    public JLabel gameLabel;
    public JLabel settingsLabel;
    public JLabel themesLabel;
    public HomeMenu(MainPanel mainPanel){
        this.mainPanel = mainPanel;

        setHomeMenuUI();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Colors.mainPanel);

        gameLabel = new JLabel("New Game");
        gameLabel.setOpaque(true);
        gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameLabel.setBorder(new LineBorder(Colors.border, 10));

        settingsLabel = new JLabel("Settings");
        settingsLabel.setOpaque(true);
        settingsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsLabel.setBorder(new LineBorder(Colors.border, 10));

        themesLabel = new JLabel("Themes");
        themesLabel.setOpaque(true);
        themesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        themesLabel.setBorder(new LineBorder(Colors.border, 10));

        HomeListener homeListener = new HomeListener(mainPanel, gameLabel, settingsLabel, themesLabel);
        gameLabel.addMouseListener(homeListener);
        settingsLabel.addMouseListener(homeListener);
        themesLabel.addMouseListener(homeListener);

        this.add(Box.createVerticalGlue());
        this.add(gameLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(settingsLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(themesLabel);
        this.add(Box.createVerticalGlue());
    }
    private void setHomeMenuUI() {
        UIManager.put("Panel.background", Colors.mainPanel);
        UIManager.put("Label.background", Colors.labels);
        UIManager.put("Label.foreground", Colors.text);
        UIManager.put("Label.font", new Font(null, Font.PLAIN, 80));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setHomeMenuUI();
    }
}
