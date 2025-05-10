package org.example.parsers;

import junit.framework.TestCase;

public class HourParserTest extends TestCase {
    private final BaseFieldParser hourParser = new HourParser();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testParseWithAllRange() {
        String input = "*";
        String result = hourParser.parse(input);
        assertEquals("hour          0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23", result);
    }

    public void testParseWithStepRange() {
        String input = "*/15";
        String result = hourParser.parse(input);
        assertEquals("hour          0 15", result);
    }

    public void testParseWithStepRangeLimit() {
        String input = "1-10/2";
        String result = hourParser.parse(input);
        assertEquals("hour          1 3 5 7 9", result);
    }

    public void testParseWithFixedValues() {
        String input = "1,2,3";
        String result = hourParser.parse(input);
        assertEquals("hour          1 2 3", result);
    }

    public void testParseWithFixedRange() {
        String input = "5-10";
        String result = hourParser.parse(input);
        assertEquals("hour          5 6 7 8 9 10", result);
    }
}