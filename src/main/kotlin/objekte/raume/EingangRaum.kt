package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class EingangRaum(save: Save) : Raum(
    2,
    0,
    Text.get(OrtText.ROOM, "eingang_opening"),
    mutableListOf(save.securityguard),
    mutableListOf()
) {
    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "eingang_l1"))

        if (save.securityguard.lebendig) {
            if (save.securityguard.guardBenebelt) umschauText.add(Text.get(OrtText.ROOM, "eingang_benebelt"))
            else umschauText.add(Text.get(OrtText.ROOM, "eingang_bewacht"))
        } else umschauText.add(Text.get(OrtText.ROOM, "eingang_frei"))

        return tippExtras(listOf(), umschauText, save)
    }
}