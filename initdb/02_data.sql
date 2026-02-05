USE ams_fx_test;

INSERT INTO `gebaeude` (`gebaeude_id`, `bezeichnung`, `adresse`) VALUES
(1, 'IT-C', 'Musterstraße 1'),
(2, 'Hauptgebäude', 'Hauptstraße 10');

INSERT INTO `raeume` (`raum_id`, `bezeichnung`, `gebaeude_id`, `laenge_in_cm`, `breite_in_cm`, `verantwortlicher`) VALUES
(NULL, 'U1', 1, 300, 500, NULL),
(NULL, 'U2', 1, 500, 400, NULL),
(NULL, '121', 2, 500, 600, NULL);

INSERT INTO `geraete` (`geraet_id`, `bezeichnung`, `defekt`, `hersteller`, `modell`, `kauf_datum`, `garantie_monate`) VALUES
(NULL, 'Beamer U1',      0, 'Epson',   'EB-1234', '2023-09-01', 24),
(NULL, 'Lehrerlaptop',   0, 'Dell',    'Latitude 5420', '2022-03-15', 36),
(NULL, 'Schüler-PC 01',  1, 'Lenovo',  'ThinkCentre M720', '2021-11-10', 24);