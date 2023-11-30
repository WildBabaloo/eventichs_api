-- Insertion Utilisateur
INSERT INTO `eventichsBD`.`Utilisateur` (`nom`, `prénom`, `courriel`, `motDePasse`) VALUES ('joe','tremblay', 'joegmail@gmail.com', 'joe123');
INSERT INTO `eventichsBD`.`Utilisateur` (`nom`, `prénom`, `courriel`, `motDePasse`) VALUES ('sam','drolet', 'samgmail@gmail.com', 'password123');
INSERT INTO `eventichsBD`.`Utilisateur` (`nom`, `prénom`, `courriel`, `motDePasse`) VALUES ('joe2','drolet', 'joe2gmail@gmail.com', 'password12356789');
-- Insertion Catégorie
INSERT INTO `eventichsBD`.`Catégorie` (`nom`, `description`) VALUES ('Party', 'Party!! Woohoo');
INSERT INTO `eventichsBD`.`Catégorie` (`nom`, `description`) VALUES ('Funérailles', 'Party!! Woohoo');
INSERT INTO `eventichsBD`.`Catégorie` (`nom`, `description`) VALUES ('Rencontre Grave', 'Party!! Woohoo');

-- Insertion Catégorie
INSERT INTO `eventichsBD`.`Catégorie_Organisation` (`nom`, `description`) VALUES ('OBNL', 'Party!! Woohoo');

-- Insertion Organisation
INSERT INTO `eventichsBD`.`Organisation` (`idUtilisateur`, `catégorie_id`,`nomOrganisation`) VALUES ('1', '1','Illuminati');
INSERT INTO `eventichsBD`.`Organisation` (`idUtilisateur`, `catégorie_id`,`nomOrganisation`) VALUES ('1', '1','Rosemont');

-- Insertion Événement
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`,  `organisation_id`) VALUES ('Soiré','134 avenue Parc', '2023-10-10', '2020-10-11', 'public', '1', 'soirée très cool', '1');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('Soiré2', '23 rue Waverly','2023-11-10', '2020-11-10', 'public', '1', 'Soirée très plate', '1');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('Soiré3', '45 13e Avenue','2023-12-10', '2020-12-12', 'private', '1', 'Les détails de cette soirée ne sont connus que par le président du madagascar', '1');

-- Insertion Membres_événement
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('1', '1');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('1', '3');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('2', '2');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('2', '3');

-- Insertion Invitation
INSERT INTO `eventichsBD`.`Invitation_événement` (`idExpediteur`, `idDestinataire`, `idÉvénement`, `status`) VALUES ('1', '1', '1', 'envoyé');
INSERT INTO `eventichsBD`.`Invitation_organisation` (`idDestinataire`, `idOrganisation`, `status`) VALUES ('1', '1', 'envoyé');
INSERT INTO `eventichsBD`.`Invitation_organisation` (`idOrganisation`, `jeton`, `status`) VALUES ('1', '9EIUYTBB', 'généré');

-- Insertion Organisations_membres
INSERT INTO `eventichsBD`.`Organisations_membres` (`id_organisation`, `id_utilisateur`) VALUES ('1', '1');

