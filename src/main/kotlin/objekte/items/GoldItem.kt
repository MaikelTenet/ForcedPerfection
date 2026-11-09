package objekte.items

import bauplaene.Item
import bauplaene.OrtText
import study.coco.project_code.Save

class GoldItem() : Item(
    Text.get(OrtText.NAME, "goldklumpen"),
    "",
    listOf("gold", "geld", "goldklumpen"),
    Text.get(OrtText.ITEM, "use_gold"),
) {
    override fun use(save: Save): String {
        save.finalArthur.scamSwitch()
        return super.use(save)
    }
}