drop schema if exists `eventichsBD`;
create schema if not exists `eventichsBD` default character set utf8;
use `eventichsBD`;

-- ----------------------------------------------------------------------------------------------
-- TABLE UTILISATEUR
CREATE TABLE Utilisateur (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    prénom VARCHAR(255) NOT NULL,
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
-- -----------------------------------------------------------------------------------------------
-- TABLE CATÉGORIE_ORGANISATION
CREATE TABLE Catégorie_Organisation (
   id INT AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   description VARCHAR(255),
   PRIMARY KEY (id)
);
-- -----------------------------------------------------------------------------------------------
-- TABLE ORGANISATION
CREATE TABLE Organisation (
  id int NOT NULL AUTO_INCREMENT,
  nomOrganisation VARCHAR(255) NOT NULL,
  idUtilisateur INT NOT NULL,
  catégorie_id INT NOT NULL,
  estPublic BIT NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  FOREIGN KEY (catégorie_id) REFERENCES Catégorie_Organisation(id),
  FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
-- -----------------------------------------------------------------------------------------------
-- TABLE EVENEMENT
CREATE TABLE Événement (
   id int NOT NULL AUTO_INCREMENT,
   nom VARCHAR(255) NOT NULL,
   adresse VARCHAR(255) NOT NULL,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   type VARCHAR(255) NOT NULL,
   categorie_id int NOT NULL,
   description VARCHAR(255),
   image VARCHAR(255) DEFAULT 'https://irp-cdn.multiscreensite.com/md/unsplash/dms3rep/multi/photo-1511578314322-379afb476865.jpg',
   organisation_id int NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (categorie_id) REFERENCES Catégorie(id),
   FOREIGN KEY (organisation_id) REFERENCES Organisation(id) ON DELETE CASCADE
);
-- -----------------------------------------------------------------------------------------------
-- TABLE ORGANISATION_MEMBRE
CREATE TABLE Organisations_membres (
    id_organisation int NOT NULL,
    id_utilisateur INT NOT NULL,
    PRIMARY KEY (id_organisation,id_utilisateur),
    FOREIGN KEY (id_organisation) REFERENCES Organisation(id),
    FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id)
);
-- -----------------------------------------------------------------------------------------------
-- TABLE INVITATION_ORGANISATION
CREATE TABLE Invitation_organisation (
	id int primary key auto_increment,
    idDestinataire int DEFAULT NULL, 
    idOrganisation int NOT NULL,
    jeton VARCHAR(255) DEFAULT NULL,
	status SET('généré','envoyé', 'accepté', 'refusé'),
	FOREIGN KEY (`idOrganisation`) REFERENCES Organisation(id) ON DELETE CASCADE,
    FOREIGN KEY (`idDestinataire`) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
-- -----------------------------------------------------------------------------------------------
-- TABLE INVITATION_ÉVÉNEMENT
CREATE TABLE Invitation_événement (
	id int primary key auto_increment,
    idExpediteur int NOT NULL,
    idDestinataire int DEFAULT NULL,
    idÉvénement int NOT NULL,
    jeton VARCHAR(255) DEFAULT NULL,
	status SET('généré','envoyé', 'accepté', 'refusé') DEFAULT 'généré',
	FOREIGN KEY (`idÉvénement`) REFERENCES Événement(id) ON DELETE CASCADE,
    FOREIGN KEY (`idDestinataire`) REFERENCES Utilisateur(id) ON DELETE CASCADE,
	FOREIGN KEY (`idExpediteur`) REFERENCES Utilisateur(id) ON DELETE CASCADE
);
-- -----------------------------------------------------------------------------------------------
-- TABLE UTILISATEUR_ÉVÉNEMENT
CREATE TABLE Utilisateur_événement (
   idUtilisateur int NOT NULL,
   idEvenement int NOT NULL,
   PRIMARY KEY (idUtilisateur, idEvenement),
   FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(id) ON DELETE CASCADE,
   FOREIGN KEY (idEvenement) REFERENCES Événement(id) ON DELETE CASCADE
);

