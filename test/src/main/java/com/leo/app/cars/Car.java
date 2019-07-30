package com.leo.app.cars;

/**
 * Car interface
 */
public interface Car {
    void move(char command);
//    void isvalidMove(int x, int y) throws Exception;
    int getPositionX();
    int getPositionY();
    void setPositionX(int x);
    void setPositionY(int y);
    char getOrientation();
}
