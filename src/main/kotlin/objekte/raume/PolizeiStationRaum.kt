package objekte.raume

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class PolizeiStationRaum(save: Save) : Raum(
    -1,
    0,
    Text.get(OrtText.ROOM, "polizei_opening"),
    mutableListOf(save.polizist),
    mutableListOf(save.substanz, save.box)
) {
    override fun tippReader(save: Save): String {

        val umschauText = mutableListOf<String>()

        umschauText.add(Text.get(OrtText.ROOM, "polizei_l1"))

        if (save.polizist.lebendig) {
            if (save.spieler.verkleidet) umschauText.add(Text.get(OrtText.ROOM, "polizist_verkleidet"))
            else umschauText.add(Text.get(OrtText.ROOM, "polizisz_da"))
        } else umschauText.add(Text.get(OrtText.ROOM, "polizist_weg"))

        if (herumliegend.contains(save.substanz)) umschauText.add(Text.get(OrtText.ROOM, "polizist_weed_da"))
        else umschauText.add(Text.get(OrtText.ROOM, "polizist_weed_weg"))

        return tippExtras(listOf(save.substanz, save.box), umschauText, save)
    }
}