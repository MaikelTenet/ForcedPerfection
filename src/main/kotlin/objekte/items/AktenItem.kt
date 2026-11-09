package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class AktenItem : Item(
    Text.get(OrtText.NAME, "akte"),
    Text.get(OrtText.ITEM, "akte_take"),
    listOf("akte", "info", "verstaubte akte", "staubige akte"),
    "${Text.get(OrtText.ITEM, "akte_use")}${Text.get(OrtText.ITEM, "akten_optionen")}",
    readable = true
) {
    var akteOpen: Boolean = false
        private set

    override fun use(save: Save): String {
        akteOpen = true
        return super.use(save)
    }

    fun welcheAkte(verb: String): String {
        return when (verb) {
            "1" -> "${Text.get(OrtText.ITEM, "akte_markus")}${Text.get(OrtText.ITEM, "akten_optionen")}"
            "2" -> "${Text.get(OrtText.ITEM, "akte_sandra")}${Text.get(OrtText.ITEM, "akten_optionen")}"
            "3" -> "${Text.get(OrtText.ITEM, "akte_du")}${Text.get(OrtText.ITEM, "akten_optionen")}"
            "4" -> {
                akteOpen = false
                Text.get(OrtText.ITEM, "akte_schließe")
            }

            else -> Text.get(OrtText.ERRORS, "falsche_antwort")
        }
    }
}