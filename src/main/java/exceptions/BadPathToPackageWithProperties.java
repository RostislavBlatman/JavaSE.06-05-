package main.java.exceptions;

public class BadPathToPackageWithProperties extends Exception {
    public BadPathToPackageWithProperties() {
        super("This package does not contain any *.properties files. Please, input right path to package with properties");
    }

    public BadPathToPackageWithProperties(String message) {
        super(message);
    }
}
