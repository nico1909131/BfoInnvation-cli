import java.io.*;
import java.util.zip.*;

// Entpackt ZIP in Zielordner
public class ZipHandler {
  public static void extractZip(String zipFilePath, String destDir) {
    File dir = new File(destDir);
    if (!dir.exists()) dir.mkdirs();

    try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
      ZipEntry e;
      byte[] buf = new byte[4096];
      while ((e = zis.getNextEntry()) != null) {
        File nf = new File(destDir, e.getName());
        if (e.isDirectory()) {
          nf.mkdirs();
        } else {
          new File(nf.getParent()).mkdirs();
          try (FileOutputStream fos = new FileOutputStream(nf)) {
            int len;
            while ((len = zis.read(buf)) > 0) {
              fos.write(buf, 0, len);
            }
          }
        }
        zis.closeEntry();
      }
      LoggerUtil.info("ZIP entpackt nach: " + destDir);
    } catch (IOException ex) {
      LoggerUtil.error("Entpacken fehlgeschlagen: " + ex.getMessage());
    }
  }
}
