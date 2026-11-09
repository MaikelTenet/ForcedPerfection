package bauplaene

import study.coco.project_code.Save

open class StuckItem(
    name: String,
    takeDialog: String,
    ansprechbareNamen: List<String>,
    readable: Boolean = false,
    eatable: Boolean = false,
) : Item(name, takeDialog, ansprechbareNamen, Text.get(OrtText.ERRORS, "still_Item"), readable, eatable) {
    override fun take(save: Save, raum: Raum): String {
        if (takeDialog == "") return Text.get(OrtText.ACTIONS, "default_take", name)
        return takeDialog
    }
}