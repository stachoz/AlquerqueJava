package org.example;

import java.util.Scanner;

public class ScannerUtil {
    static Scanner input = new Scanner(System.in);

    public static int getNaturalNumber() {
        while(true){
            String i = input1Length();
            if(isNumeric(i)) return Integer.parseInt(i);
            else System.out.println("Bad input, again");
        }
    }

    public static char getChar() {
        while(true){
            String i = input1Length();
            if(!isNumeric(i)) return i.charAt(0);
            else System.out.println("Bad input, enter again");
        }
    }

    private static String input1Length(){
        String i;
        while(true){
            i = input.nextLine();
            // clean scanner
            if(i.length() > 1 || i.length() == 0){
                System.out.println("Bad input, enter again");
            } else{
                return i;
            }
        }
    }
    private static boolean isNumeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
