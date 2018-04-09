package main.java;

public class Main {
    public static void main(String[] args) {
        Logic logic = Logic.getInstance("C:\\Users\\Ростислав\\Desktop\\Homework\\JavaSE.06.new\\src\\main\\java\\resources");
        System.out.println(logic.getMapForProperties());
        logic.writeInFile("src\\main\\java\\fileOut.txt");
    }
}

