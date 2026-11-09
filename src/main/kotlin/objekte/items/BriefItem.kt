package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class BriefItem : Item(
    Text.get(OrtText.NAME, "brief"),
    "",
    listOf("brief", "letter", "dankes brief", "nachricht"),
    Text.get(OrtText.ITEM, "use_brief"),
    readable = true
) {
    var überlegenZuLesen: Boolean = false
        private set

    override fun use(save: Save): String {
        überlegenZuLesen = true
        return super.use(save)
    }

    fun lesenCheck(antwort: String, save: Save): String {
        return when (antwort) {
            "ja", "yes", "true" -> {
                überlegenZuLesen = false
                save.markus.briefGelesen(save)
                Text.get(OrtText.STORYFEEDS, "brief_lesen1")
            }

            "nein", "no", "stop", "false" -> {
                überlegenZuLesen = false
                Text.get(OrtText.ITEM, "nicht_brief_lesen")
            }

            else -> Text.get(OrtText.ERRORS, "none_lesen")
        }
    }

    /*
    var lesenWert: Int = 0
        private set
    fun lesen(save: Save): String {
        lesenWert++
        return when (lesenWert) {
            1 -> Text.get(OrtText.STORYFEEDS, "brief_lesen1")
            else -> {
                save.markus.briefGelesen()
                lesenWert = 0
                Text.get(OrtText.STORYFEEDS, "brief_lesen")
            }
        }
    }*/
}