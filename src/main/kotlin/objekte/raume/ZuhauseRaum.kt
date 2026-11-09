package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class ZuhauseRaum(save: Save) : Raum(
    1,
    -1,
    Text.get(OrtText.ROOM, "zuhause_opening"),
    mutableListOf(),
    mutableListOf(save.jogurt, save.gabel, save.bett, save.computer),
) {
    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "haus_l1"))

        umschauText.add(
            if (save.computer.computerHeile) Text.get(OrtText.ROOM, "haus_computer_heile")
            else Text.get(OrtText.ROOM, "haus_computer_kaputt")
        )

        if (herumliegend.contains(save.jogurt)) {
            umschauText.add(Text.get(OrtText.ROOM, "haus_l2"))
            if (herumliegend.contains(save.gabel)) umschauText.add(Text.get(OrtText.ROOM, "haus_l3"))
        } else {
            if (herumliegend.contains(save.gabel)) umschauText.add(Text.get(OrtText.ROOM, "haus_l5"))
            else if (!herumliegend.contains(save.tageBuch)) umschauText.add(Text.get(OrtText.ROOM, "haus_l4"))
        }
        if (herumliegend.contains(save.tageBuch)) umschauText.add(Text.get(OrtText.ROOM, "haus_l7"))

        return tippExtras(listOf(save.jogurt, save.gabel, save.tageBuch, save.bett, save.computer), umschauText, save)
    }
}