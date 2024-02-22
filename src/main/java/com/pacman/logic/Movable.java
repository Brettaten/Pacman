package com.pacman.logic;

public interface Movable {
    int MOVE_UP = 0;
    int MOVE_RIGHT = 1;
    int MOVE_DOWN = 2;
    int MOVE_LEFT = 3;
    void move(int direction);
    void setInitialPos();
}
