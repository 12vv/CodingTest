package com.leo.app.carpark;

public class CarPark {
    private String[][] park;
    public static String note = "+";

    //default carparksize
    public static int PARK_LENGTH = 4;
    public static int PARK_WIDTH = 4;


    public String[][] getPark(){
        return this.park;
    }
    /**
     * Initial the car park, simply assign a value for each array element.
     */
    public void initPark(){
        park = new String[PARK_LENGTH][PARK_WIDTH];

        for(int i=0; i<PARK_LENGTH; i++){
            for(int j=0; j<PARK_WIDTH; j++){
                park[i][j] = note;
            }
        }
    }

}
