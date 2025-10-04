package logging_framework.entities;

import logging_framework.entities.interfaces.LogAppender;
import logging_framework.entities.interfaces.LogFormatter;

public class ConsoleAppender implements LogAppender {
    private LogFormatter formatter;
    public ConsoleAppender() {
        this.formatter = new SimpleTextFormatter();
    }
    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }

    @Override
    public void close() {

    }

    @Override
    public LogFormatter getFormatter() {
        return formatter;
    }

    @Override
    public void setFormatter(LogFormatter formatter) {
        this.formatter = formatter;
    }
}
