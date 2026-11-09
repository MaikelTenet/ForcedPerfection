package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

open class SubstanzItem(name: String, takeDialog: String) : Item(
    name,
    takeDialog,
    listOf(
        "****",
        "substanz",
        "gewächs",
        "gras",
        "grass",
        "päckchen",
        "*",
        "**",
        "***",
        "*****",
        "pflanze",
        "plant",
        "green",
        "gemüse",
        "obst",
        "fruit",
        "pflanzen"
    ),
    Text.get(OrtText.STORYFEEDS, "substanz_1"),
) {

    var benebeltWert: Int = 0
        private set

    companion object {
        val alleSubstanzen = mutableListOf<SubstanzItem>()
    }

    init {
        alleSubstanzen.add(this)
    }

    override fun use(save: Save): String {
        return statusqueEnding(save)
    }

    private fun statusqueEnding(save: Save): String {
        benebeltWert++
        return when (benebeltWert) {
            1 -> Text.get(OrtText.STORYFEEDS, "substanz_1")
            2 -> Text.get(OrtText.STORYFEEDS, "substanz_2")
            3 -> Text.get(OrtText.STORYFEEDS, "substanz_3")
            4 -> Text.get(OrtText.STORYFEEDS, "substanz_4")
            5 -> Text.get(OrtText.STORYFEEDS, "substanz_5")
            6 -> Text.get(OrtText.STORYFEEDS, "substanz_6")
            7 -> {
                save.nochLaufen = false
                Text.get(OrtText.STORYFEEDS, "substanz_7") + Text.get(OrtText.STORYFEEDS, "tod")
            }

            else -> {
                benebeltWert = 0
                "Du solltest diesen Text eigentlich nicht sehen können Fehler: 0001"
            }
        }
    }

    override fun drop(save: Save): String {
        if (save.welcherRaum() == save.farm) {
            return Text.get(OrtText.ERRORS, "drop_substanz")
        }
        return super.drop(save)
    }
}