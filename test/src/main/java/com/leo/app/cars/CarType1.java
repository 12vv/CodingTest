package com.leo.app.cars;

import com.leo.app.carpark.CarPark;


public class CarType1 implements Car{

    private int positionX; //car's X_coordinate
    private int positionY;  //car's Y_coordinate
    private char orientation;  //car's orientation

    // an array store for calculating position after moving
    int direction[][]={{0,1}, {1,0}, {0,-1}, {-1,0}};
    // a String use for get the orientation after turning clockwise
    String dir = "NESW";

    /**
     * Three instructions can be executed on the car, i.e.moving FORWARD, BACKWARD and Tuning clockwise.
     *
     * @param  command   the command(an upper-letter: F for moving FORWARD, B for moving BACKWARD, T for Tuning clockwise. )
     */
    public void move(char command) {

        int now = dir.indexOf(getOrientation());  //get current orientation
        //get current position
        int x = getPositionX();
        int y = getPositionY();

        try {
            // Three types of command
            switch (command) {
                case 'F':
                    System.out.println("Moving Forward!");
                    x = direction[now][0] + getPositionX();
                    y = direction[now][1] + getPositionY();
                    break;
                case 'B':
                    System.out.println("Moving Backward!\n");
                    x = direction[now][0] * (-1) + getPositionX();
                    y = direction[now][1] * (-1) + getPositionY();
                    break;
                case 'T':
                    System.out.println("Turning clockwise\n");
                    if (now == 3) {
                        orientation = 'N';
                    } else {
                        orientation = dir.charAt(now + 1);
                    }
                    break;
                default:
                    System.out.println("---------Invalid input!---------\n" +
                            "Please input F(moving FORWARD), B(moving BACKWARD) or T(Tuning clockwise).");
            }
            // check if this command can be safely executed.
            isvalidMove(x, y);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Check if the car is outside the boundaries.
     *
     * @param  x   the command(an upper-letter: F for moving FORWARD, B for moving BACKWARD, T for Tuning clockwise. )
     */
    public void isvalidMove(int x, int y) throws Exception{
        if(x > CarPark.PARK_LENGTH-1 || y > CarPark.PARK_WIDTH-1 || x < 0 || y <0){
            // array out of index, throw exception
            throw new Exception("Car is outside the parking lot!");
        }else{
            // valid operation, thus set car's new position
            setPositionX(x);
            setPositionY(y);
        }
  }

    /**
     * current positionX of the car.
     *
     * @return       current positionX of the car
     */
    public int getPositionX() {
        return this.positionX;
    }

    /**
     * current positionY of the car.
     *
     * @return       current positionY of the car
     */
    public int getPositionY() {
        return this.positionY;
    }

    /**
     * Increment a value by delta and return the new value.
     *
     * @return       current orientation of the car
     */
    public char getOrientation() {
        return this.orientation;
    }

    /**
     * Set car's X_coordinate.
     *
     * @param  x   new positionX for the car
     */
    public void setPositionX(int x) {
        this.positionX = x;
    }

    /**
     * Set car's Y_coordinate.
     *
     * @param  y   new positionY for the car
     */
    public void setPositionY(int y) {
        this.positionY = y;
    }

    /**
     * Set car's orientation.
     *
     * @param  orientation   the amount the value should be incremented by
     */
    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

}
