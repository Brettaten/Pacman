package com.pacman.logic;

import com.pacman.gui.GamePanel;
import com.pacman.gui.InfoPanel;

public class GameUpdate {
    GamePanel gamePanel;
    InfoPanel infoPanel;
    int maxScore;
    int collectedCoins;
    int level;
    public GameUpdate(GamePanel gamePanel, InfoPanel infoPanel){
        this.gamePanel = gamePanel;
        this.infoPanel = infoPanel;

        level = 1;

        generateLevel();
    }
    private void generateLevel(){
        maxScore = 0;
        collectedCoins = 0;
        infoPanel.level++;
        gamePanel.removeAll();

        gamePanel.setGameSize();
        gamePanel.generateGame();
        maxScore = getMaxScore();
    }
    private int getMaxScore(){
        int counter = 0;
        for(int x = 0; x < 38; x++){
            for(int y = 0; y < 18; y++){
                if(GamePanel.gameBoard[x][y] == 0){
                    counter++;
                }
            }
        }
        return counter;
    }
    public void addScore(int points){
        infoPanel.score += points;
        if(points == 1){
            collectedCoins++;
            if(collectedCoins == maxScore){
                generateLevel();
            }
        }
    }
}
