# BfoInnovation-CLI – Hausaufgaben-Sorter

Dieses Programm nimmt eine **ZIP-Datei** mit Schüler-Abgaben, **entpackt** sie, prüft die **Ordnernamen**
(`Nachname_Vorname_KWxx`) und sortiert alles automatisch in:

<Zielordner>\Nachname_Vorname\KWxx\

yaml
Code kopieren

Am Ende zeigt die Konsole, **wer abgegeben hat** und **wer fehlt**. Außerdem wird automatisch eine
**Log-Datei mit Datum** geschrieben (Nachweis).

---

## 1) Was brauche ich?

- **Java 17 (JDK)**. Test im Terminal:
```bat
javac -version
java  -version
(sollte 17.x anzeigen)

Projektstruktur:

scss
Code kopieren
src\                → alle .java-Dateien
data\classlist.txt  → deine Klassenliste (siehe unten)
2) Klassenliste anlegen
Datei: data/classlist.txt
Format (UTF-8, eine Person pro Zeile, Semikolon als Trennzeichen):

r
Code kopieren
Vorname;Nachname
Mia;Bregy
Jürg;Hallenbarter
Aurélien;Soguel
Osman;Türkarslan
Tipp: Datei als UTF-8 speichern (für Umlaute/Accents).

3) ZIP vorbereiten
Lege deine ZIP unter C:\test\hausaufgaben.zip ab.

In der ZIP müssen direkt (keine Extra-Ebene) die Ordner liegen, exakt so benannt:

makefile
Code kopieren
Nachname_Vorname_KWxx
Beispiel: Bregy_Mia_KW12/
(Optional kannst du in die Schülerordner eine Testdatei legen, z. B. abgabe.txt.)

4) Bauen & Starten
Im Projektordner (wo src liegt):

Kompilieren

bat
Code kopieren
javac -d out src/*.java
Start (Standardpfade)

bat
Code kopieren
java -cp out Main
Start mit eigenen Pfaden

bat
Code kopieren
java -cp out Main --workDir "C:\test" --destDir "C:\Aufgaben" --zipName "hausaufgaben.zip"
Schönere Umlaute in der Konsole (optional):

bat
Code kopieren
chcp 65001
java -Dfile.encoding=UTF-8 -cp out Main
5) Was sollte ich sehen?
Beispiel in der Konsole:

csharp
Code kopieren
[INFO ] Klassenliste geladen: 9 Schüler
[INFO ] ZIP entpackt nach: C:\test\unzipped
[INFO ] Verschoben: C:\test\unzipped\Bregy_Mia_KW12 -> C:\Aufgaben\Bregy_Mia\KW12

=== Abgegeben ===
 - Bregy_Mia
 - ...
=== Nicht abgegeben ===
 - ...
Auf der Festplatte:

Sortierte Ordner unter C:\Aufgaben\Nachname_Vorname\KWxx\

Logdatei unter logs\DD-MM-YYYY_exercise.log (wird automatisch erstellt)

perl
Code kopieren

> Wichtig: Kopiere **nur** den Inhalt zwischen den drei Backticks (``` … ```).  
> Wenn GitHub die README trotzdem „komisch“ anzeigt, stelle sicher, dass **ganz oben/unten** in deiner Datei **keine extra** ``` stehen und die Datei wirklich `README.md` heißt.
