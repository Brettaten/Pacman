package com.pacman.gui;

import java.awt.*;

public class Colors {
    public static Color mainPanel;
    public static Color labels;
    public static Color hoveredLabels;
    public static Color text;
    public static Color border;
    public static Color selected;
    public static Color gameBackground;
    public static Color gameBorder1;
    public static Color gameBorder2;
    public static Color gameBorder3;
    public static Color coin;

    public static void setTheme(int theme){
        switch (theme){
            case 0:
                mainPanel = new Color(60, 60, 60);
                labels = new Color(100, 100, 100);
                hoveredLabels = new Color(80, 80, 80);
                text = new Color(200, 200, 200);
                border = new Color(50, 50, 50);
                selected = new Color(70, 70, 70);
                gameBackground = new Color(30, 30, 30);
                gameBorder1 = new Color(40, 200, 40);
                gameBorder2 = new Color(20, 180, 20);
                gameBorder3 = new Color(0, 160, 0);
                coin = new Color(220, 220, 40);
                break;
            case 1:
                mainPanel = new Color(160, 160, 160);
                labels = new Color(200, 200, 200);
                hoveredLabels = new Color(180, 180, 180);
                text = new Color(45, 45, 45);
                border = new Color(150, 150, 150);
                selected = new Color(170, 170, 170);
                gameBackground = new Color(130, 130, 130);
                gameBorder1 = new Color(40, 200, 40);
                gameBorder2 = new Color(20, 180, 20);
                gameBorder3 = new Color(0, 160, 0);
                coin = new Color(220, 220, 40);
        }
    }
}
