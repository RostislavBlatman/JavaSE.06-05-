package main.java;

import main.java.exceptions.NullException;

public class Main {
    public static void main(String[] args) {
        Logic logic = new Logic("C:\\Users\\Ростислав\\Desktop\\Homework\\JavaSE.06.new\\src\\main\\java\\resources");
        logic.mainFunction();
        System.out.println(logic.getMapForProperties());
        logic.writeInFile("src\\main\\java\\fileOut.txt");
    }
}

