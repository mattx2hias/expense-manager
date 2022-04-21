package dev.matthias.utiltests;

import dev.matthias.utilities.LogLevel;
import dev.matthias.utilities.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LoggerTest {

    @Test
    @DisplayName("Should add all log types to log file")
    void shouldAddAllLogTypesToLogFile() {
        Logger.log("Info Test", LogLevel.INFO);
        Logger.log("Warning Test", LogLevel.WARNING);
        Logger.log("Debug Test", LogLevel.DEBUG);
        Logger.log("Error Test", LogLevel.ERROR);
    }

}
