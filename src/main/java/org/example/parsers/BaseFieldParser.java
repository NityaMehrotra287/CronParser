package org.example.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BaseFieldParser {
    String name;
    int min;
    int max;
    public final int columnSize = 14;

    public BaseFieldParser(String name, int min, int max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String parse(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for (int i = name.length(); i < columnSize; i++) {
            sb.append(" ");
        }
        List<Integer> parsedValues = parseValue(input);
        sb.append(formatList(parsedValues));
        return sb.toString();
    }

    private List<Integer> parseValue(String expression) {
        if (expression.equals("*")) {
            return generateRange(min, max);
        }

        if (expression.contains("*/")) {
            String[] parts = expression.split("/");
            int step = Integer.parseInt(parts[1]);
            return generateStepRange(min, max, step);
        }

        if (expression.contains("/") && expression.contains("-")) {
            String[] parts = expression.split("/");
            String rangeStr = parts[0];
            int step = Integer.parseInt(parts[1]);

            String[] rangeParts = rangeStr.split("-");
            int start = Integer.parseInt(rangeParts[0]);
            int end = Integer.parseInt(rangeParts[1]);

            return generateStepRange(start, end, step);
        }

        if (expression.contains(",")) {
            List<Integer> result = new ArrayList<>();
            String[] values = expression.split(",");

            for (String value : values) {
                result.addAll(parseValue(value));
            }
            return result.stream()
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());
        }

        if (expression.contains("-")) {
            String[] rangeParts = expression.split("-");
            int start = Integer.parseInt(rangeParts[0]);
            int end = Integer.parseInt(rangeParts[1]);

            validate(start);
            validate(end);

            return generateRange(start, end);
        }

        try {
            int value = Integer.parseInt(expression);
            validate(value);
            return Arrays.asList(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid field value: " + expression);
        }
    }

    private void validate(int value) {
        if (value < min || value > max) {
            throw new IllegalArgumentException("Invalid value " + value + " for range " + min + "-" + max);
        }
    }

    private List<Integer> generateRange(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Integer> generateStepRange(int start, int end, int step) {
        validate(start);
        validate(end);
        List<Integer> result = new ArrayList<>();
        for (int i = start; i <= end; i += step) {
            result.add(i);
        }
        return result;
    }

    private String formatList(List<Integer> parsedValues) {
        StringBuilder formattedValues = new StringBuilder();
        for (int i = 0; i < parsedValues.size(); i++) {
            formattedValues.append(parsedValues.get(i));
            if (i < parsedValues.size() - 1) {
                formattedValues.append(" ");
            }
        }
        return formattedValues.toString();
    }
}
