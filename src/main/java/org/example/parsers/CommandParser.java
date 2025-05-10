package org.example.parsers;

public class CommandParser extends BaseFieldParser {
    public CommandParser() {
        super("command", -1, -1);
    }

    public String parse(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for (int i = name.length(); i < columnSize; i++) {
            sb.append(" ");
        }
        sb.append(input);
        return sb.toString();
    }
}
