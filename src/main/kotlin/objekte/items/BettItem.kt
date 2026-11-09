package objekte.items

import bauplaene.OrtText
import bauplaene.StuckItem
import study.coco.project_code.Save

class BettItem : StuckItem(
    Text.get(OrtText.NAME, "bett"),
    Text.get(OrtText.ITEM, "take_bett"),
    listOf("bett", "bed", "beet", "bet")
) {
    private var erstesMalGeschlafen = false
    override fun use(save: Save): String {
        if (!erstesMalGeschlafen) {
            erstesMalGeschlafen = true
            save.zuhause.herumliegend.add(save.tageBuch)
            return Text.get(OrtText.STORYFEEDS, "tagebuch_finden", save.tageBuch.name.uppercase())
        }
        save.spieler.changeHealth(save, 1, false)
        return Text.get(OrtText.ROOM, "sleep")
    }
}