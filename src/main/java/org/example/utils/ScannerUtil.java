package org.example.utils;

import java.util.Scanner;

public class ScannerUtil {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String ERROR_MSG = "Bad input, enter again";
    public static int getNaturalNumber() {
        while(true){
            String i = input1Length();
            if(isNumeric(i)) return Integer.parseInt(i);
            else System.out.println(ERROR_MSG);
        }
    }

    public static int getNaturalNumberFromRange(int a, int b){
        while(true){
            int num = getNaturalNumber();
            if(num < a || num > b) {
                System.out.println(ERROR_MSG);
            }
            else return num;
        }
    }
    public static char getChar() {
        while(true){
            String i = input1Length();
            if(!isNumeric(i)) return i.charAt(0);
            else System.out.println(ERROR_MSG);
        }
    }

    public static String getWord(){
        while(true){
            String i = scanner.nextLine();
            if(i.contains(" ") || i.length() > 30 || i.length() < 4){
                System.out.println("input can't have space and be 4-30 long");
                System.out.printf(ERROR_MSG);
            } else return i;
        }

    }

    private static String input1Length(){
        String i;
        while(true){
            i = scanner.nextLine();
            if(i.length() > 1 || i.length() == 0){
                System.out.println(ERROR_MSG);
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
