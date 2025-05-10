package org.example.parsers;

import junit.framework.TestCase;

public class MinuteParserTest extends TestCase {

    private final BaseFieldParser minuteParser = new MinuteParser();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testParseWithAllRange() {
        String input = "*";
        String result = minuteParser.parse(input);
        assertEquals("minute        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59", result);
    }

    public void testParseWithStepRange() {
        String input = "*/15";
        String result = minuteParser.parse(input);
        assertEquals("minute        0 15 30 45", result);
    }

    public void testParseWithStepRangeLimit() {
        String input = "1-10/2";
        String result = minuteParser.parse(input);
        assertEquals("minute        1 3 5 7 9", result);
    }

    public void testParseWithFixedValues() {
        String input = "1,2,3";
        String result = minuteParser.parse(input);
        assertEquals("minute        1 2 3", result);
    }

    public void testParseWithFixedRange() {
        String input = "5-10";
        String result = minuteParser.parse(input);
        assertEquals("minute        5 6 7 8 9 10", result);
    }
}