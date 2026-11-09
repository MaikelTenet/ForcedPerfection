package objekte.items

import Text
import bauplaene.Item
import bauplaene.Raum
import bauplaene.OrtText
import study.coco.project_code.Save

class KabelItem : Item(
    Text.get(OrtText.NAME, "kabel"),
    "",
    listOf("kabel", "wire", "stromkabel", "electric wire", "käbel"),
    Text.get(OrtText.ITEM, "use_kabel")
) {
    override fun take(save: Save, raum: Raum): String {
        if (save.ranken.durchgeschnitten) return super.take(save, raum)
        val obWaffe: Item? = save.ranken.durchschneiden(save)
        if (obWaffe != null) {
            super.take(save, raum)
            return Text.get(OrtText.ITEM, "take_kabel", obWaffe.name)
        }
        return Text.get(OrtText.ERRORS, "stuck_kabel", name, save.pipe.name)
    }

    override fun use(save: Save): String {
        return if (save.welcherRaum() == save.zuhause && !save.computer.computerHeile) {
            save.computer.computerFix(save)
            "${Text.get(OrtText.ITEM, "reapier_computer_simple")} ${Text.get(OrtText.ITEM, "use_kabel_extra")}"
        } else super.use(save)
    }
}