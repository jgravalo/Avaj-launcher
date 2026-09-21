package com.jgravalo.avaj.simulator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static PrintWriter writer;

    private Logger() {}

    public static void open(String p_path) throws IOException {
        writer = new PrintWriter(new FileWriter(p_path));
    }

    public static void log(String p_message) {
        if (writer != null) {
            writer.println(p_message);
        }
    }

    public static void close() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}
