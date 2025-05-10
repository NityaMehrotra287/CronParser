package org.example.parsers;

import junit.framework.TestCase;

public class DayOfWeekParserTest extends TestCase {

    private final BaseFieldParser dayOfWeekParser = new DayOfWeekParser();

    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testParseWithAllRange() {
        String input = "*";
        String result = dayOfWeekParser.parse(input);
        assertEquals("day of week   1 2 3 4 5 6 7", result);
    }

    public void testParseWithStepRange() {
        String input = "*/4";
        String result = dayOfWeekParser.parse(input);
        assertEquals("day of week   1 5", result);
    }

    public void testParseWithStepRangeLimit() {
        String input = "1-5/2";
        String result = dayOfWeekParser.parse(input);
        assertEquals("day of week   1 3 5", result);
    }

    public void testParseWithFixedValues() {
        String input = "1,2,3";
        String result = dayOfWeekParser.parse(input);
        assertEquals("day of week   1 2 3", result);
    }

    public void testParseWithFixedRange() {
        String input = "1-3";
        String result = dayOfWeekParser.parse(input);
        assertEquals("day of week   1 2 3", result);
    }
}