package com.pacman.logic;

import com.pacman.gui.Colors;

public class Settings {
    public static boolean isFullscreen;
    public static int currentMenu;
    public static int theme;

    public static void setFullscreen(boolean isFullscreen){
        Settings.isFullscreen = isFullscreen;
    }
    public static void setCurrentMenu(int currentMenu){
        Settings.currentMenu = currentMenu;
    }
    public static void setTheme(int theme){
        Settings.theme = theme;
        Colors.setTheme(theme);
    }
}
