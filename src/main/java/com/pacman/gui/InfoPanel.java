package com.pacman.gui;

import com.pacman.listener.InfoListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class InfoPanel extends JPanel {
    public MainPanel mainPanel;
    JLabel scoreLabel;
    JLabel levelLabel;
    JLabel healthLabel;
    public int score;
    public int level;
    public int health;
    public InfoPanel(MainPanel mainPanel){
        setSettingsUI();
        this.mainPanel = mainPanel;
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JPanel leftLabels = new JPanel();
        leftLabels.setLayout(new BoxLayout(leftLabels, BoxLayout.X_AXIS));
        leftLabels.setBorder(new LineBorder(Colors.border, 5));

        JPanel rightLabels = new JPanel();
        rightLabels.setLayout(new BoxLayout(rightLabels, BoxLayout.X_AXIS));
        rightLabels.setBorder(new LineBorder(Colors.border, 5));

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBorder(new EmptyBorder(5,5,5,5));
        scoreLabel.setOpaque(true);
        levelLabel = new JLabel("Level: " + level+1);
        levelLabel.setBorder(new EmptyBorder(5,5,5,5));
        levelLabel.setOpaque(true);
        healthLabel = new JLabel("Health: " + health);
        healthLabel.setBorder(new EmptyBorder(5,5,5,5));
        healthLabel.setOpaque(true);
        JLabel homeLabel = new JLabel("Home");
        homeLabel.setBorder(new EmptyBorder(5,5,5,5));
        homeLabel.setOpaque(true);
        JLabel fullscreenLabel = new JLabel("Fullscreen");
        fullscreenLabel.setBorder(new EmptyBorder(5,5,5,5));
        fullscreenLabel.setOpaque(true);

        leftLabels.add(scoreLabel);
        leftLabels.add(levelLabel);
        leftLabels.add(healthLabel);

        rightLabels.add(homeLabel);
        rightLabels.add(fullscreenLabel);

        InfoListener infoListener = new InfoListener(mainPanel, homeLabel, fullscreenLabel);
        homeLabel.addMouseListener(infoListener);
        fullscreenLabel.addMouseListener(infoListener);

        this.add(leftLabels);
        this.add(Box.createHorizontalGlue());
        this.add(rightLabels);
    }
    private void setSettingsUI() {
        UIManager.put("Panel.background", Colors.mainPanel);
        UIManager.put("Label.background", Colors.labels);
        UIManager.put("Label.foreground", Colors.text);
        UIManager.put("Label.font", new Font(null, Font.PLAIN, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        scoreLabel.setText("Score: " + score);
        levelLabel.setText("Level: " + level);
        healthLabel.setText("Health: " + health);
    }
}
