drop schema if exists `eventichsBD`;
create schema if not exists `eventichsBD` default character set utf8;
use `eventichsBD`;

-- ----------------------------------------------------------------------------------------------
-- TABLE UTILISATEUR

CREATE TABLE utilisateur (
    code VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    courriel VARCHAR(255) NOT NULL UNIQUE,
    motDePasse VARCHAR(255) NOT NULL
);

-- TABLE INVITATION
CREATE TABLE Invitation (
    code VARCHAR(255) NOT NULL,
    codeExpediteur VARCHAR(255) NOT NULL,
    codeDestinataire VARCHAR(255) NOT NULL,
    codeQuoiRejoindre_id VARCHAR(255) NOT NULL,
    status SET('envoyé', 'accepté', 'refusé')
);
-- TABLE JETON
CREATE TABLE Jeton (
   id INT AUTO_INCREMENT,
   codeQuoiRejoindre VARCHAR(255) NOT NULL,
   jeton VARCHAR(255) NOT NULL,
   PRIMARY KEY (id)
);



-- -----------------------------------------------------------------------------------------------
-- TABLE EVENEMENT
CREATE TABLE Événement (
   code VARCHAR(255) NOT NULL,
   nom VARCHAR(255) NOT NULL,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   type VARCHAR(255) NOT NULL,
   categorie_id VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   photo VARCHAR(255),
   organisation_id VARCHAR(255) NOT NULL,
   PRIMARY KEY (code),
   FOREIGN KEY (categorie_id) REFERENCES Catégorie(id)
);

-- TABLE CATÉGORIE
CREATE TABLE Catégorie (
   id INT AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   PRIMARY KEY (id)
);

-- TABLE MEMBRE_ÉVÉNEMENT
CREATE TABLE Membres_événement (
   idUtilisateur VARCHAR(255) NOT NULL,
   idEvenement VARCHAR(255) NOT NULL,
   PRIMARY KEY (idUtilisateur, idEvenement),
   FOREIGN KEY (idUtilisateur) REFERENCES utilisateur(code)
   FOREIGN KEY (idEvenement) REFERENCES Événement(code)
);



-- -----------------------------------------------------------------------------------------------
-- TABLE ORGANISATION
CREATE TABLE Organisation (
  code INT NOT NULL,
  codeUtilisateur INT NOT NULL,
  catégorie_id INT NOT NULL,
  estPublic BOOL NOT NULL,
  PRIMARY KEY (code),
  FOREIGN KEY (catégorie_id) REFERENCES Catégorie(id),
  FOREIGN KEY (codeUtilisateur) REFERENCES utilisateur(code)
);

-- TABLE ORGANISATION_MEMBRE
CREATE TABLE Organisations_membres (
    code VARCHAR(255) NOT NULL,
    code_organisation INT NOT NULL,
    code_utilisateur INT NOT NULL,
    PRIMARY KEY (code),
    FOREIGN KEY (code_organisation) REFERENCES Organisation(code),
    FOREIGN KEY (code_utilisateur) REFERENCES utilisateur(code)
);
