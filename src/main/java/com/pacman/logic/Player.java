package com.pacman.logic;

import com.pacman.gui.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Player extends JLabel implements Movable{
    Point location;
    int direction;
    int[] velocityVector;
    int[] activeKeys;
    int speed = 10;
    int tick = 3;
    int playerSize;
    double relativeSize;
    double relativeTick;
    public Player(){
        relativeSize = 0.75;
        relativeTick = 120;
        playerSize = (int) (relativeSize * GamePanel.size);
        this.setOpaque(true);
        this.setBackground(Color.BLUE);
        this.setSize(new Dimension(playerSize, playerSize));

        direction = -1;
        velocityVector = new int[2];
        activeKeys = new int[4];

        setInitialPos();
        setKeyBindings();
    }
    @Override
    public void move() {
        int[] tempVec = new int[4];

        for(int i = 0; i < 4; i++){
            if(i == 0 || i == 1){
               tempVec[i] = -activeKeys[i];
            }
            else{
                tempVec[i] = activeKeys[i];
            }
        }

        int vecX = tempVec[1] + tempVec[3];
        int vecY = tempVec[0] + tempVec[2];

        if(vecX * speed == 0){
            velocityVector[0] = 0;
        }
        else{
            velocityVector[0] = GamePanel.size/(vecX * speed);
        }
        if(vecY * speed == 0){
            velocityVector[1] = 0;
        }
        else{
            velocityVector[1] = GamePanel.size/(vecY * speed);
        }
    }
    @Override
    public void setInitialPos(){
        Point start = new Point(new Random().nextInt(GamePanel.sizeX), new Random().nextInt(GamePanel.sizeY));

        while(GamePanel.gameBoard[start.x][start.y] != 0){
            if(start.y < GamePanel.sizeY-1){
                start.y += 1;
            }
            else if(start.x < GamePanel.sizeX-1){
                start.y = 0;
                start.x += 1;
            }
            else{
                start.y = 0;
                start.x = 0;
            }
        }
        GamePanel.gameBoard[start.x][start.y] = 1;
        int diff = GamePanel.size - playerSize;
        location = new Point(start.x * GamePanel.size + diff/2, start.y * GamePanel.size + diff/2);
        this.setLocation(location);
    }
    private boolean isInBounds(int x, int y){
        if(x <= 0 || y <= 0 || x >= (GamePanel.sizeX*GamePanel.size)-playerSize || y >= (GamePanel.sizeY*GamePanel.size)-playerSize){
            return false;
        }
        int xLeftMap = x/GamePanel.size;
        int xRightMap = (x+playerSize)/GamePanel.size;
        int yTopMap = y/GamePanel.size;
        int yBottomMap = (y+playerSize)/GamePanel.size;

        return GamePanel.gameBoard[xLeftMap][yTopMap] >= 0 && GamePanel.gameBoard[xRightMap][yTopMap] >= 0 && GamePanel.gameBoard[xRightMap][yBottomMap] >= 0 && GamePanel.gameBoard[xLeftMap][yBottomMap] >= 0;
    }
    public Point getRelativeLocation(Point location){
        int initialSize = GamePanel.initialSize;
        int currentSize = GamePanel.size;

        double relativeRemainder = (double) currentSize/initialSize;

        int relX = location.x / initialSize;
        int remainderX = (int) ((location.x % initialSize) * relativeRemainder);
        int relY = location.y / initialSize;
        int remainderY = (int) ((location.y % initialSize) * relativeRemainder);

        int x = relX*currentSize + remainderX;
        int y = relY*currentSize + remainderY;

        GamePanel.initialSize = GamePanel.size;

        return new Point(x, y);
    }
    private void checkCollision() {
        // check coin collision
        int playerX = location.x / GamePanel.size;
        int playerY = location.y / GamePanel.size;
        int remainderX = location.x % GamePanel.size;
        int remainderY = location.y % GamePanel.size;
        int nextX = playerSize - (GamePanel.size - remainderX);
        int nextY = playerSize - (GamePanel.size - remainderY);
        int coinDistanceTop = GamePanel.size/2 - GamePanel.size/10;
        int coinDistanceBottom = GamePanel.size/2 + GamePanel.size/10;

        if(remainderX < coinDistanceBottom && remainderY < coinDistanceBottom && remainderX + playerSize > coinDistanceTop && remainderY + playerSize > coinDistanceTop){
            GamePanel.gameBoard[playerX][playerY] = 1;
        }
        if(remainderY < coinDistanceBottom && nextX > coinDistanceTop){
            GamePanel.gameBoard[playerX+1][playerY] = 1;
        }
        if(remainderX < coinDistanceBottom && nextY > coinDistanceTop){
            GamePanel.gameBoard[playerX][playerY+1] = 1;
        }
        if(nextX > coinDistanceTop && nextY > coinDistanceTop){
            GamePanel.gameBoard[playerX+1][playerY+1] = 1;
        }

    }
    private int getRelativeSize(){
        return  (int) (GamePanel.size * relativeSize);
    }
    public void adjustPlayer(){
        try {
            Thread.sleep(tick);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if(isInBounds(location.x + velocityVector[0], location.y)){
            location.x += velocityVector[0];
        }
        if(isInBounds(location.x, location.y + velocityVector[1])){
            location.y += velocityVector[1];
        }
        location = getRelativeLocation(location);
        playerSize = getRelativeSize();

        this.setSize(playerSize, playerSize);
        this.setLocation(location);

        checkCollision();
    }
    private void setKeyBindings(){
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "wAction");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "wrAction");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0), "sAction");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0, true), "srAction");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0), "dAction");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0, true), "drAction");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0), "aAction");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0, true), "arAction");

        this.getActionMap().put("wAction", new WAction());
        this.getActionMap().put("wrAction", new WRAction());
        this.getActionMap().put("sAction", new SAction());
        this.getActionMap().put("srAction", new SRAction());
        this.getActionMap().put("dAction", new DAction());
        this.getActionMap().put("drAction", new DRAction());
        this.getActionMap().put("aAction", new AAction());
        this.getActionMap().put("arAction", new ARAction());
    }
    public class WAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[0] = 1;
            move();
        }
    }
    public class AAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[1] = 1;
            move();
        }
    }
    public class SAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[2] = 1;
            move();
        }
    }
    public class DAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[3] = 1;
            move();
        }
    }
    public class WRAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[0] = 0;
            move();
        }
    }
    public class ARAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[1] = 0;
            move();
        }
    }
    public class SRAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[2] = 0;
            move();
        }
    }
    public class DRAction extends AbstractAction{
        @Override
        public void actionPerformed(ActionEvent e) {
            activeKeys[3] = 0;
            move();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
