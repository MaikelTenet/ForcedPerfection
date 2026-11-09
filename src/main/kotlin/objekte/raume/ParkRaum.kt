package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class ParkRaum(save: Save) : Raum(
    0,
    0,
    Text.get(OrtText.ROOM, "park_opening"),
    mutableListOf(save.hundNPC, save.arthurNPC, save.omaNPC),
    mutableListOf(save.stock, save.hund)
) {
    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "park_l1"))

        if (save.omaNPC.lebendig) {
            if (herumliegend.contains(save.hund)) {
                umschauText.add(Text.get(OrtText.ROOM, "park_l2"))
                if (herumliegend.contains(save.stock)) {
                    umschauText.add(Text.get(OrtText.ROOM, "park_l3"))
                } else {
                    if (save.hundNPC.befreundet) umschauText.add(Text.get(OrtText.ROOM, "park_hund_freund"))
                    else umschauText.add(Text.get(OrtText.ROOM, "park_hund_feind"))
                }
            } else {
                if (herumliegend.contains(save.stock)) umschauText.add(Text.get(OrtText.ROOM, "park_oma_schaut_stock"))
                else umschauText.add(Text.get(OrtText.ROOM, "park_einsam_oma"))
            }
        } else {
            if (herumliegend.contains(save.hund)) {
                if (herumliegend.contains(save.stock)) umschauText.add(Text.get(OrtText.ROOM, "park_l5"))
                else {
                    umschauText.add(Text.get(OrtText.ROOM, "park_hund_existenz"))
                    if (save.hundNPC.befreundet) umschauText.add(Text.get(OrtText.ROOM, "park_hund_freund"))
                    else umschauText.add(Text.get(OrtText.ROOM, "park_hund_feind"))
                }
            } else {
                if (herumliegend.contains(save.stock)) umschauText.add(Text.get(OrtText.ROOM, "park_nur_stock"))
                else umschauText.add(Text.get(OrtText.ROOM, "park_nichts"))
            }
        }


        if (save.arthurNPC.firstdialogMarkus) umschauText.add(Text.get(OrtText.ROOM, "park_l7"))
        else umschauText.add(Text.get(OrtText.ROOM, "park_l8"))

        return tippExtras(listOf(save.stock, save.hund), umschauText, save)

    }
}