package test.java;

import main.java.Logic;
import main.java.exceptions.NullException;
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


    @BeforeEach
    void setUp() {
        logic = new Logic("C:\\Users\\Ростислав\\Desktop\\Homework\\JavaSE.06.new\\src\\test\\java\\" +
                "testResources\\testProperty.properties");
    }

    @DisplayName("Test for mainFunction")
    @Test
    void testForMainFunction() {

        logic.mainFunction();
        assertEquals(logic.getMapForProperties().toString(), "{1=qwerty, qwerty=1}");

    }

    @DisplayName("Test for getValueForKey")
    @Test
    void testForGetValue() {

        logic.mainFunction();
        try {
            assertEquals(logic.getValueForKey("1"), "qwerty");
            assertThrows(NullException.class, () -> logic.getValueForKey("11"));
            assertThrows(NullException.class, () -> logic.getValueForKey(null));
        } catch (NullException exception) {
            exception.printStackTrace();
        }
    }

    @DisplayName("Test for writeInFile")
    @Test
    void testWriteInFile() {

        logic.mainFunction();
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
