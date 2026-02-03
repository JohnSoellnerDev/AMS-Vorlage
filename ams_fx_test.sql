-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 08. Nov 2022 um 17:59
-- Server-Version: 10.4.14-MariaDB
-- PHP-Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ams_fx_test`
--
CREATE DATABASE IF NOT EXISTS `ams_fx_test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ams_fx_test`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `gebaeude`
--

CREATE TABLE `gebaeude` (
  `gebaeude_id` int(11) NOT NULL,
  `bezeichnung` varchar(20) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `gebaeude`
--

INSERT INTO `gebaeude` (`gebaeude_id`, `bezeichnung`, `adresse`) VALUES
(1, 'IT-C', 'Musterstraße 1'),
(2, 'Hauptgebäude', 'Hauptstraße 10');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `raeume`
--

CREATE TABLE `raeume` (
  `raum_id` int(11) NOT NULL,
  `bezeichnung` varchar(20) DEFAULT NULL,
  `gebaeude_id` int(11) DEFAULT NULL,
  `laenge_in_cm` double DEFAULT NULL,
  `breite_in_cm` double DEFAULT NULL,
  `verantwortlicher` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `raeume`
--

INSERT INTO `raeume` (`raum_id`, `bezeichnung`, `gebaeude_id`, `laenge_in_cm`, `breite_in_cm`, `verantwortlicher`) VALUES
(1, 'U1', 1, 500, 500, NULL),
(2, 'U2', 1, 500, 500, NULL),
(3, '121', 2, 500, 500, NULL);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `gebaeude`
--
ALTER TABLE `gebaeude`
  ADD PRIMARY KEY (`gebaeude_id`);

--
-- Indizes für die Tabelle `raeume`
--
ALTER TABLE `raeume`
  ADD PRIMARY KEY (`raum_id`),
  ADD KEY `fk_gebaeude` (`gebaeude_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `gebaeude`
--
ALTER TABLE `gebaeude`
  MODIFY `gebaeude_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT für Tabelle `raeume`
--
ALTER TABLE `raeume`
  MODIFY `raum_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `raeume`
--
ALTER TABLE `raeume`
  ADD CONSTRAINT `fk_gebaeude` FOREIGN KEY (`gebaeude_id`) REFERENCES `gebaeude` (`gebaeude_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
