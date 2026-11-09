package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class MedikamenteItem(name: String, takeDialog: String, val heilung: Int = 20) : Item(
    name,
    takeDialog,
    listOf("medizin", "heilung", "medikamente", "healing", "heal", "leichte medikamente", "starke medikamente"),
    Text.get(OrtText.ITEM, "use_medikamente", heilung),
    eatable = true
) {
    override fun use(save: Save): String {
        save.spieler.changeHealth(save, heilung, false)
        save.spieler.inventar.remove(this)
        return useItem
    }
}