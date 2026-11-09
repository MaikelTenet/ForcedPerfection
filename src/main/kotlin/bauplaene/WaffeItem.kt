package bauplaene

import study.coco.project_code.Save

open class WaffeItem(
    name: String,
    takeDialog: String,
    ansprechbareNamen: List<String>,
    val schaden: Int,
    useItem: String = ""
) : Item(name, takeDialog, ansprechbareNamen, useItem) {

    override fun use(save: Save): String {
        save.spieler.equipWaffe(this)
        if (useItem == "") return Text.get(OrtText.ACTIONS, "use_waffe", name, schaden)
        return useItem
    }

    override fun drop(save: Save): String {
        if (save.spieler.inventar.contains(this)) return super.drop(save)
        if (save.spieler.waffeAusgerüstet == this) {
            gedroppt = true
            save.spieler.deleteWeapon()
            save.welcherRaum().herumliegend.add(this)
            return Text.get(OrtText.ACTIONS, "drop_item", name)
        }
        return "Du solltest diesen Dialog nicht sehen können: Fehler 003"
    }
}