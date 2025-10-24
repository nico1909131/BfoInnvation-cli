BfoInnovation-CLI – Hausaufgaben-Sorter

Dieses Programm nimmt eine ZIP-Datei mit Schüler-Abgaben, entpackt sie, prüft die Ordnernamen (Nachname_Vorname_KWxx) und sortiert alles automatisch in:

<Zielordner>\Nachname_Vorname\KWxx\


Am Ende zeigt die Konsole, wer abgegeben hat und wer fehlt. Außerdem wird automatisch eine Log-Datei mit Datum geschrieben (Nachweis).

1) Was brauche ich?

Java 17 (JDK). Test im Terminal:

javac -version
java  -version


(sollte 17.x anzeigen)

Dieses Projekt mit:

src\   → alle .java-Dateien
data\classlist.txt  → deine Klassenliste (siehe unten)

2) Klassenliste anlegen

Datei: data/classlist.txt
Format (UTF-8, eine Person pro Zeile, Semikolon als Trennzeichen):

Vorname;Nachname
Mia;Bregy
Jürg;Hallenbarter
Aurélien;Soguel
Osman;Türkarslan


Tipp: Datei als UTF-8 speichern (für Umlaute/Accents).

3) ZIP vorbereiten

Lege deine ZIP unter C:\test\hausaufgaben.zip ab.

In der ZIP müssen direkt (keine Extra-Ebene) die Ordner liegen, exakt so benannt:

Nachname_Vorname_KWxx
Beispiel: Bregy_Mia_KW12/


(Optional kannst du in die Schülerordner eine Testdatei legen, z. B. abgabe.txt.)

4) Bauen & Starten

Im Projektordner (wo src liegt):

Kompilieren

javac -d out src/*.java


Start (Standardpfade)

java -cp out Main


Start mit eigenen Pfaden

java -cp out Main --workDir "C:\test" --destDir "C:\Aufgaben" --zipName "hausaufgaben.zip"


Schönere Umlaute in der Konsole (optional):

chcp 65001
java -Dfile.encoding=UTF-8 -cp out Main

5) Was sollte ich sehen?

Beispiel in der Konsole:

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

7) Was macht die Log-Datei?

Automatisch wird pro Tag eine Datei erstellt:

logs\DD-MM-YYYY_exercise.log


Darin stehen alle Schritte (INFO), Warnungen (WARN) und Fehler (ERROR).
So kann man den Lauf später genau nachvollziehen.