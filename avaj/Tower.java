package avaj;

import java.io.PrintWriter;

public abstract class Tower {
    private static PrintWriter writer;

    public static void setWriter(PrintWriter pw) {
        writer = pw;
    }

    public static void logMessage(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }
}
