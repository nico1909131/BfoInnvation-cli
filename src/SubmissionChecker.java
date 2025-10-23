// SubmissionChecker.java
import java.io.File;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubmissionChecker {

    // akzeptiert alle Buchstaben (inkl. Umlaute/Accents) + KW-Zahl
    private static final Pattern FOLDER =
            Pattern.compile("^([\\p{L}]+)_([\\p{L}]+)_KW(\\d{1,2})$");

    // Einheitlicher Schlüssel "Nachname_Vorname" (Unicode normalisiert)
    private static String key(String last, String first) {
        String k = (last.trim() + "_" + first.trim());
        // NFC = gleiche Darstellung für Umlaute/Accents
        return Normalizer.normalize(k, Normalizer.Form.NFC);
    }

    public static void checkAndMove(File unzippedRoot, ClassRoom room, File destRoot) {
        // erwartete Schüler aus der Klassenliste
        Set<String> expected = new LinkedHashSet<>();
        for (Student s : room.getStudents()) {
            expected.add(key(s.getLastName(), s.getFirstName()));
        }

        // wer hat effektiv abgegeben?
        Set<String> submitted = new LinkedHashSet<>();

        File[] folders = unzippedRoot.listFiles(File::isDirectory);
        if (folders == null) {
            LoggerUtil.warn("Keine Ordner in: " + unzippedRoot);
            return;
        }

        for (File f : folders) {
            String name = f.getName();
            Matcher m = FOLDER.matcher(name);
            if (!m.matches()) {
                LoggerUtil.warn("Falsches Format: " + name);
                continue;
            }

            String last  = m.group(1);
            String first = m.group(2);
            String kw    = m.group(3);
            String k     = key(last, first);

            if (!expected.contains(k)) {
                LoggerUtil.warn("Nicht in Klassenliste: " + k);
                // optional: unbekannte nicht verschieben
                continue;
            }

            submitted.add(k);

            // Zielpfad z. B. C:\Aufgaben\Nachname_Vorname\KW12\
            File dst = FileOrganizer.buildDest(destRoot, last, first,
                    "KW" + (kw.length() == 1 ? "0" + kw : kw));
            FileOrganizer.moveFolder(f, dst);
        }

        // Übersicht
        System.out.println();
        System.out.println("=== Abgegeben ===");
        for (String s : submitted) System.out.println(" - " + s);

        System.out.println("=== Nicht abgegeben ===");
        for (String s : expected) {
            if (!submitted.contains(s)) System.out.println(" - " + s);
        }
    }
}
