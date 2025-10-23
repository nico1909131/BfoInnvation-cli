// FileOrganizer.java
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/** Verschiebt Ordner und baut Zielpfade. */
public class FileOrganizer {

    /** Convenience: String-Pfade annehmen. */
    public static void moveFolder(String src, String dst) {
        moveFolder(new File(src), new File(dst));
    }

    /** Verschiebt den gesamten Ordner src -> dst (überschreibt vorhandenes Ziel). */
    public static void moveFolder(File src, File dst) {
        try {
            // Ziel-Elternordner sicherstellen (KW-Ordner etc.)
            Files.createDirectories(dst.toPath().getParent());
            // Verschieben (überschreiben erlaubt, damit Wiederholungs-Läufe funktionieren)
            Files.move(src.toPath(), dst.toPath(), StandardCopyOption.REPLACE_EXISTING);
            LoggerUtil.info("Verschoben: " + src + " -> " + dst);
        } catch (IOException e) {
            LoggerUtil.error("Move-Fehler: " + e.getMessage());
        }
    }

    /** Baut den Zielpfad: <destRoot>\Nachname_Vorname\KWxx\  */
    public static File buildDest(File destRoot, String last, String first, String kw) {
        File studentRoot = new File(destRoot, last + "_" + first);
        return new File(studentRoot, kw);
    }

    /** Hilfsfunktion zum Pfade zusammenfügen mit systemtypischem Separator. */
    public static String join(String... parts) {
        return String.join(File.separator, parts);
    }
}
