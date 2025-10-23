// ClassListReader.java
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

// Liest data/classlist.txt in den ClassRoom ein
public class ClassListReader {

  public static void readInto(String path, ClassRoom room) {
    int count = 0;

    try (BufferedReader br = new BufferedReader(
            new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {

      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) continue;

        String[] p = line.split(";", -1);
        if (p.length != 2) {
          LoggerUtil.warn("Zeile übersprungen (falsches Format): " + line);
          continue;
        }

        String first = p[0].trim();
        String last  = p[1].trim();
        if (first.isEmpty() || last.isEmpty()) {
          LoggerUtil.warn("Zeile übersprungen (leer): " + line);
          continue;
        }

      room.add(new Student(first, last));

        count++;
      }

      LoggerUtil.info("Klassenliste geladen: " + count + " Schüler");
    } catch (IOException e) {
      LoggerUtil.error("Konnte Klassenliste nicht lesen: " + path + " (" + e.getMessage() + ")");
    }
  }
}
