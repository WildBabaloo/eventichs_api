package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Exceptions.ConflitAvecUneRessourceExistanteException
import eventichs.api.eventichs_api.Exceptions.RessourceInexistanteException
import eventichs.api.eventichs_api.Mapper.InvitationOrganisationMapper
import eventichs.api.eventichs_api.Mapper.OrganisationMapper
import eventichs.api.eventichs_api.Mapper.OrganisationMembresMapper
import eventichs.api.eventichs_api.Mapper.UtilisateurMapper
import eventichs.api.eventichs_api.Modèle.InvitationOrganisation
import eventichs.api.eventichs_api.Modèle.Organisation
import eventichs.api.eventichs_api.Modèle.OrganisationMembres
import eventichs.api.eventichs_api.Modèle.Utilisateur
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject
import org.springframework.stereotype.Repository

@Repository
class InvitationOrganisationDAOImplMémoire(val db: JdbcTemplate): InvitationOrganisationDAO {
    override fun chercherTous(): List<InvitationOrganisation> =
        db.query(
            "select * from Invitation_organisation left join utilisateur on Invitation_organisation.idDestinataire = utilisateur.id join organisation on Invitation_organisation.idOrganisation = organisation.id",
            InvitationOrganisationMapper()
        )

    override fun chercherParID(id: Int): InvitationOrganisation? {
        var invitationOrganisation: InvitationOrganisation?
        try {
            invitationOrganisation = db.queryForObject(
                "select * from Invitation_organisation as invitation left join utilisateur on idDestinataire = utilisateur.id join organisation on idOrganisation = organisation.id where invitation.id = $id",
                InvitationOrganisationMapper()
            )
        } catch (e: EmptyResultDataAccessException) {
            throw RessourceInexistanteException("L'invitation $id à une organisation n'est pas inscrit au service")
        }
        return invitationOrganisation
    }

    override fun ajouter(element: InvitationOrganisation): InvitationOrganisation? {
        //Insertion d'une invitation ayant un jeton null et un status envoyé seuleument si aucune inviation existe allant de cette organisation à ce destinateire.
        val nbrDeLigneInséré: Int =
            db.update("insert into invitation_organisation (idDestinataire, idOrganisation, jeton, status) select ${element.Utilisateur?.id}, ${element.Organisation.id}, null, 'envoyé' from dual where not exists (select * from invitation_organisation where idDestinataire=${element.Utilisateur?.id} and idOrganisation=${element.Organisation.id} LIMIT 1)")

        if (nbrDeLigneInséré != 1) {
            throw ConflitAvecUneRessourceExistanteException(
                "Il y existe déjà une invitation à l'organisation ${element.Organisation.nomOrganisation} " +
                        "assigné au participant ${element.Utilisateur?.prénom} ${element.Utilisateur?.nom} inscrit au service"
            )
        }

        //Query pour obtenir l'id de l'invitation créé.
        val id = db.queryForObject<Int>("SELECT @lid:=LAST_INSERT_ID(); ")

        //Retour de l'invitation créé grace à son id obtenu plus haut
        return chercherParID(id)
    }

    override fun modifier(element: InvitationOrganisation): InvitationOrganisation? {
        return super.modifier(element)
    }

    override fun supprimerParID(id: Int): InvitationOrganisation? {
        val invitation = chercherParID(id)
        db.update("delete from Invitation_organisation where id = $id")
        return invitation
    }

    override fun chercherParOrganisation(idOrganisation: Int): List<InvitationOrganisation> {

        //Lance une exception si l'organisation n'existe pas
        try {
            db.queryForObject("select * from Organisation where id = $idOrganisation", OrganisationMapper())
        } catch (e : EmptyResultDataAccessException) {
            throw RessourceInexistanteException("L'organisation $idOrganisation n'existe pas")
        }

        return db.query("select * from Invitation_organisation as invitation " +
                "left join utilisateur on invitation.idDestinataire = utilisateur.id " +
            "join organisation on invitation.idOrganisation = organisation.id " +
            "where invitation.idOrganisation = $idOrganisation", InvitationOrganisationMapper())
    }

    override fun chercherParParticipant(idParticipant: Int): List<InvitationOrganisation> {

        //Lance une exception si le participant n'existe pas
        try {
            db.queryForObject("select * from Utilisateur where id = $idParticipant", UtilisateurMapper())
        } catch (e : EmptyResultDataAccessException) {
            throw RessourceInexistanteException("Le participant $idParticipant n'existe pas")
        }

        return db.query("select * from Invitation_organisation as invitation " +
                "join utilisateur on invitation.idDestinataire = utilisateur.id " +
                "join organisation on invitation.idOrganisation = organisation.id " +
                "where invitation.idDestinataire = $idParticipant", InvitationOrganisationMapper())
    }

    override fun changerStatus(idInvitationOrganisation: Int, status: String): InvitationOrganisation? {
        db.update("update Invitation_organisation set `status` = ? where id = ?",status, idInvitationOrganisation)
        return chercherParID(idInvitationOrganisation)
    }


    //Insertion d'une invitation ayant aucun destinataire, le bon id Organisation et le status 'envoyé'
    //Un select pour obtenir l'id de l'invitation dernièrement créé.
    //Un update sur l'invitation dernièrement créé grace à l'id pour y ajouter un jeton de 8 charactères alléatoire.
    override fun crééJeton(idOrganisation: Int): InvitationOrganisation? {
        db.update(
            "INSERT INTO Invitation_organisation (idDestinataire, idOrganisation, status) VALUES (null, $idOrganisation,'généré'); ")
        val id = db.queryForObject<Int>("SELECT @lid:=LAST_INSERT_ID(); ")
        db.update("update invitation_organisation set jeton=concat( " +
                    "substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand($id)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed:=round(rand(@seed)*4294967296))*36+1, 1)," +
                    "  substring('ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789', rand(@seed)*36+1, 1)" +
                    ")" +
                    "where id=$id;")
        return chercherParID(id)
    }

    override fun saisirJeton(jeton: String, utilisateur: Utilisateur): InvitationOrganisation? {
        //Lance une exception si le participant n'existe pas
        try {
            db.queryForObject("select * from Utilisateur where id = ${utilisateur.id}", UtilisateurMapper())
        } catch (e : EmptyResultDataAccessException) {
            throw RessourceInexistanteException("Le participant ${utilisateur.id} n'existe pas")
        }

        //Lance une exception si le jeton saisi n'est pas inscrit dans une invitation
        var invitation : InvitationOrganisation? = null
        try {
            invitation = db.queryForObject("select * from Invitation_organisation as invitation left join utilisateur on idDestinataire = utilisateur.id join organisation on idOrganisation = organisation.id where jeton = ?", InvitationOrganisationMapper(),jeton)
        } catch (e : EmptyResultDataAccessException) {
            throw RessourceInexistanteException("Le jeton ${jeton} à déjà été saisi et n'est donc plus valide")
        }

        //Lance une exception si le jeton à déjà été saisi
        if (invitation?.status != "généré") {throw RessourceInexistanteException("L'invitation ${invitation?.id} à une organisation a délà été assigné à un participant")}

        //Lance une exception si le participant est déjà membre de cette organisation
        try {
            db.queryForObject("select * from Organisations_membres where Organisations_membres.id_organisation = ${invitation.Organisation.id} and Organisations_membres.id_utilisateur = ${utilisateur.id}", OrganisationMembresMapper())
        } catch (e : EmptyResultDataAccessException) {
            //Assignation de l'invitation au participant et changement du status à 'accepté'
            db.update("update Invitation_organisation set idDestinataire = ${utilisateur.id}, status = 'accepté' where id = ${invitation.id}")

            //Ajout du participant comme membre de l'organisation
            db.update("insert into Organisations_membres (id_organisation,id_utilisateur)values (${invitation.Organisation.id} ,${utilisateur?.id})")

            return chercherParID(invitation!!.id)
        }

        throw ConflitAvecUneRessourceExistanteException("Le participant ${utilisateur.id} est déjà membre de cette l'organisation ${invitation.Organisation.id}")
    }
}