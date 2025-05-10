package org.example.parsers;

import junit.framework.TestCase;

public class MonthParserTest extends TestCase {

    private final BaseFieldParser monthParser = new MonthParser();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testParseWithAllRange() {
        String input = "*";
        String result = monthParser.parse(input);
        assertEquals("month         1 2 3 4 5 6 7 8 9 10 11 12", result);
    }

    public void testParseWithStepRange() {
        String input = "*/10";
        String result = monthParser.parse(input);
        assertEquals("month         1 11", result);
    }

    public void testParseWithStepRangeLimit() {
        String input = "1-10/2";
        String result = monthParser.parse(input);
        assertEquals("month         1 3 5 7 9", result);
    }

    public void testParseWithFixedValues() {
        String input = "1,2,3";
        String result = monthParser.parse(input);
        assertEquals("month         1 2 3", result);
    }

    public void testParseWithFixedRange() {
        String input = "5-10";
        String result = monthParser.parse(input);
        assertEquals("month         5 6 7 8 9 10", result);
    }
}