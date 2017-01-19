-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 09. Jan 2017 um 21:27
-- Server-Version: 5.7.16-log
-- PHP-Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `musikkapelle`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hauptwohnsitz`
--

CREATE DATABASE musikkapelle;

USE musikkapelle;

CREATE TABLE `hauptwohnsitz` (
  `Id` int(11) NOT NULL,
  `Strasse` varchar(100) NOT NULL,
  `Hausnummer` varchar(45) NOT NULL,
  `PLZ` int(11) NOT NULL,
  `Ort` varchar(100) NOT NULL,
  `Land` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `instrument`
--

CREATE TABLE `instrument` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Kategorie` varchar(100) NOT NULL,
  `Preis` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `musikant`
--

CREATE TABLE `musikant` (
  `Id` int(11) NOT NULL,
  `Vorname` varchar(100) NOT NULL,
  `Nachname` varchar(100) NOT NULL,
  `Geburtsdatum` date NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Spezialfunktion` varchar(100) NOT NULL,
  `Hauptwohnsitz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `musikantinstrument`
--

CREATE TABLE `musikantinstrument` (
  `MusikantId` int(11) NOT NULL,
  `InstrumentId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `hauptwohnsitz`
--
ALTER TABLE `hauptwohnsitz`
  ADD PRIMARY KEY (`Id`);

--
-- Indizes für die Tabelle `instrument`
--
ALTER TABLE `instrument`
  ADD PRIMARY KEY (`Id`);

--
-- Indizes für die Tabelle `musikant`
--
ALTER TABLE `musikant`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Musikant_Hauptwohnsitz_idx` (`Hauptwohnsitz`);

--
-- Indizes für die Tabelle `musikantinstrument`
--
ALTER TABLE `musikantinstrument`
  ADD PRIMARY KEY (`MusikantId`,`InstrumentId`),
  ADD KEY `MusikInstrument_idx` (`InstrumentId`);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `musikant`
--
ALTER TABLE `musikant`
  ADD CONSTRAINT `Musikant_Hauptwohnsitz` FOREIGN KEY (`Hauptwohnsitz`) REFERENCES `hauptwohnsitz` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `musikantinstrument`
--
ALTER TABLE `musikantinstrument`
  ADD CONSTRAINT `MusikantInstrument` FOREIGN KEY (`InstrumentId`) REFERENCES `instrument` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `MusikantInstrument_Musikant` FOREIGN KEY (`MusikantId`) REFERENCES `musikant` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
