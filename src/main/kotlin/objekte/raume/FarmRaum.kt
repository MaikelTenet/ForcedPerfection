package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class FarmRaum(save: Save) : Raum(
    0,
    2,
    Text.get(OrtText.ROOM, "farm_open"),
    mutableListOf(save.markus),
    mutableListOf(save.wachsendePflanze, save.kaputterChip, save.glascontainerItem)
) {
    override fun tippReader(save: Save): String {
        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "farm_l1"))

        if (herumliegend.contains(save.wachsendePflanze)) {
            if (save.wachsendePflanze.ausgewachsen) umschauText.add(Text.get(OrtText.ROOM, "farm_l2"))
            else umschauText.add(Text.get(OrtText.ROOM, "farm_l3"))
        } else umschauText.add(Text.get(OrtText.ROOM, "farm_l4"))
        if (herumliegend.contains(save.kaputterChip)) {
            if (save.kaputterChip.erreichbar) umschauText.add(Text.get(OrtText.ROOM, "farm_l5"))
            else umschauText.add(Text.get(OrtText.ROOM, "farm_l6"))
        } else umschauText.add(Text.get(OrtText.ROOM, "farm_l7"))
        if (save.markus.lebendig) {
            if (save.markus.erIstHigh) umschauText.add(Text.get(OrtText.ROOM, "farm_l8"))
            else umschauText.add(Text.get(OrtText.ROOM, "farm_l9"))
        }

        return tippExtras(listOf(save.wachsendePflanze, save.kaputterChip, save.glascontainerItem), umschauText, save)
    }
}