package logging_framework.entities.interfaces;

import logging_framework.entities.LogMessage;

public interface LogFormatter {
    String  format(LogMessage message);
}
