package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CronParserTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void afterEach() {
        System.setOut(originalOut);
    }

    @Test
    public void TestMainWithValidExpressionWithStepRangeInMinutes() {
        String[] args = {"*/15 0 1,15 * 1-5 /usr/bin/find"};

        CronParser.main(args);
        String result = outContent.toString();
        String expectedOutput =
                "minute        0 15 30 45\n" +
                        "hour          0\n" +
                        "day of month  1 15\n" +
                        "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                        "day of week   1 2 3 4 5\n" +
                        "command       /usr/bin/find\n";
        assertEquals(expectedOutput, result);
    }

    @Test
    public void TestMainWithValidExpressionWithFixedValuesOfMinutesAndHours() {
        String[] args = {"0 12 * * 1-5 /usr/bin/find"};
        CronParser.main(args);
        String result = outContent.toString();
        String expectedOutput =
                "minute        0\n" +
                        "hour          12\n" +
                        "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31\n" +
                        "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
                        "day of week   1 2 3 4 5\n" +
                        "command       /usr/bin/find\n";
        assertEquals(expectedOutput, result);
    }

    @Test
    public void TestMainWithValidExpressionWithAllFixedValue() {
        String[] args = {"1 20 25 7 6 /usr/bin/find"};
        CronParser.main(args);

        String result = outContent.toString();
        String expectedOutput =
                "minute        1\n" +
                        "hour          20\n" +
                        "day of month  25\n" +
                        "month         7\n" +
                        "day of week   6\n" +
                        "command       /usr/bin/find\n";
        assertEquals(expectedOutput, result);
    }


    @Test
    public void TestMainWithNoArguments() {
        String[] args = {};
        try {
            CronParser.main(args);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Please provide a cron expression"));
        }
    }

    @Test
    public void TestMainWithInvalidExpression() {
        String[] args = {"62 * * * * /usr/bin/find"};
        try {
            CronParser.main(args);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Invalid value 62 for range 0-59"));
        }
    }

    @Test
    public void TestMainWithInsufficientFields() {
        String[] args = {"* * * *"};
        try {
            CronParser.main(args);
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Incorrect length of cron expression"));
        }
    }
}