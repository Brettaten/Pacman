package com.pacman.gui;

import com.pacman.logic.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    MainPanel mainPanel;
    Dimension gameSize;
    Point gameLocation;
    public static int sizeX;
    public static int sizeY;
    public static int size;
    public static int initialSize;
    int minPath;
    int maxPath;
    int arcSize;
    public static int[][] gameBoard;
    public Player player;
    public GamePanel(MainPanel mainPanel){
        this.mainPanel = mainPanel;
        this.setBackground(Colors.gameBackground);
        this.setBorder(new LineBorder(Colors.border, 5));
        this.setLayout(null);

        minPath = 50;
        maxPath = new Random().nextInt(50, 685);
    }

    public void setGameSize() {
        Rectangle gameRect = this.getBounds();
        gameSize = new Dimension(gameRect.width-10, gameRect.height-10);
        gameLocation = new Point(5, 5);

        sizeX = 38;
        sizeY = 18;
        size = (int) gameSize.getWidth()/38;
        arcSize = size/4;

        if(initialSize == 0){
            initialSize = size;
        }
    }

    public void generateGame() {


        gameBoard = new int[sizeX][sizeY];
        Random random = new Random();

        Point start = new Point(random.nextInt(38), random.nextInt(18));
        int[][] path = new int[sizeX][sizeY];

        int amountOfPath = 0;

        while(amountOfPath < minPath){

            for(int i = 0; i < 38; i++){
                for(int j = 0; j < 18; j++){
                    path[i][j] = -3;
                }
            }
            path = getPath(path, start);
            amountOfPath = getPathSize(path);
        }
        gameBoard = path;

        setUpEntities();
    }

    private int[][] getPath(int[][] path, Point currentPoint){
        path[currentPoint.x][currentPoint.y] = 0;
        if(getPathSize(path) >= maxPath){
            return path;
        }
        if(currentPoint.y+1 < sizeY && path[currentPoint.x][currentPoint.y+1] < 0){
            int rand = new Random().nextInt(3);

            if((rand == 0 && path[currentPoint.x][currentPoint.y+1] == -2) || ((rand == 0 || rand == 1) && path[currentPoint.x][currentPoint.y+1] == -3)){
                path = getPath(path, new Point(currentPoint.x, currentPoint.y+1));
            }
            else{
                if(path[currentPoint.x][currentPoint.y+1] == -3){
                    path[currentPoint.x][currentPoint.y+1] = -2;
                }
                else{
                    path[currentPoint.x][currentPoint.y+1] = -1;
                }
            }
        }
        if(currentPoint.x+1 < sizeX && path[currentPoint.x+1][currentPoint.y] < 0){
            int rand = new Random().nextInt(3);

            if((rand == 0 && path[currentPoint.x+1][currentPoint.y] == -2) || ((rand == 0 || rand == 1) && path[currentPoint.x+1][currentPoint.y] == -3)){
                path = getPath(path, new Point(currentPoint.x+1, currentPoint.y));
            }
            else{
                if(path[currentPoint.x+1][currentPoint.y] == -3){
                    path[currentPoint.x+1][currentPoint.y] = -2;
                }
                else{
                    path[currentPoint.x+1][currentPoint.y] = -1;
                }
            }
        }
        if(currentPoint.y-1 >= 0 && path[currentPoint.x][currentPoint.y-1] < 0){
            int rand = new Random().nextInt(3);

            if((rand == 0 && path[currentPoint.x][currentPoint.y-1] == -2) || ((rand == 0 || rand == 1) && path[currentPoint.x][currentPoint.y-1] == -3)){
                path = getPath(path, new Point(currentPoint.x, currentPoint.y-1));
            }
            else{
                if(path[currentPoint.x][currentPoint.y-1] == -3){
                    path[currentPoint.x][currentPoint.y-1] = -2;
                }
                else{
                    path[currentPoint.x][currentPoint.y-1] = -1;
                }
            }
        }
        if(currentPoint.x-1 >= 0 && path[currentPoint.x-1][currentPoint.y] < 0){
            int rand = new Random().nextInt(3);

            if((rand == 0 && path[currentPoint.x-1][currentPoint.y] == -2) || ((rand == 0 || rand == 1) && path[currentPoint.x-1][currentPoint.y] == -3)){
                path = getPath(path, new Point(currentPoint.x-1, currentPoint.y));
            }
            else{
                if(path[currentPoint.x-1][currentPoint.y] == -3){
                    path[currentPoint.x-1][currentPoint.y] = -2;
                }
                else{
                    path[currentPoint.x-1][currentPoint.y] = -1;
                }
            }
        }
        return path;
    }

    private int getPathSize(int[][] path){
        int amountOfPath = 0;
        for(int i = 0; i < 38; i++) {
            for (int j = 0; j < 18; j++) {
                if (path[i][j] == 0) {
                    amountOfPath++;
                }
            }
        }
        return amountOfPath;
    }
    private void setUpEntities(){
        player = new Player();
        this.add(player);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setGameSize();
        if(player != null)
            player.adjustPlayer();

        int posX;
        int posY;

        if(gameBoard != null){
            for(int x = 0; x < sizeX; x++){
                for(int y = 0; y < sizeY; y++){
                    posX = x*size;
                    posY = y*size;

                    /*
                    0 = path with point
                    -2, -1, 1 = wall
                     */

                    switch(gameBoard[x][y]){
                        case -3:
                            g.setColor(Colors.gameBorder3);
                            g.fillRect(posX, posY, size, size);
                            break;
                        case -2:
                            g.setColor(Colors.gameBorder2);
                            g.fillRect(posX, posY, size, size);
                            break;
                        case -1:
                            g.setColor(Colors.gameBorder1);
                            g.fillRect(posX, posY, size, size);
                            break;
                        case 0:
                            g.setColor(Colors.coin);
                            g.fillArc(posX+size/2 - arcSize/2,posY+size/2 - arcSize/2,arcSize,arcSize,0, 360);
                            break;
                        case 1:
                            g.setColor(Colors.gameBackground);
                            g.fillRect(posX, posY, size, size);
                    }
                }
            }
        }
    }
}
