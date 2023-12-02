-- Insertion Utilisateur
INSERT INTO `eventichsBD`.`Utilisateur` (`nom`, `prénom`, `courriel`, `motDePasse`) VALUES ('joe','tremblay', 'joegmail@gmail.com', 'joe123');
INSERT INTO `eventichsBD`.`Utilisateur` (`nom`, `prénom`, `courriel`, `motDePasse`) VALUES ('sam','drolet', 'samgmail@gmail.com', 'password123');

-- Insertion Catégorie
INSERT INTO `eventichsBD`.`Catégorie` (`nom`, `description`) VALUES ('allo', 'allo');

-- Insertion Organisation
INSERT INTO `eventichsBD`.`Organisation` (`idUtilisateur`, `catégorie_id`,`nomOrganisation`) VALUES ('1', '1','Illuminati');

-- Insertion Organisations_membres
INSERT INTO `eventichsBD`.`organisations_membres` (`id_organisation`, `id_utilisateur`) VALUES ('1', '1');

-- Insertion Événement
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `photo`, `organisation_id`) VALUES ('Soiré','134 avenue Parc', '2020-10-10', '2020-10-10', 'public', '1', 'aaa', 'aaa', '1');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `photo`, `organisation_id`) VALUES ('Soiré2', '23 rue Waverly','2020-10-10', '2020-10-10', 'public', '1', 'aaa', 'aaa', '1');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `photo`, `organisation_id`) VALUES ('Soiré3', '45 13e Avenue','2020-10-10', '2020-10-10', 'private', '1', 'aaa', 'aaa', '1');

-- Insertion Membres_événement
INSERT INTO `eventichsbd`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('1', '1');
INSERT INTO `eventichsbd`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('1', '3');
INSERT INTO `eventichsbd`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('2', '2');
INSERT INTO `eventichsbd`.`Utilisateur_événement` (`idUtilisateur`, `idEvenement`) VALUES ('2', '3');
-- Insertion Invitation
INSERT INTO `eventichsBD`.`Invitation_événement` (`idExpediteur`, `idDestinataire`, `idÉvénement`, `status`) VALUES ('1', '1', '1', 'envoyé');
INSERT INTO `eventichsBD`.`Invitation_organisation` (`idDestinataire`, `idOrganisation`, `status`) VALUES ('1', '1', 'envoyé');
INSERT INTO `eventichsBD`.`Invitation_organisation` (`idOrganisation`, `jeton`, `status`) VALUES ('1', '9EIUYTBB', 'généré');

