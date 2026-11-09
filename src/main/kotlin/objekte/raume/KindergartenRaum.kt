package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class KindergartenRaum(save: Save) : Raum(
    0,
    1,
    Text.get(OrtText.ROOM, "kinder_open"),
    mutableListOf(save.kilianNPC, save.lisaNPC),
    mutableListOf(save.spielSchwert),
    listOf(Text.get(OrtText.HELP, "kindergarten_tipp1")),
) {
    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "kinder_l1"))

        if (save.lisaNPC.befreundetMitKilian && save.kilianNPC.lebendig && save.lisaNPC.lebendig) umschauText.add(
            Text.get(
                OrtText.ROOM,
                "kinder_l2"
            )
        )
        else {
            if (save.kilianNPC.lebendig) {
                umschauText.add(Text.get(OrtText.ROOM, "kinder_l3"))
                if (save.lisaNPC.lebendig) umschauText.add(Text.get(OrtText.ROOM, "kinder_l4"))
                else umschauText.add(Text.get(OrtText.ROOM, "kinder_l5"))
            } else {
                umschauText.add(Text.get(OrtText.ROOM, "kinder_l6"))
                if (save.lisaNPC.lebendig) umschauText.add(Text.get(OrtText.ROOM, "kinder_l7"))
                else umschauText.add(Text.get(OrtText.ROOM, "kinder_l8"))
            }
        }

        return tippExtras(listOf(save.spielSchwert), umschauText, save)
    }
}