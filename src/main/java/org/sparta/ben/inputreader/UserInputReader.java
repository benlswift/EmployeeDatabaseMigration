package org.sparta.ben.inputreader;

import org.sparta.ben.view.Printer;

import java.util.Scanner;

public class UserInputReader {
    public static String readUserInputString(String message){
        Scanner scanner = new Scanner(System.in);
        Printer.print(message);
        return scanner.next();
    }

    public static int readUserInputInt(String message){
        Scanner scanner = new Scanner(System.in);
        Printer.print(message);
        return scanner.nextInt();
    }
}
