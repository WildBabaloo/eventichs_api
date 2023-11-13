-- Insertion Utilisateur
INSERT INTO `eventichsbd`.`utilisateur` (`nom`, `courriel`, `motDePasse`) VALUES ('joe', 'joegmail', '123');
INSERT INTO `eventichsbd`.`utilisateur` (`nom`, `courriel`, `motDePasse`) VALUES ('sam', 'samgmail', '123');

-- Insertion Catégorie
INSERT INTO `eventichsbd`.`catégorie` (`nom`, `description`) VALUES ('allo', 'allo');

-- Insertion Organisation
INSERT INTO `eventichsbd`.`organisation` (`idUtilisateur`, `catégorie_id`) VALUES ('1', '1');

-- Insertion Organisations_membres

-- Insertion Événement
INSERT INTO `eventichsbd`.`événement` (`nom`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `photo`, `organisation_id`) VALUES ('Soiré', '2020-10-10', '2020-10-10', 'soiré', '1', 'aaa', 'aaa', '1');
INSERT INTO `eventichsbd`.`événement` (`nom`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `photo`, `organisation_id`) VALUES ('Soiré2', '2020-10-10', '2020-10-10', 'soiré', '1', 'aaa', 'aaa', '1');
INSERT INTO `eventichsbd`.`événement` (`nom`, `dateDebut`, `dateFin`, `type`, `categorie_id`, `description`, `photo`, `organisation_id`) VALUES ('Soiré3', '2020-10-10', '2020-10-10', 'soiré', '1', 'aaa', 'aaa', '1');

-- Insertion Membres_événement

-- Insertion Invitation
INSERT INTO `eventichsbd`.`invitation_événement` (`idExpediteur`, `idDestinataire`, `idÉvénement`, `status`) VALUES ('1', '1', '1', 'généré');
INSERT INTO `eventichsbd`.`invitation_organisation` (`idDestinataire`, `idOrganisation`, `status`) VALUES ('1', '1', 'généré');
