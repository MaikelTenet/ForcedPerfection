package bauplaene

import study.coco.project_code.Save

open class Item(
    val name: String,
    internal val takeDialog: String,
    val ansprechbareNamen: List<String>,
    internal val useItem: String = "",
    val readable: Boolean = false,
    val eatable: Boolean = false,
) {

    open fun wichTake(save: Save, raum: Raum): String {
        if (gedroppt) {
            save.spieler.inventar.add(this)
            raum.herumliegend.remove(this)
            return Text.get(OrtText.ACTIONS, "boden_nehmen", name)
        }
        return take(save, raum)
    }

    open fun take(save: Save, raum: Raum): String {
        save.spieler.inventar.add(this)
        raum.herumliegend.remove(this)
        if (takeDialog == "") return Text.get(OrtText.ACTIONS, "default_take", name) + save.firstItemCheck()
        return takeDialog + save.firstItemCheck()
    }

    open fun use(save: Save): String {
        return useItem
    }

    internal var gedroppt: Boolean = false
    open fun drop(save: Save): String {
        gedroppt = true
        save.spieler.inventar.remove(this)
        save.welcherRaum().herumliegend.add(this)
        return Text.get(OrtText.ACTIONS, "drop_item", name)
    }
}