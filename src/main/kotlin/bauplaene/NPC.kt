package bauplaene

import study.coco.project_code.Save


open class NPC(

    val name: String,
    lebenspunkte: Int,
    val schaden: Int,
    val scanInfo: String,
    val kampfScan: String,
    val letzeWorte: String,
    val ansprechbareNamen: List<String>,
    val unterhaltung: Map<Int, Dialog>,
    val besitztümer: MutableList<Item>,
    val angriffWarscheinlichkeit: Int = 34,
    val chargeWarscheinlichkeit: Int = 33,
    val trapWarscheinlichkeit: Int = 33,
    val chargeMultiplier: Int = 3,
) {

    //Dialog und Antworten wurden teils mit Chat-GPT gemacht, weil ich data classes noch nicht ganz verstehe.
    data class Dialog(
        val seineAntwort: String,
        val antwortMoeglichkeiten: List<Antworten>,
    )

    data class Antworten(
        val antwortText: String,
        val optionsZahl: Int,
        val bedingung: ((Save) -> Boolean) = { true }
    )

    var leben: Int = lebenspunkte
        private set(value) {
            if (value <= 0) {
                lebendig = false
                field = 0
            }
            field = value
        }
    var currentDialog: Int = 0
        internal set
    var anfangsDialog: Int = 0
        internal set
    var activDialog: Boolean = false
        internal set
    var activeFight: Boolean = false
        internal set
    var lebendig: Boolean = true
        internal set
    var startCharge: Boolean = false
        internal set
    var startTrap: Boolean = false
        internal set
    var currentTrap: Boolean = false
        internal set


    open fun unterhaltung(save: Save, dialogZahl: Int): String {

        if (!lebendig) {
            currentDialog = anfangsDialog //bugfix
            return Text.get(OrtText.ERRORS, "tod_gespräch")
        }

        var sichbarkeitsCheck: List<Antworten>?

        if (!activDialog) {
            activDialog = true
            currentDialog = anfangsDialog
        } else {
            val dialogZahhlIndex = dialogZahl - 1
            try {
                sichbarkeitsCheck = unterhaltung[currentDialog]!!.antwortMoeglichkeiten.filter { it.bedingung(save) }
                currentDialog = sichbarkeitsCheck[dialogZahhlIndex].optionsZahl
            } catch (e: Exception) {
                return Text.get(OrtText.ERRORS, "falsche_antwort")
            }
            if (currentDialog >= 100) {
                val extras: String? = dialogEvents(save)
                activDialog = false
                return dialogAusgabe(listOf(unterhaltung[currentDialog]!!.seineAntwort)) + (extras ?: "") + Text.get(
                    OrtText.NPC,
                    "gespräch_ende"
                )
            }
        }
        val extras: String? = dialogEvents(save)
        sichbarkeitsCheck = unterhaltung[currentDialog]!!.antwortMoeglichkeiten.filter { it.bedingung(save) }
        return dialogAusgabe(listOf(unterhaltung[currentDialog]!!.seineAntwort) + sichbarkeitsCheck.map { it.antwortText }) + (extras
            ?: "")
    }

    open fun dialogEvents(save: Save): String? {
        return null
    }

    open fun dialogAusgabe(dialoge: List<String>): String {
        val ausgabe: MutableList<String> = mutableListOf()
        var dialogAdd: Int = 0
        for (d in dialoge) {
            if (dialogAdd == 0) {
                ausgabe.add("$name: \"$d\"\n")
            } else {
                ausgabe.add("$dialogAdd: $d")
            }
            dialogAdd++
        }
        return ausgabe.joinToString("\n")
    }

    internal fun gibItem(save: Save, item: Item): String {
        if (besitztümer.contains(item)) {
            save.spieler.inventar.add(item)
            besitztümer.remove(item)
            return Text.get(OrtText.ACTIONS, "default_take", item.name) + save.firstItemCheck()
        }
        return "Weird eigentlich solltest du diesen Text nicht sehen können... Fehler 0002 oder so"
    }

    open fun scan(save: Save): String {
        return scanInfo
    }

    open fun kampf(save: Save, verb: String): String {
        if (!lebendig) {
            return Text.get(OrtText.ERRORS, "tod_kampf")
        }
        if (!activeFight) {
            activeFight = true
            return Text.get(OrtText.NPC, "kampf_start", name) + Text.get(OrtText.NPC, "kampf_optionen")
        }

        if (save.spieler.inventar.contains(save.scanner) && (verb == "scan" || verb == "info")) return kampfScan

        var aktion: Int = 0
        try {
            aktion = verb.toInt()
        } catch (e: Exception) {
            return Text.get(OrtText.ERRORS, "falsche_antwort")
        }

        if (aktion !in 1..4) return Text.get(OrtText.NPC, "kampf_error", name) + Text.get(OrtText.NPC, "kampf_optionen")

        val returnString = mutableListOf<String>()

        val gegnerAttack = wichAttack(
            listOf(
                angriffWarscheinlichkeit,
                chargeWarscheinlichkeit,
                trapWarscheinlichkeit
            )
        )

        when (aktion) {
            1 -> {
                if (gegnerAttack == "trap") {
                    returnString.add(Text.get(OrtText.NPC, "falle_laufen", name))
                    save.spieler.changeHealth(save, save.spieler.schaden, true)
                        ?.let { return returnString.joinToString("\n") + "\n" + it }
                } else {
                    if (currentTrap) {
                        returnString.add(
                            Text.get(OrtText.NPC, "normal_angriff", name) + Text.get(
                                OrtText.NPC,
                                "alte_falle"
                            )
                        )
                        save.spieler.changeHealth(save, save.spieler.schaden, true)
                            ?.let { return returnString.joinToString("\n") + "\n" + it }
                        currentTrap = false
                    } else {
                        returnString.add(Text.get(OrtText.NPC, "normal_angriff", name))
                        leben -= save.spieler.schaden
                        returnString.add(Text.get(OrtText.NPC, "gegner_leben", name, leben))
                    }
                    if (lebendig) {
                        when (gegnerAttack) {
                            "attack" -> {
                                returnString.add(Text.get(OrtText.NPC, "gegner_angriff", name))
                                save.spieler.changeHealth(save, schaden, true)
                                    ?.let { return returnString.joinToString("\n") + "\n" + it }
                            }

                            "charge" -> {
                                returnString.add(Text.get(OrtText.NPC, "gegner_aufladen", name))
                                save.spieler.changeHealth(save, schaden * chargeMultiplier, true)
                                    ?.let { return returnString.joinToString("\n") + "\n" + it }
                            }
                        }
                    }
                }
            }

            2 -> {
                if (save.spieler.chargeCooldown == 0) {
                    if (currentTrap || gegnerAttack == "trap") {
                        leben -= save.spieler.schaden * (save.spieler.chargeMultiplyer - 1)
                        currentTrap = false
                        returnString.add(
                            Text.get(OrtText.NPC, "aufladen", name) + Text.get(
                                OrtText.NPC,
                                "charge_falle",
                                name
                            )
                        )
                    } else {
                        returnString.add(Text.get(OrtText.NPC, "aufladen", name))
                        leben -= save.spieler.schaden * save.spieler.chargeMultiplyer
                    }
                    if (lebendig) {
                        returnString.add(Text.get(OrtText.NPC, "gegner_leben", name, leben))
                        save.spieler.chargeCooldown = save.spieler.anfangsChargeCooldown
                        returnString.add(Text.get(OrtText.NPC, "aufladen_aufladen", save.spieler.anfangsChargeCooldown))
                        when (gegnerAttack) {
                            "attack" -> {
                                returnString.add(Text.get(OrtText.NPC, "gegner_angriff", name))
                                save.spieler.changeHealth(save, schaden, true)
                                    ?.let { return returnString.joinToString("\n") + "\n" + it }
                            }

                            "charge" -> {
                                returnString.add(Text.get(OrtText.NPC, "gegner_aufladen", name))
                                save.spieler.changeHealth(save, schaden * chargeMultiplier, true)
                                    ?.let { return returnString.joinToString("\n") + "\n" + it }
                            }
                        }
                    }
                } else {
                    when (gegnerAttack) {
                        "charge" -> startCharge = true
                        "trap" -> startTrap = true
                        "startCharge" -> startCharge = false
                        "startTrap" -> startTrap = false
                    }
                    returnString.add(Text.get(OrtText.NPC, "aufladen_fail", save.spieler.chargeCooldown))
                    returnString.add(Text.get(OrtText.NPC, "neu_runde") + Text.get(OrtText.NPC, "kampf_optionen"))
                    return returnString.joinToString("\n")
                }
            }

            3 -> {
                when (gegnerAttack) {
                    "attack" -> returnString.add(Text.get(OrtText.NPC, "block", name))
                    "charge" -> {
                        returnString.add(Text.get(OrtText.NPC, "charge_block", name))
                        save.spieler.changeHealth(save, schaden, true)
                            ?.let { return returnString.joinToString("\n") + "\n" + it }
                    }

                    "trap" -> {
                        currentTrap = true
                        returnString.add(Text.get(OrtText.NPC, "block_falle", name))
                    }

                    else -> returnString.add(Text.get(OrtText.NPC, "block_nicht"))
                }
            }

            4 -> {
                activeFight = false
                return Text.get(OrtText.NPC, "weg_rennen")
            }
        }

        if (!lebendig) {
            returnString.add(sterbeEreignis(save))
        } else {
            if (gegnerAttack == "startCharge" || gegnerAttack == "startTrap") {
                returnString.add(Text.get(OrtText.NPC, "vorbereiten", name))
            }
            returnString.add(Text.get(OrtText.NPC, "my_leben", save.spieler.leben))
            returnString.add(Text.get(OrtText.NPC, "neu_runde") + Text.get(OrtText.NPC, "kampf_optionen"))
        }
        save.spieler.chargeCooldown--
        return returnString.joinToString("\n")
    }


    private fun wichAttack(gegnerwarscheinlichkeit: List<Int>): String {
        if (startCharge) {
            startCharge = false
            return "charge"
        }
        if (startTrap) {
            startTrap = false
            return "trap"
        }
        var maxWarscheinlichkeit: Int = 0
        for (w in gegnerwarscheinlichkeit) {
            maxWarscheinlichkeit += w
        }
        var rightNumber = (1..maxWarscheinlichkeit).random()
        var attakReinfolge = 1
        for (w in gegnerwarscheinlichkeit) {
            if (rightNumber <= w) {
                when (attakReinfolge) {
                    1 -> return "attack"
                    2 -> {
                        startCharge = true
                        return "startCharge"
                    }

                    3 -> {
                        if (currentTrap) return wichAttack(gegnerwarscheinlichkeit)
                        startTrap = true
                        return "startTrap"
                    }
                }
            }
            rightNumber -= w
            attakReinfolge++
        }
        //failsafe
        return "attack"
    }

    open fun sterbeEreignis(save: Save): String {
        activeFight = false
        val returnString = mutableListOf<String>()
        returnString.add(Text.get(OrtText.NPC, "letzte_worte", name, letzeWorte))

        save.spieler.changeHealth(save, save.spieler.startLeben / 3, false)
        returnString.add(Text.get(OrtText.NPC, "heilen", save.spieler.leben))

        if (besitztümer.isNotEmpty()) {
            returnString.add(Text.get(OrtText.NPC, "item_drop", name))
            for (i in besitztümer) {
                save.spieler.inventar.add(i)
                returnString.add("-${i.name}")
            }
            besitztümer.clear()
        }
        return returnString.joinToString("\n")
    }
}