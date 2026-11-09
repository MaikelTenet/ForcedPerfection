package objekte.items

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class SubstanzWachsend : SubstanzItem(Text.get(OrtText.NAME, "substanz2"), Text.get(OrtText.ITEM, "take_substanz2")) {

    var ausgewachsen: Boolean = false
        private set

    override fun take(save: Save, raum: Raum): String {
        if (ausgewachsen) return super.take(save, raum)
        return if (save.markus.lebendig) Text.get(OrtText.ERRORS, "take_substanz_farmer")
        else Text.get(OrtText.ERRORS, "take_substanz_wachsen")
    }

    fun ausWachsen() {
        ausgewachsen = true
    }
}