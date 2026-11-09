package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class PolizistNPC(save: Save) : NPC(
    Text.get(OrtText.NAME, "polizei"),
    50,
    12,
    Text.get(OrtText.POLIZIST, "scan"),
    Text.get(OrtText.POLIZIST, "kampfscan"),
    Text.get(OrtText.POLIZIST, "final"),
    listOf("polizist", "polizei", "cop", "police", "bulle", "soldat"),
    mapOf(
        0 to Dialog(
            Text.get(OrtText.POLIZIST, "begrüssung"),
            listOf(
                AlleAntworten.Polizist.nachWeedFragen,
                AlleAntworten.Polizist.bewerben,
                AlleAntworten.Polizist.sündenGestehen,
                AlleAntworten.Polizist.warumDuHier,
                AlleAntworten.Polizist.abhauen
            )
        ),
        -1 to Dialog(
            Text.get(OrtText.POLIZIST, "begrüssung_freund"),
            listOf(
                AlleAntworten.Polizist.nachWeedFragenPolize,
                AlleAntworten.Polizist.pistoleFragen,
                AlleAntworten.Polizist.sündenGestehen,
                AlleAntworten.Polizist.warumDuHier,
                AlleAntworten.Polizist.abhauen
            )
        ),

        1 to Dialog(Text.get(OrtText.POLIZIST, "sünden_annehmen"), AlleAntworten.Polizist.verbrechenBegangen),
        2 to Dialog(Text.get(OrtText.POLIZIST, "kanone_verlangen"), AlleAntworten.Polizist.kanoneGebenGründen),

        100 to Dialog(Text.get(OrtText.POLIZIST, "verabschieden"), listOf()),
        101 to Dialog(Text.get(OrtText.POLIZIST, "bewerbungsverfahren"), listOf()),
        102 to Dialog(Text.get(OrtText.POLIZIST, "keine_sünden"), listOf()),
        103 to Dialog(Text.get(OrtText.POLIZIST, "hund_lösung"), listOf()),
        104 to Dialog(Text.get(OrtText.POLIZIST, "ko_lösung"), listOf()),
        105 to Dialog(Text.get(OrtText.POLIZIST, "lügner_lösung"), listOf()),
        106 to Dialog(Text.get(OrtText.POLIZIST, "weed_lösung"), listOf()),
        107 to Dialog(Text.get(OrtText.POLIZIST, "schlagen_lösung"), listOf()),
        108 to Dialog(Text.get(OrtText.POLIZIST, "mein_sinn"), listOf()),
        109 to Dialog(Text.get(OrtText.POLIZIST, "weed_zivi"), listOf()),
        110 to Dialog(Text.get(OrtText.POLIZIST, "weed_poliz"), listOf()),
        111 to Dialog(Text.get(OrtText.POLIZIST, "selbstverteidigung_antwort"), listOf()),
        112 to Dialog(Text.get(OrtText.POLIZIST, "gefeuert_antwort"), listOf()),
        113 to Dialog(Text.get(OrtText.POLIZIST, "erschießen_antwort"), listOf()),
        114 to Dialog(Text.get(OrtText.POLIZIST, "gesetz_antwort"), listOf()),
        115 to Dialog(Text.get(OrtText.POLIZIST, "mitleid_antwort"), listOf()),
        116 to Dialog(Text.get(OrtText.POLIZIST, "vierte_wand_antwort"), listOf()),
        117 to Dialog(Text.get(OrtText.POLIZIST, "gaslight_antwort"), listOf()),
        118 to Dialog(Text.get(OrtText.POLIZIST, "reality_antwort"), listOf()),
    ),
    mutableListOf(save.kanone),
    20,
    50,
    30
) {
    val startLeben = leben

    var selbstverteidigungCheck: Boolean = false
        private set
    var gefeuertCheck: Boolean = false
        private set
    var erschießenCheck: Boolean = false
        private set
    var gesetzCheck: Boolean = false
        private set
    var mitleidCheck: Boolean = false
        private set
    var viertewandCheck: Boolean = false
        private set
    var gaslightCheck: Boolean = false
        private set
    var realityCheck: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            111 -> selbstverteidigungCheck = true
            112 -> gefeuertCheck = true
            113 -> erschießenCheck = true
            114 -> gesetzCheck = true
            115 -> {
                val itemAusgabe = gibItem(save, save.kanone)
                mitleidCheck = true
                return "\n" + itemAusgabe
            }

            116 -> viertewandCheck = true
            117 -> gaslightCheck = true
            118 -> realityCheck = true
        }
        return null
    }
}