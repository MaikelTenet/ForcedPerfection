package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class HauptstraßeRaum(save: Save) : Raum(
    1,
    0,
    Text.get(OrtText.ROOM, "hauptstrasse_opening"),
    mutableListOf(save.shopkeaper, save.magier),
    mutableListOf()
) {
    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "strasse_l1"))

        if (save.shopkeaper.lebendig) {
            if (save.shopkeaper.verkleidungBekommen) umschauText.add(Text.get(OrtText.ROOM, "strasse_hund"))
            else umschauText.add(Text.get(OrtText.ROOM, "strasse_allein"))
        } else {
            if (save.shopkeaper.verkleidungBekommen) umschauText.add(Text.get(OrtText.ROOM, "strasse_tod_mit_hund"))
            else umschauText.add(Text.get(OrtText.ROOM, "strasse_tod"))
        }
        if (save.magier.lebendig) umschauText.add(Text.get(OrtText.ROOM, "strasse_magier"))

        return if (save.shopkeaper.verkleidungBekommen) tippExtras(listOf(save.hund), umschauText, save)
        else tippExtras(listOf(), umschauText, save)
    }
}