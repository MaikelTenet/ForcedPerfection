package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class SandraNPC(save: Save) : NPC(
    Text.get(OrtText.NAME, "bauarbeiter"),
    300,
    10,
    Text.get(OrtText.SANDRA, "scan"),
    "",
    "",
    listOf("bauer", "bauarbeiter", "bauarbeiterin", "worker", "construction worker", "builder", "fdgchjnbv"),
    mutableMapOf(
        0 to Dialog(Text.get(OrtText.SANDRA, "begrüssung"), AlleAntworten.Sandra.anfangsAntworten),
        -1 to Dialog(Text.get(OrtText.SANDRA, "pipe_geklaut"), AlleAntworten.Sandra.anfangsAntworten),
        1 to Dialog(Text.get(OrtText.SANDRA, "bauen_antwort"), AlleAntworten.Sandra.überforderungsAntworten),
        2 to Dialog(
            Text.get(OrtText.SANDRA, "keine_hilfe"),
            listOf(
                AlleAntworten.Sandra.teammates,
                AlleAntworten.Sandra.wasBauen,
                AlleAntworten.Sandra.stess,
                AlleAntworten.Sandra.biching
            )
        ),
        3 to Dialog(Text.get(OrtText.SANDRA, "teammates_antwort"), AlleAntworten.Sandra.überforderungsAntworten),
        4 to Dialog(Text.get(OrtText.SANDRA, "seltsam_antwort"), AlleAntworten.Sandra.überforderungsAntworten),
        5 to Dialog(Text.get(OrtText.SANDRA, "freier_wille_antwort"), AlleAntworten.Sandra.überforderungsAntworten),
        6 to Dialog(
            Text.get(OrtText.SANDRA, "erinnerungen_antwort"),
            listOf(AlleAntworten.Sandra.bauenAnfrage, AlleAntworten.Sandra.bauenNachfrage)
        ),
        7 to Dialog(Text.get(OrtText.SANDRA, "beschäftigung_antwort"), AlleAntworten.Sandra.überforderungsAntworten),
        8 to Dialog(Text.get(OrtText.SANDRA, "für_dich_bauen"), AlleAntworten.Sandra.bauMöglichkeiten),
        9 to Dialog(Text.get(OrtText.SANDRA, "bauen_erinnerungen"), AlleAntworten.Sandra.bauMöglichkeiten),

        100 to Dialog(Text.get(OrtText.SANDRA, "pipe_gefallen"), listOf()),
        101 to Dialog(Text.get(OrtText.SANDRA, "nervig"), listOf()),
        102 to Dialog(Text.get(OrtText.SANDRA, "auch_bitchin"), listOf()),

        -2 to Dialog(
            Text.get(OrtText.SANDRA, "wiedersehen"),
            AlleAntworten.Sandra.bauMöglichkeiten + AlleAntworten.Sandra.überforderungNachfragen
        ),
        103 to Dialog(Text.get(OrtText.SANDRA, "enttäuscht"), listOf()),
        104 to Dialog(Text.get(OrtText.SANDRA, "seltsam_antwort"), listOf()),
        105 to Dialog(Text.get(OrtText.SANDRA, "freier_wille_antwort"), listOf()),
        106 to Dialog(Text.get(OrtText.SANDRA, "beschäftigung_antwort"), listOf()),
        107 to Dialog(Text.get(OrtText.SANDRA, "kein_buch_öffnen"), listOf()),
        10 to Dialog(
            Text.get(OrtText.SANDRA, "chip_annehmen"),
            listOf(AlleAntworten.Sandra.queastAnnehmen, AlleAntworten.Sandra.queastAblehnen)
        ),
        108 to Dialog(Text.get(OrtText.SANDRA, "annehmen_reaktion"), listOf()),
        109 to Dialog(Text.get(OrtText.SANDRA, "ablehnen_reaktion"), listOf()),

        -3 to Dialog(
            Text.get(OrtText.SANDRA, "wiedersehen"),
            listOf(
                AlleAntworten.Sandra.abgeben,
                AlleAntworten.Sandra.chipInfos,
                AlleAntworten.Sandra.scannerOrigin,
                AlleAntworten.Sandra.scannerprove,
                AlleAntworten.Sandra.codeOrigin,
                AlleAntworten.Sandra.codeProve
            ) + AlleAntworten.Sandra.überforderungNachfragen
        ),
        110 to Dialog(Text.get(OrtText.SANDRA, "woher_scanner_antwort", save.arthurNPC.name), listOf()),
        111 to Dialog(Text.get(OrtText.SANDRA, "scanner_zeigen_antwort"), listOf()),
        112 to Dialog(Text.get(OrtText.SANDRA, "woher_code_antwort"), listOf()),
        113 to Dialog(Text.get(OrtText.SANDRA, "code_zeigen_antwort"), listOf()),
        12 to Dialog(
            Text.get(OrtText.SANDRA, "chip_infos_antwort"),
            listOf(AlleAntworten.Sandra.fehlfunktion, AlleAntworten.Sandra.welcheAntworten)
        ),
        114 to Dialog(Text.get(OrtText.SANDRA, "fehlfunktion_antwort"), listOf()),
        115 to Dialog(Text.get(OrtText.SANDRA, "antworten_auf_alles"), listOf()),

        11 to Dialog(
            Text.get(OrtText.SANDRA, "zusammenbauen"),
            listOf(
                AlleAntworten.Sandra.warten,
                AlleAntworten.Sandra.warten,
                AlleAntworten.Sandra.warten,
                AlleAntworten.Sandra.warten
            )
        ),
        116 to Dialog(Text.get(OrtText.SANDRA, "fertig_gebaut"), listOf()),
        -4 to Dialog(
            Text.get(OrtText.SANDRA, "wiedersehen_nach_reparatur"),
            listOf(
                AlleAntworten.Sandra.chipOrigin,
                AlleAntworten.Sandra.verarbeitung,
                AlleAntworten.Sandra.austauschOrt
            )
        ),
        117 to Dialog(Text.get(OrtText.SANDRA, "origin_antwort"), listOf()),
        118 to Dialog(Text.get(OrtText.SANDRA, "verarbeitung_antwort"), listOf()),
        119 to Dialog(Text.get(OrtText.SANDRA, "austauschen_antwort"), listOf()),
    ),
    mutableListOf()
) {
    val realName: String = Text.get(OrtText.NAME, "bauarbeiterin_real")

    var scannerSichtung: Boolean = false
        private set
    var seltsamBeantwortet: Boolean = false
        private set
    var freierWilleBeantworten: Boolean = false
        private set
    var bescjäftigungBeantworten: Boolean = false
        private set
    var tagebuchPasswortInfo: Boolean = false
        private set
    var questAnnehmen: Boolean = false
        private set
    var questBeenden: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            4, 104 -> seltsamBeantwortet = true
            5, 105 -> freierWilleBeantworten = true
            6 -> {
                save.finalArthur.erinnerungSwitch()
                save.finalArthur.chipBrokenSwitch()
                anfangsDialog = -2
            }

            7, 106 -> bescjäftigungBeantworten = true
            107, 115, 117 -> tagebuchPasswortInfo = true
            108 -> {
                questAnnehmen = true
                anfangsDialog = -3
                save.spieler.inventar.remove(save.kaputterChip)
            }

            110 -> {
                scannerSichtung = true
                save.arthurNPC.scannerHerausfinden()
            }

            116 -> {
                questBeenden = true
                anfangsDialog = -4
                return save.reparierterChip.take(save, save.welcherRaum())
            }
        }
        return null
    }

    override fun kampf(save: Save, verb: String): String {
        if (questBeenden) return dialogAusgabe(listOf(Text.get(OrtText.SANDRA, "kein_kampf3"))) + Text.get(
            OrtText.NPC,
            "gespräch_ende"
        )
        if (questAnnehmen) return dialogAusgabe(listOf(Text.get(OrtText.SANDRA, "kein_kampf2"))) + Text.get(
            OrtText.NPC,
            "gespräch_ende"
        )
        return dialogAusgabe(listOf(Text.get(OrtText.SANDRA, "kein_kampf"))) + Text.get(OrtText.NPC, "gespräch_ende")
    }
}