package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

open class ChipItem(
    name: String,
    takeDialog: String,
    extraAnsprechbare: List<String>,
    useItem: String,
    erreichbarOriginal: Boolean = false
) : Item(
    name,
    takeDialog,
    listOf("chip", "computer", "computer-chip", "computer chip", "chip computer") + extraAnsprechbare,
    useItem
) {


    var erreichbar = erreichbarOriginal
        private set

    override fun take(save: Save, raum: Raum): String {
        return if (erreichbar) super.take(save, raum)
        else Text.get(OrtText.ERRORS, "take_chip")
    }

    internal fun openSafe() {
        erreichbar = true
    }

    override fun drop(save: Save): String {
        return if (save.welcherRaum() == save.labor) Text.get(OrtText.ERRORS, "drop_chip")
        else super.drop(save)
    }
}