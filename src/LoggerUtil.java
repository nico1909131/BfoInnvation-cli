import java.io.*;
import java.text.*;
import java.util.*;

// Logger -> Konsole + Datei; Überladung
public class LoggerUtil {
  private static final String LOG_DIR = "logs";
  private static final String DATE = new SimpleDateFormat("dd-MM-YYYY").format(new Date());

  private static File logFile() {
    File d = new File(LOG_DIR);
    if (!d.exists()) d.mkdirs();
    return new File(d, DATE + "_exercise.log");
  }

  private static void write(String lvl, String msg) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile(), true))) {
      String ts = new SimpleDateFormat("HH:mm:ss").format(new Date());
      bw.write("[" + ts + "][" + lvl + "] " + msg);
      bw.newLine();
    } catch (IOException ignore) {}
  }

  public static void info(String m)  { System.out.println("[INFO ] " + m); write("INFO",  m); }
  public static void warn(String m)  { System.out.println("[WARN ] " + m); write("WARN",  m); }
  public static void error(String m) { System.err.println("[ERROR] " + m); write("ERROR", m); }

  // Überladung mit Throwable
  public static void info(String m, Throwable t) { info(m + " (" + t.getMessage() + ")"); }
}
