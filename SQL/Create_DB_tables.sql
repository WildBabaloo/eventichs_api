drop schema if exists `eventichsBD`;
create schema if not exists `eventichsBD` default character set utf8;
use `eventichsBD`;

-- ----------------------------------------------------------------------------------------------
-- TABLE UTILISATEUR

CREATE TABLE utilisateur (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    courriel VARCHAR(255) NOT NULL UNIQUE,
    motDePasse VARCHAR(255) NOT NULL
);



-- -----------------------------------------------------------------------------------------------
-- TABLE CATÉGORIE
CREATE TABLE Catégorie (
   id INT AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   PRIMARY KEY (id)
);

-- TABLE EVENEMENT
CREATE TABLE Événement (
   id int NOT NULL AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   type VARCHAR(255) NOT NULL,
   categorie_id int NOT NULL,
   description VARCHAR(255),
   photo VARCHAR(255),
   organisation_id VARCHAR(255) NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (categorie_id) REFERENCES Catégorie(id)
);



-- TABLE MEMBRE_ÉVÉNEMENT
CREATE TABLE Membres_événement (
   idUtilisateur int NOT NULL,
   idEvenement int NOT NULL,
   PRIMARY KEY (idUtilisateur, idEvenement),
   FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(id),
   FOREIGN KEY (idEvenement) REFERENCES Événement(id)
);



-- -----------------------------------------------------------------------------------------------
-- TABLE ORGANISATION
CREATE TABLE Organisation (
  id int NOT NULL AUTO_INCREMENT,
  idUtilisateur INT NOT NULL,
  catégorie_id INT NOT NULL,
  estPublic BOOL NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  FOREIGN KEY (catégorie_id) REFERENCES Catégorie(id),
  FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(id)
);

-- TABLE ORGANISATION_MEMBRE
CREATE TABLE Organisations_membres (
    id_organisation int NOT NULL,
    id_utilisateur INT NOT NULL,
    PRIMARY KEY (id_organisation,id_utilisateur),
    FOREIGN KEY (id_organisation) REFERENCES Organisation(id),
    FOREIGN KEY (id_utilisateur) REFERENCES utilisateur(id)
);


-- -----------------------------------------------------------------------------------------------
-- TABLE INVITATION
CREATE TABLE Invitation (
    idExpediteur int NOT NULL, -- Qui veux deamander/Inviter à joindre?
    idDestinataire int NOT NULL, -- Le réceptionnaire de la demande/invitation. Celui qui doit l'accepter ou la refuser.
    idQuoiRejoindre int NOT NULL, -- Ce que l'expediteur cherche à joindre
    model SET('Organisation', 'Événement'), -- L'expediteur cherche à joindre une organisation ou un événement?
	status SET('envoyé', 'accepté', 'refusé'), -- L'état de la demande/invitation.
	PRIMARY KEY (idExpediteur, idDestinataire, idQuoiRejoindre, model),
	organisation_id INT AS (IF(model = 'Organisation', idQuoiRejoindre, NULL)) STORED,  -- Les 4 lignes suivantes pertmettent de guarantir l'intégrité des données.
    événement_id INT AS (IF(model = 'Événement', idQuoiRejoindre, NULL)) STORED,        -- Les colonnes organisation_id et événement_id sont remplis automatiquement et ne doive pas être rempli dans un INSERT.
    FOREIGN KEY (`organisation_id`) REFERENCES Organisation(id) ON DELETE CASCADE,
    FOREIGN KEY (`événement_id`) REFERENCES Événement(id) ON DELETE CASCADE
);

-- TABLE JETON
CREATE TABLE Jeton (
   id INT AUTO_INCREMENT,
   idQuoiRejoindre int NOT NULL, -- À quel organisation ou événement le jeton réfère.
   model SET('Organisation', 'Événement'), -- Est-ce une organisation ou un événement.
   jeton VARCHAR(255) NOT NULL,
   PRIMARY KEY (id),
   organisation_id INT AS (IF(model = 'Organisation', idQuoiRejoindre, NULL)) STORED,  -- Les 4 lignes suivantes pertmettent de guarantir l'intégrité des données.
   événement_id INT AS (IF(model = 'Événement', idQuoiRejoindre, NULL)) STORED,        -- Les colonnes organisation_id et événement_id sont remplis automatiquement et ne doive pas être rempli dans un INSERT.
   FOREIGN KEY (`organisation_id`) REFERENCES Organisation(id) ON DELETE CASCADE,
   FOREIGN KEY (`événement_id`) REFERENCES Événement(id) ON DELETE CASCADE
);
