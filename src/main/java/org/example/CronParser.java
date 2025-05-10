package org.example;

import org.example.parsers.*;

import java.util.StringJoiner;

public class CronParser {
    private static final BaseFieldParser minuteParser = new MinuteParser();
    private static final BaseFieldParser hourParser = new HourParser();
    private static final BaseFieldParser dayOfMonthParser = new DayOfMonthParser();
    private static final BaseFieldParser monthParser = new MonthParser();
    private static final BaseFieldParser dayOfWeekParser = new DayOfWeekParser();
    private static final BaseFieldParser commandParser = new CommandParser();

    public static void main(String[] args) {
        int argsLength = args.length;
        if (argsLength == 0) {
            throw new RuntimeException("Please provide a cron expression");
        }
        CronParser parser = new CronParser();
        String output = parser.parser(args[0]);
        System.out.println(output);
    }

    public String parser(String input) {

        String[] params = input.split("\\s+");
        int paramsLength = params.length;
        if (paramsLength != 6) {
            throw new RuntimeException("Incorrect length of cron expression");
        }

        StringJoiner output = new StringJoiner("\n");
        for (int i = 0; i <= 5; i++) {
            switch (i) {
                case 0:
                    output.add(minuteParser.parse(params[i]));
                    break;
                case 1:
                    output.add(hourParser.parse(params[i]));
                    break;
                case 2:
                    output.add(dayOfMonthParser.parse(params[i]));
                    break;
                case 3:
                    output.add(monthParser.parse(params[i]));
                    break;
                case 4:
                    output.add(dayOfWeekParser.parse(params[i]));
                    break;
                default:
                    output.add(commandParser.parse(params[i]));
            }
        }
        return output.toString();
    }
}