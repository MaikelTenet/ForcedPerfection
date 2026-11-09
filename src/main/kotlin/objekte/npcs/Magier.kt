package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class Magier(save: Save, val schockSchaden: Int = 5) : NPC(
    Text.get(OrtText.NAME, "magier"),
    50,
    15,
    Text.get(OrtText.MAGIER, "magierscan"),
    Text.get(OrtText.MAGIER, "magierkampfscan"),
    Text.get(OrtText.MAGIER, "magierfinal"),
    listOf("magier"),
    mapOf(
        0 to Dialog(
            Text.get(OrtText.MAGIER, "magierfirst"),
            listOf(AlleAntworten.Magier.annehmen, AlleAntworten.Magier.ablehnen, AlleAntworten.Magier.passwort)
        ),
        1 to Dialog(
            Text.get(OrtText.MAGIER, "spielen_annehmen"),
            listOf(AlleAntworten.Magier.regelnNachfrage, AlleAntworten.Magier.spielNachfrage)
        ),
        2 to Dialog(
            Text.get(OrtText.MAGIER, "regeln_erklären"),
            listOf(AlleAntworten.Magier.belohnung) + AlleAntworten.Magier.startOption
        ),
        3 to Dialog(
            Text.get(OrtText.MAGIER, "spiel_erklären"),
            listOf(AlleAntworten.Magier.belohnung) + AlleAntworten.Magier.startOption
        ),
        4 to Dialog(Text.get(OrtText.MAGIER, "wins"), AlleAntworten.Magier.startOption),
        5 to Dialog(
            Text.get(OrtText.MAGIER, "filler"),
            listOf(AlleAntworten.Magier.nochEinKampf, AlleAntworten.Magier.aufhören)
        ),

        100 to Dialog(Text.get(OrtText.MAGIER, "angst"), listOf()),
        101 to Dialog(Text.get(OrtText.MAGIER, "gekorbt"), listOf()),
        102 to Dialog(Text.get(OrtText.MAGIER, "aufhören"), listOf()),

        -1 to Dialog(
            Text.get(OrtText.MAGIER, "rückkerhr"),
            listOf(
                AlleAntworten.Magier.starten,
                AlleAntworten.Magier.passwort,
                AlleAntworten.Magier.regelnNachfrage,
                AlleAntworten.Magier.nichtStarten,
                AlleAntworten.Magier.gesprächStarten
            )
        ),
        6 to Dialog(Text.get(OrtText.MAGIER, "freude"), AlleAntworten.Magier.persönlicheFragen),
        103 to Dialog(Text.get(OrtText.MAGIER, "verrückt"), listOf()),
        104 to Dialog(Text.get(OrtText.MAGIER, "kein_ripoff"), listOf()),
        105 to Dialog(Text.get(OrtText.MAGIER, "arthur_freunde"), listOf()),

        7 to Dialog(
            Text.get(OrtText.MAGIER, "passwort_geben"),
            AlleAntworten.Magier.startOption + listOf(
                AlleAntworten.Magier.regelnNachfrage,
                AlleAntworten.Magier.spielNachfrage
            )
        ),


        ),
    mutableListOf(save.gold, save.notiz),
    10,
    10,
    80
) {

    override fun unterhaltung(save: Save, dialogZahl: Int): String {
        val normalAusgabe: String = super.unterhaltung(save, dialogZahl)
        return if (currentDialog == 5) gambling(save, "")
        else normalAusgabe
    }

    var glorboErklärt = false
        private set
    private var passwortRunde: Boolean = false
    var passwortBekommen: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            2, 3 -> glorboErklärt = true
            5 -> anfangsDialog = -1
            7 -> passwortRunde = true
            101, 102 -> passwortRunde = false
        }
        return null
    }

    private var deineZahl: Int = 0
    private var seineZahl: Int = 0

    var currentGambling: Boolean = false
        private set

    fun gambling(save: Save, input: String): String {
        if (currentGambling == false) {
            currentGambling = true
            var zahl1: Int?
            var zahl2: Int?
            do {
                deineZahl = 0
                zahl1 = kartenGenerator()
                zahl2 = kartenGenerator()
            } while ((zahl1 + zahl2) > 21)
            deineZahl += zahl1 + zahl2
            return if (deineZahl == 21) {
                return dialogAusgabe(
                    listOf(
                        Text.get(OrtText.MAGIER, "direkt_win", zahl1, zahl2) + gamblingBeenden(
                            save,
                            true
                        )
                    )
                ) + Text.get(OrtText.MAGIER, "nochmal")
            } else dialogAusgabe(
                listOf(
                    Text.get(
                        OrtText.MAGIER,
                        "first_gambling",
                        zahl1,
                        zahl2,
                        deineZahl
                    )
                )
            ) + Text.get(OrtText.MAGIER, "gambling_option")
        }

        when (input) {
            "1" -> {
                val zahlenAdder: Int = kartenGenerator()
                deineZahl += zahlenAdder
                if (deineZahl == 21) {
                    return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.MAGIER, "21_win", zahlenAdder) + gamblingBeenden(
                                save,
                                true
                            )
                        )
                    ) + Text.get(OrtText.MAGIER, "nochmal")
                }
                if (deineZahl > 21) {
                    return dialogAusgabe(
                        listOf(
                            Text.get(
                                OrtText.MAGIER,
                                "überschießen",
                                zahlenAdder,
                                deineZahl
                            ) + gamblingBeenden(save, false)
                        )
                    ) + Text.get(OrtText.MAGIER, "nochmal")
                }
                return dialogAusgabe(
                    listOf(
                        Text.get(
                            OrtText.MAGIER,
                            "new_round_question",
                            zahlenAdder,
                            deineZahl
                        )
                    )
                ) + Text.get(OrtText.MAGIER, "gambling_option")
            }

            "2" -> {
                val returnString = mutableListOf<String>(Text.get(OrtText.MAGIER, "sein_start"))
                do {
                    val karten = kartenGenerator()
                    seineZahl += karten
                    returnString.add(Text.get(OrtText.MAGIER, "sein_zug", karten))
                } while (deineZahl >= seineZahl)
                if (seineZahl > 21) {
                    returnString.add(Text.get(OrtText.MAGIER, "er_überzogen", seineZahl))
                    returnString.add(gamblingBeenden(save, true))
                } else {
                    returnString.add(Text.get(OrtText.MAGIER, "endstand", seineZahl, deineZahl))
                    returnString.add(gamblingBeenden(save, false))
                }
                return dialogAusgabe(listOf(returnString.joinToString("\n"))) + Text.get(OrtText.MAGIER, "nochmal")
            }

            else -> return Text.get(OrtText.ERRORS, "falsche_antwort")
        }
    }

    private fun kartenGenerator(): Int {
        val deck: MutableList<Int> = mutableListOf()
        for (n in 1..11) deck.add(n)
        for (n in 5..11) deck.add(n)
        deck.add(10)
        deck.add(10)
        return deck.random()
    }

    private fun gamblingBeenden(save: Save, gewonnenFrageZeichen: Boolean = false): String {
        currentGambling = false
        deineZahl = 0
        seineZahl = 0
        if (gewonnenFrageZeichen) {
            save.spieler.inventar.add(save.gold)
            if (passwortRunde) {
                passwortRunde = false
                passwortBekommen = true
                gibItem(save, save.notiz)
                return Text.get(OrtText.MAGIER, "passwort_bekommen")
            }
            return Text.get(OrtText.MAGIER, "gold_bekommen")
        } else {
            return if (save.spieler.leben > schockSchaden) {
                save.spieler.changeHealth(save, schockSchaden, true)
                Text.get(OrtText.MAGIER, "strom_schocken", save.spieler.leben)
            } else Text.get(OrtText.MAGIER, "kein_strom_schocken")
        }
    }
}