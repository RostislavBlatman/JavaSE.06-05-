package main.java;

import main.java.exceptions.BadPathToPackageWithProperties;
import main.java.exceptions.KeyOrValueIsNullException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Logic {

    private static Logic instance;
    private Map<String, String> mapForProperties;

    private int indexStartFileName;
    private int indexEndFileName;

    private Logic() {
    }

    public static Logic getInstance() {
        if (instance == null) {
            instance = new Logic();
        }
        return instance;
    }

    public void init(String pathToPackage) {
        mapForProperties = new HashMap<>();
        try {
            List<Path> paths = Files.walk(Paths.get(pathToPackage))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            for (Path path : paths) {

                indexStartFileName = path.getFileName().toString().lastIndexOf("\\") + 1;
                indexEndFileName = path.getFileName().toString().lastIndexOf(".");

                if (path.getFileName().toString().contains(".properties")) {

                    putPropertiesInMap(path.getFileName().toString().substring(indexStartFileName, indexEndFileName));
                } else {
                    throw new BadPathToPackageWithProperties();
                }

            }

        } catch (BadPathToPackageWithProperties badPathToPackageWithProperties) {

            badPathToPackageWithProperties.printStackTrace();

        } catch (NoSuchFileException noSuchFileException) {
            System.out.println("You have entered a non-existent way or this package is empty");
            noSuchFileException.printStackTrace();

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

        } catch (NullPointerException nullPointerException) {
            System.out.println("Please, input path");
            throw nullPointerException;
        }
    }

    public Map<String, String> getMapForProperties() {
        return mapForProperties;
    }

    public String getValueForKey(String key) throws KeyOrValueIsNullException {
        if (key == null || mapForProperties.get(key) == null) {
            throw new KeyOrValueIsNullException();
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
