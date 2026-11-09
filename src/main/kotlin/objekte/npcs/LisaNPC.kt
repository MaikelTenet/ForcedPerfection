package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class LisaNPC : NPC(
    Text.get(OrtText.NAME, "lisa"),
    50,
    6,
    Text.get(OrtText.LISA, "scan"),
    Text.get(OrtText.LISA, "kampfscan"),
    Text.get(OrtText.LISA, "verloren"),
    listOf("gisa", "mädchen", "mobber", "kleines", "kleines mädchen", "girl", "lisa"),
    mapOf(
        0 to Dialog(
            Text.get(OrtText.LISA, "begrüssung"),
            listOf(
                AlleAntworten.Lisa.duWirstGros,
                AlleAntworten.Lisa.ichBinCool,
                AlleAntworten.Lisa.anfangKilian,
                AlleAntworten.Lisa.alsKleinBeleidigen
            )
        ),
        1 to Dialog(
            Text.get(OrtText.LISA, "ich_will_groß_sein"),
            listOf(AlleAntworten.Lisa.seiStark, AlleAntworten.Lisa.gehWachsen)
        ),
        2 to Dialog(
            Text.get(OrtText.LISA, "befreundet_werden"),
            listOf(AlleAntworten.Lisa.tretten, AlleAntworten.Lisa.alter, AlleAntworten.Lisa.freundschaftAnbieten)
        ),
        3 to Dialog(
            Text.get(OrtText.LISA, "er_ist_loser"),
            listOf(AlleAntworten.Lisa.gutReden, AlleAntworten.Lisa.switchup)
        ),
        4 to Dialog(
            Text.get(OrtText.LISA, "loser2"),
            listOf(AlleAntworten.Lisa.gutReden2, AlleAntworten.Lisa.switchup, AlleAntworten.Lisa.aufgeben)
        ),
        5 to Dialog(
            Text.get(OrtText.LISA, "loser3"),
            listOf(AlleAntworten.Lisa.gutReden3, AlleAntworten.Lisa.switchup, AlleAntworten.Lisa.aufgeben)
        ),
        6 to Dialog(
            Text.get(OrtText.LISA, "loser4"),
            listOf(AlleAntworten.Lisa.gutReden4, AlleAntworten.Lisa.switchup, AlleAntworten.Lisa.aufgeben)
        ),


        100 to Dialog(Text.get(OrtText.LISA, "schleimer"), listOf()),
        101 to Dialog(Text.get(OrtText.LISA, "standart_verabschiedung"), listOf()),
        102 to Dialog(Text.get(OrtText.LISA, "kilian_niemals_befreundet_sein"), listOf()),
        103 to Dialog(Text.get(OrtText.LISA, "nie_aufhören"), listOf()),
        104 to Dialog(Text.get(OrtText.LISA, "spaß_hihihi"), listOf()),
        105 to Dialog(Text.get(OrtText.LISA, "befreunden"), listOf()),
        106 to Dialog(Text.get(OrtText.LISA, "niemals_befreunden"), listOf()),
        107 to Dialog(Text.get(OrtText.LISA, "begründung"), listOf()),
        108 to Dialog(Text.get(OrtText.LISA, "zu_weit"), listOf()),
        109 to Dialog(Text.get(OrtText.LISA, "du_alt"), listOf()),
        110 to Dialog(Text.get(OrtText.LISA, "juhuuu"), listOf()),
        111 to Dialog(Text.get(OrtText.LISA, "kompliment"), listOf()),
        112 to Dialog(Text.get(OrtText.LISA, "schmerz_hoffen"), listOf()),
        113 to Dialog(Text.get(OrtText.LISA, "geheimnis_sichern"), listOf()),
        -1 to Dialog(
            Text.get(OrtText.LISA, "wiedersehen"),
            listOf(
                AlleAntworten.Lisa.whymobbing,
                AlleAntworten.Lisa.freundschaftAnbieten,
                AlleAntworten.Lisa.why_klein,
                AlleAntworten.Lisa.why_kind
            )
        ),
        -2 to Dialog(
            Text.get(OrtText.LISA, "gute_idee"),
            listOf(AlleAntworten.Lisa.gutFürDich, AlleAntworten.Lisa.fiesTipps)
        ),
        -3 to Dialog(
            Text.get(OrtText.LISA, "kilian_verletzt"),
            listOf(AlleAntworten.Lisa.drohen, AlleAntworten.Lisa.anlügen)
        ),
    ),
    mutableListOf(),
    30,
    10,
    60
) {
    var befreundetMitKilian: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            2 -> {
                anfangsDialog = -1
                save.finalArthur.brutalSwitch()
            }

            105 -> {
                befreundetMitKilian = true
                anfangsDialog = -2
                save.kilianNPC.anfangsDialog = -4
                save.finalArthur.brutalSwitch()
            }

            107 -> save.finalArthur.brutalSwitch()
        }
        return null
    }
}