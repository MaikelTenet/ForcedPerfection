package objekte.items

import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class SubstanzKonves : SubstanzItem(Text.get(OrtText.NAME, "substanz1"), Text.get(OrtText.ITEM, "take_substanz1")) {
    override fun take(save: Save, raum: Raum): String {
        return if (save.spieler.verkleidet || !save.polizist.lebendig) super.take(save, raum)
        else Text.get(OrtText.ERRORS, "take_substanz_polize")
    }
}