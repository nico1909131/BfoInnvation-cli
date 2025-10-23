// CLI Einstieg; orchestriert den Ablauf
import java.io.File;

public class Main {
  public static void main(String[] args) {
    String workDir = "C:" + File.separator + "test";
    String destDir = "C:" + File.separator + "Aufgaben";
    String zipName = "hausaufgaben.zip";

    // einfache CLI-Parameter: --workDir --destDir --zipName
    for (int i = 0; i < args.length - 1; i += 2) {
      if ("--workDir".equals(args[i])) workDir = args[i + 1];
      if ("--destDir".equals(args[i])) destDir = args[i + 1];
      if ("--zipName".equals(args[i])) zipName = args[i + 1];
    }

    String unzipDir = workDir + File.separator + "unzipped";
    LoggerUtil.info("Start: workDir=" + workDir + " destDir=" + destDir + " zip=" + zipName);

    // 1) Klassenliste einlesen
    ClassRoom room = new ClassRoom();
    ClassListReader.readInto("data" + File.separator + "classlist.txt", room);

    // 2) ZIP entpacken
    ZipHandler.extractZip(workDir + File.separator + zipName, unzipDir);

    // 3) Ordner prüfen & verschieben
    SubmissionChecker.checkAndMove(unzipDir, room, destDir);


    // 4) Übersicht
    System.out.println();
    System.out.println("=== Abgegeben ===");
    for (Student s : room.getStudents()) if (s.isSubmitted()) {
      System.out.println(" - " + s.fullNameUnderscored());
    }
    System.out.println("=== Nicht abgegeben ===");
    for (Student s : room.getStudents()) if (!s.isSubmitted()) {
      System.out.println(" - " + s.fullNameUnderscored());
    }

    LoggerUtil.info("Fertig.");
  }
}
