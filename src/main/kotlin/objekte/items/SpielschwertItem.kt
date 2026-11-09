package objekte.items

import bauplaene.OrtText
import bauplaene.Raum
import bauplaene.WaffeItem
import study.coco.project_code.Save

class SpielschwertItem : WaffeItem(
    Text.get(OrtText.NAME, "shwert"),
    Text.get(OrtText.ITEM, "take_schwert"),
    listOf("schwert", "sword", "spielschwert", "playsword"),
    7
) {
    override fun take(save: Save, raum: Raum): String {
        if (save.lisaNPC.befreundetMitKilian) {
            save.kilianNPC.spielSchwertAbgeben(save)
            return Text.get(OrtText.KILIAN, "schwert_direkt_nehmen")
        }
        return takeDialog
    }
}