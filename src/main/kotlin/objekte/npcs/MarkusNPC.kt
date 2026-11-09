package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import objekte.items.SubstanzItem
import study.coco.project_code.Save

class MarkusNPC(save: Save) : NPC(
    Text.get(OrtText.NAME, "junkie"),
    50,
    6,
    Text.get(OrtText.MARKUS, "scan"),
    Text.get(OrtText.MARKUS, "kampfscan"),
    Text.get(OrtText.MARKUS, "final", save.arthurNPC.name),
    listOf("markus", "marcus", "farmer", "farm", "junkie"),
    mapOf(
        0 to Dialog(
            Text.get(OrtText.MARKUS, "starter"),
            listOf(AlleAntworten.Markus.kennDich, AlleAntworten.Markus.kennDichNicht, AlleAntworten.Markus.chipFrage)
        ),
        1 to Dialog(
            Text.get(OrtText.MARKUS, "dialog1"),
            listOf(AlleAntworten.Markus.wasScanner, AlleAntworten.Markus.keinScannerHaben)
        ),
        2 to Dialog(
            Text.get(OrtText.MARKUS, "dialog2"),
            listOf(AlleAntworten.Markus.wasScanner, AlleAntworten.Markus.keinScannerHaben)
        ),
        3 to Dialog(
            Text.get(OrtText.MARKUS, "dia_Scanner_erklär"),
            listOf(
                AlleAntworten.Markus.keinScannerHaben,
                AlleAntworten.Markus.werBinIch,
                AlleAntworten.Markus.nichtArthur
            )
        ),
        4 to Dialog(
            Text.get(OrtText.MARKUS, "die_kein_scan_bauen"),
            listOf(AlleAntworten.Markus.chipFrage, AlleAntworten.Markus.nichtArthur)
        ),
        5 to Dialog(
            Text.get(OrtText.MARKUS, "dia_chip_erklär"),
            listOf(AlleAntworten.Markus.questAnnehmen, AlleAntworten.Markus.nichtArthur)
        ),
        6 to Dialog(
            Text.get(OrtText.MARKUS, "dia_wer_ich"),
            listOf(AlleAntworten.Markus.anlügen, AlleAntworten.Markus.chipFrage, AlleAntworten.Markus.nichtArthur)
        ),
        7 to Dialog(
            Text.get(OrtText.MARKUS, "dia_kein_chip"),
            listOf(AlleAntworten.Markus.annehmen, AlleAntworten.Markus.ablehnen)
        ),
        100 to Dialog(Text.get(OrtText.MARKUS, "die_trippen"), listOf()),
        101 to Dialog(Text.get(OrtText.MARKUS, "dia_quest_geben"), listOf()),
        102 to Dialog(Text.get(OrtText.MARKUS, "die_quest_ablehnen"), listOf()),
        103 to Dialog(Text.get(OrtText.MARKUS, "dia_stressen"), listOf()),
        104 to Dialog(Text.get(OrtText.MARKUS, "dia_abgeben"), listOf()),
        105 to Dialog(Text.get(OrtText.MARKUS, "dia_wer_bin_ich_high"), listOf()),
        106 to Dialog(Text.get(OrtText.MARKUS, "dia_ncith_arhtur_ahigh"), listOf()),
        107 to Dialog(Text.get(OrtText.MARKUS, "dia_schönheitsop"), listOf()),
        108 to Dialog(Text.get(OrtText.MARKUS, "code_bei_mir"), listOf()),
        -1 to Dialog(
            Text.get(OrtText.MARKUS, "dia_wo_pflanze"),
            listOf(AlleAntworten.Markus.abgeben, AlleAntworten.Markus.wasScanner, AlleAntworten.Markus.besorgen)
        ),
        -2 to Dialog(
            Text.get(OrtText.MARKUS, "dia_falsch_person"),
            listOf(
                AlleAntworten.Markus.codeHerkunft,
                AlleAntworten.Markus.werBinIch2,
                AlleAntworten.Markus.nichtArthur2,
                AlleAntworten.Markus.schönheit
            )
        ),
    ),
    mutableListOf(save.knopf, save.markusesBrief),
    30,
    60,
    10,
    2,
) {
    var scannerInfo: Boolean = false
        private set
    var arthurInfo: Boolean = false
        private set
    var chipInfo: Boolean = false
        private set
    var questAnnehmen: Boolean = false
        private set
    var erIstHigh: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            3 -> {
                scannerInfo = true
                if (arthurInfo) save.arthurNPC.scannerHerausfinden()
            }

            4, 6 -> {
                arthurInfo = true
                if (scannerInfo) save.arthurNPC.scannerHerausfinden()
            }

            5 -> chipInfo = true
            101 -> {
                save.finalArthur.addictSwitch()
                anfangsDialog = -1
                questAnnehmen = true
            }

            103 -> save.finalArthur.erinnerungSwitch()
            104 -> {
                save.spieler.inventar.removeAll { it is SubstanzItem }
                save.knopf.openSafe(save)
                erIstHigh = true
                anfangsDialog = -2
                save.wachsendePflanze.ausWachsen()
                return "\nDer Tresor öffnet sich"
            }

            105 -> {
                scannerInfo = true
                arthurInfo = true
                save.arthurNPC.scannerHerausfinden()
            }
        }
        return null
    }

    fun briefGelesen(save: Save) {
        questAnnehmen = true
        scannerInfo = true
        arthurInfo = true
        chipInfo = true
        save.arthurNPC.scannerHerausfinden()
    }

    override fun kampf(save: Save, verb: String): String {
        return if (erIstHigh) dialogAusgabe(listOf(Text.get(OrtText.MARKUS, "kein_kampf"))) + Text.get(
            OrtText.NPC,
            "gespräch_ende"
        )
        else super.kampf(save, verb)
    }
}