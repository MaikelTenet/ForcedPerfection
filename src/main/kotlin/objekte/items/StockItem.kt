package objekte.items

import Text
import bauplaene.OrtText
import bauplaene.Raum
import bauplaene.WaffeItem
import study.coco.project_code.Save

class StockItem : WaffeItem(
    Text.get(OrtText.NAME, "stock"),
    Text.get(OrtText.ITEM, "take_stock"),
    listOf("stock", "stick", "stok", "stik", "ast"),
    3
) {
    var hundGetretten = false
        private set

    override fun take(save: Save, raum: Raum): String {
        save.hundNPC.anfangsDialog = -1
        hundGetretten = true
        save.omaNPC.anfangsDialog = -1
        return super.take(save, raum)
    }
}