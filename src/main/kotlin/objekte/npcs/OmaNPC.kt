package objekte.npcs

import bauplaene.NPC
import bauplaene.OrtText
import study.coco.project_code.Save

class OmaNPC : NPC(
    Text.get(OrtText.NAME, "oma"),
    10,
    1,
    Text.get(OrtText.OMA, "scan"),
    Text.get(OrtText.OMA, "kampfscan"),
    Text.get(OrtText.OMA, "final"),
    listOf("oma", "omi", "frau", "alte frau", "unc", "grandma", "grandmother"),
    mapOf(
        0 to Dialog(
            Text.get(OrtText.OMA, "dialog0"),
            listOf(AlleAntworten.Oma.hundFan, AlleAntworten.Oma.hundHater, AlleAntworten.Oma.hilfeFragen)
        ),
        1 to Dialog(
            Text.get(OrtText.OMA, "dialog1"),
            listOf(AlleAntworten.Oma.guteWelt, AlleAntworten.Oma.schlechteWelt)
        ),
        2 to Dialog(
            Text.get(OrtText.OMA, "dialog2"),
            listOf(AlleAntworten.Oma.guteWelt, AlleAntworten.Oma.schlechteWelt)
        ),
        3 to Dialog(
            Text.get(OrtText.OMA, "dialog3"),
            listOf(AlleAntworten.Oma.guteWelt, AlleAntworten.Oma.schlechteWelt)
        ),
        4 to Dialog(
            Text.get(OrtText.OMA, "dialog4"),
            listOf(AlleAntworten.Oma.jaTraining, AlleAntworten.Oma.neinTraining)
        ),
        100 to Dialog(Text.get(OrtText.OMA, "dialog100"), listOf()),
        101 to Dialog(Text.get(OrtText.OMA, "dialog101"), listOf()),
        102 to Dialog(Text.get(OrtText.OMA, "dialog102"), listOf()),
        103 to Dialog(Text.get(OrtText.OMA, "dialog103"), listOf()),
        104 to Dialog(Text.get(OrtText.OMA, "dialog104"), listOf()),
        -1 to Dialog(
            Text.get(OrtText.OMA, "dialog-1"),
            listOf(AlleAntworten.Oma.hundWollte, AlleAntworten.Oma.warWerAnders, AlleAntworten.Oma.hilfeFragen)
        ),
        -2 to Dialog(
            Text.get(OrtText.OMA, "dialog-2"),
            listOf(
                AlleAntworten.Oma.drohung1,
                AlleAntworten.Oma.drohung2,
                AlleAntworten.Oma.drohung3,
                AlleAntworten.Oma.drohung4
            )
        ),
    ), mutableListOf(),
    20,
    49,
    50
) {

    private var kampfVorschlag: Boolean = false

    var beibringenVorschlag: Boolean = false
        private set

    override fun dialogEvents(save: Save): String? {
        when (currentDialog) {
            100 -> beibringenVorschlag = true
            101, 102, 103 -> kampfVorschlag = true
        }
        return null
    }

    private var tutorialphase: Int = 0
    private var endTutorial: Boolean = false

    var omaErzähltScanner: Boolean = false
        private set


    override fun kampf(save: Save, verb: String): String {
        if (!lebendig || endTutorial) {
            return super.kampf(save, verb)
        }

        var aktion: Int = 0
        try {
            aktion = verb.toInt()
        } catch (e: Exception) {
            return Text.get(OrtText.ERRORS, "falsche_antwort")
        }

        //Das hätte ich in einen Dialog rein bauen sollen, aber jetzt ist zu spät das umzubauen!

        when (tutorialphase) {
            0 -> {
                if (kampfVorschlag) {
                    activeFight = true
                    tutorialphase = 1
                    return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "kampfstart"),
                            Text.get(OrtText.OMA, "action1"),
                        )
                    )
                }
                when (aktion) {
                    0 -> {
                        activeFight = true
                        return dialogAusgabe(
                            listOf(
                                Text.get(OrtText.OMA, "pre_kampf_start"),
                                Text.get(OrtText.OMA, "kampf_ja"),
                                Text.get(OrtText.OMA, "kampf_nein")
                            )
                        )
                    }

                    1 -> {
                        kampfVorschlag = true
                        return kampf(save, verb)
                    }

                    2 -> {
                        activeFight = false
                        return dialogAusgabe(listOf(Text.get(OrtText.OMA, "kampfAbgelehnt")))
                    }

                    else -> return Text.get(OrtText.ERRORS, "oma_kampf")
                }
            }

            1 -> {
                when (aktion) {
                    1 -> {
                        tutorialphase = 2
                        return dialogAusgabe(
                            listOf(
                                Text.get(OrtText.OMA, "kampf1"),
                                Text.get(OrtText.OMA, "action2"),
                            )
                        )
                    }
                }
            }

            2 -> {
                when (aktion) {
                    1 -> {
                        tutorialphase = 3
                        return dialogAusgabe(
                            listOf(
                                Text.get(OrtText.OMA, "kampf2", save.spieler.anfangsChargeCooldown),
                                Text.get(OrtText.OMA, "action3"),
                                Text.get(OrtText.OMA, "frage1")
                            )
                        )
                    }
                }
            }

            3 -> {
                when (aktion) {
                    1 -> {
                        tutorialphase = 4
                        return dialogAusgabe(
                            listOf(
                                Text.get(OrtText.OMA, "kampf3"),
                                Text.get(OrtText.OMA, "action1"),
                                Text.get(OrtText.OMA, "frage2"),
                                Text.get(OrtText.OMA, "frage3"),
                            )
                        )
                    }

                    2 -> return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "erklärung1"),
                            Text.get(OrtText.OMA, "action3"),
                            Text.get(OrtText.OMA, "frage1")
                        )
                    )
                }
            }

            4 -> {
                when (aktion) {
                    1 -> {
                        tutorialphase = 5
                        return dialogAusgabe(
                            listOf(
                                Text.get(OrtText.OMA, "kampf4"),
                                Text.get(OrtText.OMA, "action2"),
                                Text.get(OrtText.OMA, "action4"),
                                Text.get(OrtText.OMA, "frage4"),
                                Text.get(OrtText.OMA, "frage5")
                            )
                        )
                    }

                    2 -> return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "erklärung2"),
                            Text.get(OrtText.OMA, "action1"),
                            Text.get(OrtText.OMA, "frage2"),
                            Text.get(OrtText.OMA, "frage3"),
                        )
                    )

                    3 -> return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "erklärung3", Text.get(OrtText.NPC, "vorbereiten", name)),
                            Text.get(OrtText.OMA, "action1"),
                            Text.get(OrtText.OMA, "frage2"),
                            Text.get(OrtText.OMA, "frage3"),
                        )
                    )
                }
            }

            5 -> {
                when (aktion) {
                    1 -> {
                        tutorialphase = 6
                        return dialogAusgabe(
                            listOf(
                                Text.get(OrtText.OMA, "kampf5"),
                                Text.get(OrtText.OMA, "keine_frage"),
                                Text.get(OrtText.OMA, "frage6"),
                                Text.get(OrtText.OMA, "frage7")
                            )
                        )
                    }

                    2 -> return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "meme_antwort"),
                            Text.get(OrtText.OMA, "action2"),
                            Text.get(OrtText.OMA, "action4"),
                            Text.get(OrtText.OMA, "frage4"),
                            Text.get(OrtText.OMA, "frage5")
                        )
                    )

                    3 -> return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "erklärung4"),
                            Text.get(OrtText.OMA, "action2"),
                            Text.get(OrtText.OMA, "action4"),
                            Text.get(OrtText.OMA, "frage4"),
                            Text.get(OrtText.OMA, "frage5")
                        )
                    )

                    4 -> return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "erklärung5", Text.get(OrtText.NPC, "vorbereiten", name)),
                            Text.get(OrtText.OMA, "action2"),
                            Text.get(OrtText.OMA, "action4"),
                            Text.get(OrtText.OMA, "frage4"),
                            Text.get(OrtText.OMA, "frage5")
                        )
                    )
                }
            }

            6 -> {
                when (aktion) {
                    1 -> {
                        endTutorial = true
                        anfangsDialog = -2
                        activeFight = false
                        return dialogAusgabe(listOf(Text.get(OrtText.OMA, "final_kampf"))) + Text.get(
                            OrtText.NPC,
                            "gespräch_ende"
                        )
                    }

                    2 -> return dialogAusgabe(
                        listOf(
                            Text.get(OrtText.OMA, "erklärung6"),
                            Text.get(OrtText.OMA, "keine_frage"),
                            Text.get(OrtText.OMA, "frage6"),
                            Text.get(OrtText.OMA, "frage7")
                        )
                    )

                    3 -> {
                        save.arthurNPC.scannerHerausfinden()
                        omaErzähltScanner = true
                        return dialogAusgabe(
                            listOf(
                                Text.get(OrtText.OMA, "erklärung7"),
                                Text.get(OrtText.OMA, "keine_frage"),
                                Text.get(OrtText.OMA, "frage6"),
                                Text.get(OrtText.OMA, "frage7")
                            )
                        )
                    }
                }
            }
        }
        return Text.get(OrtText.ERRORS, "falsche_antwort")
    }
}