CREATE TABLE `forschungen` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(60) NOT NULL default '',
  `req1` int(11) NOT NULL default '0',
  `req2` int(11) NOT NULL default '0',
  `req3` int(11) NOT NULL default '0',
  `time` int(11) NOT NULL default '0',
  `costs` varchar(120) NOT NULL default '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,',
  `descrip` text NOT NULL,
  `race` tinyint(4) NOT NULL default '0',
  `visibility` tinyint(4) NOT NULL default '1',
  `flags` varchar(60) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 

set sql_mode = NO_AUTO_VALUE_ON_ZERO;
		
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (-1, '(nicht erfuellbar)', -1, -1, -1, 0, '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,', '', 0, 2, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (0, '(immer erfuellt)', -1, -1, -1, 0, '0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,', '', 0, 2, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (1, 'Fusionsreaktor-Konstruktion', 2, 0, 0, 3, '0,0,0,0,0,0,0,0,80,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (2, 'Werftsysteme', 3, 6, 0, 3, '0,0,0,0,0,0,0,0,100,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (3, 'Titanveredelung', 0, 0, 0, 2, '0,0,0,0,0,0,0,0,80,0,0,0,0,0,0,0,0,0,', 'Mit der Erforschung von Titanveredelung kann ein Titanveredler gebaut werden, in dem Platin und Titan zu einer sehr harten, grÃ¼nlichen Legierung namens Adamatium verschmolzen werden.\r\nIm groÃŸen Krieg wurden die Panzerungen aller Raumschiffe aus Xentronium bedampftem Adamatium gefertigt. Heute benutzt man fÃ¼r die Panzerungen zwar nur noch das wesentlich hÃ¤rtere Xentronium, aber Adamatium wird weiterhin im Schiffs- und GebÃ¤udebau dort verwendet, wo Titan zu instabil, aber Xentronium wiederum zu aufwendig ist. AuÃŸerdem ist es ein beliebter Werkstoff fÃ¼r LuxusmÃ¶bel geworden.\r\n', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (4, 'Antimateriereaktoren', 5, 10, 0, 20, '0,0,0,0,0,0,0,0,600,0,0,80,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (5, 'Fortgeschrittene Partikelphysik', 8, 0, 0, 30, '0,0,0,0,0,0,0,0,300,0,0,50,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (6, 'Fortgeschrittene Computer', 0, 0, 0, 2, '0,0,0,0,0,0,0,0,50,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (7, 'Kreuzer-Konstruktion', 18, 0, 0, 12, '0,0,0,0,0,0,0,0,60,0,0,10,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (8, 'Isochips', 6, 3, 0, 7, '0,0,0,0,0,0,0,0,200,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (9, 'Xentroniumherstellung', 3, 0, 0, 25, '0,0,0,0,0,0,0,0,150,0,0,0,0,0,0,0,0,0,', 'Xentroniumherstellung ermöglicht die Herstellung der härtesten bekannten Legierung: Xentronium. Es wird entweder in verbesserten Erzverarbeitern direkt aus den Roherzen gewonnen oder mithilfe eines komplexen Syntheseverfahrens in Adamatiumverdelern synthetisiert. In ihnen wird in speziellen Titan-Platin Röhren ein Adamatium - Silizium  Gemisch zur Reaktion gebracht. Am Ende liegt dann das rotbraune Xentronium vor, das wohl teuerste, aber auch wichtigste Bauelement in den Weiten des Alls.', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (10, 'Xentroniumverarbeitung', 9, 0, 0, 15, '0,0,0,0,0,0,0,0,200,0,0,20,0,0,0,0,0,0,', 'Diese Forschung ermöglicht es, dass nach seiner Herstellung noch recht spröde Xentronium so zu verstärken, dass völlig neue Gebäude und Schiffe gebaut werden können.', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (11, 'Thermalbohrer', 9, 0, 0, 16, '0,0,0,0,0,0,0,0,80,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (12, 'Orbitale Verteidigung', 1, 0, 0, 16, '0,0,0,0,0,0,0,0,180,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (13, 'GTM-2 Interceptor', 6, 3, 15, 12, '0,0,0,0,0,0,0,0,160,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (15, 'Jaeger-Konstruktion', 1, 0, 0, 12, '0,0,0,0,0,0,0,0,160,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (16, 'GTM-13 Helios', 48, 50, 0, 140, '0,0,0,0,0,0,0,0,2500,0,0,200,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (17, 'Strahlgeschuetze', 5, 0, 0, 42, '0,0,0,0,0,0,0,0,600,0,0,80,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (18, 'Tritonfrachter', 1, 32, 0, 2, '0,0,0,0,0,0,0,0,60,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (19, 'Mjolmir-Geschuetze', 17, 12, 0, 18, '0,0,0,0,0,0,0,0,400,0,0,40,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (20, 'Disruptoren', 23, 0, 0, 16, '0,0,0,0,0,0,0,0,270,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (21, 'Fortgeschrittene Raumkampftaktiken', 1, 0, 0, 6, '0,0,0,0,0,0,0,0,40,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (23, 'Elektronische Kriegsfuehrung', 21, 0, 0, 8, '0,0,0,0,0,0,0,0,60,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (25, 'Unterirdische Komplexe', 10, 0, 0, 16, '0,0,0,0,0,0,0,0,240,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (26, 'Autarkie', 25, 0, 0, 24, '0,0,0,0,0,0,0,0,360,0,0,40,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (27, 'Erzverarbeitungskomplex', 25, 0, 0, 24, '0,0,0,0,0,0,0,0,360,0,0,60,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (28, 'Multifusionskern', 25, 0, 0, 36, '0,0,0,0,0,0,0,0,420,0,0,90,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (29, 'Mittelstreckensensoren', 6, 32, 0, 16, '0,0,0,0,0,0,0,0,90,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (30, 'Langstreckensensoren	', 29, 8, 0, 32, '0,0,0,0,0,0,0,0,280,0,0,30,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (31, 'Korvetten-Konstruktion', 17, 33, 0, 24, '0,0,0,0,0,0,0,0,350,0,0,50,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (32, 'Dockanlage', 2, 0, 0, 2, '0,0,0,0,0,0,0,0,40,0,0,0,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (33, 'Ganymed-Station', 10, 7, 0, 36, '0,0,0,0,0,0,0,0,700,0,0,80,0,0,0,0,0,0,', '[color=red]Mit Erforschung dieser Technologie verlieren sie den Schutz durch die GCP und k&ouml;nnen sowohl angreifen als auch angegriffen werden[/color]', 0, 1, 'drop_noob');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (34, 'Schwere Kreuzer', 17, 33, 0, 18, '0,0,0,0,0,0,0,0,250,0,0,40,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (36, 'Schildsysteme', 23, 0, 0, 100, '0,0,0,0,0,0,0,0,800,0,0,100,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (42, 'GTM-4 Hornet', 3, 15, 0, 14, '0,0,0,0,0,0,0,0,500,0,0,0,0,0,0,0,0,0,0,0', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (43, 'Traegerkonstruktion', 15, 34, 0, 120, '0,0,0,0,0,0,0,0,900,0,0,60,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (44, 'Shivanische Energieerzeugung', 45, 0, 0, 160, '0,0,0,0,0,0,0,0,2500,0,0,400,0,0,0,10,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (45, 'Grundlangen Shivanischer Technologie', 5, 0, 0, 90, '0,0,0,0,0,0,0,0,700,0,0,150,0,0,0,1,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (48, 'GTM-12 Cyclops', 4, 21, 0, 28, '0,0,0,0,0,0,0,0,1600,0,0,80,0,0,0,0,0,0,0,0', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (50, 'High-Tech Raketen', 43, 48, 0, 60, '0,0,0,0,0,0,0,0,900,0,0,80,0,0,0,0,0,0,0,0', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (56, 'Verbesserte JÃ¤ger', 65, 36, 0, 120, '0,0,0,0,0,0,0,0,0,0,0,300,0,0,0,0,0,0,', 'Mit Hilfe dieser Technik kÃ¤nnen ihre Ingenieure verbesserte Jï¿½ger konstruieren', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (63, 'GroÃŸraumtransporter', 7, 10, 0, 40, '0,0,0,0,0,0,0,0,300,0,0,40,0,0,0,0,0,0,', '', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (64, 'Grundlagenforschung: Fortschrittlicher Schiffbau', 43, 0, 0, 120, '0,0,0,0,0,0,0,0,1500,0,0,120,0,0,0,0,0,0,', 'Mit dieser Technologie ist es mÃ¶glich, auch die letzte Bastion der Schiffbaukunst zu erforschen: die ZerstÃ¶rerkonstruktion.', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (65, 'ZerstÃ¶rerkonstruktion', 64, 31, 0, 120, '0,0,0,0,0,0,0,0,2000,0,0,250,0,0,0,0,0,0,', 'Die Basistechnik zum ZerstÃ¶rerbau', 0, 1, '');
INSERT INTO `forschungen` (`id`, `name`, `req1`, `req2`, `req3`, `time`, `costs`, `descrip`, `race`, `visibility`, `flags`) VALUES (73, 'Verbesserte Fusionsreaktoren', 1, 25, 0, 20, '0,0,0,0,0,0,0,0,300,0,0,40,0,0,0,0,0,0,', 'Die Technik erlaubt es Terranern einen verbesserten Fusionsreaktor als unterirdisches Gebäude zu bauen', 1, 1, '');
        