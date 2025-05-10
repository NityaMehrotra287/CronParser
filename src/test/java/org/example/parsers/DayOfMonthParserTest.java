package org.example.parsers;

import junit.framework.TestCase;

public class DayOfMonthParserTest extends TestCase {

    private final BaseFieldParser dayOfMonthParser = new DayOfMonthParser();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testParseWithAllRange() {
        String input = "*";
        String result = dayOfMonthParser.parse(input);
        assertEquals("day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31", result);
    }

    public void testParseWithStepRange() {
        String input = "*/15";
        String result = dayOfMonthParser.parse(input);
        assertEquals("day of month  1 16 31", result);
    }

    public void testParseWithStepRangeLimit() {
        String input = "1-10/2";
        String result = dayOfMonthParser.parse(input);
        assertEquals("day of month  1 3 5 7 9", result);
    }

    public void testParseWithFixedValues() {
        String input = "1,2,3";
        String result = dayOfMonthParser.parse(input);
        assertEquals("day of month  1 2 3", result);
    }

    public void testParseWithFixedRange() {
        String input = "5-10";
        String result = dayOfMonthParser.parse(input);
        assertEquals("day of month  5 6 7 8 9 10", result);
    }
}