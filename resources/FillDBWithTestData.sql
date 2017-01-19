INSERT INTO Hauptwohnsitz (Id, Strasse, Hausnummer, PLZ, Ort, Land) VALUES(1, 'Arzlerstrasse', '88a', 6020, 'Innsbruck', 'Oesterreich'); 
INSERT INTO Hauptwohnsitz (Id, Strasse, Hausnummer, PLZ, Ort, Land) VALUES(2, 'TestStrasse', '69', 6170, 'Zirl', 'Oesterreich'); 
INSERT INTO Hauptwohnsitz (Id, Strasse, Hausnummer, PLZ, Ort, Land) VALUES(3, 'AndereTestStrasse', '113', 1234, 'CoolStadt', 'Oesterreich'); 
INSERT INTO Hauptwohnsitz (Id, Strasse, Hausnummer, PLZ, Ort, Land) VALUES(4, 'Strasse', '17', 53453, 'Blub', 'Oesterreich');
INSERT INTO Hauptwohnsitz (Id, Strasse, Hausnummer, PLZ, Ort, Land) VALUES(5, 'Frei', '17', 53453, 'Hans', 'Oesterreich');

INSERT INTO Musikant (Id, Vorname, Nachname, Geburtsdatum, Email, Spezialfunktion, Hauptwohnsitz) VALUES (1, 'Maximilian', 'Suitner' , '1997-08-23', 'maxlisui@gmail.com', 'coolerTyp', 1);
INSERT INTO Musikant (Id, Vorname, Nachname, Geburtsdatum, Email, Spezialfunktion, Hauptwohnsitz) VALUES (2, 'Hans', 'Kanns' , '1997-05-14', 'hans@gmail.com', 'coolererTyp', 2);
INSERT INTO Musikant (Id, Vorname, Nachname, Geburtsdatum, Email, Spezialfunktion, Hauptwohnsitz) VALUES (3, 'TestTyp', 'Testy' , '1992-12-12', 'oldboy@gmail.com', 'oldies', 3);
INSERT INTO Musikant (Id, Vorname, Nachname, Geburtsdatum, Email, Spezialfunktion, Hauptwohnsitz) VALUES (4, 'Muskikant', 'Musik' , '1987-01-01', 'blasmusik@gmail.com', 'Spieler', 4);

INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(1, 'Gitarre', 'Streicher', 250.0);
INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(2, 'Schlagzeug', 'Schläger', 500.0);
INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(3, 'Bass', 'Streicher', 300.0);
INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(4, 'Keyboard', 'FunkyMusic', 100.0);
INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(5, 'Oaschgeign', 'Arsch', 0.0);
INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(6, 'Trompete', 'Blaser', 100.0);
INSERT INTO Instrument (Id, Name, Kategorie, Preis) VALUES(7, 'Klavier', 'Klopfer', 350.0);

INSERT INTO MusikantInstrument (MusikantID, InstrumentID, Stimme) VALUES(1, 1);
INSERT INTO MusikantInstrument (MusikantID, InstrumentID, Stimme) VALUES(2, 2);
INSERT INTO MusikantInstrument (MusikantID, InstrumentID, Stimme) VALUES(3, 3);
INSERT INTO MusikantInstrument (MusikantID, InstrumentID, Stimme) VALUES(4, 4);