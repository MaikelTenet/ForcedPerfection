package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import bauplaene.StuckItem
import study.coco.project_code.Save

class RankenItem(save: Save) : StuckItem(
    Text.get(OrtText.NAME, "ranken"),
    Text.get(OrtText.ITEM, "take_ranken"),
    listOf("vines", "ranken", "ranke", "gewächs")
) {
    var durchgeschnitten: Boolean = false
        private set

    private val möglicheZerstörWaffen: List<Item> = listOf(save.spielSchwert, save.axt)

    fun durchschneiden(save: Save): Item? {
        for (w in möglicheZerstörWaffen) {
            if (save.spieler.inventar.contains(w) || save.spieler.waffeAusgerüstet == w) {
                durchgeschnitten = true
                save.baustelle.herumliegend.remove(save.ranken)
                if (save.sandra.anfangsDialog == 0) save.sandra.anfangsDialog = -1
                return w
            }
        }
        return null
    }
}