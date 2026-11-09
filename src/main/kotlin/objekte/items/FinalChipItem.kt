package objekte.items

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class FinalChipItem : ChipItem(
    Text.get(OrtText.NAME, "chip3"),
    Text.get(OrtText.STORYFEEDS, "start_finale"),
    listOf(""),
    "",
    true
) {
    var finalCheck: Boolean = false
        private set

    override fun take(save: Save, raum: Raum): String {
        finalCheck = true
        return Text.get(OrtText.ITEM, "final_start")
    }

    fun finalStart(verb: String, save: Save): String {
        return when (verb) {
            "1" -> {
                finalCheck = false
                "$takeDialog\n${save.finalArthur.unterhaltung(save, 0)}"
            }

            "2" -> {
                finalCheck = false
                Text.get(OrtText.ITEM, "final_abbruch")
            }

            else -> Text.get(OrtText.ERRORS, "falsche_antwort")
        }
    }
}