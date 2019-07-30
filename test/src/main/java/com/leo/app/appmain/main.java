package com.leo.app.appmain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.leo.app.carpark.CarPark;
import com.leo.app.cars.CarType1;

import java.text.MessageFormat;
import java.util.Scanner;

public class main {

    /**
     * Display the car park and the car inside it.
     *
     * @param  cp   an CarPark object
     * @param  c    an CarType1 object
     */
    public static void display(CarPark cp, CarType1 c){

        String[][] park = cp.getPark();
        int carX = c.getPositionX();
        int carY = c.getPositionY();
        int carDir = c.getOrientation();
        park[cp.PARK_WIDTH-(carY+1)][carX] = (char)carDir+"";
        for(int i=0; i<cp.PARK_LENGTH; i++){
            for(int j=0; j<cp.PARK_WIDTH; j++){
                System.out.print(park[i][j]);
            }
            System.out.print("\n");
        }
        // after printing, recover the origin car park(i.e. erase the car), for next round
        park[cp.PARK_WIDTH-(carY+1)][carX] = CarPark.note;
        // also print car's current position
        System.out.println(MessageFormat.format("Current position: ({0}, {1})\nOrientation: {2}\n", carX, carY, (char)carDir));
    }

    /**
     * Loop until it gets an integer, and take the absolute value if the integer is negative.
     *
     * @param  s          an Scanner object
     * @param  message    Prompt user for an integer
     */
    public static int getInput(Scanner s, String message){
        int res = 1;
        System.out.println(message);
        while (!s.hasNextInt()) {
            s.next();
            System.out.println("PLEASE CHECK YOUR INPUT! INTEGER ONLY! \n" + message);
        }
        res = Math.abs(s.nextInt());
        return res;
    }


    public static void main(String arg[]) throws IOException {

        CarPark c1 = new CarPark();
        int length = c1.PARK_LENGTH;
        int width = c1.PARK_WIDTH;

        Scanner sc = null;
        try{
            sc = new Scanner(System.in);
            String getL = "Please enter the length of the parking lot:";
            String getW = "Please enter the width of the parking lot:";

            length = getInput(sc, getL);
            width = getInput(sc, getW);

            if(length==0 || width==0){
                System.out.println("The length and width cannot be zero.\nUse default setting: 4*4 ");
            }else{
                c1.PARK_LENGTH = length;
                c1.PARK_WIDTH = width;
            }
            c1.initPark();

            int x = 0;
            int y = 0;
            String getX = "Please enter the initial coordinate 'X' of the car:";
            String getY = "Please enter the initial coordinate 'Y' of the car:";

            do {
                x = getInput(sc, getX);
                y = getInput(sc, getY);
                if(x>=c1.PARK_LENGTH || y>=c1.PARK_WIDTH){
                    System.out.println("Cannot initiate the car outside the parking lot.");
                }
            }while (x>=c1.PARK_LENGTH || y>=c1.PARK_WIDTH);


            //clean the buffer
            sc.nextLine();
            char orientation = '0';
            while (orientation!='N' && orientation!='E' && orientation!='S' && orientation!='W'){
                System.out.println("Please enter the initial orientation of the car:\n" +
                        "(It can only have for direction: N(North), E(East), S(South) and W(West)");
                orientation = sc.next().charAt(0);
            }


            CarType1 bmw1 = new CarType1();
            bmw1.setPositionX(x);
            bmw1.setPositionY(y);
            bmw1.setOrientation(orientation);

            display(c1, bmw1);


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please input movement:(F: move forward, B: move backward, T: turn clockwise)");
            String inputStr = null;


            while ((inputStr = br.readLine().toUpperCase()) != null){

                if(inputStr.equals("Q")){
                    System.out.println("quit!");
                    break;
                }
                bmw1.move(inputStr.charAt(0));

                display(c1, bmw1);
                System.out.println("Please input movement:(F: move forward, B: move backward, T: turn clockwise)");

            }

        } catch (Exception e){
            System.out.println("Please check your input.");
            e.printStackTrace();
        }finally {
            if (sc != null){
                sc.close();
                sc = null;
            }
        }


    }

}
