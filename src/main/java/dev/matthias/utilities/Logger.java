package dev.matthias.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {

    private Logger(){}

    public static void log(String msg, LogLevel lvl) {
        String logMsg = "["+lvl+"] " + msg + " " + new Date() + "\n";

        try {
            Files.write(Paths.get("C:\\Users\\mattm\\Desktop\\Project1\\expenseManagerLog.log"),
                    logMsg.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
