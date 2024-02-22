package com.pacman.logic;

import com.pacman.gui.Colors;
import com.pacman.gui.Frame;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    public static Frame frame;
    //public static int[][] gameBoard;

    public Game(){
        startGameLoop();
        setSettings();
        frame = new Frame();
    }

    public static void setWindowMode(){
        frame.dispose();
        frame.setUndecorated(Settings.isFullscreen);
        frame.setVisible(true);
    }

    public static void setColors(){
        Colors.setTheme(Settings.theme);
    }
    private void setSettings() {
        Settings.setFullscreen(false);
        Settings.setCurrentMenu(0);
        Settings.setTheme(0);
    }

    private void startGameLoop(){
        Thread thread = new Thread(getGameLoop());
        thread.start();
    }
    private Runnable getGameLoop(){
        final int FPS = 120;
        Runnable run;
        run = () -> {
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    SwingUtilities.invokeLater(() -> {
                        if(frame != null){
                            frame.repaint();
                            frame.validate();
                        }
                    });
                }
            };

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0 , 1000/FPS);
        };
        return run;
    }
}
