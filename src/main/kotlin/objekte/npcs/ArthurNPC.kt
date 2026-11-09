package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class ArthurNPC(save: Save) : NPC(
    Text.get(OrtText.NAME, "badguy"),
    300,
    5,
    Text.get(OrtText.ARTHUR, "scan"),
    "",
    "",
    listOf("arthur", "arthour", "artuhr", "michael", "typ", "guy", "person", "man", "mann"),
    mapOf(
        0 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog0"),
            listOf(
                AlleAntworten.Arthur.anstarren,
                AlleAntworten.Arthur.entschuldigen,
                AlleAntworten.Arthur.scannerNehmen1,
                AlleAntworten.Arthur.markusBrief
            )
        ),
        1 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog1"),
            listOf(AlleAntworten.Arthur.konfront, AlleAntworten.Arthur.befreunden, AlleAntworten.Arthur.verwirrt)
        ),
        2 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog2"),
            listOf(AlleAntworten.Arthur.erIstSeltsam, AlleAntworten.Arthur.warumLange)
        ),
        3 to Dialog(Text.get(OrtText.ARTHUR, "dialog3"), AlleAntworten.Arthur.freierWilleDiscovery),
        4 to Dialog(Text.get(OrtText.ARTHUR, "dialog4"), AlleAntworten.Arthur.freierWilleDiscovery),
        6 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog6"),
            listOf(
                AlleAntworten.Arthur.infoMarkus,
                AlleAntworten.Arthur.infoOma,
                AlleAntworten.Arthur.sandraInfo,
                AlleAntworten.Arthur.stalkerScanner
            )
        ),
        7 to Dialog(Text.get(OrtText.ARTHUR, "dialog7"), AlleAntworten.Arthur.scannerAntworten),
        12 to Dialog(Text.get(OrtText.ARTHUR, "old_people_rant"), AlleAntworten.Arthur.scannerAntworten),
        15 to Dialog(Text.get(OrtText.ARTHUR, "sandra_interesse"), AlleAntworten.Arthur.scannerAntworten),
        8 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog8"),
            listOf(AlleAntworten.Arthur.toThePoint, AlleAntworten.Arthur.aufgeben)
        ),
        9 to Dialog(Text.get(OrtText.ARTHUR, "dialog9"), AlleAntworten.Arthur.scannerAntworten),
        10 to Dialog(Text.get(OrtText.ARTHUR, "dialog10"), AlleAntworten.Arthur.scannerAntworten),
        11 to Dialog(Text.get(OrtText.ARTHUR, "dialog11"), AlleAntworten.Arthur.scannerAntworten),
        14 to Dialog(Text.get(OrtText.ARTHUR, "massieren_ablehnen"), AlleAntworten.Arthur.scannerAntworten),
        13 to Dialog(Text.get(OrtText.ARTHUR, "buddy_ablehnen"), AlleAntworten.Arthur.scannerAntworten),

        100 to Dialog(Text.get(OrtText.ARTHUR, "dialog100"), listOf()),
        101 to Dialog(Text.get(OrtText.ARTHUR, "dialog101"), listOf()),
        102 to Dialog(Text.get(OrtText.ARTHUR, "freier_wille_antwort"), listOf()),
        103 to Dialog(Text.get(OrtText.ARTHUR, "dialog103"), listOf()),
        104 to Dialog(Text.get(OrtText.ARTHUR, "dialog104"), listOf()),
        105 to Dialog(Text.get(OrtText.ARTHUR, "oma_ko_reaktion"), listOf()),
        107 to Dialog(Text.get(OrtText.ARTHUR, "dialog107"), listOf()),
        108 to Dialog(Text.get(OrtText.ARTHUR, "dialog108"), listOf()),
        109 to Dialog(Text.get(OrtText.ARTHUR, "dialog109"), listOf()),
        110 to Dialog(Text.get(OrtText.ARTHUR, "dialog110"), listOf()),
        111 to Dialog(Text.get(OrtText.ARTHUR, "dialog111"), listOf()),
        112 to Dialog(Text.get(OrtText.ARTHUR, "dialog112"), listOf()),
        113 to Dialog(Text.get(OrtText.ARTHUR, "dialog113"), listOf()),
        -1 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog-1"),
            listOf(
                AlleAntworten.Arthur.erIstGemein,
                AlleAntworten.Arthur.entschuldigen,
                AlleAntworten.Arthur.scannerNehmen1,
                AlleAntworten.Arthur.markusBrief
            )
        ),
        -2 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog-2"),
            listOf(
                AlleAntworten.Arthur.scannerNehmen2,
                AlleAntworten.Arthur.markusBrief,
                AlleAntworten.Arthur.chipHinterfragen,
                AlleAntworten.Arthur.omaTod,
                AlleAntworten.Arthur.planLos,
                AlleAntworten.Arthur.nerven
            )
        ),
        -3 to Dialog(
            Text.get(OrtText.ARTHUR, "dialog-3"),
            listOf(
                AlleAntworten.Arthur.scannerHinterfragen,
                AlleAntworten.Arthur.scannerAufgeben,
                AlleAntworten.Arthur.chipHinterfragen,
                AlleAntworten.Arthur.markusBrief
            )
        )
    ), mutableListOf(save.scanner),
    50,
    30,
    20
) {

    var firstdialogMarkus: Boolean = false
        private set
    var tutorialAbgeschlossen: Boolean = false
        private set
    var kampfAbgelehnt: Boolean = false
        private set
    var questAbgelehnt: Boolean = false
        private set
    var massierenAbgelehnt: Boolean = false
        private set
    var buddyAblehnen: Boolean = false
        private set
    var markusAbgelehnt: Boolean = false
        private set
    var scannerKnowlege: Boolean = false
        private set
    var scannerAbgegeben: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            1 -> firstdialogMarkus = true
            10 -> kampfAbgelehnt = true
            11 -> questAbgelehnt = true
            14 -> massierenAbgelehnt = true
            13 -> buddyAblehnen = true
            101 -> anfangsDialog = -1
            102 -> {
                tutorialAbgeschlossen = true
                save.finalArthur.anfangsDialog = -1
                anfangsDialog = -2
            }

            110 -> {
                scannerAbgegeben = true
                save.finalArthur.anfangsDialog = -2
                anfangsDialog = -3
                gibItem(save, save.scanner)
                return "\n${save.scanner.takeDialog}"
            }

            113 -> {
                markusAbgelehnt = true

            }
        }
        return null
    }

    override fun kampf(save: Save, verb: String): String {
        return if (tutorialAbgeschlossen) dialogAusgabe(listOf(Text.get(OrtText.ARTHUR, "attak_know"))) + Text.get(
            OrtText.NPC,
            "gespräch_ende"
        )
        else dialogAusgabe(listOf(Text.get(OrtText.ARTHUR, "attak_unkwno"))) + Text.get(OrtText.NPC, "gespräch_ende")
    }

    internal fun scannerHerausfinden() {
        scannerKnowlege = true
    }
}