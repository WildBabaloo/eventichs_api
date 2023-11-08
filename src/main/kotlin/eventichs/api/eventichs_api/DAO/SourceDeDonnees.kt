package eventichs.api.eventichs_api.DAO

import eventichs.api.eventichs_api.Modele.Evenement
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class SourceDeDonnees {
    companion object{


        val dateFormat = SimpleDateFormat("dd-MM-yyyy")

        val evenements = mutableListOf(
            Evenement(0, "Marriage Turc", dateFormat.parse("22-10-2023"),dateFormat.parse("23-10-2023"), "123 rue Chemin, Montréal, QC","Turkish Airlines","Divers",
                "----_________-----__-_-----------___________----------____---____-----------"),
            Evenement(1, "Conférence sur les mathématiques ésotériques",  dateFormat.parse("23-10-2023"), dateFormat.parse("24-10-2023"),"1928 rue Rue, Montréal, QC", "Monster Inc.","Divers",
                "----_________-----__-_-----------___________----------____---____-----------"),
            Evenement(2, "Festival des grilles d'égouts",  dateFormat.parse("23-12-2023"), dateFormat.parse("24-12-2023"), "8765 rue Principale, Chambly, QC","Sainte-Julie Org.","Divers",
                "----_________-----__-_-----------___________----------____---____-----------"),
            Evenement(3, "Assemblée générale des lutins", dateFormat.parse("23-10-2023"), dateFormat.parse("23-11-2023"), "1928 rue Rue, Montréal, QC","Lutins Inc.","Divers",
                "----_________-----__-_-----------___________----------____---____-----------"),
            Evenement(4, "Fin du monde", dateFormat.parse("31-10-2023"), dateFormat.parse("1-11-2023"), "123 rue Chemin, Montréal, QC","Mayas et Co.","Divers",
                "----_________-----__-_-----------___________----------____---____-----------"),
        )
    }
}