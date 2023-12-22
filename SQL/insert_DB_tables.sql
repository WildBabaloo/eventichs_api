-- Insertion Utilisateur
INSERT INTO `eventichsBD`.`Utilisateur` (`code`, `courriel`, `nom`, `prénom`) VALUES ('auth0|656d2dbea19599c9209a4f01', 'joegmail@gmail.com', 'Tremblay', 'Joe'); -- mdp: joe_tremblay123
INSERT INTO `eventichsBD`.`Utilisateur` (`code`, `courriel`, `nom`, `prénom`) VALUES ('auth0|656d3cb6a19599c9209a6099', 'samdrolet@gmail.com', 'Drolet', 'Sam'); -- mdp: sam_drolet123
INSERT INTO `eventichsBD`.`Utilisateur` (`code`, `courriel`, `nom`, `prénom`) VALUES ('auth0|656d3d344178aefc03429343', 'priyapatel@gmail.com', 'Patel', 'Priya'); -- mdp: priya_patel123
INSERT INTO `eventichsBD`.`Utilisateur` (`code`, `courriel`, `nom`, `prénom`) VALUES ('auth0|656d3ecc4178aefc03429538', 'andrewsanchez@gmail.com', 'Sanchez', 'Andrew'); -- mdp: andrew_sanchez123
INSERT INTO `eventichsBD`.`Utilisateur` (`code`, `courriel`, `nom`, `prénom`) VALUES ('auth0|656d3f1aa19599c9209a6371', 'damienweber@gmail.com', 'Weber', 'Damien'); -- mdp: damien_weber123
INSERT INTO `eventichsBD`.`Utilisateur` (`code`, `courriel`, `nom`, `prénom`) VALUES ('auth0|656d3f8e4178aefc03429606', 'kierangreene@gmail.com', 'Greene', 'Kieran'); -- mdp: kieran_greene123

-- Insertion Catégorie
INSERT INTO `eventichsBD`.`Catégorie` (`nom`, `description`) VALUES ('Party', 'Party!! Woohoo');
INSERT INTO `eventichsBD`.`Catégorie` (`nom`, `description`) VALUES ('Funérailles', 'Party!! Woohoo');
INSERT INTO `eventichsBD`.`Catégorie` (`nom`, `description`) VALUES ('Rencontre Grave', 'Party!! Woohoo');

-- Insertion Catégorie
INSERT INTO `eventichsBD`.`Catégorie_Organisation` (`nom`, `description`) VALUES ('OBNL', 'Party!! Woohoo');
INSERT INTO `eventichsBD`.`Catégorie_Organisation` (`nom`, `description`) VALUES ('Organisation Secrète', 'Party!! Woohoo');
INSERT INTO `eventichsBD`.`Catégorie_Organisation` (`nom`, `description`) VALUES ('Marxiste', 'Travailleurs!! Woohoo');

-- Insertion Organisation
INSERT INTO `eventichsBD`.`Organisation` (`nomOrganisation`, `codeUtilisateur`, `catégorie_id`, `estPublic`) VALUES ('Rosemont', 'auth0|656d2dbea19599c9209a4f01', '2', true);
INSERT INTO `eventichsBD`.`Organisation` (`nomOrganisation`, `codeUtilisateur`, `catégorie_id`, `estPublic`) VALUES ('FBI', 'auth0|656d3cb6a19599c9209a6099', '1', false);
INSERT INTO `eventichsBD`.`Organisation` (`nomOrganisation`, `codeUtilisateur`, `catégorie_id`, `estPublic`) VALUES ('Illuminati', 'auth0|656d3f8e4178aefc03429606', '3', true);

-- Insertion Événement
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`,  `organisation_id`) VALUES ('Soiré','134 avenue Parc', '2023-10-10', '2020-10-11', 'public', '2', 'soirée très cool', '1');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('Soiré2', '23 rue Waverly','2023-11-10', '2020-11-10', 'public', '2', 'Soirée très plate', '2');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('Soiré3', '45 13e Avenue','2023-12-10', '2020-12-12', 'private', '3', 'Les hommes les plus riches du Tibet y seront présents', '3');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('Marriage Turc', '190 rue Hutcherson','2024-01-10', '2024-01-11', 'public', '1', 'Dansons!', '2');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('Rencontre de lutins', '2403 Sherbrooke','2024-01-14', '2024-01-14', 'private', '1', ' BYOL : Apportez votre propre Lutin', '1');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`,`dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('Soiré5', '23 rue Waverly','2024-01-14', '2024-01-14', 'public', '1', 'Soirée très plate', '3');
INSERT INTO `eventichsBD`.`Événement` (`nom`, `adresse`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `organisation_id`) VALUES ('La mort du Roi Mage', '1 rue de la mort','2024-01-14', '2024-01-14', 'public', '2', 'Les détails de cette soirée ne sont connus que par le président du madagascar', '1');

-- Insertion Membres_événement
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d2dbea19599c9209a4f01', '1');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d2dbea19599c9209a4f01', '2');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d2dbea19599c9209a4f01', '3');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d2dbea19599c9209a4f01', '6');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d2dbea19599c9209a4f01', '7');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3cb6a19599c9209a6099', '4');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3cb6a19599c9209a6099', '5');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3cb6a19599c9209a6099', '7');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3cb6a19599c9209a6099', '6');

INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3f8e4178aefc03429606', '7');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3f8e4178aefc03429606', '4');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3f8e4178aefc03429606', '5');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3f8e4178aefc03429606', '7');
INSERT INTO `eventichsBD`.`Utilisateur_événement` (`codeUtilisateur`, `idEvenement`) VALUES ('auth0|656d3f8e4178aefc03429606', '6');

-- Insertion Invitation
INSERT INTO `eventichsBD`.`Invitation_événement` (`codeExpediteur`, `codeDestinataire`, `idÉvénement`, `status`) VALUES ('auth0|656d3f1aa19599c9209a6371', 'auth0|656d3f8e4178aefc03429606', '1', 'envoyé');
INSERT INTO `eventichsBD`.`Invitation_organisation` (`codeDestinataire`, `idOrganisation`, `status`) VALUES ('auth0|656d3f8e4178aefc03429606', '1', 'envoyé');
INSERT INTO `eventichsBD`.`Invitation_organisation` (`idOrganisation`, `jeton`, `status`) VALUES ('1', '9EIUYTBB', 'généré');

-- Insertion Organisations_membres
INSERT INTO `eventichsBD`.`Organisations_membres` (`id_organisation`, `code_utilisateur`) VALUES ('2', 'auth0|656d3f1aa19599c9209a6371');
INSERT INTO `eventichsBD`.`Organisations_membres` (`id_organisation`, `code_utilisateur`) VALUES ('3', 'auth0|656d3f1aa19599c9209a6371');
INSERT INTO `eventichsBD`.`Organisations_membres` (`id_organisation`, `code_utilisateur`) VALUES ('2', 'auth0|656d3cb6a19599c9209a6099');
INSERT INTO `eventichsBD`.`Organisations_membres` (`id_organisation`, `code_utilisateur`) VALUES ('2', 'auth0|656d3f8e4178aefc03429606');
INSERT INTO `eventichsBD`.`Organisations_membres` (`id_organisation`, `code_utilisateur`) VALUES ('3', 'auth0|656d3f8e4178aefc03429606');

