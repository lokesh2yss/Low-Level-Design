package logging_framework.entities;

import logging_framework.entities.interfaces.LogFormatter;

import java.time.format.DateTimeFormatter;

public class SimpleTextFormatter implements LogFormatter {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss:SSS");
    @Override
    public String format(LogMessage message) {
        return String.format("%s [%s] %s - %s: %s\n",
                message.getTimestamp().format(DATE_TIME_FORMATTER),
                message.getThreadName(),
                message.getLevel(),
                message.getLoggerName(),
                message.getMessage());
    }
}
