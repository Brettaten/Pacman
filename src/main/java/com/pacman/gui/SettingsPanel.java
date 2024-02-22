package com.pacman.gui;

import com.pacman.listener.SettingsListener;
import com.pacman.logic.Game;
import com.pacman.logic.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public MainPanel mainPanel;
    public JPanel settingsDescription;
    public JPanel settingsAttributes;
    public JLabel fullscreenOn;
    public JLabel fullscreenOFF;

    public SettingsPanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;

        setSettingsUI();

        this.setLayout(new GridLayout(1, 2));

        settingsDescription = new JPanel();
        settingsDescription.setLayout(new BoxLayout(settingsDescription, BoxLayout.Y_AXIS));
        settingsDescription.setBorder(new MatteBorder(5, 5, 5, 5, Colors.border));
        JPanel fullscreenPanel = new JPanel();
        fullscreenPanel.setLayout(new BoxLayout(fullscreenPanel, BoxLayout.X_AXIS));
        fullscreenPanel.setBackground(Colors.labels);
        JLabel fullscreenDescription = new JLabel("Fullscreen");
        fullscreenDescription.setOpaque(true);
        fullscreenDescription.setBorder(new EmptyBorder(5,5,5,5));
        fullscreenPanel.add(Box.createHorizontalGlue());
        fullscreenPanel.add(fullscreenDescription);
        fullscreenPanel.add(Box.createHorizontalGlue());

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.X_AXIS));
        homePanel.setBackground(Colors.labels);
        JLabel home = new JLabel("Home");
        home.setOpaque(true);
        home.setBorder(new EmptyBorder(5,5,5,5));
        homePanel.add(Box.createHorizontalGlue());
        homePanel.add(home);
        homePanel.add(Box.createHorizontalGlue());

        settingsDescription.add(fullscreenPanel);
        settingsDescription.add(homePanel);

        settingsAttributes = new JPanel();
        settingsAttributes.setLayout(new BoxLayout(settingsAttributes, BoxLayout.Y_AXIS));
        settingsAttributes.setBorder(new MatteBorder(5, 0, 5, 5, Colors.border));

        JPanel fullscreenContainer = new JPanel();
        fullscreenContainer.setLayout(new BoxLayout(fullscreenContainer, BoxLayout.X_AXIS));
        fullscreenContainer.setBackground(Colors.labels);
        fullscreenOn = new JLabel("ON");
        fullscreenOFF = new JLabel("OFF");
        fullscreenOFF.setOpaque(true);
        fullscreenOn.setOpaque(true);
        fullscreenOn.setBorder(new EmptyBorder(5,5,5,5));
        fullscreenOFF.setBorder(new EmptyBorder(5,5,5,5));
        fullscreenContainer.add(Box.createHorizontalGlue());
        fullscreenContainer.add(fullscreenOn);
        fullscreenContainer.add(fullscreenOFF);
        fullscreenContainer.add(Box.createHorizontalGlue());

        JPanel defaultPanel = new JPanel();
        defaultPanel.setLayout(new BoxLayout(defaultPanel, BoxLayout.X_AXIS));
        defaultPanel.setBackground(Colors.labels);
        JLabel defaultSettings = new JLabel("Default");
        defaultSettings.setOpaque(true);
        defaultSettings.setBorder(new EmptyBorder(5,5,5,5));
        defaultPanel.add(Box.createHorizontalGlue());
        defaultPanel.add(defaultSettings);
        defaultPanel.add(Box.createHorizontalGlue());


        settingsAttributes.add(fullscreenContainer);
        settingsAttributes.add(defaultPanel);

        SettingsListener settingsListener = new SettingsListener(mainPanel, fullscreenOn, fullscreenOFF, home, defaultSettings);
        fullscreenOn.addMouseListener(settingsListener);
        fullscreenOFF.addMouseListener(settingsListener);
        home.addMouseListener((settingsListener));
        defaultSettings.addMouseListener(settingsListener);

        this.add(settingsDescription);
        this.add(settingsAttributes);

        setSettings();
    }
    private void setSettingsUI() {
        UIManager.put("Panel.background", Colors.mainPanel);
        UIManager.put("Label.background", Colors.labels);
        UIManager.put("Label.foreground", Colors.text);
        UIManager.put("Label.font", new Font(null, Font.PLAIN, 40));
    }
    private void setSettings(){
        if(Settings.isFullscreen){
            fullscreenOn.setBackground(Colors.selected);
            SettingsListener.selected.add(fullscreenOn);
        }
        else{
            fullscreenOFF.setBackground(Colors.selected);
            SettingsListener.selected.add(fullscreenOFF);
        }
    }
    public void setDefault() {
        fullscreenOFF.setBackground(Colors.selected);
        fullscreenOn.setBackground(Colors.labels);

        SettingsListener.selected.add(fullscreenOFF);
        SettingsListener.selected.remove((fullscreenOn));

        if(Settings.isFullscreen){
            Settings.isFullscreen = false;
            Game.setWindowMode();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setSettingsUI();
    }
}
