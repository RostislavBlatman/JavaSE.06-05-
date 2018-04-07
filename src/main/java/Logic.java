package main.java;

import main.java.exceptions.NullException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Logic {

    private Map<String, String> mapForProperties;
    private String pathToPackage;

    public Logic(String pathToPackage) {
        mapForProperties = new HashMap<>();
        this.pathToPackage = pathToPackage;

    }

    public void mainFunction() {

        try {
            List<Path> paths = Files.walk(Paths.get(pathToPackage))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            for (Path path : paths) {

                int a = path.getFileName().toString().lastIndexOf("\\") + 1;
                int b = path.getFileName().toString().lastIndexOf(".");

                putPropertiesInMap(path.getFileName().toString().substring(a, b));

            }

        } catch (IOException exception) {
            exception.printStackTrace();

        }

    }

    private void putPropertiesInMap(String path) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(path);

            Enumeration<String> keys = rb.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                String value = rb.getString(key);
                mapForProperties.put(key, value);
            }
        } catch (MissingResourceException missingResource) {
            System.out.println("Please, input good path to package");
            throw missingResource;
        } catch (NullPointerException nullPointerException) {
            System.out.println("Please, input path");
            throw nullPointerException;
        }
    }

    public Map<String, String> getMapForProperties() {
        return mapForProperties;
    }

    public String getValueForKey(String key) throws NullException {
        if (key == null || mapForProperties.get(key) == null) {
            throw new NullException();
        } else {
            return mapForProperties.get(key);
        }
    }

    public void writeInFile(String path) {
        try (BufferedWriter bufferedOutputStream = new BufferedWriter(new FileWriter(path))) {

            bufferedOutputStream.write(mapForProperties.toString());

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}
