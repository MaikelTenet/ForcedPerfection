package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import bauplaene.Raum
import study.coco.project_code.Save

class HundItem : Item(
    Text.get(OrtText.NAME, "hund"),
    Text.get(OrtText.ITEM, "take_hund"),
    listOf("hund", "dog"),
    Text.get(OrtText.ITEM, "use_hund"),
) {
    override fun take(save: Save, raum: Raum): String {
        if (save.shopkeaper.verkleidungBekommen) return Text.get(OrtText.ERRORS, "take_hund_again")
        if (save.hundNPC.befreundet) return realtake(save)
        save.spieler.changeHealth(
            save,
            save.hundNPC.schaden,
            true,
            "Dem Hund hat es garnicht gefallen dass du ihm nehmen wolltest. Du bist gestorben!"
        )
            ?.let { return it }
        return Text.get(OrtText.ERRORS, "take_hund")
    }

    override fun use(save: Save): String {
        save.welcherRaum().herumliegend.add(save.häufchen)
        return super.use(save)
    }

    override fun drop(save: Save): String {
        save.welcherRaum().interaktionen.add(save.hundNPC)
        return super.drop(save)
    }

    private fun realtake(save: Save): String {
        val currenRoom = save.welcherRaum()
        currenRoom.interaktionen.remove(save.hundNPC)
        return super.take(save, currenRoom)
    }
}