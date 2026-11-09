package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class TageBuchItem : Item(
    Text.get(OrtText.NAME, "tagebuch"),
    Text.get(OrtText.ITEM, "take_tagebuch"),
    listOf("buch", "tagebuch", "book", "diary"),
    readable = true
) {
    private var seitenAnzahl: Int = 0
    var anfangenLesen: Boolean = false
        private set
    private var geöffnet: Boolean = false

    val passwort: String = "6742"

    override fun use(save: Save): String {
        anfangenLesen = true
        return lesen("1", true, save)
    }

    private var passwortAbfrage: Boolean = false

    fun lesen(verbEingabe: String, firstOpen: Boolean, save: Save): String {
        var verb: String = verbEingabe
        val ausgabe: MutableList<String> = mutableListOf()
        if (firstOpen) ausgabe.add(Text.get(OrtText.ITEM, "tagebuch_first_open"))
        if (!geöffnet) {
            if (!passwortAbfrage) {
                passwortAbfrage = true
                ausgabe.add(Text.get(OrtText.ITEM, "tagebuch_abfrage"))
                return ausgabe.joinToString("\n")
            } else {
                passwortAbfrage = false
                if (verb == passwort) {
                    geöffnet = true
                    ausgabe.add(Text.get(OrtText.ITEM, "tagebuch_passwort_lösen"))
                    verb = "1"
                } else {
                    anfangenLesen = false
                    return Text.get(OrtText.ERRORS, "tagebuch_wrong_password")
                }
            }
        }

        when (verb) {
            "1" -> {
                seitenAnzahl++
                ausgabe.add(
                    when (seitenAnzahl) {
                        1 -> Text.get(OrtText.STORYFEEDS, "tagebuch_1")
                        2 -> Text.get(OrtText.STORYFEEDS, "tagebuch_2")
                        3 -> Text.get(OrtText.STORYFEEDS, "tagebuch_3", save.sandra.realName, save.markus.name)
                        4 -> Text.get(OrtText.STORYFEEDS, "tagebuch_4")
                        5 -> Text.get(OrtText.STORYFEEDS, "tagebuch_5")
                        6 -> Text.get(OrtText.STORYFEEDS, "tagebuch_6")
                        7 -> Text.get(OrtText.STORYFEEDS, "tagebuch_7")
                        8 -> Text.get(OrtText.STORYFEEDS, "tagebuch_8", save.sandra.realName)
                        9 -> {
                            save.finalArthur.erinnerungSwitch()
                            Text.get(OrtText.STORYFEEDS, "tagebuch_9")
                        }

                        10 -> Text.get(OrtText.STORYFEEDS, "tagebuch_10")
                        11 -> {
                            save.finalArthur.schuldGefühlSwitch()
                            Text.get(OrtText.STORYFEEDS, "tagebuch_11", save.sandra.realName, save.markus.name)
                        }

                        12 -> Text.get(OrtText.STORYFEEDS, "tagebuch_12")
                        13 -> Text.get(OrtText.STORYFEEDS, "tagebuch_13")
                        else -> {
                            anfangenLesen = false
                            Text.get(OrtText.STORYFEEDS, "tagebuch_ende")
                        }
                    }
                )
                if (anfangenLesen) ausgabe.add(Text.get(OrtText.ITEM, "tagebuch_zwischenfrage"))
                return ausgabe.joinToString("\n")
            }

            "2" -> {
                anfangenLesen = false
                return Text.get(OrtText.ITEM, "tagebuch_aufhören")
            }

            else -> return Text.get(OrtText.ERRORS, "falsche_antwort")
        }
    }
}