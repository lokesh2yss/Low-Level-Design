package logging_framework.entities.interfaces;

import logging_framework.entities.LogMessage;

public interface LogAppender {
    void append(LogMessage message);
    void close();
    LogFormatter getFormatter();
    void setFormatter(LogFormatter formatter);
}
