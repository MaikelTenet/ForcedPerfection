package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class JogurtItem : Item(
    Text.get(OrtText.NAME, "jogurt"),
    Text.get(OrtText.ITEM, "take_jogurt"),
    listOf(
        "jogurt",
        "yogurt",
        "abgelaufener yogurt",
        "abgelaufener jogurt",
        "stinkender yogurt",
        "stinkender jogurt",
        "joghurt",
        "yoghurt",
        "abgelaufener yoghurt",
        "abgelaufener joghurt",
        "stinkender yoghut",
        "stinkender joghurt"
    ),
    Text.get(OrtText.ITEM, "use_jogurt"),
    eatable = true
) {
    override fun use(save: Save): String {
        save.spieler.inventar.remove(this)
        save.spieler.changeHealth(
            save,
            5,
            true,
            "Du isst den stinkenden Joghurt und merkst dass er schon sehr lange abgelaufen ist. Du fällst auf der Stelle um und stirbst"
        )
            ?.let { return it }
        return useItem
    }
}