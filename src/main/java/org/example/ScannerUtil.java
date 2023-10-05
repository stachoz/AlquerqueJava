package org.example;

import java.util.Scanner;

public class ScannerUtil {
    static Scanner input = new Scanner(System.in);

    public static int get_natural_num() {
        while(true){
            String i = input_1_length();
            if(is_numeric(i)) return Integer.parseInt(i);
            else System.out.println("Bad input, again");
        }
    }

    public static char get_char() {
        while(true){
            String i = input_1_length();
            if(!is_numeric(i)) return i.charAt(0);
            else System.out.println("Bad input, enter again");
        }
    }

    private static String input_1_length(){
        String i;
        while(true){
            i = input.nextLine();
            if(i.length() > 1){
                System.out.println("Bad input, enter again");
            } else{
                return i;
            }
        }
    }
    private static boolean is_numeric(String s){
        try{
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
