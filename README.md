# BfoInnovation-CLI – Hausaufgaben-Sorter

Dieses Programm nimmt eine ZIP-Datei mit Schüler-Abgaben, entpackt sie, prüft die Ordnernamen `Nachname_Vorname_KWxx` und sortiert alles automatisch nach:

<Zielordner>\Nachname_Vorname\KWxx\

yaml
Code kopieren

Am Ende zeigt die Konsole, wer abgegeben hat und wer fehlt. Außerdem wird automatisch eine Log-Datei mit Datum geschrieben.

---

## 1) Was brauche ich?

- Java 17 (JDK). Test im Terminal:
javac -version
java -version

diff
Code kopieren
(sollte 17.x anzeigen)

- Projektstruktur:
src\ → alle .java-Dateien
data\classlist.txt → Klassenliste (siehe unten)

yaml
Code kopieren

---

## 2) Klassenliste anlegen

Datei: `data/classlist.txt`  
Format (UTF-8, eine Person pro Zeile, Semikolon als Trennzeichen):
Vorname;Nachname
Mia;Bregy
Jürg;Hallenbarter
Aurélien;Soguel
Osman;Türkarslan

yaml
Code kopieren
*Tipp: Datei als UTF-8 speichern (für Umlaute/Accents).*

---

## 3) ZIP vorbereiten

- Lege die ZIP unter `C:\test\hausaufgaben.zip` ab.  
- In der ZIP müssen **direkt** (keine Extra-Ebene) die Ordner liegen, exakt so benannt:
Nachname_Vorname_KWxx
Beispiel: Bregy_Mia_KW12/

yaml
Code kopieren
(Optional: In die Schülerordner eine Testdatei legen, z. B. `abgabe.txt`.)

---

## 4) Bauen & Starten

Im Projektordner (wo `src` liegt):

**Kompilieren**
javac -d out src/*.java

markdown
Code kopieren

**Start (Standardpfade)**
java -cp out Main

markdown
Code kopieren

**Start mit eigenen Pfaden**
java -cp out Main --workDir "C:\test" --destDir "C:\Aufgaben" --zipName "hausaufgaben.zip"

markdown
Code kopieren

*Option für saubere Umlaute in der Konsole:*
chcp 65001
java -Dfile.encoding=UTF-8 -cp out Main

yaml
Code kopieren

---

## 5) Was sollte ich sehen?

**Konsole (Beispiel):**
[INFO ] Klassenliste geladen: 9 Schüler
[INFO ] ZIP entpackt nach: C:\test\unzipped
[INFO ] Verschoben: C:\test\unzipped\Bregy_Mia_KW12 -> C:\Aufgaben\Bregy_Mia\KW12

=== Abgegeben ===

Bregy_Mia

...
=== Nicht abgegeben ===

...

markdown
Code kopieren

**Auf der Festplatte:**
- Sortierte Ordner unter `C:\Aufgaben\Nachname_Vorname\KWxx\`
- Logdatei unter `logs\DD-MM-YYYY_exercise.log` (wird automatisch erstellt)