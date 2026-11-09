package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import bauplaene.Raum
import bauplaene.WaffeItem
import study.coco.project_code.Save

class PipeItem : WaffeItem(
    Text.get(OrtText.NAME, "pipe"),
    "",
    listOf("rohr", "pipe", "metal", "metall", "metal rohr", "metal-rohr", "metal pipe"),
    10
) {
    override fun take(save: Save, raum: Raum): String {
        if (save.ranken.durchgeschnitten) return super.take(save, raum)
        val obWaffe: Item? = save.ranken.durchschneiden(save)
        if (obWaffe != null) {
            super.take(save, raum)
            return Text.get(OrtText.ITEM, "take_pipe", obWaffe.name)
        }
        return Text.get(OrtText.ERRORS, "take_pipe")
    }
}