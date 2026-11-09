package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class LaborRaum(save: Save) : Raum(
    2,
    1,
    Text.get(OrtText.ROOM, "labor_opening"),
    mutableListOf(),
    mutableListOf(save.finalChip, save.strongheal, save.akte, save.server)
) {
    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "labor_l1"))

        if (herumliegend.contains(save.akte)) {
            if (herumliegend.contains(save.strongheal)) umschauText.add(
                Text.get(
                    OrtText.ROOM,
                    "labor_akte_medikamente"
                )
            )
            else umschauText.add(Text.get(OrtText.ROOM, "labor_akte"))
        } else {
            if (herumliegend.contains(save.strongheal)) umschauText.add(Text.get(OrtText.ROOM, "labor_medikamente"))
        }

        umschauText.add(Text.get(OrtText.ROOM, "labor_chip"))

        return tippExtras(listOf(save.finalChip, save.strongheal, save.akte, save.server), umschauText, save)
    }
}