-- Datenbank anlegen und auswählen
CREATE DATABASE IF NOT EXISTS ams_fx_test;
USE ams_fx_test;

-- --------------------------------------------------------
-- Tabellenstruktur für Tabelle `gebaeude`
-- --------------------------------------------------------

CREATE TABLE `gebaeude` (
    `gebaeude_id` int(11) NOT NULL,
    `bezeichnung` varchar(20) DEFAULT NULL,
    `adresse` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `gebaeude`
    ADD PRIMARY KEY (`gebaeude_id`);

ALTER TABLE `gebaeude`
    MODIFY `gebaeude_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


-- --------------------------------------------------------
-- Tabellenstruktur für Tabelle `raeume`
-- --------------------------------------------------------

CREATE TABLE `raeume` (
                          `raum_id` int(11) NOT NULL,
                          `bezeichnung` varchar(20) DEFAULT NULL,
                          `gebaeude_id` int(11) DEFAULT NULL,
                          `laenge_in_cm` double DEFAULT NULL,
                          `breite_in_cm` double DEFAULT NULL,
                          `verantwortlicher` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `raeume`
    ADD PRIMARY KEY (`raum_id`),
    ADD KEY `fk_gebaeude` (`gebaeude_id`);

ALTER TABLE `raeume`
    MODIFY `raum_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `raeume`
  ADD CONSTRAINT `fk_gebaeude` FOREIGN KEY (`gebaeude_id`) REFERENCES `gebaeude` (`gebaeude_id`);

COMMIT;
