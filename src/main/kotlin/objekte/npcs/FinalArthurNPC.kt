package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class FinalArthurNPC : NPC(
    Text.get(OrtText.NAME, "badguy"),
    300,
    5,
    "",
    Text.get(OrtText.FINALARTHUR, "kampfscan"),
    "",
    listOf(),
    mapOf(
        0 to Dialog(Text.get(OrtText.FINALARTHUR, "begrüssung_fremd"), AlleAntworten.FinalArthur.anfangsAntworten),
        -1 to Dialog(Text.get(OrtText.FINALARTHUR, "begrüssung_freund"), AlleAntworten.FinalArthur.anfangsAntworten),
        -2 to Dialog(Text.get(OrtText.FINALARTHUR, "begrüssung_skeptisch"), AlleAntworten.FinalArthur.anfangsAntworten),
        1 to Dialog(Text.get(OrtText.FINALARTHUR, "hinterfragen_antwort"), AlleAntworten.FinalArthur.jaNein),
        2 to Dialog(Text.get(OrtText.FINALARTHUR, "hilfe_ablehnen"), AlleAntworten.FinalArthur.jaNein),
        3 to Dialog(Text.get(OrtText.FINALARTHUR, "keine_erklärung"), AlleAntworten.FinalArthur.geschichteFragen),
        4 to Dialog(Text.get(OrtText.FINALARTHUR, "erklärung"), AlleAntworten.FinalArthur.geschichteFragen),
        5 to Dialog(
            Text.get(OrtText.FINALARTHUR, "will_erzählen"),
            listOf(AlleAntworten.FinalArthur.dochHören, AlleAntworten.FinalArthur.sicherNichtHören)
        ),
        6 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte1"),
            listOf(AlleAntworten.FinalArthur.wieIch, AlleAntworten.FinalArthur.verständnis)
        ),
        7 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte2_1"),
            listOf(AlleAntworten.FinalArthur.willenVerdienen, AlleAntworten.FinalArthur.noHappy)
        ),
        8 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte2_2"),
            listOf(AlleAntworten.FinalArthur.willenVerdienen, AlleAntworten.FinalArthur.noHappy)
        ),
        9 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte3_1"),
            listOf(AlleAntworten.FinalArthur.nichtRichtig, AlleAntworten.FinalArthur.unkonsistent)
        ),
        10 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte3_2"),
            listOf(AlleAntworten.FinalArthur.nichtRichtig, AlleAntworten.FinalArthur.unkonsistent)
        ),
        11 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte4_1"),
            listOf(AlleAntworten.FinalArthur.ausrede, AlleAntworten.FinalArthur.nichtOpfern)
        ),
        12 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte4_2"),
            listOf(AlleAntworten.FinalArthur.ausrede, AlleAntworten.FinalArthur.nichtOpfern)
        ),
        13 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte5_1"),
            listOf(AlleAntworten.FinalArthur.krank, AlleAntworten.FinalArthur.arbeit)
        ),
        14 to Dialog(
            Text.get(OrtText.FINALARTHUR, "geschichte5_2"),
            listOf(AlleAntworten.FinalArthur.krank, AlleAntworten.FinalArthur.arbeit)
        ),
        15 to Dialog(Text.get(OrtText.FINALARTHUR, "geschichte6_1"), AlleAntworten.FinalArthur.falscheSachen),
        16 to Dialog(Text.get(OrtText.FINALARTHUR, "geschichte6_2"), AlleAntworten.FinalArthur.falscheSachen),
        17 to Dialog(Text.get(OrtText.FINALARTHUR, "ignorant"), AlleAntworten.FinalArthur.falscheSachen),
        18 to Dialog(Text.get(OrtText.FINALARTHUR, "kein_glück_antwort"), AlleAntworten.FinalArthur.falscheSachen),
        19 to Dialog(Text.get(OrtText.FINALARTHUR, "kinder_antwort"), AlleAntworten.FinalArthur.falscheSachen),
        20 to Dialog(Text.get(OrtText.FINALARTHUR, "freie_antwort"), AlleAntworten.FinalArthur.falscheSachen),
        21 to Dialog(Text.get(OrtText.FINALARTHUR, "scam_antwort"), AlleAntworten.FinalArthur.falscheSachen),
        22 to Dialog(Text.get(OrtText.FINALARTHUR, "drug_antwort"), AlleAntworten.FinalArthur.falscheSachen),
        23 to Dialog(Text.get(OrtText.FINALARTHUR, "schuldgefühl_antwort"), AlleAntworten.FinalArthur.falscheSachen),
        24 to Dialog(Text.get(OrtText.FINALARTHUR, "lost_memory_antwort"), AlleAntworten.FinalArthur.falscheSachen),
        25 to Dialog(Text.get(OrtText.FINALARTHUR, "final_start"), listOf()),

        -3 to Dialog(Text.get(OrtText.FINALARTHUR, "letzte_worte"), AlleAntworten.FinalArthur.finalEnden),

        100 to Dialog(Text.get(OrtText.STORYFEEDS, "ende_1"), listOf()),
        101 to Dialog(Text.get(OrtText.STORYFEEDS, "ende_2"), listOf()),
        102 to Dialog(Text.get(OrtText.STORYFEEDS, "ende_3"), listOf()),
        103 to Dialog(Text.get(OrtText.STORYFEEDS, "ende_4"), listOf()),
    ),
    mutableListOf(),
    40,
    40,
    25
) {
    var trauerDenia: Boolean = true
        private set
    var brutalAnzeige: Boolean = false
        private set
    var chipBrokenAnzeige: Boolean = false
        private set
    var scamDAnzeige: Boolean = false
        private set
    var addictAnzeige: Boolean = false
        private set
    var schuldgefühlAnzeige: Boolean = false
        private set
    var erinnerungAnzeige: Boolean = false
        private set

    private var finale: Boolean = false

    override fun unterhaltung(save: Save, dialogZahl: Int): String {
        if (finale) {
            save.nochLaufen = false
            var sichbarkeitsCheck: List<Antworten>?
            val dialogZahhlIndex = dialogZahl - 1
            sichbarkeitsCheck = unterhaltung[currentDialog]!!.antwortMoeglichkeiten.filter { it.bedingung(save) }
            currentDialog = sichbarkeitsCheck[dialogZahhlIndex].optionsZahl
            return unterhaltung[currentDialog]!!.seineAntwort
        }
        return super.unterhaltung(save, dialogZahl)
    }

    override fun dialogAusgabe(dialoge: List<String>): String {
        if (currentDialog == -3) {
            val ausgabe: MutableList<String> = mutableListOf()
            var dialogAdd: Int = 0
            for (d in dialoge) {
                if (dialogAdd == 0) {
                    ausgabe.add("$name: \"$d\"\n")
                    ausgabe.add(Text.get(OrtText.FINALARTHUR, "finale_choice"))
                } else {
                    ausgabe.add("$dialogAdd: $d")
                }
                dialogAdd++
            }
            return ausgabe.joinToString("\n")
        }
        return super.dialogAusgabe(dialoge)
    }

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            18 -> trauerDenia = false
            19 -> brutalAnzeige = false
            20 -> chipBrokenAnzeige = false
            21 -> scamDAnzeige = false
            22 -> addictAnzeige = false
            23 -> schuldgefühlAnzeige = false
            24 -> erinnerungAnzeige = false
            25 -> {
                anfangsDialog = -3
                activDialog = false
                return kampf(save, "0")
            }

            -3 -> finale = true
        }
        return null
    }

    override fun kampf(save: Save, verb: String): String {
        if (verb == "4") return Text.get(OrtText.FINALARTHUR, "keine_flucht")
        return super.kampf(save, verb)
    }

    internal fun brutalSwitch() {
        brutalAnzeige = true
    }

    internal fun chipBrokenSwitch() {
        chipBrokenAnzeige = true
    }

    fun scamSwitch() {
        scamDAnzeige = true
    }

    internal fun addictSwitch() {
        addictAnzeige = true
    }

    fun schuldGefühlSwitch() {
        schuldgefühlAnzeige = true
    }

    fun erinnerungSwitch() {
        erinnerungAnzeige = true
    }

    override fun sterbeEreignis(save: Save): String {
        lebendig = true //bugfix
        activeFight = false
        return "${Text.get(OrtText.FINALARTHUR, "besiegt")}\n${unterhaltung(save, 0)}"
    }
}