package test.java;

import main.java.Logic;
import main.java.exceptions.KeyOrValueIsNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestForLogic {
    Logic logic;
    final static String PATH_FOR_OUTPUT_FILE = "C:\\Users\\Ростислав\\Desktop\\Homework\\JavaSE.06.new\\src" +
            "\\test\\java\\testResources\\testForWriteInFile.properties";
    final static String PATH_FOR_INPUT_FILE = "C:\\Users\\Ростислав\\Desktop\\Homework\\JavaSE.06.new\\src\\test\\java\\" +
            "testResources\\testProperty.properties";


    @BeforeEach
    void setUp() {
        logic = Logic.getInstance(PATH_FOR_INPUT_FILE);
    }

    @DisplayName("Test for mainFunction")
    @Test
    void testForInit() {


        assertEquals(logic.getMapForProperties().toString(), "{1=qwerty, qwerty=1}");

    }

    @DisplayName("Test for getValueForKey")
    @Test
    void testForGetValue() {


        try {
            assertEquals(logic.getValueForKey("1"), "qwerty");
            assertThrows(KeyOrValueIsNullException.class, () -> logic.getValueForKey("11"));
            assertThrows(KeyOrValueIsNullException.class, () -> logic.getValueForKey(null));
        } catch (KeyOrValueIsNullException exception) {
            exception.printStackTrace();
        }
    }

    @DisplayName("Test for writeInFile")
    @Test
    void testWriteInFile() {


        logic.writeInFile(PATH_FOR_OUTPUT_FILE);
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(PATH_FOR_OUTPUT_FILE)))) {

            String file = bufferedReader.readLine();
            assertEquals("{1=qwerty, qwerty=1}", file);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

}
