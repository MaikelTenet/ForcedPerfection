package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class KnopfItem : Item(
    Text.get(OrtText.NAME, "knopf"),
    Text.get(OrtText.ITEM, "take_knopf"),
    listOf("knopf", "schalter", "button"),
    Text.get(OrtText.ITEM, "use_knopf_1"),
) {
    private var verwendet: Boolean = false

    override fun use(save: Save): String {
        if (!verwendet) {
            openSafe(save)
            return if (save.welcherRaum() == save.farm) "$useItem ${Text.get(OrtText.ITEM, "use_knopf_2")}"
            else "$useItem ${Text.get(OrtText.ITEM, "use_knopf_3")}"
        }
        return "$useItem ${Text.get(OrtText.ITEM, "use_knopf_4")}"
    }

    fun openSafe(save: Save) {
        verwendet = true
        save.kaputterChip.openSafe()
    }
}